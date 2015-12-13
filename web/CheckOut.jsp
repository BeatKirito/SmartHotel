<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@page import="com.smtl.fweb.service.RoomManage" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退房</title>
</head>
<body>
<%
	ApplicationContext ctx =new ClassPathXmlApplicationContext("applicationContext.xml");
	
	RoomManage roomManage = ctx.getBean("roomManage",RoomManage.class);
	
	//展示数据
	roomManage.showAllCheckOutRoom(out);
%>
</body>
</html>