/*package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOParser;
import dao.LoginDAO;
import dao.ProductDAO;
import dto.Login;
import dto.Product;

public class ProductService {
	
	//singleton pattern
	private static ProductService instance = new ProductService();
	private ProductService(){}
	public static ProductService getInstance(){
		return instance;
	}
	
	//로그??�?�� DAO 로직 구현
	public Login selectByUserId(String userId) throws SQLException{
		return LoginDAO.selectByUserId(userId);
	}	
	
	//?�품 ?�체 목록 보기
	public ArrayList<Product> selectAll() throws SQLException{
		return (ArrayList<Product>)ProductDAO.selectAll();
	}
	
	//?�품 ?�록
	public void insert(Product data) throws SQLException{
		ProductDAO.insert(data);
	}
	
	//?�품 ?�세 조회
	public Product selectById(int pCode) throws SQLException{
		return ProductDAO.selectById(pCode);
	}
	
	public void delete(int pCode) throws SQLException{
		ProductDAO.delete(pCode);
	}
	
	public void update(Product data) throws SQLException{
		ProductDAO.update(data);
	}
	
}*/