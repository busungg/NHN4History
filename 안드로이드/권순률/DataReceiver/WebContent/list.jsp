<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.dto.Post"%>
<%@ page import="service.PostService"%>
<!--  http://servlets.com/cos/ 여기서 multipart 관련 라이브러리를 받아서 사용해야함 -->
<%-- <%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%> --%>
<%@ page import="com.oreilly.servlet.MultipartRequest,com.oreilly.servlet.multipart.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>데이터 전송 테스트</title>
</head>
<body>
	<%
		/* 자바 코드로 여기서 직접 DB에 있는 데이터를 가져와서 뿌려준다. 
			    이 경우 페이지를 넘기면서 DB에 있는 자료를 긁어서 가져오는 작업이 필요없어짐 
			 */		 
			ArrayList<Post> allData = PostService.getInstance().selectAll();
	%>

	<center>
	<h1>Image, Text 전송 테스트 페이지</h1>
	
	<table border="1" bordercolor="blue">
		<tr>
			<td align="center" height="35" width="100">이미지</td>
			<td align="center">텍스트</td>
			<td align="center">올린시간</td>
			<td align="center">위도</td>
			<td align="center">경도</td>
		</tr>
		<%
			for (int i = 0; i < allData.size(); i++) {
		%>
		<tr><!-- 이미지는 새로고침해도 제대로 못 읽는 문제 발생 http://cafe.naver.com/javachobostudy/67786 참고 및 네이버 검색 HTTP Status 404 -->
			<td height="35" width="100"><img src = "http://localhost:8080/DataReceiver/upload/<%=allData.get(i).getImage()%>" width="100" height="100"></td>
			<td><%=allData.get(i).getText()%></td>
			<td><%=allData.get(i).getUptime() %></td>
			<td><%=allData.get(i).getLatitude() %></td>
			<td><%=allData.get(i).getLongitude() %></td>
		</tr>
		<%
			}
		%>

	</table>
	</center> 

</body>
</html>