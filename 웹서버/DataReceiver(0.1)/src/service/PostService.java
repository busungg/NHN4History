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
}