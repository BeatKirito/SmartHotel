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
import com.smtl.mweb.domain.CusCall;
import com.smtl.mweb.service.CusCallManage;

/**
 * Servlet implementation class CusCallAction
 */
public class CusCallAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext ctx =
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private CusCallManage cusCallManage = ctx.getBean("cusCallManage",CusCallManage.class);
	
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CusCallAction() {
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
		StateResult stateResult = new BaseResult();
		CusCall cusCall = new CusCall();
		
		try {
			
			//第一步：获取参数，参数验证，并包装
			if(!validateParam(request,cusCall)) {
				stateResult.setSuccess(false);
				stateResult.setMessage("错误的参数");
				out.write(gson.toJson(stateResult));
				return;
			}
			
			//第二步：创建一个呼叫
			if(!cusCallManage.createACall(cusCall)) {
				stateResult.setSuccess(false);
				stateResult.setMessage("无法呼叫");
				out.write(gson.toJson(stateResult));
				return;
			}
			
			//第三步：返回成功呼叫信息
			stateResult.setSuccess(true);
			out.write(gson.toJson(stateResult));
			
		} catch (Exception e) {
			
			e.printStackTrace();
			stateResult.setSuccess(false);
			stateResult.setMessage("服务器错误");
			out.write(gson.toJson(stateResult));
			return;
		}
	}
	
	//验证参数并包装参数对象
	private boolean validateParam(HttpServletRequest request, CusCall cusCall) {
		
		//第一步：获取所有的参数字符串
		String sUserTypeID = request.getParameter("serviceID");
		RoomWT room = (RoomWT)request.getSession().getAttribute("room");
		
		//第二步：参数验证
		if(sUserTypeID == null || Integer.parseInt(sUserTypeID) < 0 || Integer.parseInt(sUserTypeID)  > 5  )
			return false;
		
		//第三步：包装处理
		cusCall.setRoomID(room.getRoomID());
		cusCall.setSuserTypeID(Integer.parseInt(sUserTypeID)); 
		
		return true;
	}

}
