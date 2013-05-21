<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<script type="text/javascript" src="../se2/js/HuskyEZCreator.js" charset="utf-8"></script>



</head>
<body>
<table>
	<tr>
		<td width="20%">제목</td>
		<td><input id="title" name="title" type="text" style="width: 630px; height:20px" /></td>
	</tr>
	<tr>
		<td width="20%">포스팅 지점</td>
		<td><input id="shopname" name="shopname" type="text" style="width:630px; height:20px" /></td>
	</tr>
	<tr>
		<td width="20%">전화번호</td>
		<td><input id="tel" name="tel" type="text" style="width: 630px; height:20px" /></td>
	</tr>
	<tr>
		<td width="20%">대표 사진</td>
		<td><input id="image" name="image" type="text" style="width: 630px; height:20px" /></td>
	</tr>
	<tr>
		<td width="20%">올린 날짜</td>
		<td><input id="uptime" name="uptime" type="text" style="width: 630px; height:20px" /></td>
</table>


<!-- Editor -->
<textarea name="ir1" id="ir1" rows="10" cols="100" style="width:800px; heigth:412px; display:none;">에디터에 기본으로 삽입할 글 (수정 모드)이 없다면 이 value 값을 지정하지 않으시면 됩니다.</textarea>

<script type="text/javascript">
var oEditors= [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "ir1",
	sSkinURI: "../se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
});
</script>


<!-- 지도 -->
<script type="text/javascript" src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=7939d865a2c45d3d933694f2ddbf9c59"></script>
<div id="map" style="border:1px solid #000;"></div>
<script type="text/javascript">
		var oSeoulCityPoint = new nhn.api.map.LatLng(37.5675451, 126.9773356);
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
						size : new nhn.api.map.Size(800, 480)		});
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
		var oIcon = new nhn.api.map.Icon('http://static.naver.com/maps2/icons/pin_spot2.png', oSize, oOffset);

		var oInfoWnd = new nhn.api.map.InfoWindow();
		oInfoWnd.setVisible(false);
		oMap.addOverlay(oInfoWnd);

		oInfoWnd.setPosition({
			top : 20,
			left :20
		});

		var oLabel = new nhn.api.map.MarkerLabel(); // - 마커 라벨 선언.
		oMap.addOverlay(oLabel); // - 마커 라벨 지도에 추가. 기본은 라벨이 보이지 않는 상태로 추가됨.

		oInfoWnd.attach('changeVisible', function(oCustomEvent) {
			if (oCustomEvent.visible) {
				oLabel.setVisible(false);
			}
		});
		
		var oPolyline = new nhn.api.map.Polyline([], {
			strokeColor : '#f00', // - 선의 색깔
			strokeWidth : 5, // - 선의 두께
			strokeOpacity : 0.5 // - 선의 투명도
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

		oMap.attach('click', function(oCustomEvent) {
			var oPoint = oCustomEvent.point;
			var oTarget = oCustomEvent.target;
			oInfoWnd.setVisible(false);
			// 마커 클릭하면
			if (oTarget instanceof nhn.api.map.Marker) {
				// 겹침 마커 클릭한거면
				if (oCustomEvent.clickCoveredMarker) {
					return;
				}
				// - InfoWindow 에 들어갈 내용은 setContent 로 자유롭게 넣을 수 있습니다. 외부 css를 이용할 수 있으며, 
				// - 외부 css에 선언된 class를 이용하면 해당 class의 스타일을 바로 적용할 수 있습니다.
				// - 단, DIV 의 position style 은 absolute 가 되면 안되며, 
				// - absolute 의 경우 autoPosition 이 동작하지 않습니다. 
				oInfoWnd.setContent('<DIV style="border-top:1px solid; border-bottom:2px groove black; border-left:1px solid; border-right:2px groove black;margin-bottom:1px;color:black;background-color:white; width:auto; height:auto;">'+
					'<span style="color: #000000 !important;display: inline-block;font-size: 12px !important;font-weight: bold !important;letter-spacing: -1px !important;white-space: nowrap !important; padding: 2px 5px 2px 2px !important">' + 
					'Hello World <br /> ' + oTarget.getPoint()
					+'<span></div>');
				oInfoWnd.setPoint(oTarget.getPoint());
				oInfoWnd.setPosition({right : 15, top : 30});
				oInfoWnd.setVisible(true);
				oInfoWnd.autoPosition();
				return;
			}
			var oMarker = new nhn.api.map.Marker(oIcon, { title : '마커 : ' + oPoint.toString() });
			oMarker.setPoint(oPoint);
			oMap.addOverlay(oMarker);

			var aPoints = oPolyline.getPoints(); // - 현재 폴리라인을 이루는 점을 가져와서 배열에 저장.
			aPoints.push(oPoint); // - 추가하고자 하는 점을 추가하여 배열로 저장함.
			oPolyline.setPoints(aPoints); // - 해당 폴리라인에 배열에 저장된 점을 추가함
		});
	</script>


</body>
</html>
