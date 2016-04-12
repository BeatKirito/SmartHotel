package com.smtl.fweb.controller;

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
import com.smtl.fweb.damain.SUserWT;
import com.smtl.fweb.service.SUserManage;

/**
 * Servlet implementation class SUserLogin
 */
public class SUserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext ctx =
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private SUserManage sUserManage = ctx.getBean("sUserManage",SUserManage.class);
	
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SUserLogin() {
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
		
		StateResult stateResult = new BaseResult();
		PrintWriter out = response.getWriter();
		SUserWT sUser = new SUserWT();
		
		try {
			
			//第一步：获取参数，参数验证，并包装
			if(!validateParam(request,sUser)) {
				stateResult.setSuccess(false);
				stateResult.setMessage("参数错误");
				out.write(gson.toJson(stateResult));
				return;
			}
			
			//第二步：调用用户验证接口，验证用户信息
			if(!sUserManage.userValidate(sUser)) {
				stateResult.setSuccess(false);
				stateResult.setMessage("用户名或密码错误");
				out.write(gson.toJson(stateResult));
				return;
			}
			
			//第三步：成功验证，保存session
			HttpSession session = request.getSession();
			session.setAttribute("sUser", sUser);
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
	
	private boolean validateParam(HttpServletRequest request, SUserWT sUser) {
		
		//第一步：获取所有的参数字符串
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		//第二步：参数验证
		if(userName == null || userName.length() < 2 || userName.length()  > 20 )
			return false;
		if(password == null || password.length() < 2 || password.length()  > 20 )
			return false;
		
		//第三步：包装处理
		sUser.setAccount(userName);
		sUser.setPassword(MD5.GetMD5Code(password));
		
		return true;
	}	

}
