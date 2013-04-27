package com.paar.ch9;

import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LocationView extends Activity {
	
	//메인 정보
    protected static RelativeLayout maininfo = null;
    protected static ImageView image = null;
    protected static TextView name = null;
    protected static TextView category = null;
    
    //상세 정보
    protected static LinearLayout descinfo = null;
    protected static TextView address = null;
    protected static TextView phone = null;
    
    //포스팅, 검색
    protected static LinearLayout buttoninfo = null;
    protected static Button posting = null;
    protected static Button search = null;
    
    //마커id 및 아이콘 확인
    protected static String marker_id;
    protected static int check_icon;
    
    //uri
    private final String urlNaver = "http://map.naver.com/local/siteview.nhn?code=";
    
    //상세설명 저장 변수
  	private String joName = null;
	private String joAddress = null;
	private String joCategory = null;
  	private String joTel = null;
    
    //Database
    private SQLiteDatabase mDB;
    private static final String DB_NAME = "postingTable.db";
    private static final String CREATE_TABLE_PUBLISHERINFO = "CREATE TABLE IF NOT EXISTS postingInfo(" 
    						+ "name TEXT, category TEXT, tel TEXT, address TEXT);";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locationview);
		
		//해상도
		int width = AugmentedActivity.deviceWidth;
		int height = AugmentedActivity.deviceHeight;
		
		//DB 생성
		createDB();
		
		//상세설명 json을 저장할 변수
		JSONObject description = null;
		description = descJson();
	
		//json 분석
		try {
			if(description.has("result")) 
			{	
				//이름
				joName = description.getJSONObject("result").getJSONObject("site").
						getJSONArray("list").getJSONObject(0).getString("name");
				
				//카테고리
				joCategory = description.getJSONObject("result").getJSONObject("site").
						getJSONArray("list").getJSONObject(0).getString("category");
				
				//전화번호
				joTel = description.getJSONObject("result").getJSONObject("site").
						getJSONArray("list").getJSONObject(0).getString("tel");
				
				//주소
				joAddress = description.getJSONObject("result").getJSONObject("site").
						getJSONArray("list").getJSONObject(0).getString("address");
				
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		//메인 정보
		maininfo = (RelativeLayout)findViewById(R.id.lo_titleinfo);
		LinearLayout.LayoutParams maininfoParams =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 5);
		maininfoParams.topMargin = height / 20;
		maininfoParams.gravity = Gravity.CENTER;
		maininfo.setLayoutParams(maininfoParams);
		
		image = (ImageView)findViewById(R.id.lo_image);
		image.setBackgroundDrawable(getResources().getDrawable(setIcon()));
		RelativeLayout.LayoutParams imageparams =  new RelativeLayout.LayoutParams(width / 5, height / 6);
		image.setLayoutParams(imageparams);
		
		name = (TextView)findViewById(R.id.lo_title);
		name.setText(joName);
		name.setGravity(Gravity.CENTER);
		RelativeLayout.LayoutParams titleparams =  new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 15);
		titleparams.topMargin = 10;
		titleparams.addRule(RelativeLayout.RIGHT_OF, R.id.lo_image);
		name.setLayoutParams(titleparams);
		
		category = (TextView)findViewById(R.id.lo_category);
		category.setText(joCategory);
		category.setGravity(Gravity.CENTER);
		RelativeLayout.LayoutParams categoryparams =  new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 15);
		categoryparams.topMargin = 10;
		categoryparams.addRule(RelativeLayout.RIGHT_OF, R.id.lo_image);
		categoryparams.addRule(RelativeLayout.BELOW, R.id.lo_title);
		category.setLayoutParams(categoryparams);
		
		//상세 정보
		descinfo = (LinearLayout)findViewById(R.id.lo_descinfo);
		LinearLayout.LayoutParams descinfoParams =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 4);
		descinfoParams.topMargin = height / 20;
		descinfoParams.gravity = Gravity.CENTER;
		descinfo.setLayoutParams(descinfoParams);
		
		address = (TextView)findViewById(R.id.lo_address);
		address.setText(joAddress);
		address.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams addressparams =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 10);
		addressparams.topMargin = 10;
		address.setLayoutParams(addressparams);
		
		phone = (TextView)findViewById(R.id.lo_phone);
		phone.setText(joTel);
		phone.setGravity(Gravity.CENTER);
		phone.setLayoutParams(addressparams);
		
		//버튼 정보
		buttoninfo = (LinearLayout)findViewById(R.id.lo_button);
		LinearLayout.LayoutParams buttoninfoParams =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 5);
		buttoninfoParams.topMargin = height / 20;
		buttoninfoParams.gravity = Gravity.CENTER;
		buttoninfo.setOrientation(LinearLayout.HORIZONTAL);
		buttoninfo.setLayoutParams(buttoninfoParams);
		
		posting = (Button)findViewById(R.id.lo_posting);
		LinearLayout.LayoutParams postingparams =  new LinearLayout.LayoutParams(width / 3, height / 10);
		posting.setText("포스팅");
		posting.setLayoutParams(postingparams);
		
		search = (Button)findViewById(R.id.lo_search);
		search.setText("서치");
		search.setLayoutParams(postingparams);
		
		//서치
		search.setOnClickListener(new OnClickListener() 
		{
			
        	// 클릭 이벤트를 처리
            public void onClick(View v)
            {
            	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlNaver + marker_id));
        		startActivity(intent);
            }
		});
		
		//포스팅
		posting.setOnClickListener(new OnClickListener() 
		{
			
        	// 클릭 이벤트를 처리
            public void onClick(View v)
            {   
            	//테이블 insert
            	ContentValues values = new ContentValues();
            	values.put("name", joName);
            	values.put("category", joCategory);
            	values.put("tel", joTel);
            	values.put("address", joAddress);
            
            	mDB.insert("postingInfo", null, values);
            	
            	//테이블 전체목록 뽑아오기
            	Cursor c = mDB.query("postingInfo", null, null, null, null, null, null);
            	
            	c.moveToFirst();
            	while(c.isAfterLast() == false)
            	{
            		Log.d("Alt",c.getString(0));
            		Log.d("Alt",c.getString(1));
            		Log.d("Alt",c.getString(2));
            		Log.d("Alt",c.getString(3));
            		c.moveToNext();
            	}
            	c.close();
            	
            	//테이블삭제
            	//mDB.delete("postingInfo", null, null);
            	
            	Intent intent = new Intent(LocationView.this, PostActivity.class);
                
                // 인텐트에 있는 정보대로 액티비티를 시작한다.
                startActivity(intent);
            }
		});
		
	}
	
	//create DB 데이터베이즈 및 테이블 생성
	private void createDB()
	{
		mDB = this.openOrCreateDatabase(DB_NAME, SQLiteDatabase.CREATE_IF_NECESSARY, null);
		
		//테이블 생성
		mDB.execSQL(CREATE_TABLE_PUBLISHERINFO);
	}
	
	//josn을 해석한다.
	private JSONObject descJson()
	{
		//로그들을 확인
		InputStream stream = null;
        stream = NetworkDataSource.getHttpGETInputStream("http://map.naver.com/search2/pinLeftInfo.nhn?id=" + marker_id + "&type=SITE");
        
        if (stream == null)
            throw new NullPointerException();

        String string = null;
        string = NetworkDataSource.getHttpInputString(stream);
        
        if (string == null)
            throw new NullPointerException();
        
        //고도 json 정보를 저장할수 있는 json object
        JSONObject DescJson = null;
        try {
        	DescJson = new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (DescJson == null)
            throw new NullPointerException();
        
        return DescJson;
	}
	
	
	//아이콘을 설정한다.
	private int setIcon()
	{
		//icon
        switch (check_icon) 
	    {
			case 0:
				return R.drawable.memory_eat_n;
			
			case 1:
				return R.drawable.memory_cafe_n;
			
			case 2:
				return R.drawable.memory_drink_n;
			
			case 3:
				return R.drawable.memory_shopping_n;
			
			case 4:
				return R.drawable.memory_sports_n;
			
			case 5:
				return R.drawable.memory_sleep_n;
			
			case 6:
				return R.drawable.memory_hospital_n;
				
			case 7:
				return R.drawable.memory_money_n;
				
			case 8:
				return R.drawable.memory_government_n;
				
			case 9:
				return R.drawable.memory_beauty_n;
				
			case 10:
				return R.drawable.memory_pc_n;
				
			case 11:
				return R.drawable.memory_gasstation_n;
		}
        
        return (Integer) null;
	}
}
