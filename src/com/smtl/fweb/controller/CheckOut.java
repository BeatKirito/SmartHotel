package com.smtl.fweb.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smtl.extend.MD5;
import com.smtl.fweb.damain.Room;
import com.smtl.fweb.service.RoomManage;

/**
 * Servlet implementation class CheckOut
 */
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private RoomManage roomManage = ctx.getBean("roomManage",RoomManage.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOut() {
        super();
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
		
		//获取jsp页面的信息
		String roomID = request.getParameter("roomID");
		
		//获取密码
		String password =  createPassword();
		
		//实例化实体类，进行数据的存储
		Room roomWT = new Room();
		roomWT = roomManage.getRoom(Integer.parseInt(roomID));
				
		roomWT.setState(false);
		roomWT.setPassword(MD5.GetMD5Code(password));
		roomManage.updateRoom(roomWT); 							//更新房间密码和状态
		
		request.setAttribute("judge", "true");
		request.setAttribute("text", "退房成功！");
		request.setAttribute("URL", "CheckOut.jsp");
		request.getRequestDispatcher("/Master/Tip.jsp").forward(request, response);
		return;
	}
	
	//生成房间密码
	String createPassword(){
				
		Random rand = new Random();
		int randNum = rand.nextInt(10000);
		int num		= rand.nextInt(8);
		while(num == 0){
			num = rand.nextInt(8);
		}
				
		//生成密码
		long password = randNum + num * 100000 +  num * 10000 + num *10;
		return String.valueOf(password);
	}

}
