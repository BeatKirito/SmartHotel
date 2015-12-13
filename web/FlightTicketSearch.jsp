<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞机机票查询</title>
</head>
<body>
	<form action="FlightTicketCralwer" method="post" >
		<span>出发城市</span>
		<input type="text" name="dCity" /><br />
		<span>到达城市</span>
		<input type="text" name="aCity"  /><br />
		<span>出发日期</span>
		<input type="text" name="dDate"  /><br />
		<span>人数</span>
		<input type="text" name="num"  /><br />
		<span>乘客类型</span>
		<select	name="pType">
			<option value="adu">成人</option>
			<option value="chi">儿童</option>
			<option value="bab">婴儿</option>
		</select><br />
		<span>舱位等级</span>
		<select	name="cType">
			<option value="y">经济舱</option>
			<option value="cf">公务舱/头等舱</option>
		</select><br />
		
		<input type="submit" value="提交" /> 
	</form>
</body>
</html>