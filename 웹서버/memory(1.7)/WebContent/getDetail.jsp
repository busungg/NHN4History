<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="This example shows how records in the data grid widget can be presented with a detailed template.">
    
    <title>get</title>
</head>
<body>

<%
	String id = request.getParameter("id");
	String year = request.getParameter("year");
	String month = request.getParameter("month");
	String day = request.getParameter("day");
	
	if(month.length() != 2)
	{
		month = "0" + month;
	}
	
	if(day.length() != 2)
	{
		day = "0" + day;
	}
	
	String startDate = year + month + day + "000000";
	String endDate = year + month + day + "235959";
	
	request.setAttribute("startDate", startDate);
	request.setAttribute("endDate", endDate);
	
	request.getRequestDispatcher("/Service?action=detail").forward(request, response);
%>

</body>
</html>
