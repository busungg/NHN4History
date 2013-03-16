package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dto.Post;

import service.PostService;

public class InputCommand extends Command{

	private static PostService service = PostService.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("input 로직 진입");
		int max = 5 * 1024 * 1024;
		MultipartRequest m = null;
		String url = null;
		String saveDirectory = "C:\\NHN\\0.android\\DataReceiver\\DataReceiver\\WebContent\\upload";
		
		m = new MultipartRequest(request, saveDirectory, max, "UTF-8", new DefaultFileRenamePolicy());

		System.out.println("image name = " + m.getOriginalFileName("image"));	
		System.out.println("변경된 image name = " + m.getFilesystemName("image"));
		
		//String text = m.getParameter("text");
		String text = URLDecoder.decode(m.getParameter("text"), "UTF-8");
		System.out.println("text = " + text);
		System.out.println("uptime = " + m.getParameter("uptime"));
		System.out.println("latitude = " + Double.parseDouble(m.getParameter("latitude")));
		System.out.println("longitude = " + Double.parseDouble(m.getParameter("longitude")));
				
		//String image = m.getOriginalFileName("image");
		//이미지 파일명이 동일한 경우, 이미지 파일명을 변경하고, 변경된 파일 명을 DB에 저장한다.
		//큐브리드 데이터타입 및 자바 날짜 포맷관련 자료 사이트 참고
		//http://cafe.naver.com/studycubrid/547
		String image = m.getFilesystemName("image");
		try{
			//uptime은 DB 상에 Timestamp로 선언된 공간에 insert됨
			service.insertMessage(new Post(image, text, m.getParameter("uptime"), Double.parseDouble(m.getParameter("latitude")),  Double.parseDouble(m.getParameter("longitude"))));
			//request.setAttribute("allData", service.selectAll());
			url = "list.jsp";
		}catch(SQLException s){
			url = "error.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		//response.sendRedirect(url);
	}
}