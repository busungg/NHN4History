<%@page import="java.util.Date"%>
<%@page import="model.dto.Post"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko" xmlns:v="urn:schemas-microsoft-com:vml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>상세페이지</title>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<link href="CSS/default.css" rel="stylesheet" type="text/css">
<link href="CSS/main.css" rel="stylesheet" type="text/css">
<link rel="alternate" type="application/rss+xml"
	title="Sean Hurley &raquo; Feed" href="http://www.seanhurley.com/feed/" />
<link rel="alternate" type="application/rss+xml"
	title="Sean Hurley &raquo; Comments Feed"
	href="http://www.seanhurley.com/comments/feed/" />
<link rel="alternate" type="application/rss+xml"
	title="Sean Hurley &raquo; Home Comments Feed"
	href="http://www.seanhurley.com/home/feed/" />
<!-- 메뉴바 가운데정렬 -->
<link rel='stylesheet' id='rgs-css'
	href='http://www.seanhurley.com/wp-content/themes/salient/css/rgs.css?ver=3.5.1'
	type='text/css' media='all' />
<link rel='stylesheet' id='responsive-css'
	href='http://www.seanhurley.com/wp-content/themes/salient/css/responsive.css?ver=3.5.1'
	type='text/css' media='all' />
<link rel='stylesheet' id='orbit-css'
	href='http://www.seanhurley.com/wp-content/themes/salient/css/orbit.css?ver=3.5.1'
	type='text/css' media='all' />
<script type='text/javascript'
	src='http://www.seanhurley.com/wp-includes/js/jquery/jquery.js?ver=1.8.3'></script>
<script type='text/javascript'
	src='http://www.seanhurley.com/wp-content/themes/salient/js/modernizr.js?ver=2.6.2'></script>

<!-- 이게 메뉴바야ㅑㅑㅑㅑㅑㅑ -->
<script type='text/javascript'
	src='http://www.seanhurley.com/wp-content/themes/salient/js/superfish.js?ver=1.4.8'></script>
<script type='text/javascript'
	src='http://www.seanhurley.com/wp-content/themes/salient/js/easing.js?ver=1.3'></script>
<script type='text/javascript'
	src='http://www.seanhurley.com/wp-content/themes/salient/js/nicescroll.js?ver=3.1'></script>
<script type='text/javascript'
	src='http://www.seanhurley.com/wp-content/themes/salient/js/prettyPhoto.js?ver=3.1.5'></script>
<script type='text/javascript'
	src='http://www.seanhurley.com/wp-content/themes/salient/js/init.js?ver=1.0'></script>
<script type='text/javascript'
	src='http://www.seanhurley.com/wp-content/themes/salient/js/orbit.js?ver=1.4'></script>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>

</head>
<body class="page page-id-18 page-template-default noise"
	data-smooth-scrolling="1">

	<jsp:include page="reference/header.jsp" flush="false" />

	<%
		String startDate = (String) request.getAttribute("startDate");
		System.out.println(startDate);
	%>

	<div id="all" align="center">

		<div id="tabs"
			style="width: 800px; text-align: center; margin-top: 20px;"
			align="center">
			<ul>
				<br>
				<h5>포스트가 없습니다. 새로운 포스트를 작성해 보세요!</h5>
				<br>
				
			</ul>
			<br><br><br>
			<!-- 포스팅 쓰기 버튼, location.href를 클릭시, 스마트 에디터가 있는 페이지로 이동하는 URL 주소를 적으면 된다. -->
			<input type="button" class="Large button blue" value="New posting!"
				style="font-size: 13pt; height: 80px; width: 200px"
				onclick="location.href='pages/write.jsp?date=<%=startDate%>'" /> <br>
			<br><br><br><br>
		</div>

		<!-- 맵부분 -->
		<div id="map"
			style="width: 900px; text-align: center; margin-top: 20px;"
			align="center"></div>

		<!-- 공유페이지 -->
		<div id="share" align="center" style="margin: 10px;"></div>

	</div>
			<div id="footer-widgets">
			</div>
	<div id="footer-outer">

		<jsp:include page="reference/footer.jsp" flush="false" />
	</div>
	<!--/footer-outer-->

</body>
</html>