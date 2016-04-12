package com.smtl.app.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import antlr.ASdebug.ASDebugStream;

import com.google.gson.Gson;
import com.smtl.app.param.UserLoginParam;
import com.smtl.app.result.BaseResult;
import com.smtl.app.result.SUserResult;
import com.smtl.extend.MD5;
import com.smtl.fweb.damain.SUserWT;
import com.smtl.fweb.service.SUserManage;

/**
 * Servlet implementation class AppUserLogin
 */
public class AppUserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext ctx =
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private SUserManage sUserManage = ctx.getBean("sUserManage",SUserManage.class);
	
	private Gson gson = new Gson();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppUserLogin() {
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
		SUserWT sUser = new SUserWT();
		BaseResult baseParam = new BaseResult();
		
		try {
			
			//第一步：获取参数，参数验证，并包装
			if(!validateParam(request,sUser)) {
				baseParam.setSuccess(false);
				baseParam.setMessage("参数验证失败");
				out.write(gson.toJson(baseParam));
				return;
			}
			
			//第二步：调用用户验证接口，验证用户信息
			if(!sUserManage.userValidate(sUser)) {
				baseParam.setSuccess(false);
				baseParam.setMessage("用户名或密码错误");
				out.write(gson.toJson(baseParam));
				return;
			}
			
			//第三步：检查是否重复登陆，并维护重复登陆验证机制
			ServletContext application = this.getServletContext();
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<String, SUserWT> sessionPool = (Map<String, SUserWT>)application.getAttribute("sessionPool");
			if(sessionPool == null || sessionPool.size() == 0) {
				
				sessionPool = new HashMap<String, SUserWT>();
				sessionPool.put(session.getId(), sUser);
				application.setAttribute("sessionPool", sessionPool);
				
			}else {
				
				Object[] keys =  sessionPool.keySet().toArray();
				for(Object key : keys) {
					
						SUserWT swt =  sessionPool.get(key);
						if(swt.getAccount().equals(sUser.getAccount())) {
							
							baseParam.setSuccess(false);
							baseParam.setMessage("账户重复登陆");
							out.write(gson.toJson(baseParam));
							return;
						}
					sessionPool.put(session.getId(), sUser);
				}
					
			}
			
			//第四步：包装Json参数发回客户端
			//session.setMaxInactiveInterval(-1); //session永不过期
			//session.setAttribute("user", sUser);
			//System.out.println("ssid :" + session.getId());	
			
			SUserResult sUserParam = 
					gson.fromJson(gson.toJson(sUser), SUserResult.class);
			sUserParam.setSsid(session.getId()); 
			
			baseParam.setData(sUserParam);
			baseParam.setSuccess(true);
			out.write(gson.toJson(baseParam));
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			baseParam.setSuccess(false);
			baseParam.setMessage("服务器错误");
			out.write(gson.toJson(baseParam));
			return;
		}
	}
	
	private boolean validateParam(HttpServletRequest request, SUserWT sUser) {
		
		//第一步：获取所有的参数字符串
		String jsonString = request.getParameter("params");
		UserLoginParam jsonData = gson.fromJson(jsonString, UserLoginParam.class);
		String userName = jsonData.getUserName();
		String password = jsonData.getPassword();
		
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
