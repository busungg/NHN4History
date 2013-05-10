package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PostService;

//특정 일자에 해당하는 모든 포스팅 삭제하는 기능
public class DeleteCommandDay extends Command{

	private static PostService service = PostService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String dayId = request.getParameter("dayId");
		String url = null;
		
		try{
			service.deleteDay(dayId);
//			request.setAttribute("allData", service.selectAll());
//			url = "list.jsp";
			
		}catch(SQLException s){
			request.setAttribute("message", "삭제 중 에러 발생\n"+s);
			url = "error.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
