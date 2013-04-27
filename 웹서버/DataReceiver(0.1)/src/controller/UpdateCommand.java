/*package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductService;

import dto.Product;

public class UpdateCommand extends Command{
	private static ProductService service = ProductService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pCode = request.getParameter("pCode").trim();
		String pName = request.getParameter("pName").trim();
		String price = request.getParameter("price").trim();
		String quantity = request.getParameter("quantity").trim();
		String pDesc = request.getParameter("pDesc").trim();
		String url = null;
		
		try{
			service.update(new Product(Integer.parseInt(pCode), pName, Integer.parseInt(price), Integer.parseInt(quantity), pDesc));
			request.setAttribute("allData", service.selectAll());
			url = "list.jsp";
		}catch(SQLException s){
			request.setAttribute("message", "상품 변경 중 예외 발생\n"+s);
			url = "error.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	
	}//메서드 마지막
}//클래스 마지막
*/