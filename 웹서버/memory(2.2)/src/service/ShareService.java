package service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.Share;
import dao.ShareDAO;

public class ShareService {
	
	//PostService
		private static ShareService instance = new ShareService();
		private ShareService(){}
		public static ShareService getInstance(){
			return instance;
		}
		
		public void insertMessage(Share data) throws SQLException{
			ShareDAO.insertShare(data);
		}
		
		public ArrayList<Share> selectAll() throws SQLException{
			return (ArrayList<Share>)ShareDAO.selectAll();
		}
		
		public ArrayList<Share> selectByUserId(int shareId) throws SQLException{
			return (ArrayList<Share>)ShareDAO.selectId(shareId);
		}
		
		public void delete(int shareId) throws SQLException{
			ShareDAO.delete(shareId);
		}
}
