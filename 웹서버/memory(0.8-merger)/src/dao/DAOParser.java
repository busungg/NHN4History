package dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;


public class DAOParser {
	private static SqlMapClient sqlMapper;
	public static SqlMapClient getParser(){
	    if(sqlMapper == null ){
			try {
		        Reader reader = Resources.getResourceAsReader("dbConfig/SqlMapConfig.xml");
		        sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		        System.out.println("sql mapping complete");
		        reader.close(); 
		      } catch (IOException e) {
		    	 System.out.println("XML Cofig Load !!"+e);
		      }	
	    }
		return sqlMapper;
	}
}