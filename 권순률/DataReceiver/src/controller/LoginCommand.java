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
		//�α��ΰ� ���õ� ó���� ��� �Ѵ�. try, catch �ʼ�
		System.out.println("�α��� ���� ���� ���� Ȯ��");
		String userId = request.getParameter("user").trim(); //���̵� �Ѱܹ���
		String password = request.getParameter("password").trim();
		String url = null;
		System.out.println(userId + password);
		
		try{
			//System.out.println(service.selectByUserId(userId).getUserId());
			//System.out.println(service.selectByUserId(userId).getPassword());
			System.out.println(service.selectByUserId(userId).getUserId());
			//�Ѿ�� ���̵� DB�� �����ϴ� ���̵��̰� �н����嵵 ������ ��쿡�� �Ѿ���� �Ѵ�.
			if(userId.equals(service.selectByUserId(userId).getUserId()) && password.equals(service.selectByUserId(userId).getPassword())){
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				
				//�����ϴٸ� request�� ���̵� ������ ��ǰ ��� �������� �̵�
				//request.setAttribute("userId", userId);
			
				//��ǰ ����� ����ϱ� ������ �ϴ�, ��ǰ�� ���� ��� ����(selectProductAll�� ���ؼ� ��ǰ ������ ������ �Ѿ��.)
				request.setAttribute("allData", service.selectAll());
				
				url = "list.jsp";
			}else{//���̵� �н����带 Ȯ���ϵ��� �Ѵ�.
				request.setAttribute("message", "���̵� �н����带 Ȯ���ϼ���.");
				url = "error.jsp";
			}
		}catch(SQLException s){//�α��� �� ���� �߻�
			System.out.println("���� �߻��ϸ� �����...");
			request.setAttribute("message", "�α��� �� ���� �߻�\n"+s);
			url = "error.jsp";
		}//���� ������		
		request.getRequestDispatcher(url).forward(request, response);		
	}//�޼��� ������
}//Ŭ���� ������
*/