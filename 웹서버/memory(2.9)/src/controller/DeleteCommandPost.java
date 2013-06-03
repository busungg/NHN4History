package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.PostService;
import service.ShareService;

//포스팅 하나 삭제하는 기능
public class DeleteCommandPost extends Command{

	private static PostService service = PostService.getInstance();
	private static ShareService serviceShare = ShareService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String postId = request.getParameter("postId");
		String userId = (String) session.getAttribute("userId");
		String startDate = (String)request.getParameter("startDate");
		String endDate = (String)request.getParameter("endDate");
		String url = null;

		System.out.println("postId : " + postId);
		System.out.println("userId = " + userId);
		System.out.println("startDate = " + startDate);
		System.out.println("endDate = " + endDate);
		
		//시작시간 20130522000000
		HashMap<String, String> mapShare = new HashMap<String, String>();
		
		String year = startDate.substring(0, 4);
		String month = startDate.substring(4, 6);
		String day = startDate.substring(6, 8);
		
		//쉐어 delete
		System.out.println(year + " " + month + " " + day);
		
		mapShare.put("id", userId);
		mapShare.put("year", year);
		mapShare.put("month", month);
		mapShare.put("day", day);
		
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("userid", userId);
		map.put("startdate", startDate);
		map.put("enddate", endDate);
		
		try{
			serviceShare.delete(mapShare);
			service.deletePost(Integer.parseInt(postId));
			request.setAttribute("userId", userId);
			
			if(service.selectByUserIdUptime(map).isEmpty()){
				System.out.println("detailCommand 데이터 없는 경우");
				url = "emptyDetail.jsp";
			}else{
				System.out.println("detailCommand 데이터 있는 경우");
				request.setAttribute("startDate", startDate);
				request.setAttribute("endDate", endDate);
				request.setAttribute("detailData", service.selectByUserIdUptime(map));
				url = "detail.jsp";
			}
			
		}catch(SQLException s){
			request.setAttribute("message", "삭제 중 에러 발생\n"+s);
			url = "error.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}