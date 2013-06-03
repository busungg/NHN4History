package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PostService;

public class UpdateCommand extends Command{
	
	private static PostService service = PostService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 어떤 데이터에 대해서 업데이트 할지 생각할 것
		String postid = request.getParameter("postid").trim();
		String userid = request.getParameter("userid").trim();
		String title = request.getParameter("title").trim();
		String shopname = request.getParameter("shopname").trim();
		String category = request.getParameter("category").trim();
		String tel = request.getParameter("tel").trim();
		String url = null;
		
		/*
		try{
			service.update(new Product(Integer.parseInt(pCode), pName, Integer.parseInt(price), Integer.parseInt(quantity), pDesc));
			request.setAttribute("allData", service.selectAll());
			//url = "list.jsp";
		}catch(SQLException s){
			request.setAttribute("message", "포스팅 수정 중 에러 발생\n"+s);
			//url = "error.jsp";
		}
		*/
		
		request.getRequestDispatcher(url).forward(request, response);
	}//메서드 마지막
}//클래스 마지막