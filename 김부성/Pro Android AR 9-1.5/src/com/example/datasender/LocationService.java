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
 * LocationService -> ��Ƽ��Ƽ ���Խ� GPS ������ ��� ���� ����ϴ� ����
 * ��ġ ������ �ľ��ؼ� �ϴ� ����, �浵������ �����ϰ� �޼ҵ忡�� �ش� ���� �浵 ������ ��ȯ
 * @author k
 *
 *//*

public class LocationService{
	*//**
     * ��ġ ���� Ȯ���� ���� ������ �޼ҵ�
     *//*
    public static void startLocationService(Context context) {
    	// ��ġ ������ ��ü ����
    	LocationManager manager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

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

				Toast.makeText(context.getApplicationContext(), "Last Known Location : " + "Latitude : "+ latitude + "\nLongitude:"+ longitude, Toast.LENGTH_LONG).show();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		Toast.makeText(context.getApplicationContext(), "��ġ Ȯ�� ����", Toast.LENGTH_SHORT).show();

    }

    *//**
     * ������ Ŭ���� ����
     *//*
	private class GPSListener implements LocationListener {
		*//**
		 * ��ġ ������ Ȯ�ε� �� �ڵ� ȣ��Ǵ� �޼ҵ�
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
	 * ��ġ ���� Ȯ���� ���� ������ �޼ҵ�
	 *//*
	private static Context cont;//context�� �����ϴ� Ŭ���� ���� ����
	
	public static void startLocationService(Context context) {
		cont = context;
		// ��ġ ������ ��ü ����
		LocationManager manager = (LocationManager)cont.getSystemService(Context.LOCATION_SERVICE);
		
		// ��ġ ������ ���� ������ ����
		GPSListener gpsListener = new GPSListener();
		long minTime = 10000;
		float minDistance = 0;

		// GPS�� �̿��� ��ġ ��û
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime,
				minDistance, gpsListener);

		// ��Ʈ��ũ�� �̿��� ��ġ ��û
		manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
				minTime, minDistance, gpsListener);

		// ��ġ Ȯ���� �ȵǴ� ��쿡�� �ֱٿ� Ȯ�ε� ��ġ ���� ���� Ȯ��
		try {
			Location lastLocation = manager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (lastLocation != null) {
				Double latitude = lastLocation.getLatitude(); //����
				Double longitude = lastLocation.getLongitude(); //�浵

				Toast.makeText(
						cont.getApplicationContext(),
						"Last Known Location : " + "Latitude : " + latitude
								+ "\nLongitude:" + longitude, Toast.LENGTH_LONG)
						.show();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Toast.makeText(cont.getApplicationContext(), "��ġ Ȯ�� ����",
				Toast.LENGTH_SHORT).show();

	}

	*//**
	 * ������ Ŭ���� ����
	 *//*
	private class GPSListener implements LocationListener {
		*//**
		 * ��ġ ������ Ȯ�ε� �� �ڵ� ȣ��Ǵ� �޼ҵ�
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
