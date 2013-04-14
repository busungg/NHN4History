/**
 *  DataSender Version 0.6 - 
 *  ���� IP(���� ������ ����)�� ������ �ּҿ� ����ؾ��ϴ� ������ �߻� -> ���� �ذ�
 *  �ؽ�Ʈ ���ڵ� ���� -> �ѱ� �ؽ�Ʈ�� ���������� �̻��� ������ ��µ�(UTF-8 ���ڵ� ����) -> ���� �ذ�
 *  
 *  -������
 *  1. �̹����� MultipartRequest�� ���۵�, ������ �������� �ٷ� img src�� ����� �ȵ�
 *  refresh(F5)�� upload������ ������ �Ŀ� ������ �� -> AJAX�� ����ؾ� ������ �ذ�� �� ����
 *  
 *  2. ��Ƽ��Ƽ�� �߸�, ��ġ EditText�� ��ġ������ ȹ���ؼ� ���� ǥ����
 *  ����ڴ� �� �κ��� ������ ���� �ְ�, �ƴϸ� ���� ���� �״�� ������ ���� ����
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

	//�� �κ��� BasicInfo�� �� -> 0.7 ������ ���Խ�ų ��
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
		// xml ���̾ƿ� ���� �ڵ带 ��� ��üȭ ��Ų��.
		
		//��ġ���� ȹ��

		ImageView photo = (ImageView) findViewById(R.id.insertPhoto);
		// �̹��� �� ��ü ���� �ڵ�

		/**
		 * �̹��� �並 Ŭ���ϸ� � �۾��� ���� �����Ѵ�. �̹��� �� Ŭ���� �ϴ� �� 1. �̹� mediaStore�� ����Ǿ��ִ�
		 * �̹��� ������ �ҷ����� �͸� �ϵ��� �Ѵ�. ��, �̹� ���� �̹��� ������ ����ϴ� ��ɸ� ����
		 * 
		 * �̹� ���� ������ ��� �ִ°�? �� �������� �����ͼ� ��� �迭�� ���ΰ�? ���۷��� �ҽ��󿡼��� Overflow ������
		 * �̿��Ͽ� ����ϰ� ����
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
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		//�ּ� �Էºκ��� ��Ƽ��Ƽ�� ó�� ���۵� ��, ���� �浵������ �� GeoCoding���� ��ġ ������ �ٷ� �Է½��� ���´�.
		editText02 = (EditText) findViewById(R.id.editText02); //�ּ� �Է� �κ�
		
		//���� �浵 ������ ��´�.
		startLocationService();
		//Toast.makeText(getApplicationContext(), "��ġ ���� ȹ�� �� : " + MainActivity.getLatitude() + " " + MainActivity.getLongitude(), Toast.LENGTH_SHORT).show();
		
		//Log.d("resultLocation", resultLocation);
		//editText02.setText(resultLocation);
		
		////////////////////////////////////////////////////////////////////////////////////////////
				
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
							FileUpload.DoFileUpload(picturePath, editText01.getText().toString(), hashLocation);
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
	

    /**
     * ��ġ ���� Ȯ���� ���� ������ �޼ҵ�
     */
    private void startLocationService() {
    	// ��ġ ������ ��ü ����
    	LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// ��ġ ������ ���� ������ ����
    	GPSListener gpsListener = new GPSListener();
		long minTime = 10000;
		float minDistance = 0;

		// GPS�� �̿��� ��ġ ��û
		manager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER,
					minTime,
					minDistance,
					gpsListener);

		// ��Ʈ��ũ�� �̿��� ��ġ ��û
		manager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER,
				minTime,
				minDistance,
				gpsListener);

		// ��ġ Ȯ���� �ȵǴ� ��쿡�� �ֱٿ� Ȯ�ε� ��ġ ���� ���� Ȯ��
		try {
			Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (lastLocation != null) {
				Double latitude = lastLocation.getLatitude();
				Double longitude = lastLocation.getLongitude();
				
				///////////////////////////////////////////////////////////////
				//���� �浵 ����
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

		Toast.makeText(getApplicationContext(), "��ġ Ȯ�� ����", Toast.LENGTH_SHORT).show();

    }

    /**
     * ������ Ŭ���� ����
     */
	private class GPSListener implements LocationListener {
		/**
		 * ��ġ ������ Ȯ�ε� �� �ڵ� ȣ��Ǵ� �޼ҵ�
		 * ��ġ ������ Ȯ�εǸ鼭 �ڵ����� �ش� ��ġ����(����, �浵)�� ȭ�鿡 ǥ���ϵ��� �Ѵ�.(�ǽð� ������ ������ ��)
		 */
	    public void onLocationChanged(Location location) {
			Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();

			String msg = "GPSListener - Latitude : "+ latitude + "\nLongitude:"+ longitude;
			Log.i("GPSListener", msg);
			
			///////////////////////////////////////////////////////////////
			//���� �浵 ����
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
	
	
}// Ŭ���� ������