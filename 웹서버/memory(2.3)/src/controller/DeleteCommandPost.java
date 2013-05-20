package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.PostService;

//포스팅 하나 삭제하는 기능
public class DeleteCommandPost extends Command{

	private static PostService service = PostService.getInstance();
	
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
		
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("userid", userId);
		map.put("startdate", startDate);
		map.put("enddate", endDate);
		
		try{
			service.deletePost(Integer.parseInt(postId));
			request.setAttribute("userId", userId);
			
			if(service.selectByUserIdUptime(map).isEmpty()){
				System.out.println("detailCommand 데이터 없는 경우");
				url = "emptyDetail.jsp";
			}else{
				System.out.println("detailCommand 데이터 있는 경우");
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