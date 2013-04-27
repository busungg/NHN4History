package util;

public class Korea{
	
	public static String toKor(String eng){
			String kor = null;
			try{ 
				  kor= new String(eng.getBytes("8859_1"),"euc-kr");
			}catch(java.io.UnsupportedEncodingException encodingError){
				  System.out.println(encodingError);
			}	  
			return kor;
	}	
	public static String toUTF(String eng){
			String kor = null;
			try{ 
				  kor= new String(eng.getBytes("8859_1"),"utf-8");
			}catch(java.io.UnsupportedEncodingException encodingError){
				  System.out.println(encodingError);
			}	  
			return kor;
	}	
}
