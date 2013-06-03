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



<title>마이 페이지</title>

<link
	href="http://cdn.kendostatic.com/2013.1.319/styles/kendo.common.min.css"
	rel="stylesheet" />
<link
	href="http://cdn.kendostatic.com/2013.1.319/styles/kendo.default.min.css"
	rel="stylesheet" />
<link href="CSS/default.css" rel="stylesheet" type="text/css">
<link href="CSS/main.css" rel="stylesheet" type="text/css">
<link href="CSS/mypage.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script src="http://cdn.kendostatic.com/2013.1.319/js/kendo.all.min.js"></script>


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


<script type="text/javascript">

	//trim 함수 제작
	//http://dragonraja2010.blogspot.kr/2011/01/trim.html
	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/gi, "");
	}

	var httpRequest2 = null;

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
	</script>
</head>

<body class="page page-id-18 page-template-default noise"
	data-smooth-scrolling="1">

	<jsp:include page="reference/header.jsp" flush="false" />


	<!-- 이것도 필수 글자 화면에 맞춰주는거 -->
	<div class="container main-content">

		<!-- 뭔지몰라도 구분짓는건가보임 -->
		<div class="row">

			<!--  중간에 다 넣을꺼 넣어봐 -->
			<div class="divider"></div>
			<div class="col span_4">
				<h2>My Story</h2>
				<p><%=session.getAttribute("userId") %>님 안녕하세요</p>
				<p>
				마이페이지 부분입니다.
				</p>
			</div>
			<div class="col span_8 col_last">
				<h2>My Calender</h2>

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
				
				//달력을 눌렀을시 상세페이지로 이동
				 $("#calendar").click(function() {
					var calendar = $("#calendar").data("kendoCalendar");
					var datavalue =  calendar.value();
					var uyear = datavalue.getFullYear();
					var umonth = datavalue.getMonth() + 1;
					var uday = datavalue.getDate();
					
					location.href = "getDetail.jsp?"+"year="+uyear+"&month="+umonth+"&day="+uday;					
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

						<h2>Gallery</h2>

						<div class="demo-section" style="width: 600px">
							<div style="margin: -6px 0 0 30px;">
								<form name="galleryForm" action="/memory/Service?action=gallery"
									method="post">
									<font style="font-size: 20px;">Start Date : </font><input
										id="start" name="startDate" value=""
										style="width: 140px; height: 30px;" /> <font
										style="font-size: 20px;">End Date : </font><input id="end"
										name="endDate" value="" style="width: 140px; height: 30px;" />
								</form>
							</div>
						</div>

						<!-- Footer widget area 3 -->
						<div id="recent-posts-3" class="widget widget_recent_entries">
							<div id="example" class="k-content">
								<script>	
		
			//연, 월, 일 전역변수 선언 -> 특정 일자 범위 내에 있는 포스팅만 출력하기 위해 사용되는 전역변수
			var currentTime, year, month;
			var startDate, endDate; // 20120203 포맷형태로 전역변수 저장
			var dataSource; // dataSource는 gallery에 표시할 데이터를 담는 역할을 한다.
			
				$(document).ready(function() {
					currentTime = new Date();
					//연 계산
					year = currentTime.getFullYear();
					//달 계산 -> MM 형태로 나타낼 수 없는가 -> 해결
					month = currentTime.getMonth() + 1;
					//일 계산 -> 사용안함, 아래 로직 확인
					//var day = currentTime.getDate();
					
					/*
						1. kendoDatePicker를 바꿀 때마다 해당 부분이 반영되어서 DB에서 데이터를 가지고 와야한다.
						2. change function에서 날짜가 바뀌면 날짜 데이터(startDate, endDate)가 XmlHttpRequest를 통해 gallery.jsp로 이동
						3. gallery.jsp에서 start, endDate를 getParameter를 통해서 DB에서 데이터를 가지고 와서 image, category 정보를 CSV형식으로 저장
						4. change function 부분에서 request 보내고 이를 받아서 바로 jquery이용하여 반영
						   setDatasource 후에 refresh로 새로운 Datasource를 로드함
					*/
					$("#start").kendoDatePicker({
						start: "month",
						depth: "year",
						format: "yyyy/MM/dd",
						
						change: function(e){
							//alert($('#start').attr("value"));
							//바꾼 날짜값이 전역 변수에 저장됨
							
							//startDate, endDate 계산 
							startDate = $('#start').attr("value");
							startDate = startDate.split('/')[0] + startDate.split('/')[1] + startDate.split('/')[2];
							endDate = $('#end').attr("value");
							endDate = endDate.split('/')[0] + endDate.split('/')[1] + endDate.split('/')[2];
							//alert("change : " + startDate + ", " + endDate);
							
							if(startDate > endDate){
								alert("날짜 범위가 잘못되었습니다.");
								return false;
							}
							
							//ajax 통신 시작
							httpRequest2 = getXMLHttpRequest();
							httpRequest2.onreadystatechange = galleryCallbackFunction; // 콜백 함수 등록
							httpRequest2.open("POST", "gallery.jsp", true);
							httpRequest2.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
							httpRequest2.send("startDate=" + startDate + "&endDate=" + endDate);
						}//function 마지막
					});	
					
					$("#end").kendoDatePicker({
						start: "month",
						depth: "year",
						format: "yyyy/MM/dd",
						
						change: function(e){
							//alert($('#end').attr("value"));
							//startDate, endDate 계산 
							startDate = $('#start').attr("value");
							startDate = startDate.split('/')[0] + startDate.split('/')[1] + startDate.split('/')[2];
							endDate = $('#end').attr("value");
							endDate = endDate.split('/')[0] + endDate.split('/')[1] + endDate.split('/')[2];
							//alert("change : " + startDate + ", " + endDate);
							
							if(startDate > endDate){
								alert("날짜 범위가 잘못되었습니다.");
								return false;
							}
							
							//ajax 통신 시작
							httpRequest2 = getXMLHttpRequest();
							httpRequest2.onreadystatechange = galleryCallbackFunction; // 콜백 함수 등록
							httpRequest2.open("POST", "gallery.jsp", true);
							httpRequest2.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
							httpRequest2.send("startDate=" + startDate + "&endDate=" + endDate);
						}//function 마지막
					});	
					
					//숫자 변환에 관한 정리
					//http://stackoverflow.com/questions/6040515/how-do-i-get-month-and-date-of-javascript-in-2-digit-format
					//$("#start").val(year+"/"+ month + "/" + new Date(year, month, 1).getDate());
					//$("#end").val(year+"/" + month + "/" + new Date(year, month, 0).getDate());
					
					$("#start").val(year+"/"+ ("0" + month).slice(-2) + "/" + ("0"+new Date(year, month, 1).getDate()).slice(-2) );
					$("#end").val(year+"/" + ("0" + month).slice(-2) + "/" + ("0"+new Date(year, month, 0).getDate()).slice(-2));
					
					//JSON 사용방법
					//http://stackoverflow.com/questions/2098276/nested-json-objects-do-i-have-to-use-arrays-for-everything
					var objResult; // JSON 객체를 담기위한 변수
					
					function parseData(strInfo){
						objResult = {};
						objResult = eval("("+strInfo+")");
					}
					
					function galleryCallbackFunction() {
						if (httpRequest2.readyState == 4) {
							//alert("4 진입");
							//alert(httpRequest2.status);
							if (httpRequest2.status == 200) {//httpRequest1.status == 0인 경우 로컬 파일에서 
								//alert(httpRequest2.responseText.trim());//데이터를 정상적으로 뽑아온다.
								var result = httpRequest2.responseText;
								parseData(result);
								//alert(objResult);
								
								//parseData 이후 objResult객체는 JSON 객체를 가지고 있으며, 이를 dataSource에 붓고 refresh하면 완료					
								//alert(objResult.post[0].image); 정상동작 확인
								
								//http://demos.kendoui.com/web/datasource/index.html - dataSource참고 필수
								//여기서 Datasource를 새롭게 정의하고 gallery 부분을 refresh하도록한다.
								//alert("JSON Object 개수 : " + objResult.length);
							
								dataSource = new kendo.data.DataSource({
									data: objResult,
									pageSize: 15
								});
							
								//기존의 dataSource를 새로운 dataSource로 교체
								$("#listView").data("kendoListView").setDataSource(dataSource);
							
								//실제 listView에 반영하기 위해 refresh() 사용
								$("#listView").data("kendoListView").refresh();
								
								dataSource.read();
							
							}//if문 마지막
						}//if문 마지막
					}//함수 마지막
					
					//startDate 및 endDate 세팅
					//startDate = year + ("0" + month).slice(-2) + ("0"+new Date(year, month, 1).getDate()).slice(-2);
					//endDate = year + ("0" + month).slice(-2) + ("0"+new Date(year, month, 0).getDate()).slice(-2);
				
					//처음 화면 뜰때, Datasource를 지정하여 제대로 된 데이터 출력하도록 한다.
					//날짜는 startDate, endDate 이용해서 여기서 일단 현재 날짜의 1 ~ 마지막날까지 한달간의 데이터를 가져와서 출력
					//이후에는 유저가 지정한 날짜에 대해서 이벤트 핸들러 형식으로 DataSource 부분을 계속 수정해서 refresh를 하도록 한다.
					//myPage로 처음 넘어왔을 때는 서블릿을 거쳐서 이동하므로, 서블릿에서 날에 맞는 1일~막일의 데이터 정보를 미리 request에 넣고 온다.
					
					//dataSource는 readyfunction 시작점에서 정의함
					dataSource = new kendo.data.DataSource({
						data:[<%ArrayList<Post> arrPost = null;
						      if(request.getAttribute("galleryData") == null){
						    	  //galleryData에 데이터가 없는경우, 아무것도 출력하지 않는다.
						    	  //arrPost = (ArrayList<Post>)PostService.getInstance().selectByUserId((String)session.getAttribute("userId"));
						      }else{//데이터가 있는경우, 해당 데이터를 출력하면 된다.(사진 및 category 출력)
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
				
				});//ready function 마지막
								
				
				//유저ID를 통해서 각각의 Post 객체에 대해서 image 획득
				//image 및 image 경로 확인
				
				//데이터 소스에 데이터를 넣을 때, 반드시 지정한 일자 사이에 해당하는 포스트만 뽑아서 나열해야함
				//즉, 날짜가 정해지면 그 날짜 사이의 데이터만 뽑아옴
				//http://docs.kendoui.com/api/framework/kendo -> template 개념 확실히 할 것
				//화면 제일 처음 로딩시 아래 ready function 기능이 수행되도록 해야함
				
<%-- 					$(document).ready(function(){		
						var dataSource = new kendo.data.DataSource({
							data:[<%ArrayList<Post> arrPost = null;
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
			        });//화면 처음 로딩시 이 과정이 수행된다.  --%>
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