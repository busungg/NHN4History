<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<head>
<title>Memory</title>

<link href="CSS/default.css" rel="stylesheet" type="text/css">
<link href="CSS/main.css" rel="stylesheet" type="text/css">
<link rel="alternate" type="application/rss+xml" title="Sean Hurley &raquo; Feed" href="http://www.seanhurley.com/feed/" />
<link rel="alternate" type="application/rss+xml" title="Sean Hurley &raquo; Comments Feed" href="http://www.seanhurley.com/comments/feed/" />
<link rel="alternate" type="application/rss+xml" title="Sean Hurley &raquo; Home Comments Feed" href="http://www.seanhurley.com/home/feed/" />
<link rel='stylesheet' id='jetpack_likes-css'  href='http://www.seanhurley.com/wp-content/plugins/jetpack/modules/likes/style.css?ver=2.2.5' type='text/css' media='all' />
<link rel='stylesheet' id='contact-form-7-css'  href='http://www.seanhurley.com/wp-content/plugins/contact-form-7/includes/css/styles.css?ver=3.4.1' type='text/css' media='all' />
<link rel='stylesheet' id='jetpack-widgets-css'  href='http://www.seanhurley.com/wp-content/plugins/jetpack/modules/widgets/widgets.css?ver=20121003' type='text/css' media='all' />
<link rel='stylesheet' id='rgs-css'  href='http://www.seanhurley.com/wp-content/themes/salient/css/rgs.css?ver=3.5' type='text/css' media='all' />
<link rel='stylesheet' id='font-awesome-css'  href='http://www.seanhurley.com/wp-content/themes/salient/css/font-awesome.min.css?ver=3.5' type='text/css' media='all' />
<!--[if lt IE 9]>
<link rel='stylesheet' id='ie8-css'  href='http://www.seanhurley.com/wp-content/themes/salient/css/ie8.css?ver=3.5' type='text/css' media='all' />
<![endif]-->
<link rel='stylesheet' id='orbit-css'  href='http://www.seanhurley.com/wp-content/themes/salient/css/orbit.css?ver=3.5' type='text/css' media='all' />
<link rel='stylesheet' id='widget-grid-and-list-css'  href='http://www.seanhurley.com/wp-content/plugins/jetpack/modules/widgets/widget-grid-and-list.css?ver=3.5' type='text/css' media='all' />
<script type='text/javascript' src='http://www.seanhurley.com/wp-includes/js/jquery/jquery.js?ver=1.8.3'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/plugins/jetpack/_inc/postmessage.js?ver=2.2.5'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/plugins/jetpack/_inc/jquery.inview.js?ver=2.2.5'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/plugins/jetpack/_inc/jquery.jetpack-resize.js?ver=2.2.5'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/modernizr.js?ver=2.6.2'></script>
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
		httpRequest1.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
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
					loginRow.innerHTML = "<div id='logout'><a href='/memory/Service?action=logout&mode=PC'>logout</a></div>"
							+ loginRow.innerHTML;
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

	<div id="featured" data-bg-color="" data-slider-height=""
		data-animation-speed="" data-advance-speed="" data-autoplay="1">


		<div class="slide orbit-slide">

			<article
				style="background-image: url('http://www.seanhurley.com/wp-content/uploads/2013/04/slider-slider1.jpg')">
				<div class="container">
					<div class="col span_12">
						<div class="post-title">
							<h2>
								<span> Clippr - Take photos of your favourite outfits,
									clip them to a retailer and share! </span>
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


	<div id="footer-outer">

		<div id="footer-widgets">

			<div class="container">

				<div class="row">
					<div class="col span_3">
						<!-- Footer widget area 3 -->
						<div id="recent-posts-3" class="widget widget_recent_entries">
							<h4>Memory</h4>
							<ul>
								<li>메모리란?</a></li>

							</ul>
						</div>
					</div>
					<!--/span_3-->

					<div class="col span_8 col_last">
						<!-- Footer widget area 3 -->
						<div id="recent-posts-3" class="widget widget_recent_entries">
							<h4>video</h4>
							<video src="video/nhn_ad.mp4" width="720px;" height="480px;"
								controls>
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


		<jsp:include page="reference/footer.jsp" flush="false" />


	</div>
	<!--/footer-outer-->
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-27219747-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script> 

	<div style="display:none">
	</div>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/plugins/contact-form-7/includes/js/jquery.form.min.js?ver=3.32.0-2013.04.03'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/plugins/contact-form-7/includes/js/scripts.js?ver=3.4.1'></script>
<script type='text/javascript' src='http://s0.wp.com/wp-content/js/devicepx-jetpack.js?ver=201324'></script>
<script type='text/javascript' src='http://s.gravatar.com/js/gprofiles.js?ver=2013Junaa'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/plugins/jetpack/modules/wpgroho.js?ver=3.5'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/superfish.js?ver=1.4.8'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/easing.js?ver=1.3'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/respond.js?ver=1.1'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/swipe.min.js?ver=1.6'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/nicescroll.js?ver=3.1'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/sticky.js?ver=1.0'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/prettyPhoto.js?ver=3.1.5'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/flexslider.min.js?ver=2.1'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/isotope.min.js?ver=1.5.25'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/carouFredSel.min.js?ver=6.2'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/jplayer.min.js?ver=2.1'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/orbit.js?ver=1.4'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/init.js?ver=1.0'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/nectar/love/js/nectar-love.js?ver=1.0'></script>


</body>
</html>