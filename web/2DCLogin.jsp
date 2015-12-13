<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登陆</title>
</head>
<body>
	<%
		String roomNum  =  request.getParameter("roomNum");
		
		if(roomNum == null) {
			request.getRequestDispatcher("ErrorPage.html");
			return;
		}
		/* Pattern p = Pattern.compile("\\D");
		Matcher m = p.matcher(roomNum);
		
		if(m.find()) {
			request.getRequestDispatcher("ErrorPage.html");
			return;
		} */
	%>
	<span>房号：<%= roomNum %></span>
	<form method='post' action='Login' >
		<span>房间密码：</span>
		<!-- 隐藏表单记录访问的房号 -->
		<input type="hidden" name="roomNum" value="<%=roomNum %>"  /> 
		<input type='password' name ='password' />
		<input type="submit" value="登陆" /> 
	</form>	
	<%-- <a href="#?roomNum=<%= roomNum %>" >vip用户登录</a> --%>
</body>
</html>