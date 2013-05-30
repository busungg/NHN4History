package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

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
		
/*		#userid#, #title#, 
		#shopname#, #category#, 
		#tel#, #image#, #imageNumber#, 
		#text#, #uptime#, 
		#latitude#, #longitude#*/
		
		String userId= (String)session.getAttribute("userId");
		String title=request.getParameter("title");
		String shopname=request.getParameter("shopname");
		String category=request.getParameter("category");
		String tel=request.getParameter("tel");
		String titleImage=request.getParameter("titleImage");
		String text=request.getParameter("htmlText");
		String upTime = request.getParameter("upTime");
		String latitude=request.getParameter("latitude");
		String longitude=request.getParameter("longitude");
		int imageNumber=0;
		
/*		location.href = "../writeCollection.jsp?" +"userId="+userId+"&title="+title+"&titleImage="+titleImage+
				"&category="+category+"&date="+date+"&shopname="+shopname+"&tel="+tel+"&htmlText="+htmlText+"&latitude="+lat+"&longitude="+lng;
*/
		System.out.println(userId);
		System.out.println("title = " + title);
		System.out.println("shopname = " + shopname);
		System.out.println("image = " + titleImage);
		System.out.println("category = " + category);
		System.out.println("text" + text);
		System.out.println("upTime" + upTime);
		System.out.println("tel = " + tel);
		System.out.println("latitude = " +latitude);
		System.out.println("longitude = " + longitude);
		String url=null;
		
		try{
/*			service.insertMessage(new Post((String) session.getAttribute("userId"), title, shopname, category, tel, image, imageNumber, text, m.getParameter("uptime"), Double.parseDouble(m.getParameter("latitude")), Double.parseDouble(m.getParameter("longitude"))));
*/
			service.insertMessage(new Post(userId, title, shopname, category, tel, titleImage, imageNumber, text, upTime, Double.parseDouble(latitude), Double.parseDouble(longitude)));
			//request.setAttribute("allData", service.selectAll());
			url = "mypage.jsp";
			
		}catch(SQLException s){
			//url = "error.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
