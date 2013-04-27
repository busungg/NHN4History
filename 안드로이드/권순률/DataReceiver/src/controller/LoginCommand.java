/*package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ProductService;

public class LoginCommand extends Command{
	private static ProductService service = ProductService.getInstance();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//로그인과 관련된 처리를 모두 한다. try, catch 필수
		System.out.println("로그인 검증 로직 진입 확인");
		String userId = request.getParameter("user").trim(); //아이디 넘겨받음
		String password = request.getParameter("password").trim();
		String url = null;
		System.out.println(userId + password);
		
		try{
			//System.out.println(service.selectByUserId(userId).getUserId());
			//System.out.println(service.selectByUserId(userId).getPassword());
			System.out.println(service.selectByUserId(userId).getUserId());
			//넘어온 아이디가 DB에 존재하는 아이디이고 패스워드도 동일할 경우에만 넘어가도록 한다.
			if(userId.equals(service.selectByUserId(userId).getUserId()) && password.equals(service.selectByUserId(userId).getPassword())){
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				
				//동일하다면 request에 아이디 저장후 상품 목록 페이지로 이동
				//request.setAttribute("userId", userId);
			
				//상품 목록을 출력하기 때문에 일단, 상품에 대한 모든 정보(selectProductAll을 통해서 제품 정보도 가지고 넘어간다.)
				request.setAttribute("allData", service.selectAll());
				
				url = "list.jsp";
			}else{//아이디나 패스워드를 확인하도록 한다.
				request.setAttribute("message", "아이디나 패스워드를 확인하세요.");
				url = "error.jsp";
			}
		}catch(SQLException s){//로그인 중 예외 발생
			System.out.println("예외 발생하면 여기로...");
			request.setAttribute("message", "로그인 중 예외 발생\n"+s);
			url = "error.jsp";
		}//예외 마지막		
		request.getRequestDispatcher(url).forward(request, response);		
	}//메서드 마지막
}//클래스 자지막
*/