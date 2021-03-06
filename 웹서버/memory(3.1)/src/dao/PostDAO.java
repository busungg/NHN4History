package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import model.dto.GetDetail;
import model.dto.Post;

public class PostDAO {

	// insert
	public static void insertPost(Post data) throws SQLException {
		DAOParser.getParser().insert("Post.insertPost", data);
	}

	public static ArrayList<Post> selectAll() throws SQLException {
		return (ArrayList<Post>) DAOParser.getParser().queryForList(
				"Post.selectAll");
	}

	public static ArrayList<Post> selectByUserId(String userId)
			throws SQLException {
		return (ArrayList<Post>) DAOParser.getParser().queryForList(
				"Post.selectByUserId", userId);
	}

	public static ArrayList<Post> selectByUserIdUptime(
			HashMap<String, String> map) throws SQLException {
		return (ArrayList<Post>) DAOParser.getParser().queryForList(
				"Post.selectByUserIdUptime", map);
	}
	
	public static ArrayList<Post> selectByUptime(String uptime) throws SQLException{
		return (ArrayList<Post>)DAOParser.getParser().queryForList("Post.selectByUptime", uptime);
	}

	public static void deletePost(int postid) throws SQLException {
		DAOParser.getParser().delete("Post.deletePost", postid);
	}

	public static void deleteDay(String dayId) throws SQLException {
		DAOParser.getParser().delete("Post.deleteDay", dayId);
	}
}
