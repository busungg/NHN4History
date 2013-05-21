package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.PostService;

/* 
 * mypage.jsp로 이동하는 서블릿
 * 현재 특정 월에 기록된 포스팅을 모두 출력하도록 한다.
 * 현재의 달을 찾아서 해당 달의 처음날부터 마지막날까지 해당 유저ID의 모든 포스팅정보를 galleryData에 저장한다.
 */
public class ListCommand extends Command{
	
	private static PostService service = PostService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		//http://www.yunsobi.com/blog/325 - 자바 날짜 시간 계산
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat fullFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat yearMonthFormat = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		
		cal.set(Integer.parseInt(yearFormat.format(today)), Integer.parseInt(monthFormat.format(today))-1, Integer.parseInt(dayFormat.format(today)));
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		//yyyyMM 까지만 받고ㅡ ddhhmmss
		String firstDate = yearMonthFormat.format(today) + "01000000"; 
		String lastDate = fullFormat.format(cal.getTime()) + "235959";	
		
		// 이제 특정 ID에 대해서 정해진 기간에 대해 기록한 포스트를 모두 galleryData에 저장
		// 다중쿼리 이해 필수 - http://cafe.naver.com/java001/157

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("userid", userId);
		map.put("startdate", firstDate);
		map.put("enddate", lastDate);
		
		try{
			request.setAttribute("galleryData", service.selectByUserIdUptime(map));
			//request.setAttribute("allData", service.selectAll());
			url = "mypage.jsp";		
		}catch(SQLException s){
			request.setAttribute("message", "전체 조회 중 예외 발생\n"+s);
			url = "error.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}