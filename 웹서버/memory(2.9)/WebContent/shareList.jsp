<%@page import="model.dto.Share"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>공유 페이지</title>

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

<!-- kendo ui -->
<link
	href="http://cdn.kendostatic.com/2013.1.514/styles/kendo.common.min.css"
	rel="stylesheet" />
<link
	href="http://cdn.kendostatic.com/2013.1.514/styles/kendo.default.min.css"
	rel="stylesheet" />
<link href="/content/shared/styles/examples.css?20121115"
	rel="stylesheet" />
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="http://cdn.kendostatic.com/2013.1.514/js/kendo.all.min.js"></script>

<script type="text/javascript">
	//trim 함수 제작
	//http://dragonraja2010.blogspot.kr/2011/01/trim.html
	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/gi, "");
	}

	var httpRequest3 = null;

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

		httpRequest3 = getXMLHttpRequest();
		httpRequest3.onreadystatechange = loginCallbackFunction; // 콜백 함수 등록
		httpRequest3.open("POST", "login.jsp", true);
		httpRequest3.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		httpRequest3.send("userId=" + uid + "&userPw=" + upassword);
	}

	function loginCallbackFunction() {
		if (httpRequest3.readyState == 4) {
			if (httpRequest3.status == 200) {//httpRequest3.status == 0인 경우 로컬 파일에서 
				var text = httpRequest3.responseText.trim();
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
<body>

	<jsp:include page="reference/header.jsp" flush="false" />


	<div id="share" align="center" style="margin: 10px;">
		<!-- 리스트 -->
		<div id="example" class="k-content">

			<div class="demo-section">
				<h2>공유 리스트</h2>
				<div id="listView"></div>
				<div id="pager" class="k-pager-wrap"></div>
			</div>

		</div>

		<script type="text/x-kendo-tmpl" id="template">
        <div class="product">
            <img src="upload/#:image#" alt="#:id# image" />
            <h3>#:id# 님의 <br> #:year#년 &nbsp;  #:month#월 &nbsp; #:day#일 <br> 메모리</h3>
        </div>
    </script>

		<script>
       	$(document).ready(function() {
       		
       		var shareDataArray = new Array();
       		
       		//json 작업
       		<%List list = (List) request.getAttribute("shareAll");
			Share data;

			for (int i = 0; i < list.size(); i++) {

				data = (Share) list.get(i);

				String id = data.getUserid();
				String year = data.getSyear();
				String month = data.getSmonth();
				String day = data.getSday();
				String image = data.getSimage();
				
				if (month.length() != 2) {
					month = "0" + month;
				}

				if (day.length() != 2) {
					day = "0" + day;
				}

				String startDate = year + month + day + "000000";
				String endDate = year + month + day + "235959";%>
             
			var userid = "<%=id%>";
       		var startDate = "<%=startDate%>";
       		var endDate = "<%=endDate%>";
       		var simage = "<%=image%>";
       		var uyear = "<%=year%>";
       		var umonth = "<%=month%>";
       		var uday = "<%=day%>";
       		
			var shareData = {
				id : userid,
				start : startDate,
				end : endDate,
				image : simage,
				year : uyear,
				month : umonth,
				day : uday
			};

			shareDataArray.push(shareData);
		<%}%>
			var sharedDataSource = new kendo.data.DataSource(
										{
											data : shareDataArray,
											pageSize : 9
										});

								$("#listView").kendoListView(
										{
											dataSource : sharedDataSource,
											selectable : "single",
											change : onChange,
											template : kendo.template($(
													"#template").html())
										});

								$("#pager").kendoPager({
									dataSource : sharedDataSource
								});

								function onChange() {
									var data = sharedDataSource.view(), selected = $
											.map(
													this.select(),
													function(item) {
														location.href = "/memory/Service?action=sharedetail&userId="
																+ data[$(item)
																		.index()].id
																+ "&startDate="
																+ data[$(item)
																		.index()].start
																+ "&endDate="
																+ data[$(item)
																		.index()].end;
													});
								}
							});
		</script>

		<style scoped>
.demo-section {
	padding: 15px;
	width: 692px;
}

.demo-section h2 {
	font-size: 1.2em;
	margin-bottom: 10px;
	text-transform: uppercase;
}

.demo-section .console {
	margin: 0;
}

.product {
	float: left;
	width: 220px;
	height: 110px;
	margin: 0;
	padding: 5px;
	cursor: pointer;
}

.product img {
	float: left;
	width: 100px;
	height: 100px;
}

.product h3 {
	margin: 0;
	padding: 10px 0 0 10px;
	font-size: .9em;
	overflow: hidden;
	font-weight: normal;
	float: left;
	max-width: 100px;
	text-transform: uppercase;
}

.k-pager-wrap {
	border-top: 0;
}

.k-listview:after {
	content: ".";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}

.k-listview {
	padding: 0;
	min-width: 690px;
	min-height: 360px;
}
</style>
	</div>


	<!-- 푸터 -->
	<div id="footer-outer">
		<jsp:include page="reference/footer.jsp" flush="false" />
	</div>
	<!--/footer-outer-->

</body>
</html>