<%@page import="model.dto.Post"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>상세페이지</title>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />

<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>

</head>
<body>

	<div id="tabs">
		<ul>
		<%
		List list = (List) request.getAttribute("detailData");
		Post data;

		for (int i = 0; i < list.size(); i++) {
			data = (Post) list.get(i);
		%>
		
		<%="<li><a href=#tabs-" + i + ">"%><%=data.getTitle()%><%="</a></li>"%>	
		
		<%
			}
		%>
		</ul>
		
		<%
		for (int i = 0; i < list.size(); i++) {
			data = (Post) list.get(i);
		%>
		
		<%="<div id=tabs-"+ i + ">" + "<p>"%>
		<img src=<%="upload/" + data.getImage()%> width="100" height="100"><br>
		<%=data.getUptime() + " " + data.getCategory()%>
		<%="</p> </div>"%>
		
		<%
			}
		%>
	</div>

</body>
</html>