package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
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
		System.out.println("PC 포스팅 로직 진입");
		
		//수정해야함
		HttpSession session = request.getSession(false);
		
		int max = 5 * 1024 * 1024;
		MultipartRequest m = null;
		String url = null;
		//saveDirectory는 항상 자기 시스템에 맞게 경로 찾아서 적을 것
		String saveDirectory = "C:\\Users\\k\\Desktop\\1. webserver version\\memory(0.2)\\WebContent\\upload";
		//String saveDirectory = "C:\\Users\\k\Desktop\\1. webserver version\\memory(0.2)\\WebContent\\upload";
		m = new MultipartRequest(request, saveDirectory, max, "UTF-8",
				new DefaultFileRenamePolicy());

		//포맷을 정해서 가져와야함
		//이미지 가져오는것 중요
		String text = URLDecoder.decode(m.getParameter("text"), "UTF-8");
		String title = URLDecoder.decode(m.getParameter("title"), "UTF-8");
		String shopname = URLDecoder.decode(m.getParameter("shopname"), "UTF-8");
		String category = URLDecoder.decode(m.getParameter("category"), "UTF-8");
		int imageNumber = Integer.parseInt(m.getParameter("imageNumber"));
		String tel = m.getParameter("tel");
		
		System.out.println("title = " + title);
		System.out.println("shopname = " + shopname);
		System.out.println("category = " + category);
		System.out.println("tel = " + tel);
		System.out.println("image = " + m.getFilesystemName("image"));
		System.out.println();
		System.out.println("text = " + text);
		System.out.println("uptime = " + m.getParameter("uptime"));
		System.out.println("latitude = "
				+ Double.parseDouble(m.getParameter("latitude")));
		System.out.println("longitude = "
				+ Double.parseDouble(m.getParameter("longitude")));
		
		String image = m.getFilesystemName("image");
		
		try{
			service.insertMessage(new Post(1, (String) session.getAttribute("userId"), title, shopname, category, tel, image, imageNumber, text, m.getParameter("uptime"), Double.parseDouble(m.getParameter("latitude")),  Double.parseDouble(m.getParameter("longitude"))));
			//request.setAttribute("allData", service.selectAll());
			//url = "list.jsp";
		}catch(SQLException s){
			//url = "error.jsp";
		}
		// request.getRequestDispatcher("post.jsp").forward(request, response);
	}
}
