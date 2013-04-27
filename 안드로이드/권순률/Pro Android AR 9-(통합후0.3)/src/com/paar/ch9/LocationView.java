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
	
	//���� ����
    protected static RelativeLayout maininfo = null;
    protected static ImageView image = null;
    protected static TextView name = null;
    protected static TextView category = null;
    
    //�� ����
    protected static LinearLayout descinfo = null;
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
		
		//�� ����
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
		
		//��ư ����
		buttoninfo = (LinearLayout)findViewById(R.id.lo_button);
		LinearLayout.LayoutParams buttoninfoParams =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, height / 5);
		buttoninfoParams.topMargin = height / 20;
		buttoninfoParams.gravity = Gravity.CENTER;
		buttoninfo.setOrientation(LinearLayout.HORIZONTAL);
		buttoninfo.setLayoutParams(buttoninfoParams);
		
		posting = (Button)findViewById(R.id.lo_posting);
		LinearLayout.LayoutParams postingparams =  new LinearLayout.LayoutParams(width / 3, height / 10);
		posting.setText("������");
		posting.setLayoutParams(postingparams);
		
		search = (Button)findViewById(R.id.lo_search);
		search.setText("��ġ");
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
            	
            	//���̺� ��ü��� �̾ƿ���
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
            	
            	//���̺����
            	//mDB.delete("postingInfo", null, null);
            	
            	Intent intent = new Intent(LocationView.this, PostActivity.class);
                
                // ����Ʈ�� �ִ� ������� ��Ƽ��Ƽ�� �����Ѵ�.
                startActivity(intent);
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
        
        //�� json ������ �����Ҽ� �ִ� json object
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
