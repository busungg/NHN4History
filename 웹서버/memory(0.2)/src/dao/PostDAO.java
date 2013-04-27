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
}
