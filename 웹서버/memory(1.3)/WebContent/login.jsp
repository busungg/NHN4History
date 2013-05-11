<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 화면</title>
</head>
<body>
	<form action="/memory/Service?action=login" method="post">
		<label>아이디</label><input name="userId" type="text">
		<label>패스워드</label><input name="userPw" type="password">
		<input type="submit"value="로그인"/>
		<input type="reset" value="취소" />
	</form>
</body>
</html>