package com.smtl.fweb.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class getOnlineAsk
 */
public class Consult extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	private ApplicationContext ctx =
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private ConsultManage userOnlineAsk= ctx.getBean("consult",ConsultManage.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Consult() {
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
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//第一步，获取文本框内容
		String askContent = null;
		askContent = request.getParameter("content");
		System.out.println(askContent);
		
		//第二步，判断文本是否为空
		if(askContent == null){
			return ;
		}
		
		//第三步，若文本不为空，将文本信息存储到数据库表格中
		CusConsult cc =new CusConsult();
		cc.setContent(askContent);
		cc.setRoomID(12);
		cc.setIsReply(false);
		/*Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time1=df.format(date);
		try {
			cc.setConsultTime(df.parse(time1));
		} catch (ParseException e) {
			System.out.println("字符串转换出错！");
			e.printStackTrace();
		}*/
		
		//第四步，存储持久性数据到数据库
		if(userOnlineAsk.SendMassage(cc)){
			System.out.println("信息加载到数据库成功！");
		}
		else{
			System.out.println("信息加载失败");
		}
		
	}

}
