package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;
import service.PostService;

/* 로그인 로직
 * 아이디와 패스워드 확인 로직
 * jQuery Mobile이 기본적으로 Ajax 통신을 하므로 코드에 setHeader를 사용함
 * 
 */

public class LoginCommand extends Command{
	private static LoginService loginService = LoginService.getInstance();
	private static PostService postService = PostService.getInstance();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		System.out.println("로그인 로직 진입");
		
		String userId = request.getParameter("userId").trim(); 
		String userPw = request.getParameter("userPw").trim(); 
		boolean isMobile = false;
		String url = null;
		System.out.println("userId = " + userId);
		System.out.println("password = " + userPw);
		System.out.println(userId + " " + userPw);
		
		if(request.getParameter("mode") != null && request.getParameter("mode").equals("mobile")){
			isMobile = true;
		}
		
		try{		
			if((loginService.selectById(userId)) != null && userId.equals(loginService.selectById(userId).getId()) && userPw.equals(loginService.selectById(userId).getPassword()) ){
				HttpSession session = request.getSession();
				
				//세션에 userId를 저장해두고 인증 로직을 구성할 때 userId값이 null인지 아닌지 확인
				session.setAttribute("userId", userId);
				//로그인 한 유저만의 데이터만 뽑아서 넘겨줘야한다.
				request.setAttribute("allData", postService.selectByUserId(userId));
				
				System.out.println("UserId Session값 : " + session.getId());
				//response는 모바일에서 ajax 통신에 사용되는 로직
				response.setHeader("loginValidate", "validate");
				System.out.println("loginValidate 설정 완료");
				
				url = "mypage.jsp";
			}else{
				request.setAttribute("error", "아이디와 패스워드를 확인하세요.");
				response.setHeader("loginValidate", "invalidate");
				//url = "error.jsp";
			}
		}catch(SQLException s){
			request.setAttribute("error", "에러 발생\n" + s );
			//url = "error.jsp";
		}		
		
		if(isMobile == false){
			request.getRequestDispatcher(url).forward(request, response);
		}
		
	}//메서드 마지막
}//클래스 마지막