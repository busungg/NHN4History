package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import service.PostService;

public class ListCommand extends Command{
	
	private static PostService service = PostService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = null;
		
		try{
			request.setAttribute("allData", service.selectAll());
			url = "list.jsp";			
		}catch(SQLException s){
			request.setAttribute("message", "전체 조회 중 예외 발생\n"+s);
			url = "error.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
/*	private static ProductService service = ProductService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = null;
		
		try{
			request.setAttribute("allData", service.selectAll());
			url = "list.jsp";
		}catch(SQLException s){
			request.setAttribute("message", "전체 조회 중 예외 발생\n"+s);
			url = "error.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}*/
}