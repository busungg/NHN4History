<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page import="java.io.*"%>
<%@page import="com.oreilly.servlet.*"%>
<%@page import="com.oreilly.servlet.multipart.*"%>

<%
	// ���ε�� ������ ������ ����� ��ġ ����
	String cp = request.getContextPath();
	String root = pageContext.getServletContext().getRealPath("/");
	
	// �������� �����ؼ� �ٿ�ε� ���� �� �ְ� �ϱ� ���� ��ġ ������ �̷���.!
	// ��θ� ����
	String path = "D:\\NHN\\1.version management\\memory(2.8)\\WebContent\\upload";
	File dir = new File(path); // ���丮 ��ġ ����
	
	//System.out.println(path);
	
	if (!dir.exists()) { // ���丮�� �������� ������
		dir.mkdirs(); // ���丮 ����.!
	}

	// ���ε�� ���Ͽ� ���� ó��
	String enctype = "UTF-8";
	// ���� �̸� �ѱ��� ���� �ÿ���
	// String enctype = "EUC-KR";
	int maxFileSize = 5 * 1024 * 1024; // 5Mbytes�� ���ε� ���� �뷮 ����
	
	//���� ���ε� �̹��� ����
	String imageName = null;
	
	String str = "";
	try {
		MultipartRequest req = null;
		req = new MultipartRequest(request, path // ���������� ����� ��ġ
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
		System.out.println("�̹��� ���� : " + imageName);
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
%>

<%=imageName %>
