package com.paar.ch9;

import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainActivity extends AugmentedActivity {
    private static final String TAG = "MainActivity";
    private static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1);
    private static final ThreadPoolExecutor exeService = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, queue);
	private static final Map<String,NetworkDataSource> sources = new ConcurrentHashMap<String,NetworkDataSource>();    
	private final String urlNaver = "http://map.naver.com/local/siteview.nhn?code=";
	public static String categoryString = "음식점";
	
	//handler
	public static Handler handler = new Handler();
	
	//로딩화면을 위한 layout
	public static ProgressBar loadingLayout = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        loadingLayout = (ProgressBar)findViewById(1);
        
        NetworkDataSource naver = new NaverDataSource(this.getResources());
        sources.put("naver", naver);
    }

	@Override
    public void onStart() {
        super.onStart();
        
        Location last = ARData.getCurrentLocation();
        
        //현위치
        whereAmI(last);
        updateData(last.getLatitude(),last.getLongitude(),last.getAltitude());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v(TAG, "onOptionsItemSelected() item="+item);
        switch (item.getItemId()) {
            case R.id.showZoomBar:
                showZoomBar = !showZoomBar;
                item.setTitle(((showZoomBar)? "Hide" : "Show")+" Zoom Bar");
                zoomLayout.setVisibility((showZoomBar)?LinearLayout.VISIBLE:LinearLayout.GONE);
                break;
            case R.id.exit:
                finish();
                break;
        }
        return true;
    }

	@Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);
        whereAmI(location);
        updateData(location.getLatitude(),location.getLongitude(),location.getAltitude());
    }

	//마커를 터치했을때
	@Override
	protected void markerTouched(Marker marker) {
		
		LocationView.marker_id = (marker.getId().length() == 9) ? marker.getId().substring(1, 9) : marker.getId().substring(2, 10);
		LocationView.check_icon = NaverDataSource.check_icon;
		Intent intent = new Intent(MainActivity.this, LocationView.class);
		
		//지정된 네이버 사이트로 이동
		//Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlNaver + ((marker.getId().length() == 9) ? marker.getId().substring(1, 9) : marker.getId().substring(2, 10))));
		startActivity(intent);
	}

    @Override
	protected void updateDataOnZoom() {
	    super.updateDataOnZoom();
	    
	    //원래 있던 정보를 지우고 다시 생성한다.
    	ARData.removeList();
    	//원래 남아있던 마커들을 지운다.
    	AugmentedView.checkRedraw = true;
	    
        Location last = ARData.getCurrentLocation();
        updateData(last.getLatitude(),last.getLongitude(),last.getAltitude());
	}
    
    //카테고리가 바뀔때 다시 업데이트를 한다.
    public static void changeCategory()
    {	
    	//원래 있던 정보를 지우고 다시 생성한다.
    	ARData.removeList();
    	//원래 남아있던 마커들을 지운다.
    	AugmentedView.checkRedraw = true;
    	
    	Location last = ARData.getCurrentLocation();
        updateData(last.getLatitude(),last.getLongitude(),last.getAltitude());
        
    }
    
    private static void updateData(final double lat, final double lon, final double alt) {
    	
        try {
            exeService.execute(
                new Runnable() {
                    
                    public void run() {
                    	
                    	//로딩 화면을 보여준다.
                		handler.post(new Runnable() {
                	        public void run() {
                	        	loadingLayout.setVisibility(View.VISIBLE);
                	        }
                	    });
                    	
                        for (NetworkDataSource source : sources.values())
                            download(source, lat, lon, alt);
                        
                        //로딩 화면을 없앤다.
                		handler.post(new Runnable() {
                	        public void run() {
                	        	loadingLayout.setVisibility(View.GONE);
                	        }
                	    });
                    }
                  
                }
            );
        } catch (RejectedExecutionException rej) {
            Log.w(TAG, "Not running new download Runnable, queue is full.");
        } catch (Exception e) {
            Log.e(TAG, "Exception running download Runnable.",e);
        }
    }
    
    private static boolean download(NetworkDataSource source, double lat, double lon, double alt) {
    	
		if (source==null) return false;
		
		String url = null;
		try {
			//url을 생성 한다.
			url = source.createRequestURL(lon, lat, (int)(ARData.getRadius() * 1000), URLEncoder.encode(categoryString, "euc-kr"));
		} catch (Exception e) {
			return false;
		}
    	
		List<Marker> markers = null;
		try {
			markers = source.parse(url, alt);
		} catch (NullPointerException e) {
			return false;
		}

    	ARData.addMarkers(markers);
    	
    	return true;
    }
    
    //현재위치를 파악
    private void whereAmI(Location location)
	{
		Geocoder geoCoder = new Geocoder(this, Locale.KOREAN);
		String Area;
		
    	try { 
			List<Address> addresses = geoCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 5);
			if(addresses.size() > 0) 
			{
				Address mAddress = addresses.get(0); 
				
				StringBuffer strbuf = new StringBuffer();
				String buf;
				
				for (int i = 0; (buf = mAddress.getAddressLine(i)) != null; i++){

					strbuf.append(buf+"\n");
				}
			   
				Area = strbuf.toString();
				
				whereTextView.setText(Area);
				whereTextView.invalidate();
				
				return;
			}
	    }
    	catch (Exception e) 
    	{
    		e.printStackTrace();
	    }
    	
    	whereTextView.setText("Loading...");
    	whereTextView.invalidate();
	}
}