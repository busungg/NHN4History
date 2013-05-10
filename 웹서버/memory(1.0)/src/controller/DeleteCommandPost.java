package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PostService;

//포스팅 하나 삭제하는 기능
public class DeleteCommandPost extends Command{

	private static PostService service = PostService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String postId = request.getParameter("postId");
		String url = null;
		
		try{
			service.deletePost(Integer.parseInt(postId));
//			request.setAttribute("allData", service.selectAll());
//			url = "list.jsp";
			
		}catch(SQLException s){
			request.setAttribute("message", "삭제 중 에러 발생\n"+s);
			url = "error.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}
}