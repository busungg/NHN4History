package com.example.datasender;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.paar.ch9.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 로그인 매커니즘 구현
 * 1. id, 패스워드를 전달
 * 2. 웹서버에서 받은 id, password를 DB에서 확인
 * 3. 웹서버에서 유효성 검증후 그 결과를 안드로이드로 반환해 줘야함
 * 4. 반환 결과에 따라 다음 화면 넘어가거나, 로그인 or 패스워드 확인 토스트 띄움 
 * @author k
 */

public class LoginActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginactivity);
		
		final EditText id = (EditText)findViewById(R.id.usernameEntry);
		final EditText password = (EditText)findViewById(R.id.passwordEntry);
		
		Button loginBtn = (Button)findViewById(R.id.loginBtn);
		loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(id.getText().toString() == null){
					Toast.makeText(getApplicationContext(), "아이디를 입력해 주세요.", Toast.LENGTH_SHORT);
				}else if(password.getText().toString() == null){
					Toast.makeText(getApplicationContext(), "패스워드를 입력해 주세요.", Toast.LENGTH_SHORT);
				}else{ // 아이디값과 패스워드가 모두 적힌 경우에만 아래 로직 수행, 웹서버로 전송해야함
					//참고자료는 아래에서
					//http://www.androidsnippets.com/get-the-content-from-a-httpresponse-or-any-inputstream-as-a-string
					//http://www.androidsnippets.com/executing-a-http-post-request-with-httpclient
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost("http://203.233.196.146:8080/DataReceiver/message");
					
					try{
						//name, value query String 생성
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
						nameValuePairs.add(new BasicNameValuePair("action", "login"));
						nameValuePairs.add(new BasicNameValuePair("id", id.getText().toString()));
						nameValuePairs.add(new BasicNameValuePair("password", password.getText().toString()));
						httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
						
						//Http Post Request 전송
						//ResponseHandler<String> reshandler = new BasicResponseHandler();
						HttpResponse response = httpClient.execute(httpPost);
						//response에 서버로 전송한 값들을 확인 가능함
						//String result = httpClient.execute(httpPost, reshandler);
						//Log.d("result", result);
						
						//서버에서 넘어온 결과에 따라서
						//접속 불가면, 어떤 접속불가인지 확인
						//접속 가능이면 MainActivity로 넘어감
						Header [] header = response.getHeaders("loginValidate");
						
						if(header[0].getValue().equals("true")){
							//인텐트로 화면 넘어감
							Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
							startActivity(myIntent);
						}else{
							Toast.makeText(getApplicationContext(), "아이디나 패스워드를 확인해 주세요.", Toast.LENGTH_SHORT).show();
						}
						//Log.d("header", header[0].getValue());
						
						//String result = inputStreamToString(response.getEntity().getContent()).toString();
						//Log.d("result", result);
					}catch(ClientProtocolException e){
						Log.d("ClientProtocolException", e.toString());
					}catch(IOException e){
						Log.d("IOException", e.toString());
					}
				}
			}
		});
		
		Button exitBtn = (Button)findViewById(R.id.exitBtn);
		exitBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();				
			}
		});		
		
	}//onCreate 메서드 마지막

}//Class 마지막
