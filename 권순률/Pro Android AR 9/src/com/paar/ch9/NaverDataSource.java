package com.paar.ch9;

import java.io.InputStream;
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
	
	protected static Bitmap icon_eat = null;
	protected static Bitmap icon_cafe = null;
	protected static Bitmap icon_drink = null;
	protected static Bitmap icon_shopping = null;
	protected static Bitmap icon_sports = null;
	protected static Bitmap icon_sleep = null;
	protected static Bitmap icon_hospital = null;
	protected static Bitmap icon_money = null;
	protected static Bitmap icon_government= null;
	protected static Bitmap icon_beauty = null;
	protected static Bitmap icon_pc = null;
	protected static Bitmap icon_gasstation = null;
	
	public static int check_icon = 0;
	
	public NaverDataSource(Resources res) {        
	    if (res==null) throw new NullPointerException();
        
	    createIcon(res);
    }

    protected void createIcon(Resources res) {
        if (res==null) throw new NullPointerException();
   
        icon_eat = BitmapFactory.decodeResource(res, R.drawable.memory_eat_n);
        icon_cafe = BitmapFactory.decodeResource(res, R.drawable.memory_cafe_n);
        icon_drink = BitmapFactory.decodeResource(res, R.drawable.memory_drink_n);
        icon_shopping = BitmapFactory.decodeResource(res, R.drawable.memory_shopping_n);
        icon_sports = BitmapFactory.decodeResource(res, R.drawable.memory_sports_n);
        icon_sleep = BitmapFactory.decodeResource(res, R.drawable.memory_sleep_n);
        icon_hospital = BitmapFactory.decodeResource(res, R.drawable.memory_hospital_n);
        icon_money = BitmapFactory.decodeResource(res, R.drawable.memory_money_n);
        icon_government = BitmapFactory.decodeResource(res, R.drawable.memory_government_n);
        icon_beauty = BitmapFactory.decodeResource(res, R.drawable.memory_beauty_n);
        icon_pc = BitmapFactory.decodeResource(res, R.drawable.memory_pc_n);
        icon_gasstation = BitmapFactory.decodeResource(res, R.drawable.memory_gasstation_n);
    }

    //네이버 종류
	@Override
	public String createRequestURL(double xPos, double yPos, double radius, String query)
	{
		return BASE_URL + "&xPos=" + xPos + "&yPos=" + yPos + "&radius=" + radius + "&query=" + query;
	}

	@Override
	public List<Marker> parse(JSONObject root, double alt) {
		
		if (root==null) return null;
		
		JSONObject jo = null;
		JSONArray dataArray = null;
    	List<Marker> markers=new ArrayList<Marker>();

		try {
			if(root.has("result")) 
			{
				dataArray = root.getJSONObject("result").getJSONObject("items").getJSONArray("item");
			}
			
			if (dataArray == null || dataArray.length() == 0) 
			{
				return markers;
			}
		
			int top = Math.min(MAX, dataArray.length());
			for (int i = 0; i < top; i++) {					
				jo = dataArray.getJSONObject(i);
				Marker ma = processJSONObject(jo, alt);
				if(ma!=null) markers.add(ma);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return markers;
	}
	
	private Marker processJSONObject(JSONObject jo, double alt) {
		if (jo==null) return null;
		
        Marker ma = null;
        
        if (	jo.has("comID") && 
        		jo.has("name") &&
        		jo.has("longitude") && 
        		jo.has("latitude") ) 
        {
        	try 
        	{		
        		//고도값을 얻기위해 다시 파싱힌다.
    	        InputStream stream = null;
    	        stream = getHttpGETInputStream("http://maps.googleapis.com/maps/api/elevation/json?locations=" + jo.getDouble("latitude") + "," + jo.getDouble("longitude") +"&sensor=false");
    	        
    	        if (stream == null)
    	            throw new NullPointerException();

    	        String string = null;
    	        string = getHttpInputString(stream);
    	        
    	        if (string == null)
    	            throw new NullPointerException();
    	        
    	        //고도 json 정보를 저장할수 있는 json object
    	        JSONObject altJson = null;
    	        try {
    	        	altJson = new JSONObject(string);
    	        } catch (JSONException e) {
    	            e.printStackTrace();
    	        }
    	        if (altJson == null)
    	            throw new NullPointerException();
    	        
    	        ma = new IconMarker(
        				jo.getString("comID"),
        				jo.getString("name"),
        				jo.getDouble("latitude"),
        				jo.getDouble("longitude"),
        				altJson.getJSONArray("results").getJSONObject(0).getDouble("elevation"), //- alt,
        				Color.WHITE,
        				reIcon());
        	} 
        	catch (JSONException e) 
        	{
        		e.printStackTrace();
        	}
        }
        return ma;
	}
	
	private Bitmap reIcon()
	{
		//icon
        switch (check_icon) 
	    {
			case 0:
				return icon_eat;
			
			case 1:
				return icon_cafe;
			
			case 2:
				return icon_drink;
			
			case 3:
				return icon_shopping;
			
			case 4:
				return icon_sports;
			
			case 5:
				return icon_sleep;
			
			case 6:
				return icon_hospital;
				
			case 7:
				return icon_money;
				
			case 8:
				return icon_government;
				
			case 9:
				return icon_beauty;
				
			case 10:
				return icon_pc;
				
			case 11:
				return icon_gasstation;
		}
        
        return null;
	}
}