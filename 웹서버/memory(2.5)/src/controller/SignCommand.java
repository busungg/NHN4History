package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.Login;

import service.LoginService;

public class SignCommand extends Command{
	private static LoginService service = LoginService.getInstance();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		System.out.println("회원가입 로직 진입");
		
		String userId = request.getParameter("userId").trim(); 
		String userPw = request.getParameter("userPw").trim(); 
		//String name = request.getParameter("uname").trim();
		//이름은 한글값이 들어오는게 일반적이므로, UTF-8로 인코딩되어서 들어옴
		String name = URLDecoder.decode(request.getParameter("uname"), "UTF-8");
		String email = request.getParameter("email").trim();
		String url = null;
		
		System.out.println("userId = " + userId);
		System.out.println("password = " + userPw);
		System.out.println("name = " + name);
		System.out.println("email = " + email);

		//모바일용 회원가입 로직
		//회원 아이디가 이미 존재하는가?
			//존재하면 이미 존재하는 아이디라고 response 돌려줌
		//회원 아이디가 없으면 아이디와 패스워드 등록 후, 등록 완료라고 response 돌려줌
		
		try{
			if(service.selectById(userId) == null){//유저 아이디가 없다면 회원 가입 가능
				service.insertLogin(new Login(userId, userPw, name, email));
				response.setHeader("sign", "yes");
			}else{ // 유저 아이디가 있으므로 회원 가입 불가
				response.setHeader("sign", "no");
			}
			System.out.println("회원 가입 로직 완료");
		}catch(SQLException s){
			request.setAttribute("error", "에러 발생\n" + s );
		}		
		/*try{		
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
		}		*/
		//request.getRequestDispatcher(url).forward(request, response);
		
	}//메서드 마지막
}//클래스 마지막