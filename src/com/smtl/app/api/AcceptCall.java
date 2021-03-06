package com.smtl.app.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Proxy;
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
import com.smtl.mweb.domain.CusCallWO;
import com.smtl.mweb.service.CusCallManage;

/**
 * Servlet implementation class AcceptCall
 */
public class AcceptCall extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext ctx =
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private CusCallManage cusCallManage = ctx.getBean("cusCallManage",CusCallManage.class);
	
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptCall() {
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
		
		String[] s = new String[]{"a","b","c"};
		
		try {
			
			//第一步：获取工作人员的session对象
			ServletContext application = this.getServletContext();
			Map<String, Object> sessionPool = 
					(Map<String, Object>)application.getAttribute("sessionPool");
			
			//第二步：解析参数，获取接受呼叫的用户ID，以及呼叫ID
			String params = request.getParameter("params");
			BaseParam baseParam = gson.fromJson(params, BaseParam.class);
			
			CusCallParam cusCallParam = gson.fromJson(params, CusCallParam.class);
			SUserWT sUserWT =  (SUserWT)sessionPool.get(baseParam.getSsid());
			
			CusCallWO cusCallWO = new CusCallWO();
			cusCallWO.setCallID(cusCallParam.getCallID());
			cusCallWO.setAcceptedSUserID(sUserWT.getUserID());
			
			//把任务最新状态包装进结果参数中
			baseResult.setData(cusCallWO);
			
			//第三步：调用呼叫业务的接收呼叫接口
			if(!cusCallManage.acceptCall(cusCallWO)) {
				baseResult.setSuccess(true);
				baseResult.setMessage("任务已被接收");
				out.write(gson.toJson(baseResult));
				return;
			}
			
			//第四步：将结果数据发送回客户端
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
