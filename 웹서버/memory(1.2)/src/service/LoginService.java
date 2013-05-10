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

	//ȸ�� ���� ����
	public void insertLogin(Login data) throws SQLException {
		LoginDAO.insertLogin(data);
	}

	//ȸ�� �α��� �� �� ����� ����
	public Login selectById(String id) throws SQLException {
		return LoginDAO.selectById(id);
	}
}