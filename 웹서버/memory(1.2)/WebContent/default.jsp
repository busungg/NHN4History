<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.security.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="model.dto.Post"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="service.PostService"%>
<%@page import="java.util.HashMap"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%
	System.out
	.println("mypage 세션값 : " + session.getAttribute("userId"));
	if (session.getAttribute("userId") != null) {
%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />



<title>Sean Hurley | About Me | Sean Hurley</title>

<link
	href="http://cdn.kendostatic.com/2013.1.319/styles/kendo.common.min.css"
	rel="stylesheet" />
<link
	href="http://cdn.kendostatic.com/2013.1.319/styles/kendo.default.min.css"
	rel="stylesheet" />
<link href="CSS/mypage.css" rel="stylesheet" type="text/css">
<link href="CSS/default.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script src="http://cdn.kendostatic.com/2013.1.319/js/kendo.all.min.js"></script>


<link rel='stylesheet' id='jetpack-widgets-css'  href='http://www.seanhurley.com/wp-content/plugins/jetpack/modules/widgets/widgets.css?ver=20121003' type='text/css' media='all' />
<link rel='stylesheet' id='rgs-css'  href='http://www.seanhurley.com/wp-content/themes/salient/css/rgs.css?ver=3.5.1' type='text/css' media='all' />
<link rel='stylesheet' id='responsive-css'  href='http://www.seanhurley.com/wp-content/themes/salient/css/responsive.css?ver=3.5.1' type='text/css' media='all' />
<link rel='stylesheet' id='main-styles-css'  href='http://www.seanhurley.com/wp-content/themes/salient/style.css?ver=3.5.1' type='text/css' media='all' />

<!-- 작은창 메뉴뜨게 하는거 -->
<script type='text/javascript' src='http://www.seanhurley.com/wp-includes/js/jquery/jquery.js?ver=1.8.3'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/plugins/jetpack/_inc/postmessage.js?ver=2.2.2'></script>

<!-- 이게 메뉴바야ㅑㅑㅑㅑㅑㅑ -->
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/superfish.js?ver=1.4.8'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/easing.js?ver=1.3'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/nicescroll.js?ver=3.1'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/prettyPhoto.js?ver=3.1.5'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/init.js?ver=1.0'></script>

</head>

<body class="page page-id-18 page-template-default noise"
	data-smooth-scrolling="1">

	<div id="header-space"></div>

	<div id="header-outer" data-using-logo="1" data-logo-height="40"
		data-padding="" data-header-resize="">

		<div id="search-outer">

			<div id="search">

				<div class="container">

					<div id="search-box">

						<!--  꼭 있어야해 메뉴바랑 밑에 구분지어줘 -->
						<div class="col span_12">
							<!-- 		      	<form action="http://www.seanhurley.com" method="GET">
			      		<input type="text" name="s" value="Start Typing..." data-placeholder="Start Typing..." />
			      	</form> -->
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
		<!-- 여기꺼는 바꾸지마 -->
		<header id="top">

			<div class="container">

				<div class="row">

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
					<div class="col span_3">
						<a id="logo" href="./"> <img alt="Sean Hurley"
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
									href="#">Solution</a></li>
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
				<li
					class="menu-item menu-item-type-post_type menu-item-object-page menu-item-16"><a
					href="http://www.seanhurley.com/">Home.</a></li>
				<li
					class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item menu-item-2614"><a
					href="/seanhurley">About Me.</a></li>
				<li
					class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2625"><a
					href="http://www.seanhurley.com/experience/">Experience.</a></li>
				<li
					class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2600"><a
					href="http://www.seanhurley.com/services/">Services.</a></li>
				<li
					class="menu-item menu-item-type-post_type menu-item-object-page menu-item-216"><a
					href="http://www.seanhurley.com/blog/">Blog.</a></li>
				<li
					class="menu-item menu-item-type-post_type menu-item-object-page menu-item-217"><a
					href="http://www.seanhurley.com/contact/">Contact.</a></li>

			</ul>
		</div>
	</div>


	<!-- 이것도 필수 글자 화면에 맞춰주는거 -->
	<div class="container main-content">

		<!-- 뭔지몰라도 구분짓는건가보임 -->
		<div class="row">

			<!--  중간에 다 넣을꺼 넣어봐 -->
			<div class="divider"></div>
			<div class="col span_4">
				<h2>My Story</h2>
				<p>Creative, highly energetic Senior Online Marketing Manager
					with a wide range of experience in branding, Content management
					systems, website design, ecommerce development, and project
					management. Extensive experience in Email marketing and Social
					Media campaigns with full follow up analytic diagnostics. Strong
					skill set in Search engine optimization and other forms of online
					growth management.</p>
				<p>
					I&#8217;ve successfully launched and operated a freelance online
					marketing consultancy for the past seven years. My consulting
					services have taken a number of major online retailers from a loss
					year over year, to positive two digits results. In the past month,
					I&#8217;ve launched a new start up called &#8220;<a
						title="Clippr - Shop Socially!" href="http://clippr.co"
						target="_blank"><strong>Clippr</strong></a>&#8220;.
				</p>
			</div>
			<div class="col span_8 col_last">
				<h2>My Skills</h2>

				달력부분?

				<div id="calendar"></div>

				<script>
				$(document)
						.ready(
								function() {
									
									var today = new Date(),
									events = [
									
									// 이벤트리스트를 가져온다.
									// events에 배열을 저장한다.
									<%List eventList = (List)PostService.getInstance().selectByUserId((String)session.getAttribute("userId"));
				Post event = null;

				for (int i = 0; i < eventList.size(); i++) {
					event = (Post) eventList.get(i);%>
									 <%out.println("+new Date(");%>
									 <%out.println(event.getUptime().substring(0, 4));%>, 
									 <%out.println(Integer.parseInt(event.getUptime()
							.substring(5, 7).replace("0", "")) - 1);%>, 
									 <%out.println(event.getUptime().substring(8, 10)
							.replace("0", ""));%>
									 <%out.println(")");%>
									 <%if (i != eventList.size() - 1)
						out.println(",");%>
									<%}%>
									 ];
									
									$("#calendar")
											.kendoCalendar(
													{
														value : today,
														dates : events,
														month : {
															// template for dates in month view
															content : '# if ($.inArray(+data.date, data.dates) != -1) { #'
																	+ '<div class="'
																	+ '# if (data.value < 10) { #'
																	+ "party"
																	+ '# } else if ( data.value < 20 ) { #'
																	+ "party"
																	+ '# } else { #'
																	+ "party"
																	+ '# } #'
																	+ '">#= data.value #</div>'
																	+ '# } else { #'
																	+ '#= data.value #'
																	+ '# } #'
														},
														footer : false
													});
								});
			</script>


			</div>


			<!-- 	<div class="clear"></div> -->



		</div>
		<!--/row-->

	</div>
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
							<h4>Gallery</h4>

							<div id="example" class="k-content">
								<div class="demo-section" style="width: 600px">
									<div style="margin-top: -6px;">
										<form name="galleryForm"
											action="/memory/Service?action=gallery" method="post">
											Start Date : <input id="start" name="startDate" value=""
												style="width: 150px;" /> End Date : <input id="end"
												name="endDate" value="" style="width: 150px;" /> <input
												type="button" value="조회" onclick="check();" />
										</form>
									</div>
								</div>
							</div>

						<script>	
			function check(){
				alert("check 로직 진입");
				
				//UserId 정보 및 datepicker의 startDate, endDate를 받아서 서버로 전송 (GalleryCommand)
				//서블릿에서 galleryData로 request에 저장한 다음 여기서 for구문으로 galleryData로 뽑아냄
				
				startDate = $('#start').attr("value");
				//alert(startDate.split('/')[0]+startDate.split('/')[1]+startDate.split('/')[2]);
				startDate = startDate.split('/')[0] + startDate.split('/')[1] + startDate.split('/')[2];
				$('#start').val(startDate);
				
				endDate = $('#end').attr("value");
				//alert(endDate.split('/')[0]+endDate.split('/')[1]+endDate.split('/')[2]);
				endDate = endDate.split('/')[0] + endDate.split('/')[1] + endDate.split('/')[2];
				$('#end').val(endDate);
				
				document.galleryForm.submit();
			}//check 함수 마지막
		
			//연, 월, 일 전역변수 선언 -> 특정 일자 범위 내에 있는 포스팅만 출력하기 위해 사용되는 전역변수
			var currentTime, year, month;
			var startDate, endDate; // 20120203 포맷형태로 전역변수 저장
			
				$(document).ready(function() {
					currentTime = new Date();
					//연 계산
					year = currentTime.getFullYear();
					//달 계산 -> MM 형태로 나타낼 수 없는가 -> 해결
					month = currentTime.getMonth() + 1;
					//일 계산 -> 사용안함, 아래 로직 확인
					//var day = currentTime.getDate();

					$("#start").kendoDatePicker({
						start: "month",
						depth: "year",
						format: "yyyy/MM/dd",
						
						change: function(e){
							//alert($('#start').attr("value"));
							//바꾼 날짜값이 전역 변수에 저장됨
							startDate = $('#start').attr("value");
							//alert(startDate.split('/')[0]+startDate.split('/')[1]+startDate.split('/')[2]);
							startDate = startDate.split('/')[0] + startDate.split('/')[1] + startDate.split('/')[2];
						}//function 마지막
					});	
					
					$("#end").kendoDatePicker({
						start: "month",
						depth: "year",
						format: "yyyy/MM/dd",
						
						change: function(e){
							//alert($('#end').attr("value"));
							endDate = $('#end').attr("value");
							//alert(endDate.split('/')[0]+endDate.split('/')[1]+endDate.split('/')[2]);
							endDate = endDate.split('/')[0] + endDate.split('/')[1] + endDate.split('/')[2];
						}//function 마지막
					});	
					
					//http://stackoverflow.com/questions/6040515/how-do-i-get-month-and-date-of-javascript-in-2-digit-format
					//$("#start").val(year+"/"+ month + "/" + new Date(year, month, 1).getDate());
					//$("#end").val(year+"/" + month + "/" + new Date(year, month, 0).getDate());
					
					$("#start").val(year+"/"+ ("0" + month).slice(-2) + "/" + ("0"+new Date(year, month, 1).getDate()).slice(-2) );
					$("#end").val(year+"/" + ("0" + month).slice(-2) + "/" + ("0"+new Date(year, month, 0).getDate()).slice(-2));

					//startDate 및 endDate 세팅
					//startDate = year + ("0" + month).slice(-2) + ("0"+new Date(year, month, 1).getDate()).slice(-2);
					//endDate = year + ("0" + month).slice(-2) + ("0"+new Date(year, month, 0).getDate()).slice(-2);
				}); 
		</script>

						<div class="demo-section2">
							<div id="listView"></div>
							<div id="pager" class="k-pager-wrap"></div>
						</div>

						<!-- http://docs.kendoui.com/api/framework/kendo 여기서 해결방법 찾을 것-->
						<script type="text/x-kendo-tmpl" id="template">
        <div class="product">
            <img src="upload/#: imageName #" alt="#: imageName #" />
			<p>#: category #</p>
        </div>
    </script>

						<script>
	//유저ID를 통해서 각각의 Post 객체에 대해서 image 획득
	//image 및 image 경로 확인
	
	//데이터 소스에 데이터를 넣을 때, 반드시 지정한 일자 사이에 해당하는 포스트만 뽑아서 나열해야함
	//즉, 날짜가 정해지면 그 날짜 사이의 데이터만 뽑아옴
	//http://docs.kendoui.com/api/framework/kendo -> template 개념 확실히 할 것
		
		$(document).ready(function(){		
			var dataSource = new kendo.data.DataSource({
				data:[<%
				      ArrayList<Post> arrPost = null;
				      if(request.getAttribute("galleryData") == null){
				    	  arrPost = (ArrayList<Post>)PostService.getInstance().selectByUserId((String)session.getAttribute("userId"));
				      }else{
				    	  arrPost = (ArrayList<Post>)request.getAttribute("galleryData");
				      }
				      	for(int i = 0; i < arrPost.size(); i++){
				      	System.out.println(arrPost.get(i).getImage());
				      	System.out.println(arrPost.get(i).getCategory());%>
					 {imageName: "<%=arrPost.get(i).getImage()%>", category: "<%=arrPost.get(i).getCategory()%>"},
					<%}%>	
					],
					pageSize: 15
			});

            $("#pager").kendoPager({
                dataSource: dataSource
            });

            $("#listView").kendoListView({
                dataSource: dataSource,
				template: kendo.template($("#template").html())
            }); 
        });
    </script>
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
				<p>			All contents Copyright 2013 memory Inc. all rights reserved
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



</body>
</html>

<%
	} else {
%><!-- 로그인 로직중 if 부분 마지막 -->
<script>
alert("로그인이 필요한 서비스입니다.");
history.go(-1);
</script>
<%
	}
%>