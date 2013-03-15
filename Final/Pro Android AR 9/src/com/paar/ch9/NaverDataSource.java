package com.paar.ch9;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

public class NaverDataSource extends NetworkDataSource {
	private static final String BASE_URL = "http://map.naver.com/search2/searchCompanyInRadius.nhn?pageSize=100";

	private static Bitmap icon = null;
	
	public NaverDataSource(Resources res) {        
	    if (res==null) throw new NullPointerException();
        
	    createIcon(res);
    }

    protected void createIcon(Resources res) {
        if (res==null) throw new NullPointerException();
   
        icon=BitmapFactory.decodeResource(res, R.drawable.naver);
    }

	@Override
	public String createRequestURL(double lat, double lon, double alt, float radius, String locale) {
		return null;
	}
	
	//http://map.naver.com/search2/searchCompanyInRadius.nhn?pageSize=100&xPos=127.0487089&yPos=37.518352&radius=1000&query=%EC%8A%88%ED%8D%BC
	//네이버 종류
	@Override
	public String createRequestURL(double xPos, double yPos, double radius, String query)
	{
		return BASE_URL + "&xPos=" + xPos + "&yPos=" + yPos + "&radius=" + radius + "&query=" + query;
	}

	@Override
	public List<Marker> parse(JSONObject root) {
		if (root==null) return null;
		
		JSONObject jo = null;
		JSONArray dataArray = null;
    	List<Marker> markers=new ArrayList<Marker>();

		try {
			if(root.has("result")) 
			{
				dataArray = root.getJSONObject("result").getJSONObject("items").getJSONArray("item");
			}
			
			if (dataArray == null) 
			{
				return markers;
			}
				
			int top = Math.min(MAX, dataArray.length());
			for (int i = 0; i < top; i++) {					
				jo = dataArray.getJSONObject(i);
				Marker ma = processJSONObject(jo);
				if(ma!=null) markers.add(ma);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return markers;
	}
	
	private Marker processJSONObject(JSONObject jo) {
		if (jo==null) return null;
		
        Marker ma = null;
        
        if (	jo.has("comID") && 
        		jo.has("name") &&
        		jo.has("longitude") && 
        		jo.has("latitude") ) 
        {
        	try 
        	{
        		Log.d("Naver", jo.getString("comID") + " " +
        				jo.getString("name") + " " +
        				jo.getDouble("longitude") + " " +
        				jo.getDouble("latitude"));
        		
        		ma = new IconMarker(
        				jo.getString("comID"),
        				jo.getString("name"),
        				jo.getDouble("latitude"),
        				jo.getDouble("longitude"),
        				100,
        				Color.WHITE,
        				icon);
        	} 
        	catch (JSONException e) 
        	{
        		e.printStackTrace();
        	}
        }
        return ma;
	}
}