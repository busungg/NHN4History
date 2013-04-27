/*package com.example.datasender;

import org.androidtown.lbs.location.MainActivity.GPSListener;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

*//**
 * LocationService -> 액티비티 진입시 GPS 정보를 얻기 위해 사용하는 서비스
 * 위치 정보를 파악해서 일단 위도, 경도정보를 저장하고 메소드에서 해당 위도 경도 정보를 반환
 * @author k
 *
 *//*

public class LocationService{
	*//**
     * 위치 정보 확인을 위해 정의한 메소드
     *//*
    public static void startLocationService(Context context) {
    	// 위치 관리자 객체 참조
    	LocationManager manager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

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

				Toast.makeText(context.getApplicationContext(), "Last Known Location : " + "Latitude : "+ latitude + "\nLongitude:"+ longitude, Toast.LENGTH_LONG).show();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		Toast.makeText(context.getApplicationContext(), "위치 확인 시작", Toast.LENGTH_SHORT).show();

    }

    *//**
     * 리스너 클래스 정의
     *//*
	private class GPSListener implements LocationListener {
		*//**
		 * 위치 정보가 확인될 때 자동 호출되는 메소드
		 *//*
	    public void onLocationChanged(Location location) {
			Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();

			String msg = "Latitude : "+ latitude + "\nLongitude:"+ longitude;
			Log.i("GPSListener", msg);
			
			Toast.makeText(.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		}

	    public void onProviderDisabled(String provider) {
	    }

	    public void onProviderEnabled(String provider) {
	    }

	    public void onStatusChanged(String provider, int status, Bundle extras) {
	    }

	}

}*/


/*
public class LocationService {
	*//**
	 * 위치 정보 확인을 위해 정의한 메소드
	 *//*
	private static Context cont;//context를 저장하는 클래스 내부 변수
	
	public static void startLocationService(Context context) {
		cont = context;
		// 위치 관리자 객체 참조
		LocationManager manager = (LocationManager)cont.getSystemService(Context.LOCATION_SERVICE);
		
		// 위치 정보를 받을 리스너 생성
		GPSListener gpsListener = new GPSListener();
		long minTime = 10000;
		float minDistance = 0;

		// GPS를 이용한 위치 요청
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime,
				minDistance, gpsListener);

		// 네트워크를 이용한 위치 요청
		manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
				minTime, minDistance, gpsListener);

		// 위치 확인이 안되는 경우에도 최근에 확인된 위치 정보 먼저 확인
		try {
			Location lastLocation = manager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (lastLocation != null) {
				Double latitude = lastLocation.getLatitude(); //위도
				Double longitude = lastLocation.getLongitude(); //경도

				Toast.makeText(
						cont.getApplicationContext(),
						"Last Known Location : " + "Latitude : " + latitude
								+ "\nLongitude:" + longitude, Toast.LENGTH_LONG)
						.show();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Toast.makeText(cont.getApplicationContext(), "위치 확인 시작",
				Toast.LENGTH_SHORT).show();

	}

	*//**
	 * 리스너 클래스 정의
	 *//*
	private class GPSListener implements LocationListener {
		*//**
		 * 위치 정보가 확인될 때 자동 호출되는 메소드
		 *//*
		public void onLocationChanged(Location location) {
			Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();

			String msg = "Latitude : " + latitude + "\nLongitude:" + longitude;
			Log.i("GPSListener", msg);

			Toast.makeText(cont.getApplicationContext(), msg, Toast.LENGTH_SHORT)
					.show();
		}

		public void onProviderDisabled(String provider) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

	}
}*/
