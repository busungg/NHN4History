package com.paar.ch9;

import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
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
import android.graphics.Color;

public class LocationView extends Activity {
	
	//���� ����
    protected static RelativeLayout maininfo = null;
    protected static ImageView image = null;
    protected static TextView name = null;
    protected static TextView category = null;
    
    //�� ����
    protected static RelativeLayout descinfo = null;
    protected static ImageView addressImage = null;
    protected static ImageView phoneImage = null;
    protected static TextView address = null;
    protected static TextView phone = null;
    
    //������, �˻�
    protected static LinearLayout buttoninfo = null;
    protected static Button posting = null;
    protected static Button search = null;
    
    //��Ŀid �� ������ Ȯ��
    protected static String marker_id;
    protected static int check_icon;
    
    //uri
    private final String urlNaver = "http://map.naver.com/local/siteview.nhn?code=";
    
    //�󼼼��� ���� ����
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
		
		//�ػ�
		int width = AugmentedActivity.deviceWidth;
		int height = AugmentedActivity.deviceHeight;
		
		//DB ����
		createDB();
		
		//�󼼼��� json�� ������ ����
		JSONObject description = null;
		description = descJson();
	
		//json �м�
		try {
			if(description.has("result")) 
			{	
				//�̸�
				joName = description.getJSONObject("result").getJSONObject("site").
						getJSONArray("list").getJSONObject(0).getString("name");
				
				//ī�װ�
				joCategory = description.getJSONObject("result").getJSONObject("site").
						getJSONArray("list").getJSONObject(0).getString("category");
				
				//��ȭ��ȣ
				joTel = description.getJSONObject("result").getJSONObject("site").
						getJSONArray("list").getJSONObject(0).getString("tel");
				
				//�ּ�
				joAddress = description.getJSONObject("result").getJSONObject("site").
						getJSONArray("list").getJSONObject(0).getString("address");
				
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		//���� ����
		maininfo = (RelativeLayout)findViewById(R.id.lo_titleinfo);
		LinearLayout.LayoutParams maininfoParams =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 5);
		maininfoParams.topMargin = height / 20;
		maininfoParams.gravity = Gravity.CENTER;
		maininfo.setLayoutParams(maininfoParams);
		
		image = (ImageView)findViewById(R.id.lo_image);
		image.setBackgroundDrawable(getResources().getDrawable(setIcon()));
		RelativeLayout.LayoutParams imageparams =  new RelativeLayout.LayoutParams(width / 5, height / 6);
		imageparams.setMargins(10, 10, 10, 10);
		image.setLayoutParams(imageparams);
		
		name = (TextView)findViewById(R.id.lo_title);
		name.setText(joName);
		name.setGravity(Gravity.CENTER);
		name.setTextColor(Color.DKGRAY);
		name.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_text_round));
		RelativeLayout.LayoutParams titleparams =  new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 15);
		titleparams.topMargin = 10;
		titleparams.rightMargin = 10;
		titleparams.addRule(RelativeLayout.RIGHT_OF, R.id.lo_image);
		name.setLayoutParams(titleparams);
		
		category = (TextView)findViewById(R.id.lo_category);
		category.setText(joCategory);
		category.setTextColor(Color.DKGRAY);
		category.setGravity(Gravity.CENTER);
		category.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_text_round));
		RelativeLayout.LayoutParams categoryparams =  new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 15);
		categoryparams.topMargin = 10;
		categoryparams.rightMargin = 10;
		categoryparams.addRule(RelativeLayout.RIGHT_OF, R.id.lo_image);
		categoryparams.addRule(RelativeLayout.BELOW, R.id.lo_title);
		category.setLayoutParams(categoryparams);
		
		//�� ����
		descinfo = (RelativeLayout)findViewById(R.id.lo_descinfo);
		LinearLayout.LayoutParams descinfoParams =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 4);
		descinfoParams.topMargin = height / 20;
		descinfoParams.gravity = Gravity.CENTER;
		descinfo.setLayoutParams(descinfoParams);
		
		//�̹��� �κ�
		addressImage = (ImageView)findViewById(R.id.lo_address_image);
		addressImage.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_home_mark));
		RelativeLayout.LayoutParams addressimageparams =  new RelativeLayout.LayoutParams(width / 10, height / 12);
		addressimageparams.topMargin = 10;
		addressimageparams.leftMargin = 10;
		addressImage.setLayoutParams(addressimageparams);
	
		phoneImage = (ImageView)findViewById(R.id.lo_phone_image);
		phoneImage.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_phone_mark));
		RelativeLayout.LayoutParams phoneimageparams =  new RelativeLayout.LayoutParams(width / 10, height / 12);
		phoneimageparams.topMargin = 10;
		phoneimageparams.leftMargin = 10;
		phoneimageparams.addRule(RelativeLayout.BELOW, R.id.lo_address_image);
		phoneImage.setLayoutParams(phoneimageparams);
		
		address = (TextView)findViewById(R.id.lo_address);
		address.setText(joAddress);
		address.setTextColor(Color.DKGRAY);
		address.setGravity(Gravity.CENTER);
		address.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_text_round));
		RelativeLayout.LayoutParams addressparams =  new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 12);
		addressparams.addRule(RelativeLayout.RIGHT_OF, R.id.lo_address_image);
		addressparams.topMargin = 10;
		addressparams.leftMargin = 10;
		addressparams.rightMargin = 10;
		address.setLayoutParams(addressparams);
		
		//�ؽ�Ʈ �κ�
		phone = (TextView)findViewById(R.id.lo_phone);
		phone.setText(joTel);
		phone.setTextColor(Color.DKGRAY);
		phone.setGravity(Gravity.CENTER);
		phone.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_text_round));
		RelativeLayout.LayoutParams phoneparams =  new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 12);
		phoneparams.addRule(RelativeLayout.BELOW, R.id.lo_address);
		phoneparams.addRule(RelativeLayout.RIGHT_OF, R.id.lo_phone_image);
		phoneparams.leftMargin = 10;
		phoneparams.topMargin = 10;
		phoneparams.rightMargin = 10;
		phone.setLayoutParams(phoneparams);
		
		//��ư ����
		buttoninfo = (LinearLayout)findViewById(R.id.lo_button);
		LinearLayout.LayoutParams buttoninfoParams =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 5);
		buttoninfoParams.topMargin = height / 20;
		buttoninfoParams.gravity = Gravity.CENTER;
		buttoninfo.setOrientation(LinearLayout.HORIZONTAL);
		buttoninfo.setLayoutParams(buttoninfoParams);
		
		posting = (Button)findViewById(R.id.lo_posting);
		posting.setBackgroundResource(R.drawable.memory_location_btn_n);
		LinearLayout.LayoutParams postingparams =  new LinearLayout.LayoutParams(width / 3, height / 10);
		postingparams.rightMargin = 10;
		posting.setText("������");
		posting.setLayoutParams(postingparams);
		
		search = (Button)findViewById(R.id.lo_search);
		search.setBackgroundResource(R.drawable.memory_location_btn_n);
		search.setText("��ġ");
		postingparams.rightMargin = 0;
		postingparams.leftMargin = 10;
		search.setLayoutParams(postingparams);
		
		//��ġ
		search.setOnClickListener(new OnClickListener() 
		{
			
        	// Ŭ�� �̺�Ʈ�� ó��
            public void onClick(View v)
            {
            	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlNaver + marker_id));
        		startActivity(intent);
            }
		});
		
		search.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				
				if(event.getAction() == MotionEvent.ACTION_DOWN)
					search.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_location_btn_c));
				
				else
					search.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_location_btn_n));
				
				return false;
			}
		});
		
		//������
		posting.setOnClickListener(new OnClickListener() 
		{
			
        	// Ŭ�� �̺�Ʈ�� ó��
            public void onClick(View v)
            {   
            	//���̺� insert
            	ContentValues values = new ContentValues();
            	values.put("name", joName);
            	values.put("category", joCategory);
            	values.put("tel", joTel);
            	values.put("address", joAddress);
            
            	mDB.insert("postingInfo", null, values);
            	
            	Intent intent = new Intent(LocationView.this, PostActivity.class);
                
                // ����Ʈ�� �ִ� ������� ��Ƽ��Ƽ�� �����Ѵ�.
                startActivity(intent);
            }
		});
		
		posting.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				
				if(event.getAction() == MotionEvent.ACTION_DOWN)
					posting.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_location_btn_c));
				
				else
					posting.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_location_btn_n));
				
				return false;
			}
		});
		
	}
	
	//create DB �����ͺ����� �� ���̺� ����
	private void createDB()
	{
		mDB = this.openOrCreateDatabase(DB_NAME, SQLiteDatabase.CREATE_IF_NECESSARY, null);
		
		//���̺� ����
		mDB.execSQL(CREATE_TABLE_PUBLISHERINFO);
	}
	
	//josn�� �ؼ��Ѵ�.
	private JSONObject descJson()
	{
		//�α׵��� Ȯ��
		InputStream stream = null;
        stream = NetworkDataSource.getHttpGETInputStream("http://map.naver.com/search2/pinLeftInfo.nhn?id=" + marker_id + "&type=SITE");
        
        if (stream == null)
            throw new NullPointerException();

        String string = null;
        string = NetworkDataSource.getHttpInputString(stream);
        
        if (string == null)
            throw new NullPointerException();
        
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
	
	
	//�������� �����Ѵ�.
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
