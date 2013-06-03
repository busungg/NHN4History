package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.Post;
import service.PostService;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class PostCommandPC extends Command {
	
	private static PostService service = PostService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		System.out.println("PC 포스팅 로직 진입");
		
		HttpSession session = request.getSession(false);
		
		String userId= (String)session.getAttribute("userId");
		String title=request.getParameter("htitle");
		String shopname=request.getParameter("hshopname");
		String category=request.getParameter("hcategory");
		String tel=request.getParameter("htel");
		String titleImage=request.getParameter("htitleImage");
		String text=request.getParameter("hHtmlText");
		String upTime = request.getParameter("hupTime");
		String latitude=request.getParameter("hlatitude");
		String longitude=request.getParameter("hlongitude");
		int imageNumber=0;
		
		System.out.println(userId);
		System.out.println("title = " + title);
		System.out.println("shopname = " + shopname);
		System.out.println("image = " + titleImage);
		System.out.println("category = " + category);
		System.out.println("text = " + text);
		System.out.println("upTime = " + upTime);
		System.out.println("tel = " + tel);
		System.out.println("latitude = " +latitude);
		System.out.println("longitude = " + longitude);
		String url=null;
		
		String date = upTime.substring(0,8);
		
		String startDate =date + "000000";
		String endDate =date + "235959";
		
		System.out.println(startDate);
		System.out.println(endDate);
		
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("userid", userId);
		map.put("startdate", startDate);
		map.put("enddate", endDate);
		
		try{
			service.insertMessage(new Post(userId, title, shopname, category, tel, titleImage, imageNumber, text, upTime, Double.parseDouble(latitude), Double.parseDouble(longitude)));
			
			request.setAttribute("userId", userId);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			
			request.setAttribute("detailData", service.selectByUserIdUptime(map));
			url = "detail.jsp";
			
		}catch(SQLException s){
			//url = "error.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
