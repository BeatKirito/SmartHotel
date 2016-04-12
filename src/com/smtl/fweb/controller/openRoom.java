package com.smtl.fweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.smtl.extend.*;
import com.smtl.extend.MD5;
import com.smtl.fweb.damain.Room;
import com.smtl.fweb.damain.RoomRecord;
import com.smtl.fweb.damain.RoomType;
import com.smtl.fweb.service.RoomManage;
import com.smtl.fweb.service.RoomRecordManage;
import com.smtl.fweb.service.RoomTypeManage;

/**
 * Servlet implementation class openRoom
 */
public class openRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private RoomManage roomManage = ctx.getBean("roomManage",RoomManage.class);
	
	private RoomTypeManage roomTypeManage = ctx.getBean("roomTypeManage",RoomTypeManage.class);
	
	private RoomRecordManage roomRecordManage = ctx.getBean("roomRecordManage",RoomRecordManage.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public openRoom() {
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
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//获取jsp页面信息
		String roomID = request.getParameter("roomID");
		String name= request.getParameter("name");
		if(name!=null)
			name = new String(name.getBytes("ISO-8891"),"UTF-8");
		String IDNum = request.getParameter("IDNum");
		String phoneNum =request.getParameter("phoneNum");
		String cost = request.getParameter("cost");
		String time =request.getParameter("time");
		
		//检验获取的信息是否为空
		if(name.length()==0 || IDNum.length()==0 || phoneNum.length()==0 || cost.length()==0){
			System.out.print("信息不能为空！");
			response.sendRedirect("ComfirmRoom.jsp?errorInfo='所填信息不能为空！'");
		}
		
		//生成密码
		String password = createPassword();
		
		//获取退房天数
		String leaveTime = countLeaveTime(time);
		
		//实例化实体类，进行数据的存储
		Room roomWT = new Room();
		roomWT = roomManage.getRoom(Integer.parseInt(roomID));
		
		roomWT.setState(true);
		roomWT.setPassword(MD5.GetMD5Code(password));
		roomManage.updateRoom(roomWT); 							//更新房间密码和状态
		
		//计算押金
		RoomType roomType = new RoomType();
		roomType = roomTypeManage.getRoomType(roomWT.getRoomTypeID());
		double roomPrice = roomType.getPrice();
		double roomcost = Double.parseDouble(cost) - (roomPrice * Integer.parseInt(time));
		
		//填充房间记录数据
		RoomRecord roomRecord = new RoomRecord();
		roomRecord.setRoomID(roomWT.getRoomID());
		roomRecord.setCusName(name);
		roomRecord.setCardNumber(IDNum);
		roomRecord.setIsAvailable(true);
		roomRecord.setLeaveTime(new Date(leaveTime));
		roomRecord.setPhoneNumber(phoneNum);
		roomRecord.setInitCost(Double.parseDouble(cost));
		roomRecord.setReturnDeposit(roomcost);
		
		if(roomRecordManage.saveRoomRecord(roomRecord)>0){
			
			try {
				MatrixToImageWriter(request,roomWT.getRoomNum(),password);
			} catch (Exception e) {
				System.out.println("生成二维码失败！");
				e.printStackTrace();
			}
			System.out.println("开房成功！");
		}
	}
	
	//计算退房天数
	String countLeaveTime(String time){
		
		Calendar calendar = Calendar.getInstance();
		
		int passTime = Integer.parseInt(time);
		calendar.add(Calendar.DATE,passTime);
		Date leaveTime =  calendar.getTime();
		
		System.out.println(leaveTime);
		return leaveTime.toString();
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
	
	//生成二维码
		@SuppressWarnings("unchecked")
		void MatrixToImageWriter(HttpServletRequest request,String RoomNo,String Password) throws IOException, WriterException{
				
			 	String text; 
			 	//text = String.format("http://192.168.1.105:8080/SmartHotel/Copyindex.jsp?RoomNo=%s&Password=%s", RoomNo,Password);
			 	text = String.format("http://beatkirito:81/SmartHotel/Copyindex.jsp?RoomNo=%s&Password=%s", RoomNo,Password);
			 	System.out.println(text);
		        int width = 300; 
		        int height = 300; 
		        //二维码的图片格式 
		        String format = "gif"; 
		        Hashtable hints = new Hashtable(); 
		        //内容所使用编码 
		        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, 
		                BarcodeFormat.QR_CODE, width, height, hints); 
		        //生成二维码 
		        String uploadDir = request.getSession().getServletContext().getRealPath("/")+ "\\images\\";
		        File outputFile = new File(uploadDir+File.separator+"new.gif");
		        //MatrixToImageWriter m = new MatrixToImageWriter();
		        //m.writeToFile(bitMatrix, format, outputFile);  
		       MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		}

}
