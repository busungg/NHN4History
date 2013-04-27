<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<!-- 
포스팅 부분
 -->
	<div id="post" data-role="page" data-theme="a">
		<script type="text/javascript">
			function getPhoto() {
				navigator.camera
						.getPicture(
								function(imageURI) {
									var image = document
											.getElementById('image');
									image.style.display = 'block';
									image.src = imageURI;
								},
								function(message) {
									alert('Failed because: ' + message);
								},
								{
									quality : 50,
									destinationType : navigator.camera.DestinationType.FILE_URI,
									sourceType : navigator.camera.PictureSourceType.SAVEDPHOTOALBUM
								});
			}

			function sendPhoto() {
				var image = document.getElementById('image');
				var imageURI = image.src;

				var options = new FileUploadOptions();
				options.fileKey = "file";
				options.fileName = imageURI
						.substr(imageURI.lastIndexOf('/') + 1);
				options.mimeType = "image/jpeg";
				var params = new Object();
				params.param1 = "value1";
				params.param2 = "value2";
				options.params = params;

				var fileTransfer = new FileTransfer();
				fileTransfer
						.upload(
								imageURI,
								"http://192.168.11.19:8080/phonegap/hardware/upload.jsp",
								function(fileUploadResult) {
									alert("Code = "
											+ fileUploadResult.responseCode
											+ "\n" + "Response = "
											+ fileUploadResult.response + "\n"
											+ "Sent = "
											+ fileUploadResult.bytesSent);
								}, function(error) {
									alert("Error Code = " + error.code);
								}, options);
			}
		</script>
		<div data-role="header" data-theme="a">
			<h1>포스팅</h1>
			<a href="menu.jsp" data-icon="arrow-l" data-theme="a">이전</a>
		</div>
		<!-- 
		1. 화면 구성
이미지 올리는 부분 -> 이미지를 선택할 수 있고, 이미지는 여러개 올릴 수도 있어야 함
텍스트 작성 부분 -> 텍스트 작성은 폰트 변경과 같은 다양한 기능을 담당할 수 있어야 함
위도, 경도 정보 획득 및 표시 -> 수정도 가능한데..... 해당 위도경도 정보를 어떻게 위치 정보로 바꿀까?
		 -->
		<div data-role="content" data-theme="b">
			<button onclick="getPhoto();">앨범에서 사진 선택</button>
			<button onclick="sendPhoto();">사진 보내기</button>
			<hr />
			<div>
				<img id="image" style="display: none; width: 290px; height: 210px;"
					src="" />
			</div>
		</div>
	</div>
</body>
</html>