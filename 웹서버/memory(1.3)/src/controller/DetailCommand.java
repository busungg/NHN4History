package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;
import service.PostService;

public class DetailCommand extends Command {
	private static LoginService loginService = LoginService.getInstance();
	private static PostService postService = PostService.getInstance();

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Detail 조회 로직 진입");
		HttpSession session = request.getSession();
		// 세션값을 가지고 온다.

		String userId = (String) session.getAttribute("userId");
		String startDate = (String)request.getAttribute("startDate");
		String endDate = (String)request.getAttribute("endDate");
		
		String url = null;
		System.out.println("userId = " + userId);
		System.out.println("startDate = " + startDate.toString());
		System.out.println("endDate = " + endDate.toString());

		// 이제 특정 ID에 대해서 정해진 기간에 대해 기록한 포스트를 모두 galleryData에 저장
		// 다중쿼리 이해 필수 - http://cafe.naver.com/java001/157

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("userid", userId);
		map.put("startdate", startDate);
		map.put("enddate", endDate);

		try {
			request.setAttribute("detailData", postService.selectByUserIdUptime(map));
			url = "detail.jsp";

		} catch (SQLException s) {
			request.setAttribute("message", "조회 중 에러 발생\n" + s);
			url = "error.jsp";
		}

		request.getRequestDispatcher(url).forward(request, response);
	}// 메서드 마지막
}// 클래스 마지막
