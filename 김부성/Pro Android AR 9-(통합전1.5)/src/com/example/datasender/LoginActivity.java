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
 * �α��� ��Ŀ���� ����
 * 1. id, �н����带 ����
 * 2. ���������� ���� id, password�� DB���� Ȯ��
 * 3. ���������� ��ȿ�� ������ �� ����� �ȵ���̵�� ��ȯ�� �����
 * 4. ��ȯ ����� ���� ���� ȭ�� �Ѿ�ų�, �α��� or �н����� Ȯ�� �佺Ʈ ��� 
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
					Toast.makeText(getApplicationContext(), "���̵� �Է��� �ּ���.", Toast.LENGTH_SHORT);
				}else if(password.getText().toString() == null){
					Toast.makeText(getApplicationContext(), "�н����带 �Է��� �ּ���.", Toast.LENGTH_SHORT);
				}else{ // ���̵𰪰� �н����尡 ��� ���� ��쿡�� �Ʒ� ���� ����, �������� �����ؾ���
					//�����ڷ�� �Ʒ�����
					//http://www.androidsnippets.com/get-the-content-from-a-httpresponse-or-any-inputstream-as-a-string
					//http://www.androidsnippets.com/executing-a-http-post-request-with-httpclient
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost("http://203.233.196.146:8080/DataReceiver/message");
					
					try{
						//name, value query String ����
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
						nameValuePairs.add(new BasicNameValuePair("action", "login"));
						nameValuePairs.add(new BasicNameValuePair("id", id.getText().toString()));
						nameValuePairs.add(new BasicNameValuePair("password", password.getText().toString()));
						httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
						
						//Http Post Request ����
						//ResponseHandler<String> reshandler = new BasicResponseHandler();
						HttpResponse response = httpClient.execute(httpPost);
						//response�� ������ ������ ������ Ȯ�� ������
						//String result = httpClient.execute(httpPost, reshandler);
						//Log.d("result", result);
						
						//�������� �Ѿ�� ����� ����
						//���� �Ұ���, � ���ӺҰ����� Ȯ��
						//���� �����̸� MainActivity�� �Ѿ
						Header [] header = response.getHeaders("loginValidate");
						
						if(header[0].getValue().equals("true")){
							//����Ʈ�� ȭ�� �Ѿ
							Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
							startActivity(myIntent);
						}else{
							Toast.makeText(getApplicationContext(), "���̵� �н����带 Ȯ���� �ּ���.", Toast.LENGTH_SHORT).show();
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
		
	}//onCreate �޼��� ������

}//Class ������
