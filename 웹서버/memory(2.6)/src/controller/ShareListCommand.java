package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ShareService;

public class ShareListCommand extends Command{
	
	private static ShareService shareService = ShareService.getInstance();
	
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = null;
		
		try {
			request.setAttribute("shareAll", shareService.selectAll());
			url = "shareList.jsp";

		} catch (SQLException s) {
			request.setAttribute("message", "조회 중 에러 발생\n" + s);
			url = "error.jsp";
		}

		request.getRequestDispatcher(url).forward(request, response);
	}// 메서드 마지막
}
