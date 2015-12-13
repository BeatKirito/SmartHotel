<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确定开房</title>
</head>
<body>
<%
	String roomID = request.getParameter("roomID");
	String roomNum = request.getParameter("roomNum");
	String roomType = request.getParameter("roomType");
	roomType= new String(roomType.getBytes("ISO-8859-1"),"utf-8");
%>
<p>确定开<%=roomNum %>房</p>
<p>类型：<%=roomType %></p>

<p>请填写下列信息完成入住登记</p>
<form action="openRoom" method="post">
	入住人信息：<input type="text" name="name" /><br />
	入住人身份证号：<input type="text" name="IDNum" /><br />
	入住人电话号码：<input type="text" name="phoneNum" /><br />
	已收金额：<input type="text" name="cost" /><br />
	入住天数：<input type="text" name="time" />
	<input type="hidden" value=<%=roomID %> name="roomID">
	<input type="submit" value="确定保存" />
</form>
</body>
</html>