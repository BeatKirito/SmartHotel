package com.smtl.app.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smtl.app.param.BaseParam;
import com.smtl.app.result.BaseResult;
import com.smtl.app.result.StateResult;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
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
		StateResult stateResult = new StateResult(); 
		
		try {

			ServletContext application = this.getServletContext();
			Map<String, Object> sessionPool = 
					(Map<String, Object>)application.getAttribute("sessionPool");
			
			String params = request.getParameter("params");
			BaseParam baseParam = gson.fromJson(params, BaseParam.class);
			
			sessionPool.remove(baseParam.getSsid());
			stateResult.setSuccess(true); 
			out.write(gson.toJson(stateResult));
			
		} catch (Exception e) {
			e.printStackTrace();
			stateResult.setSuccess(false); 
			stateResult.setMessage("服务器错误"); 
			out.write(gson.toJson(stateResult));
		}
		
		
		
	}

}
