package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;

public class SessionCommand extends Command{
	private static LoginService service = LoginService.getInstance();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		System.out.println("세션 검사 로직 진입");
		
		//현재 세션값 확인
		HttpSession session = request.getSession(false);
		//System.out.println("session값 : " + session.getId());
		
		if(session != null){ // 세션이 존재하는 경우
			response.setHeader("session", "yes");
		}else{
			response.setHeader("session", "no");
		}
	
	}// 메서드 마지막
}// 클래스 마지막