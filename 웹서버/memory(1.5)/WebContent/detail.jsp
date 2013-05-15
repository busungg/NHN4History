<%@page import="model.dto.Post"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko" xmlns:v="urn:schemas-microsoft-com:vml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>상세페이지</title>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />

<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>

</head>
<body>

	<div id="tabs">
		<ul>
			<%
				List list = (List) request.getAttribute("detailData");
				Post data;

				for (int i = 0; i < list.size(); i++) {
					data = (Post) list.get(i);
			%>

			<%="<li><a href=#tabs-" + i + ">"%><%=data.getTitle()%><%="</a></li>"%>

			<%
				}
			%>
		</ul>

		<%
			for (int i = 0; i < list.size(); i++) {
				data = (Post) list.get(i);
		%>

		<%="<div id=tabs-" + i + ">" + "<p>"%>
		<img src=<%="upload/" + data.getImage()%> width="100" height="100"><br>
		<%=data.getUptime() + " " + data.getCategory()%>
		<%="</p> </div>"%>

		<%
			}
		%>
	</div>

	<!-- 맵부분 -->
	<div id="map">

		<script type="text/javascript"
			src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=7939d865a2c45d3d933694f2ddbf9c59"></script>
		<div id="map" style="border: 1px solid #000;"></div>
		<script type="text/javascript">
			<%
					data = (Post) list.get(0);
			%>
		
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
			<%
				for (int i = 0; i < list.size(); i++) {
					data = (Post) list.get(i);
			%>
			
			var oPoint2 = new nhn.api.map.LatLng("" + <%=data.getLatitude()%>, "" + <%=data.getLongitude()%>);
			
			//설명부분
			var oTitle = "<%= data.getTitle()%>" ;
			var oShopname = "<%=data.getShopname()%>";
			var oCategory = "<%=data.getCategory()%>";
			
			var oMarker2 = new nhn.api.map.Marker(oIcon, {
				title : '제목 : ' + oTitle.toString() + ' 장소 : ' + oShopname.toString() 
				+ ' 종류 : ' + oCategory.toString() 
			});
			
			//마커를 추가하는 부분이다.
			oMarker2.setPoint(oPoint2);
			oMap.addOverlay(oMarker2);
			
			var aPoints2 = oPolyline.getPoints(); // - 현재 폴리라인을 이루는 점을 가져와서 배열에 저장.
			aPoints2.push(oPoint2); // - 추가하고자 하는 점을 추가하여 배열로 저장함.
			oPolyline.setPoints(aPoints2);// - 해당 폴리라인에 배열에 저장된 점을 추가함
			
			<%
				}
			%>
			
		</script>

	</div>

</body>
</html>