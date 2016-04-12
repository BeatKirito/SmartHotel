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
import com.smtl.app.result.BaseResult;
import com.smtl.app.result.StateResult;
import com.smtl.fweb.damain.SUserWT;
import com.smtl.mweb.domain.CusCall;
import com.smtl.mweb.service.CusCallManage;

/**
 * Servlet implementation class CusCallList
 */
public class CusCallList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext ctx =
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private CusCallManage cusCallManage = ctx.getBean("cusCallManage",CusCallManage.class);
	
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CusCallList() {
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

		//response.getWriter().write("成功");
		
		PrintWriter out = response.getWriter();
		BaseResult baseResult = new BaseResult();
		
		
		try {
			
			//第一步：获取工作人员的session对象
			ServletContext application = this.getServletContext();
			Map<String, Object> sessionPool = 
					(Map<String, Object>)application.getAttribute("sessionPool");
			
			String params = request.getParameter("params");
			BaseParam baseParam = gson.fromJson(params, BaseParam.class);
			SUserWT sUserWT =  (SUserWT)sessionPool.get(baseParam.getSsid());
			
			//第二步：根据工作人员的职务类型，获取该职务的顾客呼叫列表
			baseResult.setData(cusCallManage.loadListBySUTypeID(sUserWT.getUserTypeID()));
			
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
