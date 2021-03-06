<%@page import="java.util.Date"%>
<%@page import="model.dto.Post"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko" xmlns:v="urn:schemas-microsoft-com:vml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>공유 상세페이지</title>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />

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

<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>


</head>
<body>
	<jsp:include page="reference/header.jsp" flush="false" />

	<div id="all" align="center">

		<div id="tabs"
			style="width: 800px; text-align: center; margin-top: 20px;"
			align="center">
		<ul>
			<%
				List list = (List) request.getAttribute("detailData");
				Post data;

				data = (Post) list.get(0);

				//facebook 좋아요 연동을 위한 작업
				String userId = data.getUserid();
				String year = data.getUptime().split(" ")[0].split("-")[0];
				String month = data.getUptime().split(" ")[0].split("-")[1];
				String day = data.getUptime().split(" ")[0].split("-")[2];

				if (month.length() != 2) {
					month = "0" + month;
				}

				if (day.length() != 2) {
					day = "0" + day;
				}

				String startDate = year + month + day + "000000";
				String endDate = year + month + day + "235959";

				for (int i = 0; i < list.size(); i++) {
					data = (Post) list.get(i);
			%>

			<!-- tab부분 이미지와 타이틀을 보여준다. -->
			<%="<li><a href=#tabs-" + i + ">"%>
			<img src=<%="upload/" + data.getImage()%> width="100" height="100" />
			<br>
			<center><%=data.getTitle()%></center>
			<%="</a></li>"%>

			<%
				}
			%>
		</ul>

		<%
			for (int i = 0; i < list.size(); i++) {
				data = (Post) list.get(i);
		%>

		<%="<div id=tabs-" + i + ">" + "<p>"%>
		<%=data.getText()%>
		<%=data.getUptime() + " " + data.getCategory()%>
		<%="</p> </div>"%>

		<%
			}
		%>
	</div>

	<!-- 맵부분 -->
	<div id="map"
			style="width: 900px; text-align: center; margin-top: 20px;"
			align="center">

		<script type="text/javascript"
			src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=b60fa1bbda6024ee3cd76d495c6f82d4"></script>
		<div id="map" style="border: 1px solid #000;"></div>
		<script type="text/javascript">
			<%data = (Post) list.get(0);%>
		
			var oSeoulCityPoint = new nhn.api.map.LatLng("" + <%=data.getLatitude()%>, "" + <%=data.getLongitude()%>);
			
			var defaultLevel = 11;
			var oMap = new nhn.api.map.Map(document.getElementById('map'), {
				point : oSeoulCityPoint,
				zoom : defaultLevel,
				enableWheelZoom : true,
				enableDragPan : true,
				enableDblClickZoom : false,
				mapMode : 0,
				activateTrafficMap : false,
				activateBicycleMap : false,
				minMaxLevel : [ 1, 14 ],
				//맵사이즈
				size : new nhn.api.map.Size(800, 480)
			});
			var oSlider = new nhn.api.map.ZoomControl();
			oMap.addControl(oSlider);
			oSlider.setPosition({
				top : 10,
				left : 10
			});

			var oMapTypeBtn = new nhn.api.map.MapTypeBtn();
			oMap.addControl(oMapTypeBtn);
			oMapTypeBtn.setPosition({
				bottom : 10,
				right : 80
			});

			var oThemeMapBtn = new nhn.api.map.ThemeMapBtn();
			oThemeMapBtn.setPosition({
				bottom : 10,
				right : 10
			});
			oMap.addControl(oThemeMapBtn);

			var oSize = new nhn.api.map.Size(28, 37);
			var oOffset = new nhn.api.map.Size(14, 37);
			var oIcon = new nhn.api.map.Icon(
					'http://static.naver.com/maps2/icons/pin_spot2.png', oSize,
					oOffset);

			//상세창 윈도우
			var oInfoWnd = new nhn.api.map.InfoWindow();
			oInfoWnd.setVisible(false);
			oMap.addOverlay(oInfoWnd);
			
			oInfoWnd.setPosition({
				top : 20,
				left : 20
			});

			var oLabel = new nhn.api.map.MarkerLabel(); // - 마커 라벨 선언.
			oMap.addOverlay(oLabel); // - 마커 라벨 지도에 추가. 기본은 라벨이 보이지 않는 상태로 추가됨.

			oInfoWnd.attach('changeVisible', function(oCustomEvent) {
				if (oCustomEvent.visible) {
					oLabel.setVisible(false);
				}
			});

			var oPolyline = new nhn.api.map.Polyline([], {
				strokeColor : '#a11', // - 선의 색깔
				strokeWidth : 3, // - 선의 두께
				strokeOpacity : 0.5
			// - 선의 투명도
			}); // - polyline 선언, 첫번째 인자는 선이 그려질 점의 위치. 현재는 없음.
			oMap.addOverlay(oPolyline); // - 지도에 선을 추가함.

			oMap.attach('mouseenter', function(oCustomEvent) {

				var oTarget = oCustomEvent.target;
				// 마커위에 마우스 올라간거면
				if (oTarget instanceof nhn.api.map.Marker) {
					var oMarker = oTarget;
					oLabel.setVisible(true, oMarker); // - 특정 마커를 지정하여 해당 마커의 title을 보여준다.
				}
			});

			oMap.attach('mouseleave', function(oCustomEvent) {

				var oTarget = oCustomEvent.target;
				// 마커위에서 마우스 나간거면
				if (oTarget instanceof nhn.api.map.Marker) {
					oLabel.setVisible(false);
				}
			});
			
			//마커 클릭
			oMap
					.attach(
							'click',
							function(oCustomEvent) {
								var oPoint = oCustomEvent.point;
								var oTarget = oCustomEvent.target;
								oInfoWnd.setVisible(false);
								// 마커 클릭하면
								if (oTarget instanceof nhn.api.map.Marker) {
									var oMarker = oTarget;
									oMarker.setVisible(false);
									
									var aPoints = oPolyline.getPoints(); // - 현재 폴리라인을 이루는 점을 가져와서 배열에 저장.
									
									//저장되어있는 위치중 찍었던 마커의 좌표가 같은것이 있는지 확인한다.
									for(var i in aPoints) { 
									    if(aPoints[i].toString() == oMarker.getPoint().toString())
									    	aPoints.splice(i,1);
									}
									//좌표 배열을 다시 추가한다.
									oPolyline.setPoints(aPoints);// - 해당 폴리라인에 배열에 저장된 점을 추가함
								}
								
								else
								{
									//추가하는 부분
									var oMarker = new nhn.api.map.Marker(oIcon, {
										title : '장소 : ' + oPoint.toString()
									});
									
									//마커를 추가하는 부분이다.
									oMarker.setPoint(oPoint);
									oMap.addOverlay(oMarker);
	
									var aPoints = oPolyline.getPoints(); // - 현재 폴리라인을 이루는 점을 가져와서 배열에 저장.
									aPoints.push(oPoint); // - 추가하고자 하는 점을 추가하여 배열로 저장함.
									oPolyline.setPoints(aPoints);// - 해당 폴리라인에 배열에 저장된 점을 추가함
								}
								
							});
			
			
			
			//마커를 추가하는 부분
			<%for (int i = 0; i < list.size(); i++) {
				data = (Post) list.get(i);%>
			
			var oPoint2 = new nhn.api.map.LatLng("" + <%=data.getLatitude()%>, "" + <%=data.getLongitude()%>);
			
			//설명부분
			var oTitle = "<%=data.getTitle()%>" ;
			var oShopname = "<%=data.getShopname()%>";
			var oCategory = "<%=data.getCategory()%>";

			var oMarker2 = new nhn.api.map.Marker(oIcon, {
				title : '제목 : ' + oTitle.toString() + ' 장소 : '
						+ oShopname.toString() + ' 종류 : '
						+ oCategory.toString()
			});

			//마커를 추가하는 부분이다.
			oMarker2.setPoint(oPoint2);
			oMap.addOverlay(oMarker2);

			var aPoints2 = oPolyline.getPoints(); // - 현재 폴리라인을 이루는 점을 가져와서 배열에 저장.
			aPoints2.push(oPoint2); // - 추가하고자 하는 점을 추가하여 배열로 저장함.
			oPolyline.setPoints(aPoints2);// - 해당 폴리라인에 배열에 저장된 점을 추가함
		<%}%>
			
		</script>
	</div>

	<div id="facebook" align="center" style="margin-top: 30px;">
		<div id="fb-root"></div>
		<script>
			(function(d, s, id) {
				var js, fjs = d.getElementsByTagName(s)[0];
				if (d.getElementById(id))
					return;
				js = d.createElement(s);
				js.id = id;
				js.src = "//connect.facebook.net/ko_KR/all.js#xfbml=1";
				fjs.parentNode.insertBefore(js, fjs);
			}(document, 'script', 'facebook-jssdk'));
		</script>

		<!--  "http://155.230.52.63:8080/memory/Service?action=sharedetail&userId=<%=userId%>&startDate=<%=startDate%>&endDate=<%=endDate%>" -->
		<div class="fb-like"
			data-href="http://troll.kert.or.kr:8080/memory/Service?action=sharedetail&userId=<%=userId%>&startDate=<%=startDate%>&endDate=<%=endDate%>"
			data-send="false" data-width="450" data-show-faces="true"></div>
	</div>
	
</div>
	
	<div id="footer-outer">	
		<jsp:include page="reference/footer.jsp" flush="false" />
	</div><!--/footer-outer-->
		
	
</div><!--/footer-outer-->

</body>
</html>