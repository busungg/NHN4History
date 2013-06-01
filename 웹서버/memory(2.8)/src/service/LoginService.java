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

	//회원 가입 메서드
	public void insertLogin(Login data) throws SQLException {
		LoginDAO.insertLogin(data);
	}

	//유저 ID값을 통해서 해당 ID가 존재하는지 확인하는 메서드
	public Login selectById(String id) throws SQLException {
		return LoginDAO.selectById(id);
	}
}