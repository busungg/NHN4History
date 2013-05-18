<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="service.LoginService"%>
<%@ page import="service.PostService"%>
<%@ page import="java.sql.SQLException"%>
<%
	System.out.println("로그인 로직 진입");
	LoginService loginService = LoginService.getInstance();
	PostService postService = PostService.getInstance();

	String userId = request.getParameter("userId").trim();
	String userPw = request.getParameter("userPw").trim();
	String url = null;
	System.out.println("userId = " + userId);
	System.out.println("password = " + userPw);

	System.out.println(userId + " " + userPw);

	try {
		if ((loginService.selectById(userId)) != null
				&& userId.equals(loginService.selectById(userId)
						.getId())
				&& userPw.equals(loginService.selectById(userId)
						.getPassword())) {

			//세션에 userId를 저장해두고 인증 로직을 구성할 때 userId값이 null인지 아닌지 확인
			session.setAttribute("userId", userId);
			//로그인 한 유저만의 데이터만 뽑아서 넘겨줘야한다.
			request.setAttribute("allData",
					postService.selectByUserId(userId));

			System.out.println("UserId Session값 : " + session.getId());
			out.println("yes");
		} else {
			out.println("no");
		}
	} catch (SQLException s) {
		request.setAttribute("error", "에러 발생\n" + s);
	}
%>