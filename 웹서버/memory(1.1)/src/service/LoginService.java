package service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.Login;
import model.dto.Post;
import dao.LoginDAO;
import dao.PostDAO;

public class LoginService {

	// PostService
	private static LoginService instance = new LoginService();

	private LoginService() {
	}

	public static LoginService getInstance() {
		return instance;
	}

	//회원 가입 로직
	public void insertLogin(Login data) throws SQLException {
		LoginDAO.insertLogin(data);
	}

	//회원 로그인 할 시 사용할 로직
	public Login selectById(String id) throws SQLException {
		return LoginDAO.selectById(id);
	}
}