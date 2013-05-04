package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("로그아웃 로직 진입");
		HttpSession session = request.getSession();
		System.out.println("로그아웃 전 세션 아이디 : " + session.getId());
		//System.out.println("�α׾ƿ� �� ���� �� " + session.getAttribute("userId"));
		session.invalidate(); //세션 invalidate
		System.out.println("세션 invalidate");
			
		response.setHeader("logoutResult", "yes");
		response.setHeader("loginValidate", "invalidate");
/*		response.setHeader("pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		
		response.setDateHeader("Expires", 1L);*/
		//request.getRequestDispatcher("login.html").forward(request, response);
	}
}
