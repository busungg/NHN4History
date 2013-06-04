package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.dto.Post;
import model.dto.Share;

public class ShareDAO {
		
		// insert
		public static void insertShare(Share data) throws SQLException {
			DAOParser.getParser().insert("Share.insertShare", data);
		}

		public static ArrayList<Share> selectAll() throws SQLException {
			return (ArrayList<Share>) DAOParser.getParser().queryForList(
					"Share.selectAll");
		}

		public static ArrayList<Share> selectId(int shareId) throws SQLException {
			return (ArrayList<Share>) DAOParser.getParser().queryForList(
					"Share.selectId", shareId);
		}
		
		public static void delete(HashMap<String, String> map) throws SQLException {
			DAOParser.getParser().delete("Share.delete", map);
		}
}
