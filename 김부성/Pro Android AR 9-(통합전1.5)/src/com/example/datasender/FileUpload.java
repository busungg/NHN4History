package com.example.datasender;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;

import android.util.Log;

public class FileUpload {
	public static void DoFileUpload(String filePath, String text, HashMap<String, Double> hashLocation) throws IOException {
		Log.d("DoFileUpload", "DoFileUpload 진입 : file path = " + filePath + " text = " + text);
		Log.d("DoFileUpload", hashLocation.get("latitude") +", " + hashLocation.get("longitude"));
		// POST 형식으로 request를 Main Servlet에 보내게 됨
		HttpFileUpload("http://203.233.196.146:8080/DataReceiver/message?action=input",
				"", filePath, text, hashLocation);
		
		// IP 주소에 주의할 것
		// "http://203.233.196.142:8080/DataReceiver03/list.jsp"
	}

	////////////////////////////////////////////////////////////////////////////////////
	// HttpFileUpload 로직 구현은 아래 사이트 참고할 것
	// http://blog.naver.com/legendx/40132716891 참고
	// http://blog.naver.com/boxer61kg?Redirect=Log&logNo=150142349309
	// http://blog.naver.com/PostView.nhn?blogId=q1q3q5&logNo=10099523918
	//http://blog.naver.com/boxer61kg?Redirect=Log&logNo=150142349309 인코딩 문제는 여기서
	public static void HttpFileUpload(String urlString, String params,
			String filePath, String text, HashMap<String, Double> hashLocation) {
		
		Log.d("HttpFileUpload", "HttpFileUpload 진입");
		String boundary = "SpecificString";
		URL url = null;
		URLConnection con = null;
	
		// 문자열 보내는 소스
		DataOutputStream wr = null;
		FileInputStream fileInputStream = null;
		BufferedReader rd = null;

		try {
			url = new URL(urlString);
			con = url.openConnection();
			con.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + boundary);
			con.setDoOutput(true);
			
			/////////////////////////////////////////////////////////////////////////////////////////
			//텍스트 부분은 URLEncoder를 통해 URL 인코딩을 해서 해결한다.
			//http://www.androidside.com/plugin/mobile/board.php?bo_table=B49&wr_id=98943
			//http://vulpecula.tistory.com/36
			//http://blog.naver.com/PostView.nhn?blogId=boxer61kg&logNo=150141252249
			Log.d("Time", "Time = " + BasicInfo.dateNameFormat4.format(new Date()));
			Log.d("DoFileUpload", hashLocation.get("latitude") +", " + hashLocation.get("longitude"));
			
			//텍스트 전송 부분
			wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes("\r\n--" + boundary + "\r\n");
			wr.writeBytes("Content-Disposition: form-data; name=\"text\"\r\n\r\n" + URLEncoder.encode(text, "UTF-8"));
			wr.writeBytes("\r\n--" + boundary + "\r\n");
			//wr.writeBytes("\r\n--" + boundary + "\r\n");
			wr.writeBytes("Content-Disposition: form-data; name=\"latitude\"\r\n\r\n" + hashLocation.get("latitude"));
			wr.writeBytes("\r\n--" + boundary + "\r\n");
			//wr.writeBytes("\r\n--" + boundary + "\r\n");
			wr.writeBytes("Content-Disposition: form-data; name=\"longitude\"\r\n\r\n" + hashLocation.get("longitude"));
			wr.writeBytes("\r\n--" + boundary + "\r\n");
			//wr.writeBytes("\r\n--" + boundary + "\r\n");
			wr.writeBytes("Content-Disposition: form-data; name=\"uptime\"\r\n\r\n" + BasicInfo.dateNameFormat4.format(new Date()));
			wr.writeBytes("\r\n--" + boundary + "\r\n");
			
			//이미지 전송 부분 -> 파일명을 잡는데 문제가 생김 (각 media store의 경로가 다르기 때문)
			wr.writeBytes("Content-Disposition: form-data; name=\"image\"; filename=\""
					+ filePath.split("/")[filePath.split("/").length-1] + "\"" + "\r\n");
			wr.writeBytes("Content-Type: application/octet-stream\r\n\r\n");
			
			/* 3월 15일 백업 데이터 - 한글 인코딩 문제 해결
			 wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes("\r\n--" + boundary + "\r\n");
			wr.writeBytes("Content-Disposition: form-data; name=\"text\"\r\n\r\n" + URLEncoder.encode(text, "UTF-8"));
			wr.writeBytes("\r\n--" + boundary + "\r\n");
			wr.writeBytes("Content-Disposition: form-data; name=\"image\"; filename=\""
					+ filePath.split("/")[5] + "\"" + "\r\n");
			wr.writeBytes("Content-Type: application/octet-stream\r\n\r\n");
			 */
			
/*	3월 14일 백업 데이터 - 한글 인코딩 문제 해결 이전		
 * wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes("\r\n--" + boundary + "\r\n");
			wr.writeBytes("Content-Disposition: form-data; name=\"text\"\r\n\r\n"
					+ text);
			wr.writeBytes("\r\n--" + boundary + "\r\n");
			wr.writeBytes("Content-Disposition: form-data; name=\"image\"; filename=\""
					+ filePath.split("/")[5] + "\"" + "\r\n");
			wr.writeBytes("Content-Type: application/octet-stream\r\n\r\n");*/
	
			////////////////////////////////////////////////////////////////////////////////////

			fileInputStream = new FileInputStream(filePath);
			int bytesAvailable = fileInputStream.available();
			int maxBufferSize = 1024;
			int bufferSize = Math.min(bytesAvailable, maxBufferSize);
			byte[] buffer = new byte[bufferSize];
			int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			while (bytesRead > 0) {
				// Upload file part(s)
				DataOutputStream dataWrite = new DataOutputStream(
						con.getOutputStream());
				dataWrite.write(buffer, 0, bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			}
			fileInputStream.close();

			wr.writeBytes("\r\n--" + boundary + "--\r\n");
			wr.flush();

			rd = new BufferedReader(new InputStreamReader(con.getInputStream(),
					"UTF-8"));
			String line = null;
			while ((line = rd.readLine()) != null) {
				Log.i("BestClinic", line);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// 메서드 마지막
}
