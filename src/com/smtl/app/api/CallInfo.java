package com.smtl.app.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smtl.app.param.BaseParam;
import com.smtl.app.param.CusCallParam;
import com.smtl.app.result.BaseResult;
import com.smtl.fweb.damain.SUserWT;
import com.smtl.mweb.service.CusCallManage;

/**
 * Servlet implementation class CallInfo
 */
public class CallInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext ctx =
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private CusCallManage cusCallManage = ctx.getBean("cusCallManage",CusCallManage.class);
	
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallInfo() {
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
		BaseResult baseResult = new BaseResult();
		
		try {
			
			//第一步：获取工作人员的session对象
			ServletContext application = this.getServletContext();
			Map<String, Object> sessionPool = 
					(Map<String, Object>)application.getAttribute("sessionPool");
			
			String params = request.getParameter("params");
			BaseParam baseParam = gson.fromJson(params, BaseParam.class);
			CusCallParam cusCallParam = gson.fromJson(params, CusCallParam.class);
			//SUserWT sUserWT =  (SUserWT)sessionPool.get(baseParam.getSsid());
			
			//第二步：调用顾客呼叫管理接口，传入呼叫ID，获取呼叫的详细信息
			baseResult.setData(cusCallManage.loadByID(cusCallParam.getCallID()));
			
			//第三步：将列表数据送给客户端
			baseResult.setSuccess(true);
			out.write(gson.toJson(baseResult));
			
		} catch (Exception e) {
			e.printStackTrace();
			baseResult.setSuccess(false);
			baseResult.setMessage("服务器错误");
			out.write(gson.toJson(baseResult));
			return;
		}
	}

}
