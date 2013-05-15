<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="service.LoginService"%>
<%@ page import="service.PostService"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.dto.Post"%>
<%
	System.out.println("Gallery 조회 로직 진입");
	//HttpSession session = request.getSession();
	// 세션값을 가지고 온다.

	LoginService loginService = LoginService.getInstance();
	PostService postService = PostService.getInstance();

	String userId = (String) session.getAttribute("userId");
	String startDate = request.getParameter("startDate").trim()+ "000000";
	String endDate = request.getParameter("endDate").trim() + "235959";
	String url = null;
	
	System.out.println(startDate);
	System.out.println(endDate);

	System.out.println("userId = " + userId);
	System.out.println("startDate = " + startDate);
	System.out.println("endDate = " + endDate);

	// 이제 특정 ID에 대해서 정해진 기간에 대해 기록한 포스트를 모두 galleryData에 저장
	// 다중쿼리 이해 필수 - http://cafe.naver.com/java001/157

	HashMap<String, String> map = new HashMap<String, String>();

	map.put("userid", userId);
	map.put("startdate", startDate);
	map.put("enddate", endDate);

	//데이터 처리 방식을 잘 정할것, 반환할 떄 JSON 형식을 이용하도록 한다.
	try {
		//request.setAttribute("galleryData", postService.selectByUserIdUptime(map));
		ArrayList<Post> galleryData = postService.selectByUserIdUptime(map);

		StringBuilder str = new StringBuilder();
		
		//JSON 객체로 전달함
		str.append("[");
		
		for (int i = 0; i < galleryData.size(); i++) {
			str.append("{");
			str.append("\"imageName\""+":\"");
			str.append(galleryData.get(i).getImage());
			str.append("\",");
			str.append("\"category\""+":\"");
			str.append(galleryData.get(i).getCategory());
			str.append("\"}");
			if (i != galleryData.size()-1)
				str.append(",");
			//out.println(galleryData.get(i).getImage() + "," + galleryData.get(i).getCategory());	
		}

		str.append("]");
		
		System.out.println("JSON Array : " + str.toString());
		
		//웹페이지에 JSON 배열 출력
		out.println(str.toString());

	} catch (SQLException s) {
		request.setAttribute("message", "조회 중 에러 발생\n" + s);
	}
%>