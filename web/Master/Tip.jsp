<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		//取得参数正确或错误显示图片
		String judge = "";
		String text = "";
		String URL = "";
		
		//是否操作成功
		if (request.getAttribute("judge") != null)
			judge = request.getAttribute("judge").toString();
		if (judge != "") {
			if (judge == "true")
				out.write("<p class='succeed'>操作成功</p>");
			else
				out.write("<p class='fail'>操作失败</p>");
		}
		
		//传递要显示的页面文本text
		if (request.getAttribute("text") != null)
			text = request.getAttribute("text").toString();
		if (text != "")
			out.write("<p class='textCenter pmargin' >" + text + "</p>");
		
		//设置3秒后跳转
		if (request.getAttribute("URL") != null)
			URL = request.getAttribute("URL").toString();
		if (URL != "") {
			
			response.setContentType("text/html; charset=UTF-8");
			response.setHeader("refresh","3;url = " + URL);
			
		}
	%>
	<p class='textCenter'>页面将在3秒后跳转,<a href="<%= URL %>" >立即跳转</a></p>
</body>
</html>