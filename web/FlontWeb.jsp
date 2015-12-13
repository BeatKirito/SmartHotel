<%@page import="com.smtl.fweb.service.impl.ConsultManageImpl"%>
<%@page import="com.smtl.fweb.dao.CusComsultDao" %>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@page import="com.smtl.fweb.service.ConsultManage" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台接受消息页面</title>
</head>
<body>
<%
	//展示咨询列表
	ApplicationContext ctx =
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	ConsultManage userOnlineAsk= ctx.getBean("consult",ConsultManage.class);
	//ConsultManageImpl cmi = new ConsultManageImpl();
	userOnlineAsk.ShowNoReplyConsult(out);
	
%>
</body>
</html>