<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 화면</title>
<link href="CSS/default.css" rel="stylesheet" type="text/css">
<link href="CSS/front.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<header>
			<!-- 로그인 검증로직 -->
			<%
				System.out.println("index.jsp 세션값 : " + session.getAttribute("userId"));
				if (session.getAttribute("userId") == null) {
			%>
			<div id="login">
				<a href="login.jsp">login</a> | <a href="#">Join</a>
			</div>
			<% }else{ %>
			
			<div id="logout">
				<a href="/memory/Service?action=logout">logout</a>
			</div>
			<% }%>
			
			<div class="clear"></div>
			<div id="logo">
				<img src="./images/logo1.jpg" width="265" height="150" alt="Memory">
			</div>
			<nav id="top_menu">
				<ul>
					<li><a href="index.jsp">HOME</a></li>
					<li><a href="company/welcome.html">PROJECT</a></li>
					<li><a href="#">SOLUTIONS</a></li>
					<li><a href="mypage.jsp">MY PAGE</a></li>
					<li><a href="center/qna.html">Q & A</a></li>
				</ul>
			</nav>
			<nav></nav>
		</header>

		<!-- 로그인 창 -->
		<div class="clear"></div>
		<div id="main_img"></div>
		<div id="login_box">
			<form>
				<label>아이디</label><input name="userid" type="text">
				<div class="clear"></div>
				<label>패스워드</label><input name="passwprd" type="password"> <span
					class="btn"><a href="#"> 로그인</a></span> <span class="btn"><a
					href="#"> 회원가입</a></span>
			</form>
		</div>
		<body>


			<article id="front">
				<!-- update 내용 계속 올라오는 부분 -->
				<div id="new_date">
					<h3 class=orange>NEW POSTER</h3>
					<table>
						<tr>
							<td class="contxt"><a href="#">데이터를 뽑아와용ㅋㅋㅋㅋ으히히</a></td>
							<td>2013.04.13
							</th>
						</tr>
						<tr>
							<td class="contxt"><a href="#">얄루야룰ㄹ아러니러만ㄹ</a></td>
							<td>2013.04.13</td>
						</tr>
						<tr>
							<td class="contxt"><a href="#">뉴뉴뉴뉴뉸</a></td>
							<td>2013.04.13</td>
						</tr>
						<tr>
							<td class="contxt"><a href="#">힘냅시다 모두들 </a></td>
							<td>2013.04.13
						<tr>
							<td class="contxt"><a href="#">으겔겔겔겔겔 </a></td>
							<td>2013.04.13</td>
						</tr>
					</table>
				</div>
				<div class="clear"></div>
				<div id="introduction">

					<div id="kim">
						<h3>kim boo sung</h3>
						<p>김부성은요 PL이에요 여러가지 업무를 하고 있는데 일단 주된 업무는 증강현실이죠. ㅇㅁㄴㅇㅁㄴㅇㅁㄴ
							ㄴㅁㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ ㄴㅁㅇㅁㄴㅇ</p>
					</div>

					<div id="kuen">
						<h3>kuen sun reual</h3>
						<p>권순률 오빠는요 PM인데요 이 오빠는 메모리의 포스팅작업을 담당하고 있어요 맛잇는 꿀단지 냠냠냠ㄴㅁ
							ㅔ헤하헤헤헤헿</p>
					</div>

					<div id="han">
						<h3>han hye won</h3>
						<p>나는 UI 담당 으헤헤헿 ZZZZZZZZ ZZZZZZZZZZZZZZZZZZZZZZZZZZZ
							ZZZZZZZZZZZ 또또도엄니ㅏㅇㅁㄴ</p>
					</div>
				</div>

			</article>
			<div class="clear"></div>

			<footer>
				<hr>
				<div id="copy">
					All contents Copyright 2013 memory Inc. all rights reserved<br>
					Contact mail : memory@google.com Tel: 010-4742-1444
				</div>
				<div id="social">
					<img src="images/facebook.gif" width="33" height="33"
						alt="Facebook">
				</div>
	</div>
	</footer>
	</div>

</body>
</html>