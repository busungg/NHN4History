<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">

<title>memory home</title>
<link href="CSS/default.css" rel="stylesheet" type="text/css">

<link rel="alternate" type="application/rss+xml"
	title="Sean Hurley &raquo; Feed" href="http://www.seanhurley.com/feed/" />
<link rel="alternate" type="application/rss+xml"
	title="Sean Hurley &raquo; Comments Feed"
	href="http://www.seanhurley.com/comments/feed/" />
<link rel="alternate" type="application/rss+xml"
	title="Sean Hurley &raquo; Home Comments Feed"
	href="http://www.seanhurley.com/home/feed/" />
<!-- <link rel='stylesheet' id='jetpack_likes-css'  href='http://www.seanhurley.com/wp-content/plugins/jetpack/modules/likes/style.css?ver=2.2.2' type='text/css' media='all' />
 -->
<!-- 메뉴바 가운데정렬 -->
<link rel='stylesheet' id='rgs-css'
	href='http://www.seanhurley.com/wp-content/themes/salient/css/rgs.css?ver=3.5.1'
	type='text/css' media='all' />
<link rel='stylesheet' id='responsive-css'
	href='http://www.seanhurley.com/wp-content/themes/salient/css/responsive.css?ver=3.5.1'
	type='text/css' media='all' />
<link rel='stylesheet' id='main-styles-css'
	href='http://www.seanhurley.com/wp-content/themes/salient/style.css?ver=3.5.1'
	type='text/css' media='all' />

<link rel='stylesheet' id='orbit-css'
	href='http://www.seanhurley.com/wp-content/themes/salient/css/orbit.css?ver=3.5.1'
	type='text/css' media='all' />
<script type='text/javascript'
	src='http://www.seanhurley.com/wp-includes/js/jquery/jquery.js?ver=1.8.3'></script>
<script type='text/javascript'
	src='http://www.seanhurley.com/wp-content/themes/salient/js/modernizr.js?ver=2.6.2'></script>


<script type="text/javascript">
	//trim 함수 제작
	//http://dragonraja2010.blogspot.kr/2011/01/trim.html
	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/gi, "");
	}

	var httpRequest1 = null;

	function getXMLHttpRequest() {
		if (window.ActiveXObject) {
			try {
				return new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					return new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e1) {
					return null;
				}
			}
		} else if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else {
			return null;
		}
	}

	function sendLoginInfo() {

		alert("sendLoginInfo() 로직 진입");
		//alert(uid = $("#uid").attr("value"));

		var uid = document.getElementById("uid").value;
		var upassword = document.getElementById("upassword").value;

		//$('#uid').attr('value', "");
		//$('#upassword').attr('value', "");
		alert(uid + upassword);

		httpRequest1 = getXMLHttpRequest();
		httpRequest1.onreadystatechange = loginCallbackFunction; // 콜백 함수 등록
		//httpRequest1.open("POST", "http://155.230.52.63:8080/memory/Service", true);
		httpRequest1.open("POST", "login.jsp", true);
		httpRequest1.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		httpRequest1.send("userId=" + uid + "&userPw=" + upassword);
	}

	function loginCallbackFunction() {
		if (httpRequest1.readyState == 4) {
			//alert("4 진입");
			//alert(httpRequest1.status);
			if (httpRequest1.status == 200) {//httpRequest1.status == 0인 경우 로컬 파일에서 
				var text = httpRequest1.responseText.trim();
				if (text == "yes") {
					//alert("로그인 성공");
					document.getElementById("loginRow").removeChild(
							document.getElementById("loginForm"));

					/* 					$("#loginForm").hide();
					 $("#logout").show(); */
				} else {
					alert("아이디와 패스워드를 확인하세요.");
				}
			}
		}//if문 마지막
	}//함수 마지막
</script>

</head>

<body class="page page-id-18 page-template-default noise"
	data-smooth-scrolling="1">

	<div id="header-space"></div>

	<div id="header-outer" data-using-logo="1" data-logo-height="60"
		data-padding="" data-header-resize="">

		<div id="search-outer">

			<div id="search">

				<div class="container">

					<div id="search-box">

						<!--  꼭 있어야해 메뉴바랑 밑에 구분지어줘 -->
						<div class="col span_12"></div>
						<!--/span_12-->

					</div>
					<!--/search-box-->

					<div id="close">
						<a href=""></a>
					</div>

				</div>
				<!--/container-->

			</div>
			<!--/search-->

		</div>
		<!--/search-outer-->
		<!-- 여기꺼는 바꾸지마 -->
		<header id="top">

			<div class="container">

				<div class="row" id="loginRow">
<!-- 로그인이 안되어 있다면, LOGIN, JOIN 부분 필요
						 로그인 되어 있다면, LOGOUT 부분 필요
					 -->
					<%
						if (session.getAttribute("userId") == null) {
					%>
					<div id="loginForm">
						아이디<input id="uid" name="uid" type="text" /> 패스워드<input
							id="upassword" name="upassword" type="password" /> <input
							type="button" value="로그인" onclick="sendLoginInfo()" /> <input
							type="reset" value="취소" />
					</div>
					<!-- 회원가입은 추후 추가 -->
					<%
						} else {
					%>
					<div id="logout">
						<a href="/memory/Service?action=logout">logout</a>
					</div>

					<%
						}
					%>
					<div class="col span_3">
						<a id="logo" href="./"> <img alt="memory"
							src="./images/memory.png" />
						</a>
					</div>
					<!--/span_3-->

					<div class="col span_9 col_last">

						<!--  작은창일때 아이콘임 -->
						<a href="#" id="toggle-nav"></a>

						<nav>
							<ul class="sf-menu">
								<li id="menu-item-16"
									class="menu-item menu-item-type-post_type menu-item-object-page current-menu-item page_item page-item-12 current_page_item menu-item-16"><a
									href="./">Home</a></li>
								<li id="menu-item-2614"
									class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2614"><a
									href="company/welcome.htm">Project</a></li>
								<li id="menu-item-2625"
									class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2625"><a
									href="/memory/Service?action=shareList">Solution</a></li>
								<li id="menu-item-2600"
									class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2600"><a
									href="/memory/Service?action=list">My Page</a></li>
								<li id="menu-item-216"
									class="menu-item menu-item-type-post_type menu-item-object-page menu-item-216"><a
									href="center/qna.html">Q & A</a></li>

							</ul>
						</nav>

					</div>
					<!--/span_9-->

				</div>
				<!--/row-->

			</div>
			<!--/container-->

		</header>

	</div>
	<!--/header-outer-->

	<!-- 작은화면일때 메뉴바 뜨는거 -->
	<div id="mobile-menu">
		<div class="container">
			<ul>
				<li id="menu-item-16"
					class="menu-item menu-item-type-post_type menu-item-object-page current-menu-item page_item page-item-12 current_page_item menu-item-16"><a
					href="./">Home</a></li>
				<li id="menu-item-2614"
					class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2614"><a
					href="company/welcome.htm">Project</a></li>
				<li id="menu-item-2625"
					class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2625"><a
					href="#">Solution</a></li>
				<li id="menu-item-2600"
					class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2600"><a
					href="/memory/Service?action=list">My Page</a></li>
				<li id="menu-item-216"
					class="menu-item menu-item-type-post_type menu-item-object-page menu-item-216"><a
					href="center/qna.html">Q & A</a></li>

			</ul>
		</div>
	</div>

	<div id="featured" data-bg-color="" data-slider-height=""
		data-animation-speed="" data-advance-speed="" data-autoplay="1">


		<div class="slide orbit-slide">

			<article
				style="background-image: url('http://www.seanhurley.com/wp-content/uploads/2013/04/slider-slider1.jpg')">
				<div class="container">
					<div class="col span_12">
						<div class="post-title">
							<h2>
								<span> Take photos of your favourite outfits, clip them
									to a retailer and share! </span>
							</h2>

						</div>
						<!--/post-title-->
					</div>
				</div>
			</article>
		</div>
		<div class="slide orbit-slide">

			<article
				style="background-image: url('http://www.seanhurley.com/wp-content/uploads/2013/03/me-slider.png')">
				<div class="container">
					<div class="col span_12">
						<div class="post-title">
							<h2>
								<span> When im not working, I'm taking photos. Check out
									my Instagram feed. </span>
							</h2>

						</div>
						<!--/post-title-->
					</div>
				</div>
			</article>
		</div>
		<div class="slide orbit-slide">

			<article
				style="background-image: url('http://www.seanhurley.com/wp-content/uploads/2013/04/mybike.jpg')">
				<div class="container">
					<div class="col span_12">
						<div class="post-title">
							<h2>
								<span> Goal: Bike from Toronto to Kingston (2013) in one
									day. Total ride 349KM. </span>
							</h2>


						</div>
						<!--/post-title-->
					</div>
				</div>
			</article>
		</div>
	</div>

	<!-- 이것도 필수 글자 화면에 맞춰주는거 -->
	<div class="container main-content"></div>
	<!--/container-->



	<!-- 푸터위에꺼 잡아주는 곳 -->
	<div id="footer-outer">


		<div id="footer-widgets">

			<!-- row의 정렬? 정도? 가로로 쫙쫙 된다 -->
			<div class="container">

				<!-- 푸터위에 회색화면 -->
				<div class="row">

					<div class="col span_3">
						<!-- Footer widget area 3 -->
						<div id="recent-posts-3" class="widget widget_recent_entries">
							<h4>Recent Posts</h4>
							<ul>
								<li><a href="http://www.seanhurley.com/new-seo-formula/"
									title="The New SEO Formula">The New SEO Formula</a></li>
								<li><a
									href="http://www.seanhurley.com/best-practices-for-post-interview-follow-ups/"
									title="Best practices for post interview follow ups">Best
										practices for post interview follow ups</a></li>
								<li><a
									href="http://www.seanhurley.com/my-favourite-iphone-app-2/"
									title="My favourite iphone app #2 &#8211; Analytics StatsWidget">My
										favourite iphone app #2 &#8211; Analytics StatsWidget</a></li>
								<li><a
									href="http://www.seanhurley.com/the-3-worst-questions-to-ask-in-an-interview/"
									title="The 3 worst questions to ask in an interview">The 3
										worst questions to ask in an interview</a></li>
								<li><a
									href="http://www.seanhurley.com/how-to-price-your-freelance-business/"
									title="How to price your freelance business">How to price
										your freelance business</a></li>
								<li><a
									href="http://www.seanhurley.com/5-great-questions-to-ask-in-an-interview/"
									title="5 great questions to ask in an interview">5 great
										questions to ask in an interview</a></li>
								<li><a
									href="http://www.seanhurley.com/how-to-use-linkedin-to-find-a-job/"
									title="How to use LinkedIn to find a job">How to use
										LinkedIn to find a job</a></li>
							</ul>
						</div>
					</div>
					<!--/span_3-->



					<div class="col span_3">
						<!-- Footer widget area 3 -->
						<div id="recent-posts-3" class="widget widget_recent_entries">
							<h4>video</h4>
							<video src="video/20130502_192845.mp4" width="800" height="400"
								controls autoplay preload="auto">
							</video>
						</div>
					</div>
					<!--/span_3-->



				</div>
				<!--/row-->

			</div>
			<!--/container-->

		</div>
		<!--/footer-widgets-->


		<!-- footer -->

		<div class="row" id="copyright">

			<div class="container">

				<div class="col span_6">
					<p>All contents Copyright 2013 memory Inc. all rights reserved
						Contact mail : memory@google.com Tel: 010-4742-1444</p>
				</div>

				<div class="col span_6 col_last">
					<ul id="social">
						<li><a href="http://www.twitter.com/96robots" class="twitter">twitter
						</a></li>
						<li><a href="http://www.facebook.com/hurley.sean"
							class="facebook">facebook</a></li>
						<li><a href="ca.linkedin.com/in/seanhurley/" class="linkedin">linkedin
						</a></li>
						<li><a href="http://www.youtube.com/user/dynamicclothing"
							class="youtube">youtube </a></li>
						<li><a href="http://www.seanhurley.com/feed/rss/" class="rss">rss
						</a></li>
					</ul>
				</div>

			</div>
		</div>

	</div>
	<!--/footer-outer-->



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


</body>
</html>