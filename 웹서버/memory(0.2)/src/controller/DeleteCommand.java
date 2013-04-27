package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteCommand extends Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

/*	private static ProductService service = ProductService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		String pCode = request.getParameter("pCode");
		String url = null;
		
		try{
			service.delete(Integer.parseInt(pCode));
			request.setAttribute("allData", service.selectAll());
			url = "list.jsp";
			
		}catch(SQLException s){
			request.setAttribute("message", "??†ú Ï§??àÏô∏ Î∞úÏÉù\n"+s);
			url = "error.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}*/
}