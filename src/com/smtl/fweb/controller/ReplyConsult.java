package com.smtl.fweb.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smtl.fweb.damain.CusConsult;
import com.smtl.fweb.service.ConsultManage;

/**
 * Servlet implementation class ReplyConsult
 */
public class ReplyConsult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext ctx =
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private ConsultManage userOnlineAsk= ctx.getBean("consult",ConsultManage.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyConsult() {
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
		
		request.setCharacterEncoding("utf-8");
		
		//第一步，获取回复内容
		String reply = request.getParameter("reply");
		String consultID = request.getParameter("consultID");
		
		//第二步，检验内容是否为空
		if(reply.length()==0){
			response.sendRedirect("consultReply.jsp?errorInfo='回复不能为空！'");
			return;
		}
		
		//第三步，存储回复
		CusConsult cc = new CusConsult();
		
		cc = userOnlineAsk.getConsult(Integer.parseInt(consultID));
		
		//cc.setConsultID(Integer.parseInt(consultID));
		cc.setIsReply(true);
		cc.setReply(reply);
		cc.setReplyTime(new Date());
		
		if(userOnlineAsk.insertReply(cc) >0){
			System.out.print("回复成功！");
			response.sendRedirect("FlontWeb.jsp");
		}
		else System.out.print("回复失败！");
	}

}
