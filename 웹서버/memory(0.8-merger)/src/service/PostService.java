package service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.Post;
import dao.PostDAO;

public class PostService {
	
	//PostService
	private static PostService instance = new PostService();
	private PostService(){}
	public static PostService getInstance(){
		return instance;
	}
	
	public void insertMessage(Post data) throws SQLException{
		PostDAO.insertPost(data);
	}
	
	public ArrayList<Post> selectAll() throws SQLException{
		return (ArrayList<Post>)PostDAO.selectAll();
		
	}
	
	public void deletePost(int postid) throws SQLException{
		PostDAO.deletePost(postid);
	}
	
	//deleteDay
	//특정 일에 해당하는 모든 포스팅을 삭제한다.
	//들어가는 입력값은 String 형으로 해서 전달할 것
	public void deleteDay(String dayId) throws SQLException{
		PostDAO.deleteDay(dayId);
	}
}