<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div id="header-space"></div>

<div id="header-outer" data-using-logo="1" data-logo-height="60"
	data-padding="" data-header-resize="">

	<div id="search-outer">

		<div id="search">

			<div class="container">

				<div id="search-box">

					<div class="col span_12">
						<form action="http://www.seanhurley.com" method="GET">
							<input type="text" name="s" value="Start Typing..."
								data-placeholder="Start Typing..." />
						</form>
					</div>
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
	<header id="top">

		<div class="container">

			<div class="row" id="loginRow">
				<!-- 로그인이 안되어 있다면, LOGIN, JOIN 부분 필요 로그인 되어 있다면, LOGOUT 부분 필요-->
				<%
					if (session.getAttribute("userId") == null) {
				%>
				<div id="loginForm" align="right">

					ID <input id="uid" name="uid" type="text" style="width: 100px"
						style="height:12px" /> &nbsp; &nbsp; 
					Password 
						<input id="upassword" name="upassword" type="password"
						style="width: 100px" style="height:12px" />
					
						<input type="button" class="medium button blue" value="login" onclick="sendLoginInfo()" />
						<input type="button" class="medium button blue" value="join"
						onclick="location.href='center/join.html'" />
				</div>
<!-- 						<input class="medium button blue">ID
						<a class="medium button blue">Password</a>
					</ul> -->
<style>

li {
	list-style: none;
	padding-top: 10px;
	padding-bottom: 10px;
}

.button,.button:visited {
	background: #222 url(overlay.png) repeat-x;
	display: inline-block;
	padding: 5px 10px 6px;
	color: #fff;
	text-decoration: none;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	-moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.6);
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.6);
	text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);
	border-bottom: 1px solid rgba(0, 0, 0, 0.25);
	position: relative;
	cursor: pointer
}

.button:hover {
	background-color: #111;
	color: #fff;
}

.button:active {
	top: 1px;
}

.button,.button:visited,.medium.button,.medium.button:visited {
	font-size: 13px;
	font-weight: bold;
	line-height: 1;
	text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);
}

.large.button,.large.button:visited {
	font-size: 14px;
	padding: 8px 14px 9px;
}

.super.button,.super.button:visited {
	font-size: 34px;
	padding: 8px 14px 9px;
}


.blue.button,.blue.button:visited {
	background-color: #2981e4;
}

.blue.button:hover {
	background-color: #2575cf;
}

</style>


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

					<a href="#" id="toggle-nav"></a>

					<nav>
						<ul class="sf-menu">
							<li id="menu-item-2625"
								class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2625"><a
								href="./">Home</a></li>
							<li id="menu-item-2625"
								class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2625"><a
								href="/memory/Service?action=shareList">Share</a></li>
							<li id="menu-item-2600"
								class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2600"><a
								href="/memory/Service?action=list">My Page</a></li>
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


<div id="mobile-menu">
	<div class="container">
		<ul>
			<li id="menu-item-16"
				class="menu-item menu-item-type-post_type menu-item-object-page current-menu-item page_item page-item-12 current_page_item menu-item-16"><a
				href="./">Home</a></li>
			<li id="menu-item-2625"
				class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2625"><a
				href="/memory/Service?action=shareList">Share</a></li>
			<li id="menu-item-2600"
				class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2600"><a
				href="/memory/Service?action=list">My Page</a></li>

		</ul>
	</div>
</div>