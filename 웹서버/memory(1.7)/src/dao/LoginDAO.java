package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.Login;
import model.dto.Post;

public class LoginDAO {
	//insert
	public static void insertLogin(Login data) throws SQLException{
		DAOParser.getParser().insert("Login.insertLogin", data);
	}
		
	public static Login selectById(String id) throws SQLException{
		return (Login) DAOParser.getParser().queryForObject("Login.selectById", id);
	}
}
