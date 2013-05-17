package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.Share;

import service.LoginService;
import service.PostService;
import service.ShareService;

public class PostShareCommand extends Command{
	
	private static int shareid = 0;
	
	private static ShareService shareService = ShareService.getInstance();
	
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = (String) request.getParameter("userId");
		String year = (String)request.getParameter("year");
		String month = (String)request.getParameter("month");
		String day = (String)request.getParameter("day");
		
		String url = null;
		Share data = new Share(shareid, userId, year, month, day);
		
		try {
			shareService.insertMessage(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		shareid++;

		// 이제 특정 ID에 대해서 정해진 기간에 대해 기록한 포스트를 모두 galleryData에 저장
		
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
