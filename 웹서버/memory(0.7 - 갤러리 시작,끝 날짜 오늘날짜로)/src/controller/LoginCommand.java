package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;

public class LoginCommand extends Command{
	private static LoginService service = LoginService.getInstance();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		System.out.println("로그인 로직 진입");
		
		String userId = request.getParameter("userId").trim(); 
		String userPw = request.getParameter("userPw").trim(); 
		String url = null;
		System.out.println("userId = " + userId);
		System.out.println("password = " + userPw);
				
		System.out.println(userId + " " + userPw);
		
		try{		
			if((service.selectById(userId)) != null && userId.equals(service.selectById(userId).getId()) && userPw.equals(service.selectById(userId).getPassword()) ){
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				System.out.println("UserId Session값 : " + session.getId());
				//PrintWriter out = response.getWriter();
				//String result = "pass";
				//out.write(result);
				response.setHeader("loginValidate", "validate");
				System.out.println("loginValidate 설정 완료");
			}else{
				request.setAttribute("error", "아이디와 패스워드를 확인하세요.");
				//PrintWriter out = response.getWriter();
				//String result = "no";
				//out.write(result);
				response.setHeader("loginValidate", "invalidate");
				//url = "error.jsp";
			}
		}catch(SQLException s){
			request.setAttribute("error", "에러 발생\n" + s );
			//url = "error.jsp";
		}		
		//request.getRequestDispatcher(url).forward(request, response);
		
	}//메서드 마지막
}//클래스 마지막