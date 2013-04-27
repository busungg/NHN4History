/*package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductService;

import dto.Product;

public class ViewCommand extends Command{
	private static ProductService service = ProductService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = null;
		String pCode = request.getParameter("pCode");
		//해당 상품코드에 대한 상품 정보를 돌려준다(상세 조회)
		Product data = null;
		
		try{
			data = service.selectById(Integer.parseInt(pCode));
			request.setAttribute("data", data);
			url = "view.jsp";
		}catch(SQLException s){
			request.setAttribute("message", "상세 조회 중 예외 발생\n"+s);
			url = "error.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
*/