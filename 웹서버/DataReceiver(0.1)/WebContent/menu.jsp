<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.3.0/jquery.mobile-1.3.0.min.css" />
<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.3.0/jquery.mobile-1.3.0.min.js"></script>
</head>
<body>
	<%
		HttpSession validSession = request.getSession(false);
		System.out.println(validSession);
	%>

	<%
		if (validSession != null) {
			System.out.println("세션 유지됨");
	%>
	<div id="menu" data-role="page" data-theme="a">
		<div data-role="header" data-theme="a">
			<h1>메뉴</h1>
		</div>

		<!--  post.jsp로 이동하는게 아니라 단말기의 파일에 접근 -->
		<div data-role="content" data-theme="b">
			<div data-role="controlgroup">
				<a href="file:///android_asset/www/filetransfer.html" data-role="button" data-icon="forward">포스팅</a> <a
					href="controller?action=logout" data-role="button"
					data-icon="delete">로그아웃</a>
			</div>
			<!-- 		<div data-role="content" data-theme="b">
			<div data-role="controlgroup" data-type="vertical">
				<a href="#" data-role="button" data-inline="true">포스팅</a> 
				<a href="#" data-role="button" data-inline="true">로그아웃</a>
			</div>
		</div> -->

		</div>
	</div>
	<%
		} else {
			System.out.println("세션 끊김");
			request.setAttribute("error", "로그아웃 상태입니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	%>
	}
</body>
</html>