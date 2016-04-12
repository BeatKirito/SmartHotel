package com.smtl.fweb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.smtl.fweb.damain.CusConsultWN;
import com.smtl.fweb.service.ConsultManage;

/**
 * Servlet implementation class GetConsult
 */
public class GetConsult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext ctx =
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private ConsultManage userOnlineAsk= ctx.getBean("consult",ConsultManage.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetConsult() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//获取房间ID为12的咨询信息
		List<CusConsultWN> ccWN = userOnlineAsk.GetConsultByRoomID(12);
		
		//gson封装
		Gson gson =new Gson();
		String consult = gson.toJson(ccWN);
		System.out.print(consult);
		
	}

}
