<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="java.io.*"%>
<%@page import="com.oreilly.servlet.*"%>
<%@page import="com.oreilly.servlet.multipart.*"%>

<%
	// 업로드된 파일이 서버에 저장될 위치 지정
	String cp = request.getContextPath();
	String root = pageContext.getServletContext().getRealPath("/");
	
	// 웹서버에 접근해서 다운로드 받을 수 있게 하기 위해 위치 지정을 이렇게.!
	// 경로를 수정
	String path = "D:\\NHN\\1.version management\\memory(2.8)\\WebContent\\upload";
	File dir = new File(path); // 디렉토리 위치 지정
	
	//System.out.println(path);
	
	if (!dir.exists()) { // 디렉토리가 존재하지 않으면
		dir.mkdirs(); // 디렉토리 생성.!
	}

	// 업로드된 파일에 대한 처리
	String enctype = "UTF-8";
	// 파일 이름 한글이 깨질 시에는
	// String enctype = "EUC-KR";
	int maxFileSize = 5 * 1024 * 1024; // 5Mbytes로 업로드 파일 용량 제한
	
	//파일 업로드 이미지 네임
	String imageName = null;
	
	String str = "";
	try {
		MultipartRequest req = null;
		req = new MultipartRequest(request, path // 물리적으로 저장될 위치
				, maxFileSize, enctype, new DefaultFileRenamePolicy());
		str += "originalfilename : ";
		str += req.getOriginalFileName("upload");
		str += "<br>";
		str += "savefilename : ";
		str += req.getFilesystemName("upload");
		str += "<br>";
		str += "filetype : ";
		str += req.getContentType("upload");
		str += "<br>";
		
		System.out.println(str);

		File file = req.getFile("upload");
		if (file != null) {
			str += "filesize : ";
			str += (file.length() / 1024) + " Kbytes";
		}
		
		imageName = req.getFilesystemName("upload");
		System.out.println("이미지 네임 : " + imageName);
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
%>

<%=imageName %>
