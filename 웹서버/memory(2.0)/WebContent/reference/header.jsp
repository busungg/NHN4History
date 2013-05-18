<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<div id="header-space"></div>

<div id="header-outer" data-using-logo="1" data-logo-height="60" data-padding="" data-header-resize="">
	
	<div id="search-outer">
		
	<div id="search">
	  	 
		<div class="container">
		  	 	
		     <div id="search-box">
		     	
		     	<div class="col span_12">
			      	<form action="http://www.seanhurley.com" method="GET">
			      		<input type="text" name="s" value="Start Typing..." data-placeholder="Start Typing..." />
			      	</form>
		        </div><!--/span_12-->
			      
		     </div><!--/search-box-->
		     
		     <div id="close"><a href=""></a></div>
		     
		 </div><!--/container-->
	    
	</div><!--/search-->
	  
</div><!--/search-outer-->	
	<header id="top">
		
		<div class="container">
			
			<div class="row" id="loginRow">
			<!-- 로그인이 안되어 있다면, LOGIN, JOIN 부분 필요 로그인 되어 있다면, LOGOUT 부분 필요-->
			<%
				if (session.getAttribute("userId") == null) {
			%>
			<div id="loginForm" align="right">

				ID <input id="uid" name="uid" type="text" style="width: 100px"
					style="height:10px" /> &nbsp; &nbsp; Password <input id="upassword"
					name="upassword" type="password" style="width: 100px"
					style="height:10px" /> <input type="button" value="로그인"
					onclick="sendLoginInfo()" />

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
					<a id="logo" href="./"> <img alt="memory" src="./images/memory.png" />				</a>
				</div><!--/span_3-->
				
				<div class="col span_9 col_last">
					
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
					
				</div><!--/span_9-->
			
			</div><!--/row-->
			
		</div><!--/container-->
		
	</header>

</div><!--/header-outer-->


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
							href="/memory/Service?action=shareList">Solution</a></li>
						<li id="menu-item-2600"
							class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2600"><a
							href="/memory/Service?action=list">My Page</a></li>
						<li id="menu-item-216"
							class="menu-item menu-item-type-post_type menu-item-object-page menu-item-216"><a
							href="center/qna.html">Q & A</a></li>
		
		</ul>
	</div>
</div>