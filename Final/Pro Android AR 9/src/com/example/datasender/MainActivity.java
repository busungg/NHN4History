/**
 *  DataSender Version 0.6 - 
 *  고정 IP(랜선 연결한 상태)의 웹서버 주소와 통신해야하는 문제점 발생 -> 문제 해결
 *  텍스트 인코딩 문제 -> 한글 텍스트는 웹서버에서 이상한 값으로 출력됨(UTF-8 인코딩 기준) -> 문제 해결
 *  
 *  -문제점
 *  1. 이미지는 MultipartRequest로 전송됨, 하지만 서버에서 바로 img src로 출력이 안됨
 *  refresh(F5)를 upload폴더에 수행한 후에 적용이 됨 -> AJAX를 사용해야 문제가 해결될 것 같음
 *  
 *  2. 액티비티가 뜨면, 위치 EditText에 위치정보를 획득해서 먼저 표시함
 *  사용자는 이 부분을 수정할 수도 있고, 아니면 원래 값을 그대로 가져갈 수도 있음
 *  
 */
package com.example.datasender;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import com.paar.ch9.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

	//이 부분은 BasicInfo로 들어감 -> 0.7 버전에 포함시킬 것
	private final static int RESULT_LOAD_IMAGE = 1;
	
	private String picturePath;
	private static double latitude;
	private static double longitude;
	private static String resultLocation;
	private static EditText editText02;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// xml 레이아웃 상의 코드를 모두 객체화 시킨다.
		
		//위치정보 획득

		ImageView photo = (ImageView) findViewById(R.id.insertPhoto);
		// 이미지 뷰 객체 참조 코드

		/**
		 * 이미지 뷰를 클릭하면 어떤 작업을 할지 정의한다. 이미지 뷰 클릭시 하는 일 1. 이미 mediaStore에 저장되어있는
		 * 이미지 파일을 불러오는 것만 하도록 한다. 즉, 이미 찍은 이미지 파일을 등록하는 기능만 구현
		 * 
		 * 이미 찍은 사진이 어디에 있는가? 그 사진들을 가져와서 어떻게 배열할 것인가? 레퍼런스 소스상에서는 Overflow 위젯을
		 * 이용하여 출력하고 있음
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
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		//주소 입력부분은 액티비티가 처음 시작될 때, 위도 경도정보를 얻어서 GeoCoding으로 위치 정보를 바로 입력시켜 놓는다.
		editText02 = (EditText) findViewById(R.id.editText02); //주소 입력 부분
		
		//위도 경도 정보를 얻는다.
		startLocationService();
		//Toast.makeText(getApplicationContext(), "위치 정보 획득 후 : " + MainActivity.getLatitude() + " " + MainActivity.getLongitude(), Toast.LENGTH_SHORT).show();
		
		//Log.d("resultLocation", resultLocation);
		//editText02.setText(resultLocation);
		
		////////////////////////////////////////////////////////////////////////////////////////////
				
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
							FileUpload.DoFileUpload(picturePath, editText01.getText().toString(), hashLocation);
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
	

    /**
     * 위치 정보 확인을 위해 정의한 메소드
     */
    private void startLocationService() {
    	// 위치 관리자 객체 참조
    	LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// 위치 정보를 받을 리스너 생성
    	GPSListener gpsListener = new GPSListener();
		long minTime = 10000;
		float minDistance = 0;

		// GPS를 이용한 위치 요청
		manager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER,
					minTime,
					minDistance,
					gpsListener);

		// 네트워크를 이용한 위치 요청
		manager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER,
				minTime,
				minDistance,
				gpsListener);

		// 위치 확인이 안되는 경우에도 최근에 확인된 위치 정보 먼저 확인
		try {
			Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (lastLocation != null) {
				Double latitude = lastLocation.getLatitude();
				Double longitude = lastLocation.getLongitude();
				
				///////////////////////////////////////////////////////////////
				//위도 경도 지정
				MainActivity.setLatitude(latitude);
				MainActivity.setLongitude(longitude);
				Log.d("latitude", "latitude = " + MainActivity.getLatitude());
				Log.d("longtude", "longtude = " + MainActivity.getLongitude());
				resultLocation = LocationGeocoding.searchLocation(getApplicationContext(), MainActivity.getLatitude(), MainActivity.getLongitude()).get(0);
				Log.d("resultLocation", "resultLocation = " + resultLocation);
				////////////////////////////////////////////////////////////////
				
				Toast.makeText(getApplicationContext(), "Last Known Location : " + "Latitude : "+ latitude + "\nLongitude:"+ longitude, Toast.LENGTH_LONG).show();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		Toast.makeText(getApplicationContext(), "위치 확인 시작", Toast.LENGTH_SHORT).show();

    }

    /**
     * 리스너 클래스 정의
     */
	private class GPSListener implements LocationListener {
		/**
		 * 위치 정보가 확인될 때 자동 호출되는 메소드
		 * 위치 정보가 확인되면서 자동으로 해당 위치정보(위도, 경도)를 화면에 표시하도록 한다.(실시간 반응에 주의할 것)
		 */
	    public void onLocationChanged(Location location) {
			Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();

			String msg = "GPSListener - Latitude : "+ latitude + "\nLongitude:"+ longitude;
			Log.i("GPSListener", msg);
			
			///////////////////////////////////////////////////////////////
			//위도 경도 지정
			MainActivity.setLatitude(latitude);
			MainActivity.setLongitude(longitude);
			Log.d("latitude", "latitude = " + MainActivity.getLatitude());
			Log.d("longtude", "longtude = " + MainActivity.getLongitude());
			resultLocation = LocationGeocoding.searchLocation(getApplicationContext(), MainActivity.getLatitude(), MainActivity.getLongitude()).get(0);
			Log.d("resultLocation", "resultLocation = " + resultLocation);
			editText02.setText(resultLocation);
			////////////////////////////////////////////////////////////////
			
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		}

	    public void onProviderDisabled(String provider) {
	    }

	    public void onProviderEnabled(String provider) {
	    }

	    public void onStatusChanged(String provider, int status, Bundle extras) {
	    }

	}

	public static double getLatitude() {
		return latitude;
	}

	public static void setLatitude(double latitude) {
		MainActivity.latitude = latitude;
	}

	public static double getLongitude() {
		return longitude;
	}

	public static void setLongitude(double longitude) {
		MainActivity.longitude = longitude;
	}
	
	
}// 클래스 마지막