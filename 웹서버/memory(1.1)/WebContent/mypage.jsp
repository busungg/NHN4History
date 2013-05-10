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
<!DOCTYPE html>
<!-- 로그인 검증 로직 포함 -->
<%
	System.out
	.println("mypage 세션값 : " + session.getAttribute("userId"));
	if (session.getAttribute("userId") != null) {
%>

<html>
<head>
<meta charset="utf-8">
<title>마이페이지</title>
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

<!-- 메뉴 -->
<link rel="alternate" type="application/rss+xml" title="Sean Hurley &raquo; Feed" href="http://www.seanhurley.com/feed/" />
<link rel="alternate" type="application/rss+xml" title="Sean Hurley &raquo; Comments Feed" href="http://www.seanhurley.com/comments/feed/" />
<link rel="alternate" type="application/rss+xml" title="Sean Hurley &raquo; Home Comments Feed" href="http://www.seanhurley.com/home/feed/" />
<link rel='stylesheet' id='jetpack_likes-css'  href='http://www.seanhurley.com/wp-content/plugins/jetpack/modules/likes/style.css?ver=2.2.2' type='text/css' media='all' />
<link rel='stylesheet' id='contact-form-7-css'  href='http://www.seanhurley.com/wp-content/plugins/contact-form-7/includes/css/styles.css?ver=3.4' type='text/css' media='all' />
<link rel='stylesheet' id='jetpack-widgets-css'  href='http://www.seanhurley.com/wp-content/plugins/jetpack/modules/widgets/widgets.css?ver=20121003' type='text/css' media='all' />
<link rel='stylesheet' id='rgs-css'  href='http://www.seanhurley.com/wp-content/themes/salient/css/rgs.css?ver=3.5.1' type='text/css' media='all' />
<link rel='stylesheet' id='responsive-css'  href='http://www.seanhurley.com/wp-content/themes/salient/css/responsive.css?ver=3.5.1' type='text/css' media='all' />
<link rel='stylesheet' id='font-awesome-css'  href='http://www.seanhurley.com/wp-content/themes/salient/css/font-awesome.min.css?ver=3.5.1' type='text/css' media='all' />
<link rel='stylesheet' id='main-styles-css'  href='http://www.seanhurley.com/wp-content/themes/salient/style.css?ver=3.5.1' type='text/css' media='all' />
<!--[if lt IE 9]>
<link rel='stylesheet' id='ie8-css'  href='http://www.seanhurley.com/wp-content/themes/salient/css/ie8.css?ver=3.5.1' type='text/css' media='all' />
<![endif]-->
<link rel='stylesheet' id='orbit-css'  href='http://www.seanhurley.com/wp-content/themes/salient/css/orbit.css?ver=3.5.1' type='text/css' media='all' />
<link rel='stylesheet' id='dynamic_colors-css'  href='http://www.seanhurley.com/wp-content/themes/salient/css/colors.css?ver=3.5.1' type='text/css' media='all' />
<link rel='stylesheet' id='custom_css-css'  href='http://www.seanhurley.com/wp-content/themes/salient/css/custom.css?ver=3.5.1' type='text/css' media='all' />
<link rel='stylesheet' id='widget-grid-and-list-css'  href='http://www.seanhurley.com/wp-content/plugins/jetpack/modules/widgets/widget-grid-and-list.css?ver=3.5.1' type='text/css' media='all' />
<script type='text/javascript' src='http://www.seanhurley.com/wp-includes/js/jquery/jquery.js?ver=1.8.3'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/plugins/jetpack/_inc/postmessage.js?ver=2.2.2'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/plugins/jetpack/_inc/jquery.inview.js?ver=2.2.2'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/plugins/jetpack/_inc/jquery.jetpack-resize.js?ver=2.2.2'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/modernizr.js?ver=2.6.2'></script>
<link rel="EditURI" type="application/rsd+xml" title="RSD" href="http://www.seanhurley.com/xmlrpc.php?rsd" />
<link rel="wlwmanifest" type="application/wlwmanifest+xml" href="http://www.seanhurley.com/wp-includes/wlwmanifest.xml" /> 
<link rel='prev' title='Experience.' href='http://www.seanhurley.com/experience/' />
<link rel='next' title='Blog' href='http://www.seanhurley.com/blog/' />
<meta name="generator" content="WordPress 3.5.1" />
<link rel='shortlink' href='http://wp.me/P3lFQM-c' />
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/plugins/jetpack/modules/wpgroho.js?ver=3.5.1'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/superfish.js?ver=1.4.8'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/easing.js?ver=1.3'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/respond.js?ver=1.1'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/swipe.min.js?ver=1.6'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/nicescroll.js?ver=3.1'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/prettyPhoto.js?ver=3.1.5'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/flexslider.min.js?ver=2.1'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/isotope.min.js?ver=1.5.25'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/carouFredSel.min.js?ver=6.2'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/jplayer.min.js?ver=2.1'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/orbit.js?ver=1.4'></script>
<script type='text/javascript' src='http://www.seanhurley.com/wp-content/themes/salient/js/init.js?ver=1.0'></script>
<!-- 메뉴 바 -->

</head>
<body
	class="home page page-id-12 page-template page-template-template-home-3-php noise"
	data-smooth-scrolling="1">

	<header>

		<div id="header-space"></div>

		<div id="header-outer" data-using-logo="1" data-logo-height="100"
			data-padding="" data-header-resize="">

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
							<a id="logo" href="./"> <img
								alt="Sean Hurley" src="./images/memory.png"  heigth="70px"/>
							</a>
						</div>
						<!--/span_3-->

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


	
<!-- 
		 선 그어주네
		<div class="divider-border"></div>

 -->
		<!-- 스크롤바 -->
		<div id="footer-outer"></div>


	<div id="wrap">

		<!-- 달력 -->
		<h1>Calendar</h1>
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

		<div class="clear"></div>

		<!-- 갤러리 부분  -->
		<h1>Gallery</h1>

		<div id="example" class="k-content">
			<div class="demo-section" style="width: 600px">
				<div style="margin-top: -6px;">
					<form name="galleryForm" action="/memory/Service?action=gallery"
						method="post">
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