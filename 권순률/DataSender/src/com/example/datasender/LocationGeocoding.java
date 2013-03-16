package com.example.datasender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

public class LocationGeocoding{
	public static HashMap<String, Double> searchLocation(Context context, String searchStr){
		ArrayList<Address> addressList = null;
		Geocoder gc = null;
		//HashMap<String, HashMap<String, Double>> hashLocation = new HashMap<String, HashMap<String, Double>>();
		HashMap<String, Double> hashLocation = new HashMap<String, Double>();
		
		Log.d("LocationGeocoding", "LocationGeocoding class ����");
		
		try{
			gc = new Geocoder(context, Locale.KOREAN);
			Log.d("LocationGeocoding", "Geocoder ����");
			addressList = (ArrayList<Address>) gc.getFromLocationName(searchStr, 1);
			
			if(!addressList.isEmpty()){
				for(int i = 0; i < addressList.size(); i++){
					hashLocation.put("latitude", addressList.get(i).getLatitude());
					hashLocation.put("longitude", addressList.get(i).getLongitude());
				}
			}else{ // �˻� ����� ���� ����̹Ƿ�, null�� ��ȯ
				return null;
			}			
			/*
			if(!addressList.isEmpty()){
				for(int i = 0; i < addressList.size(); i++){
					int addrCount = addressList.get(i).getMaxAddressLineIndex() + 1;//Ư�� �ּ��� ����
					StringBuffer outAddrStr = new StringBuffer();//Ư�� �ּ� 1���� ���� �� �ּ��� ����
					for(int k = 0; k < addrCount; k++){
						outAddrStr.append(addressList.get(i).getAddressLine(k));
					}					
					hashLocation.put(outAddrStr.toString(), new HashMap<String, Double>());
					hashLocation.get(outAddrStr.toString()).put("Latitude", addressList.get(i).getLatitude());
					hashLocation.get(outAddrStr.toString()).put("Longitude", value)
				}
			}
			*/
			
		}catch(IOException e){
			Log.d("LocationGeocoding error", "Exception : " + e.toString());
		}
		return hashLocation;
	}
}
