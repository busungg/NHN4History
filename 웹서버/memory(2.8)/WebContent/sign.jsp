<%@page import="service.PostService"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.dto.*"%>
<%@page import="service.LoginService" %>
<%
String mode = request.getParameter("mode");
System.out.println("sign.jsp 진입 : " + mode );

if(mode.equals("userIdCheck")){
	//서버에 동일한 ID값이 있는지 없는지 확인해야한다.
	String userId = request.getParameter("userId");
	System.out.println("userId : " + userId);
	
	try{
		if(LoginService.getInstance().selectById(userId) != null){
		//널값이 아니면, 이미 DB에 동일한 ID값이 있다는 의미이므로,
			out.print("no");//사용중인 아이디
		}else{
			out.print("yes");//사용하지 않은 아이디, 등록 가능
		}
	}catch(SQLException s){
		request.setAttribute("message", "유저 아이디 체크 중 DB 에러 발생\n" + s);
	}
}
%>