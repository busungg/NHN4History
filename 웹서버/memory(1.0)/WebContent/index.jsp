<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link href="CSS/default.css" rel="stylesheet" type="text/css">
<link
	href="http://cdn.kendostatic.com/2013.1.319/styles/kendo.common.min.css"
	rel="stylesheet" />
<link
	href="http://cdn.kendostatic.com/2013.1.319/styles/kendo.default.min.css"
	rel="stylesheet" />
<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script src="http://cdn.kendostatic.com/2013.1.319/js/kendo.all.min.js"></script>


<!-- 사진 css -->
<!-- stylesheets -->
<link rel="stylesheet"
	href="http://www.creativelywise.com/css/flexslider.css" type="text/css"
	media="screen" />
<link rel="stylesheet"
	href="http://www.creativelywise.com/css/style.css" type="text/css" />

<!-- javascripts -->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script src="http://www.creativelywise.com/js/jquery.flexslider-min.js"></script>
<script type="text/javascript"
	src="http://www.creativelywise.com/js/jquery.easing.1.3.js"></script>

<!-- Fontkit -->

<script type="text/javascript" src="http://use.typekit.com/udn5gwv.js"></script>
<script type="text/javascript">
	try {
		Typekit.load();
	} catch (e) {
	}
</script>


<!-- FlexSlider -->

<script type="text/javascript">
	$(window).load(function() {
		$('.flexslider').flexslider({
			animation : "slide",
			slideshow : false,
			controlsContainer : ".flex-container"
		});
	});
</script>
</head>
<body>

	<header>
		<!-- 로그인 -->
		<%
			System.out.println("index.jsp 세션값 : "
					+ session.getAttribute("userId"));
			if (session.getAttribute("userId") == null) {
		%>
		<div id="login">
			<a href="login.jsp">login</a> | <a href="#">Join</a>
		</div>
		<%
			} else {
		%>

		<div id="logout">
			<a href="/memory/Service?action=logout">logout</a>
		</div>
		<%
			}
		%>
		<!-- 로고 -->
		<div class="clear"></div>
		<div id="logo">
			<img src="./images/memory.png" width="500" height="100" alt="Memory">
		</div>

		<nav id="top_menu">
			<ul>
				<li><a href="./">Home</a></li>
				<li><a href="company/welcome.html">Project</a></li>
				<li><a href="#">Solution</a></li>
				<li><a href="/memory/Service?action=list">My page</a></li>
				<li><a href="center/qna.html">Q & A</a></li>
			</ul>
		</nav>


	</header>

	<!-- FlexSlider -->

	<article id="front">
		<section>

<!-- 			<div class="container cf" id="work"> -->

				<div class="grid_full">
					<div id="flexslider-outer">
						<div class="flex-container">
							<div class="flexslider">
								<ul class="slides">
									<li><img src="images/Tulips.jpg" />
										<p class="flex-caption">Biofuel Energy Website |
											Environmental PR Group</p></li>
									<li><img src="images/Penguins.jpg" /></li>

									<li><img src="images/main_img.jpg" />
										<p class="flex-caption">Florida Gator Football Program |
											Group 5 Advertising</p></li>



								</ul>
								<!-- end slides -->
							</div>
							<!-- end flexslider -->
						</div>
						<!-- end flex-container -->
					</div>
					<!-- end flexslider-outer -->
				</div>
				<!-- end grid-full -->

			</div>
			<!-- end work -->

		</section>

		<div id="wrap">
			<video src="video/20130502_192845.mp4" width="800" height="400"
				controls autoplay preload="auto">
			</video>


		</div>
	</article>


	<footer>
		<hr>
		<div id="copy">
			All contents Copyright 2013 memory Inc. all rights reserved<br>
			Contact mail : memory@google.com Tel: 010-4742-1444
		</div>
		<div id="social">
			<img src="images/facebook.gif" width="33" height="33" alt="Facebook">
		</div>
	</footer>

</body>
</html>