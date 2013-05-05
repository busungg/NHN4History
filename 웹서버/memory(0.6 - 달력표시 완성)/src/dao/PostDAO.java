package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.Post;

public class PostDAO {
	//insert
	public static void insertPost(Post data) throws SQLException{
		DAOParser.getParser().insert("Post.insertPost", data);
	}
	
	public static ArrayList<Post> selectAll() throws SQLException{
		return (ArrayList<Post>) DAOParser.getParser().queryForList("Post.selectAll");
	}
	
	public static void deletePost(int postid) throws SQLException{
		DAOParser.getParser().delete("Post.deletePost", postid);
	}
	
	public static void deleteDay(String dayId) throws SQLException{
		DAOParser.getParser().delete("Post.deleteDay", dayId);
	}
}
