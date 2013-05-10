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
<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script src="http://cdn.kendostatic.com/2013.1.319/js/kendo.all.min.js"></script>
</head>
<body>
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
	</div>

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