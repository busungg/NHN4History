/**
 *  DataSender Version 0.3 - 
 *  ���� IP(���� ������ ����)�� ������ �ּҿ� ����ؾ��ϴ� ������ �߻� -> ���� �ذ�
 *  �ؽ�Ʈ ���ڵ� ���� -> �ѱ� �ؽ�Ʈ�� ���������� �̻��� ������ ��µ�(UTF-8 ���ڵ� ����) -> ���� �ذ�
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
		// xml ���̾ƿ� ���� �ڵ带 ��� ��üȭ ��Ų��.

		ImageView photo = (ImageView) findViewById(R.id.insertPhoto);
		// �̹��� �� ��ü ���� �ڵ�

		/**
		 * �̹��� �並 Ŭ���ϸ� � �۾��� ���� �����Ѵ�. �̹��� �� Ŭ���� �ϴ� �� 1. �̹� mediaStore�� ����Ǿ��ִ�
		 * �̹��� ������ �ҷ����� �͸� �ϵ��� �Ѵ�. ��, �̹� ���� �̹��� ������ ����ϴ� ��ɸ� ����
		 * 
		 * �̹� ���� ������ ��� �ִ°�? �� �������� �����ͼ� ��� �迭�� ���ΰ�? ���۷��� �ҽ��󿡼��� Overflow ������
		 * �̿��Ͽ� ����ϰ� ����
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
				// Toast.makeText(getApplicationContext(), "Image View Ŭ��",
				// Toast.LENGTH_LONG).show(); -> �����°� Ȯ��
				// showDialog(BasicInfo.CONTENT_PHOTO); -> �̺κ� �ҽ� ���� �м� �ȵ�
				// Ŭ���� ���ο� ��Ƽ��Ƽ�� ����� mediaStore�� �ִ� ������ ������
				Intent myIntent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				startActivityForResult(myIntent, RESULT_LOAD_IMAGE); // �׸� ���� â��
																		// ���� ��
			}// ���� �޼��� ������
		}); // ������ ������

		Button button01 = (Button) findViewById(R.id.button01);
		
		button01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		/**
		 * ����Ÿ ���ε� ��ư�� Ŭ����, 2�� ������ �������� �����ؾ��Ѵ�. 
		 * 1. �׸� ���� (�׸� ������ ��� �������κ�����???) 
		 * 2. �ؽ�Ʈ (���, GPS ���� �� ���߿� ����ȭ)
		 * 3. ���� �浵 ���� -> �� ������ ����ڰ� ���� �Է���
		 * 4. ������ �ø� ��¥ + �ð� ����
		 */
		Button button03 = (Button) findViewById(R.id.button03);	//���� ��ư
		final EditText editText01 = (EditText) findViewById(R.id.editText01); //���۸޽��� �Է� �κ�
		final EditText editText02 = (EditText) findViewById(R.id.editText02); //�ּ� �Է� �κ�
		
		button03.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (picturePath == null) {
					Toast.makeText(getApplicationContext(), "�̹����� ������ �ּ���.",
							Toast.LENGTH_SHORT).show();
				}else if(editText02.getText().toString() == null){
					Toast.makeText(getApplicationContext(), "�ּҸ� �Է��� �ּ���.", Toast.LENGTH_SHORT);
				}else {
					try {
						Log.d("hashLocation", "hashLocation ����");
						HashMap<String, Double> hashLocation = LocationGeocoding.searchLocation(getApplicationContext(), editText02.getText().toString());
						if(hashLocation == null){
							Toast.makeText(getApplicationContext(), "�ּҰ� �ùٸ��� �ʽ��ϴ�.", Toast.LENGTH_SHORT).show();
						}else{
							Log.d("hashLocation", hashLocation.get("latitude") +", " + hashLocation.get("longitude"));
							////////////////////////////////////////////////////////
							//���� ���ε� �ٽ� �޼���
							DoFileUpload(picturePath, editText01.getText().toString(), hashLocation);
							Toast.makeText(getApplicationContext(), "������ �Ϸ�", Toast.LENGTH_SHORT).show();
						}						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		final TextView textView03 = (TextView)findViewById(R.id.textView03);
		//���ڰ� �Էµʿ� ���� �����Ʈ�� �ԷµǾ����� Ȯ����
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
						textView03.setText(text.getBytes("KSC5601").length + "/200 ����Ʈ");
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});		

	}// onCreate ������

	// ��Ƽ��Ƽ ���� ����� Ȯ���ϰ�, �ű⼭ ���õ� �̹����� ȭ�鿡 ����ϵ��� ��
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
		Log.d("DoFileUpload", "DoFileUpload ���� : file path = " + filePath + " text = " + text);
		Log.d("DoFileUpload", hashLocation.get("latitude") +", " + hashLocation.get("longitude"));
		// POST �������� request�� Main Servlet�� ������ ��
		HttpFileUpload("http://14.45.28.47:8080/DataReceiver/message?action=input",
				"", filePath, text, hashLocation);
		
		// IP �ּҿ� ������ ��
		// "http://203.233.196.142:8080/DataReceiver03/list.jsp"
	}

	////////////////////////////////////////////////////////////////////////////////////
	// HttpFileUpload ���� ������ �Ʒ� ����Ʈ ������ ��
	// http://blog.naver.com/legendx/40132716891 ����
	// http://blog.naver.com/boxer61kg?Redirect=Log&logNo=150142349309
	// http://blog.naver.com/PostView.nhn?blogId=q1q3q5&logNo=10099523918
	//http://blog.naver.com/boxer61kg?Redirect=Log&logNo=150142349309 ���ڵ� ������ ���⼭
	private void HttpFileUpload(String urlString, String params,
			String filePath, String text, HashMap<String, Double> hashLocation) {
		
		Log.d("HttpFileUpload", "HttpFileUpload ����");
		String boundary = "SpecificString";
		URL url = null;
		URLConnection con = null;
	
		// ���ڿ� ������ �ҽ�
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
			//�ؽ�Ʈ �κ��� URLEncoder�� ���� URL ���ڵ��� �ؼ� �ذ��Ѵ�.
			//http://www.androidside.com/plugin/mobile/board.php?bo_table=B49&wr_id=98943
			//http://vulpecula.tistory.com/36
			//http://blog.naver.com/PostView.nhn?blogId=boxer61kg&logNo=150141252249
			Log.d("Time", "Time = " + BasicInfo.dateNameFormat4.format(new Date()));
			Log.d("DoFileUpload", hashLocation.get("latitude") +", " + hashLocation.get("longitude"));
			
			//�ؽ�Ʈ ���� �κ�
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
			
			//�̹��� ���� �κ� -> ���ϸ��� ��µ� ������ ���� (�� media store�� ��ΰ� �ٸ��� ����)
			wr.writeBytes("Content-Disposition: form-data; name=\"image\"; filename=\""
					+ filePath.split("/")[filePath.split("/").length-1] + "\"" + "\r\n");
			wr.writeBytes("Content-Type: application/octet-stream\r\n\r\n");
			
			/* 3�� 15�� ��� ������ - �ѱ� ���ڵ� ���� �ذ�
			 wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes("\r\n--" + boundary + "\r\n");
			wr.writeBytes("Content-Disposition: form-data; name=\"text\"\r\n\r\n" + URLEncoder.encode(text, "UTF-8"));
			wr.writeBytes("\r\n--" + boundary + "\r\n");
			wr.writeBytes("Content-Disposition: form-data; name=\"image\"; filename=\""
					+ filePath.split("/")[5] + "\"" + "\r\n");
			wr.writeBytes("Content-Type: application/octet-stream\r\n\r\n");
			 */
			
/*	3�� 14�� ��� ������ - �ѱ� ���ڵ� ���� �ذ� ����		
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

	}// �޼��� ������

}// Ŭ���� ������