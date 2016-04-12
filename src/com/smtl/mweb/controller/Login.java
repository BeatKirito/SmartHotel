package com.smtl.mweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.smtl.app.result.BaseResult;
import com.smtl.app.result.StateResult;
import com.smtl.extend.MD5;
import com.smtl.fweb.damain.RoomWT;
import com.smtl.fweb.damain.SUserWT;
import com.smtl.fweb.service.RoomManage;
import com.smtl.fweb.service.SUserManage;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext ctx =
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private RoomManage roomManage = ctx.getBean("roomManage",RoomManage.class);
	
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		RoomWT room = new RoomWT();
		String tips = "";
		String preURL = request.getHeader("Referer");
		System.out.println("preURL :" + preURL);
		//StateResult stateResult = new BaseResult();
		
		try {
			
			//第一步：获取参数，参数验证，并包装
			if(!validateParam(request,room)) {
				/*stateResult.setSuccess(false);
				stateResult.setMessage("错误的参数");
				out.write(gson.toJson(stateResult));*/
				tips = "错误的参数";
				request.setAttribute("tips", tips);
				request.setAttribute("redirectUrl", preURL);
				request.getRequestDispatcher("Error.jsp").forward(request, response);
				return;
			}
			
			//第二步：调用用户验证接口，验证用户信息
			if(!roomManage.validatePassword(room)) {
				/*stateResult.setSuccess(false);
				stateResult.setMessage("密码错误");
				out.write(gson.toJson(stateResult));*/
				tips = "密码错误";
				request.setAttribute("tips", tips);
				request.setAttribute("redirectUrl", preURL);
				request.getRequestDispatcher("Error.jsp").forward(request, response);
				return;
			}
			
			//第三步：成功验证，保存session
			HttpSession session = request.getSession();
			session.setAttribute("room", room);
			/*stateResult.setSuccess(true);
			out.write(gson.toJson(stateResult));*/
			response.sendRedirect("index.html");
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			/*stateResult.setSuccess(false);
			stateResult.setMessage("服务器错误");
			out.write(gson.toJson(stateResult));*/
			tips = "服务器错误";
			request.setAttribute("tips", tips);
			request.setAttribute("redirectUrl", preURL);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
	}
	
	private boolean validateParam(HttpServletRequest request, RoomWT room) {
		
		//第一步：获取所有的参数字符串
		String roomNum = request.getParameter("roomNum");
		String password = request.getParameter("password");
		
		//第二步：参数验证
		if(roomNum == null || roomNum.length() < 2 || roomNum.length()  > 20 )
			return false;
		if(password == null || password.length() < 2 || password.length()  > 20 )
			return false;
		
		//第三步：包装处理
		room.setRoomNum(roomNum);
		room.setPassword(MD5.GetMD5Code(password));
		
		return true;
	}

}
