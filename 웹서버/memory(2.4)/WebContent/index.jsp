<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<head>


<title>Memory</title>

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

<!-- <link rel='stylesheet' id='main-styles-css'
	href='http://www.seanhurley.com/wp-content/themes/salient/style.css?ver=3.5.1'
	type='text/css' media='all' /> -->

<link rel='stylesheet' id='orbit-css'
	href='http://www.seanhurley.com/wp-content/themes/salient/css/orbit.css?ver=3.5.1'
	type='text/css' media='all' />
<script type='text/javascript'
	src='http://www.seanhurley.com/wp-includes/js/jquery/jquery.js?ver=1.8.3'></script>
<script type='text/javascript'
	src='http://www.seanhurley.com/wp-content/themes/salient/js/modernizr.js?ver=2.6.2'></script>

	<!-- 이게 메뉴바야 -->
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

		var uid = document.getElementById("uid").value;
		var upassword = document.getElementById("upassword").value;

		httpRequest1 = getXMLHttpRequest();
		httpRequest1.onreadystatechange = loginCallbackFunction; // 콜백 함수 등록
		httpRequest1.open("POST", "login.jsp", true);
		httpRequest1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		httpRequest1.send("userId=" + uid + "&userPw=" + upassword);
	}

	function loginCallbackFunction() {
		if (httpRequest1.readyState == 4) {
			if (httpRequest1.status == 200) {//httpRequest1.status == 0인 경우 로컬 파일에서 
				var text = httpRequest1.responseText.trim();
				if (text == "yes") {
					document.getElementById("loginRow").removeChild(
							document.getElementById("loginForm"));
					var loginRow = document.getElementById("loginRow");
					loginRow.innerHTML = "<div id='logout'><a href='/memory/Service?action=logout'>logout</a></div>" + loginRow.innerHTML;
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

		<jsp:include page="/reference/header.jsp" flush="false" />

<div id="featured" data-bg-color="" data-slider-height="" data-animation-speed="" data-advance-speed="" data-autoplay="1"> 
	
		
					<div class="slide orbit-slide">
				
								<article style="background-image: url('http://www.seanhurley.com/wp-content/uploads/2013/04/slider-slider1.jpg')">
					<div class="container">
						<div class="col span_12">
							<div class="post-title">
								<h2><span>
									Clippr - Take photos of your favourite outfits, clip them to a retailer and share!								</span></h2>
								
																	
								 								 
							</div><!--/post-title-->
						</div>
					</div>
				</article>
			</div>
					<div class="slide orbit-slide">
				
								<article style="background-image: url('http://www.seanhurley.com/wp-content/uploads/2013/03/me-slider.png')">
					<div class="container">
						<div class="col span_12">
							<div class="post-title">
								<h2><span>
									When im not working, I'm taking photos. Check out my Instagram feed.								</span></h2>
								
								 								 
							</div><!--/post-title-->
						</div>
					</div>
				</article>
			</div>
					<div class="slide orbit-slide">
				
								<article style="background-image: url('http://www.seanhurley.com/wp-content/uploads/2013/04/mybike.jpg')">
					<div class="container">
						<div class="col span_12">
							<div class="post-title">
								<h2><span>
									Goal: Bike from Toronto to Kingston (2013) in one day. Total ride 349KM.								</span></h2>
								
																 
							</div><!--/post-title-->
						</div>
					</div>
				</article>
			</div>
					</div>


<div id="footer-outer">
	
			
	<div id="footer-widgets">
		
		<div class="container">
			
			<div class="row">
					<div class="col span_3">
						<!-- Footer widget area 3 -->
						<div id="recent-posts-3" class="widget widget_recent_entries">
							<h4>Memory</h4>
							<ul>
								<li>블라블라</a></li>

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
				
			</div><!--/row-->
			
		</div><!--/container-->
	
	</div><!--/footer-widgets-->
	
			
		<jsp:include page="reference/footer.jsp" flush="false" />
		
	
</div><!--/footer-outer-->


</body>
</html>