<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>咨询回复页面</title>
</head>
<body>
<%
	String consultID = request.getParameter("consultID");
	String content = request.getParameter("content");
	if(content.length()!=0)
		content= new String(content.getBytes("ISO-8859-1"),"utf-8");
	String time = request.getParameter("consultTime");
	String roomNum = request.getParameter("roomNum");
	String errorInfo = null;
	errorInfo = request.getParameter("errorInfo");
%>
	<h3>咨询回复</h3>
	<h4>房号：<%=roomNum %></h4>
	<h4>咨询内容：<%=content%></h4>
	<h4>咨询时间：<%=time %></h4>
	
	<h3>回复</h3>
	<form action="ReplyConsult" method="post">
			<textarea name="reply" >请输入回复</textarea>
			<%
				if(errorInfo!=null)	
					out.println(errorInfo);
			%>
			<input type="hidden" value=<%=consultID %> name="consultID">
			<!-- input type="text" value="对话输出" class="inPut"> -->
			<input type="submit" value="发送" class="submit">
		</form>
</body>
</html>