package com.smtl.fweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.smtl.app.result.BaseResult;
import com.smtl.app.result.StateResult;
import com.smtl.extend.MD5;
import com.smtl.fweb.damain.SUser;
import com.smtl.fweb.service.SUserManage;

/**
 * Servlet implementation class SUserRegister
 */
public class SUserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext ctx =
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private SUserManage sUserManage = ctx.getBean("sUserManage",SUserManage.class);
    
	private Gson gson = new Gson();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SUserRegister() {
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
		SUser sUser = new SUser();
		StateResult stateResult = new BaseResult();
		
		try {
			
			//第一步：获取参数，参数验证，并包装
			if(!validateParam(request,sUser)) {
				stateResult.setSuccess(false);
				stateResult.setMessage("错误的参数");
				out.write(gson.toJson(stateResult));
				return;
			}
			
			//第二步：调用用户创建的业务接口，实现用户创建
			if(!sUserManage.CreateSUser(sUser)) {
				stateResult.setSuccess(false);
				stateResult.setMessage("用户注册失败");
				out.write(gson.toJson(stateResult));
				return;
			}
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
	
	private boolean validateParam(HttpServletRequest request, SUser sUser) {
		
		//第一步：获取所有的参数字符串
		String userName = request.getParameter("userName");
		String name = request.getParameter("name");
		String userType = request.getParameter("userType");
		String password = request.getParameter("password");
		
		//第二步：参数验证
		if(userName == null || userName.length() < 2 || userName.length()  > 20 )
			return false;
		if(name == null || name.length() < 2 || name.length()  > 20 )
			return false;
		if(userType == null || Integer.parseInt(userType) < 1 || Integer.parseInt(userType) > 5  )
			return false;
		if(password == null || password.length() < 2 || password.length()  > 20 )
			return false;
		
		//第三步：包装处理
		sUser.setAccount(userName);
		sUser.setUserName(name);
		sUser.setUserTypeID(Integer.parseInt(userType));
		sUser.setPassword(MD5.GetMD5Code(password));
		sUser.setIsManager(false);
		
		return true;
	}

}
