<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<%
		String errorMessage = (String) request.getAttribute("error");
	%>

	<div id="error" data-role="page" data-theme="a">
		<div data-role="header" data-theme="a">
			<h1>로그인 실패</h1>
		</div>

		<div data-role="content" style="text-align: center;" data-theme="b">
			<h3><%=errorMessage%></h3>

			<div style="text-align: center">
				<a href="loginform.html" data-role="button" data-icon="gear"
					data-direction="reverse">로그인 화면 이동</a>
			</div>
		</div>
	</div>
</body>
</html>