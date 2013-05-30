<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Memory 에디트</title>

<script type="text/javascript"
	src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=7939d865a2c45d3d933694f2ddbf9c59"></script>

<!-- nicedit -->
<script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>

</head>
<body>
<% String userId=(String)session.getAttribute("userId"); %>

	<div id="allText" align="center">
	
		<table>
			<tr>
				<td width="20%">제목</td>
				<td><input id="title" name="title" type="text"
					style="width: 630px; height: 20px" /></td>
			</tr>
			<tr>
				<td width="20%">대표사진</td>
				<td><input id="titleImage" name="titleImage" type="text"
					style="width: 630px; height: 20px" /></td>
			</tr>
			
			<tr>
				<td width="20%">카테고리</td>
				<td><input id="category" name="category" type="text"
					style="width: 630px; height: 20px" /></td>
			</tr>
			
			<tr>
				<td width="20%">장소</td>
				<td><input id="shopname" name="shopname" type="text"
					style="width: 630px; height: 20px" /></td>
			</tr>
			<tr>
				<td width="20%">전화번호</td>
				<td><input id="tel" name="tel" type="text"
					style="width: 630px; height: 20px" /></td>
			</tr>
		</table>

	</div>



	<!-- NicEdit 사용 -->
	<div id="NicEditor" align="center">
		<script type="text/javascript">
			bkLib.onDomLoaded(function() {
				new nicEditor().panelInstance('NicEdit');
			});
			
			window.onload = function(){
				document.getElementById("uploadForm").onsubmit = function(){
					
					document.getElementById("uploadForm").target = "uploadIFrame";
				}
				
				document.getElementById("uploadIFrame").onload = function()
				{
					alert("파일 업로드 완료!");
					
					//전에 있는 내용이 지워지지 않도록 다시 합친다.
					var edit = nicEditors.findEditor('NicEdit').getContent();
					
					//이미지 태그를 만든다.
					var imgName = "" + frames["uploadIFrame"].document.body.innerHTML;
					var imgTag = "<P><img src=\"../upload/" + imgName + "\"></P>";
					
					nicEditors.findEditor('NicEdit').setContent(edit + imgTag);
					
					alert(imgName + " 이미지 내용추가 완료");
				}
			}
			
		</script>
		
		<form id="uploadForm" style="margin: 10px;" method="post" enctype="multipart/form-data" action="../fileupload.jsp">
		<input name="upload" id="uploadFile" type="file" />
		<input type="submit" name="action" value="Upload" />
		
		<!-- 이미지 업로드용 -->
		<iframe id="uploadIFrame" name="uploadIFrame" style="display:none; visibility:hidden"></iframe>
		
		<textarea id="NicEdit" style="width: 800px; height: 300px;"></textarea>
		
		<button style="margin-right: 10px; margin-top: 10px;" onclick="check();">저장</button>
		
	</form>
		
	</div>

	<!-- 지도 -->
	<div id="naverMap" align="center">
	<div id="map" style="border: 1px solid #000;"></div>
	</div>

	<script type="text/javascript">
		var lat = 0;
		var lng = 0;
	
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

/* 		var oPolyline = new nhn.api.map.Polyline([], {
			strokeColor : '#f00', // - 선의 색깔
			strokeWidth : 5, // - 선의 두께
			strokeOpacity : 0.5
		// - 선의 투명도
		}); // - polyline 선언, 첫번째 인자는 선이 그려질 점의 위치. 현재는 없음.
		oMap.addOverlay(oPolyline); // - 지도에 선을 추가함. */

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

		
		/* var oPoint = oCustomEvent.point; */
		oMap.attach('click', function(oCustomEvent) {
							var oPoint = oCustomEvent.point;
							oInfoWnd.setVisible(false);
							var oMarker = new nhn.api.map.Marker(oIcon, {
								title : '마커 : ' + oPoint.toString()  });
							
							oMap.clearOverlay();
							oMarker.setPoint(oPoint);
							oMap.addOverlay(oMarker);
							
							lat = oPoint.getLat();
							lng = oPoint.getLng();
						});
		
		function check(){
			
			var title = document.getElementById("title").value;
			var titleImage = document.getElementById("titleImage").value;
			var category = document.getElementById("category").value;
			var shopName = document.getElementById("shopname").value;
			var tel = document.getElementById("tel").value;
			var htmlText = nicEditors.findEditor('NicEdit').getContent();
			var date= new Date();
			
			var month = 0;
			var day = 0;
			var hours = 0;
			var minute = 0;
			var seconds = 0;
			
			if((date.getMonth() + 1).toString().length != 2)
			{
				month = "0" + (date.getMonth() + 1);
			}else{
				month = (date.getMonth() + 1);
			}
			
			if( date.getDate().toString().length != 2)
			{
				day = "0" +  date.getDate();
			}else{
				day =  date.getDate();
			}
			
			if(date.getHours().toString().length !=2){
				hours="0"+date.getHours();
			}else{
				hours=	date.getHours();
			}
			
			if(date.getMinutes().toString().length !=2){
				minute="0"+date.getMinutes();
			}else{
				minute=date.getMinutes();
			}
			
			if(date.getSeconds().toString().length !=2){
				 seconds="0"+date.getSeconds();
			}else{
				seconds=date.getSeconds();
			}
			
			var upTime = date.getFullYear() + "" +month + "" +  day +  "" + hours + "" + minute + "" + seconds;
			
			alert(upTime);
			alert(title);
			alert(titleImage);
			alert(category);
			alert(shopName);
			alert(tel);
			alert(htmlText);
			
			alert(lat + " " + lng);
			
			location.href = "/memory/Service?action=postP&" +"title="+title+"&titleImage="+titleImage+
					"&category="+category+"&upTime="+upTime+"&shopname="+shopName+"&tel="+tel+"&htmlText="+htmlText+"&latitude="+lat+"&longitude="+lng;
		}

	</script>


</body>
</html>
