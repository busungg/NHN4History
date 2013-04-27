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
		//�ش� ��ǰ�ڵ忡 ���� ��ǰ ������ �����ش�(�� ��ȸ)
		Product data = null;
		
		try{
			data = service.selectById(Integer.parseInt(pCode));
			request.setAttribute("data", data);
			url = "view.jsp";
		}catch(SQLException s){
			request.setAttribute("message", "�� ��ȸ �� ���� �߻�\n"+s);
			url = "error.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
*/