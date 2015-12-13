<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@page import="com.smtl.fweb.service.RoomRecordManage" %>
<%@page import="com.smtl.fweb.damain.RoomRecord" %>
<%@page import="java.util.List"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确定退房信息</title>
</head>
<body>
<%
	String roomID = request.getParameter("roomID");
	String roomNum = request.getParameter("roomNum");
	String roomType = request.getParameter("roomType");
	String cardNumber = request.getParameter("cardNumber");
	roomType= new String(roomType.getBytes("ISO-8859-1"),"utf-8");
	
	ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
	int t=0;
	RoomRecordManage roomRecordManage = ctx.getBean("roomRecordManage",RoomRecordManage.class);
	List<RoomRecord> roomRecordList = roomRecordManage.getRoomRecordByRoomNum(roomID);
	RoomRecord rr = new RoomRecord();
	if(roomRecordList.size()!=0){
		rr = roomRecordList.get(roomRecordList.size()-1);
	}
	String cusName = new String(rr.getCusName().getBytes("ISO-8859-1"),"UTF-8");
%>
<h3>要退的房间是<%=roomNum %></h3>
<p>退房人：<%=cusName %></p>
<p>已收费用：<%=rr.getInitCost() %></p>
<p>应退还金额：<%=rr.getReturnDeposit() %></p>
<form action="CheckOut" method="post">
	<input type="hidden" value=<%=roomID %> name="roomID" />
	<input type="submit" value="退房" />
</form>
</body>
</html>