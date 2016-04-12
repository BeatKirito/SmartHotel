package com.smtl.app.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smtl.app.result.StateResult;

/**
 * Servlet implementation class ConnectTest
 */
public class ConnectTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Gson gson =  new Gson();  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectTest() {
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
		
		StateResult stateResult = new StateResult();
		stateResult.setSuccess(true);
		response.getWriter().write(gson.toJson(stateResult));
	}

}
