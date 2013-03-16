/**
 *  DataSender Version 0.3 - 
 *  고정 IP(랜선 연결한 상태)의 웹서버 주소와 통신해야하는 문제점 발생 -> 문제 해결
 *  텍스트 인코딩 문제 -> 한글 텍스트는 웹서버에서 이상한 값으로 출력됨(UTF-8 인코딩 기준) -> 문제 해결
 */
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

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final static int RESULT_LOAD_IMAGE = 1;
	private String picturePath = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// xml 레이아웃 상의 코드를 모두 객체화 시킨다.

		ImageView photo = (ImageView) findViewById(R.id.insertPhoto);
		// 이미지 뷰 객체 참조 코드

		/**
		 * 이미지 뷰를 클릭하면 어떤 작업을 할지 정의한다. 이미지 뷰 클릭시 하는 일 1. 이미 mediaStore에 저장되어있는
		 * 이미지 파일을 불러오는 것만 하도록 한다. 즉, 이미 찍은 이미지 파일을 등록하는 기능만 구현
		 * 
		 * 이미 찍은 사진이 어디에 있는가? 그 사진들을 가져와서 어떻게 배열할 것인가? 레퍼런스 소스상에서는 Overflow 위젯을
		 * 이용하여 출력하고 있음
		 * 
		 * mPhoto.setOnClickListener(new OnClickListener() { public void
		 * onClick(View v) { if(isPhotoCaptured || isPhotoFileSaved) {
		 * showDialog(BasicInfo.CONTENT_PHOTO_EX); } else {
		 * showDialog(BasicInfo.CONTENT_PHOTO); } } });
		 * 
		 */
		photo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Toast.makeText(getApplicationContext(), "Image View 클릭",
				// Toast.LENGTH_LONG).show(); -> 들어가지는거 확인
				// showDialog(BasicInfo.CONTENT_PHOTO); -> 이부분 소스 전혀 분석 안됨
				// 클릭시 새로운 액티비티를 띄워서 mediaStore에 있는 사진을 가져옴
				Intent myIntent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				startActivityForResult(myIntent, RESULT_LOAD_IMAGE); // 그림 선택 창을
																		// 띄우게 됨
			}// 내부 메서드 마지막
		}); // 리스너 마지막

		Button button01 = (Button) findViewById(R.id.button01);
		
		button01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		/**
		 * 데이타 업로드 버튼을 클릭시, 2개 정보가 웹서버로 전송해야한다. 
		 * 1. 그림 파일 (그림 파일은 어떻게 웹서버로보낼까???) 
		 * 2. 텍스트 (댓글, GPS 정보 등 나중에 세분화)
		 * 3. 위도 경도 정보 -> 이 정보는 사용자가 직접 입력함
		 * 4. 사진을 올린 날짜 + 시간 정보
		 */
		Button button03 = (Button) findViewById(R.id.button03);	//전송 버튼
		final EditText editText01 = (EditText) findViewById(R.id.editText01); //전송메시지 입력 부분
		final EditText editText02 = (EditText) findViewById(R.id.editText02); //주소 입력 부분
		
		button03.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (picturePath == null) {
					Toast.makeText(getApplicationContext(), "이미지를 선택해 주세요.",
							Toast.LENGTH_SHORT).show();
				}else if(editText02.getText().toString() == null){
					Toast.makeText(getApplicationContext(), "주소를 입력해 주세요.", Toast.LENGTH_SHORT);
				}else {
					try {
						Log.d("hashLocation", "hashLocation 직전");
						HashMap<String, Double> hashLocation = LocationGeocoding.searchLocation(getApplicationContext(), editText02.getText().toString());
						if(hashLocation == null){
							Toast.makeText(getApplicationContext(), "주소가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
						}else{
							Log.d("hashLocation", hashLocation.get("latitude") +", " + hashLocation.get("longitude"));
							////////////////////////////////////////////////////////
							//파일 업로드 핵심 메서드
							DoFileUpload(picturePath, editText01.getText().toString(), hashLocation);
							Toast.makeText(getApplicationContext(), "포스팅 완료", Toast.LENGTH_SHORT).show();
						}						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		final TextView textView03 = (TextView)findViewById(R.id.textView03);
		//문자가 입력됨에 따라 몇바이트가 입력되었는지 확인함
		editText01.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				String text = s.toString();
				try {
					if(s.toString().getBytes().length > 200){
						s.delete(s.length()-2, s.length()-1);
					}else{
						textView03.setText(text.getBytes("KSC5601").length + "/200 바이트");
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});		

	}// onCreate 마지막

	// 액티비티 새로 띄워서 확인하고, 거기서 선택된 이미지를 화면에 출력하도록 함
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			picturePath = cursor.getString(columnIndex);
			cursor.close();

			//Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
			ImageView imageView = (ImageView) findViewById(R.id.insertPhoto);
			imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

		}
	}

	private void DoFileUpload(String filePath, String text, HashMap<String, Double> hashLocation) throws IOException {
		Log.d("DoFileUpload", "DoFileUpload 진입 : file path = " + filePath + " text = " + text);
		Log.d("DoFileUpload", hashLocation.get("latitude") +", " + hashLocation.get("longitude"));
		// POST 형식으로 request를 Main Servlet에 보내게 됨
		HttpFileUpload("http://14.45.28.47:8080/DataReceiver/message?action=input",
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
	private void HttpFileUpload(String urlString, String params,
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

}// 클래스 마지막