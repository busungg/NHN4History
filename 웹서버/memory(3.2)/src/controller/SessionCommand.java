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
		System.out.println("모바일용 세션 검사 로직 진입");
		
		//현재 세션값 확인
		//세션값이 서버에 있다면 그 세션값을 사용하고, 세션값이 없다면 null값이 반환
		//안드로이드에서 막 생성한 request 이기 때문에 아마 세션값이 저장이 안된듯 하다.
		//HttpSession session = request.getSession(false);
		HttpSession session = request.getSession(false);
		//System.out.println("세션 컨트롤러 세션값 확인 : " + session.getId());
		
		if(session != null){ // 세션이 존재하는 경우
			response.setHeader("session", "yes");
			System.out.println("세션 컨트롤러 세션값 확인 : " + session.getId());
		}else{
			response.setHeader("session", "no");
		}
	}// 메서드 마지막
}// 클래스 마지막