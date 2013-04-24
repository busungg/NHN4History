package com.paar.ch9;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MenuActivity extends Activity {

	// ȭ�� �ػ�
	public static int deviceWidth = 0;
	public static int deviceHeight = 0;
	
	protected static RelativeLayout menuLayout = null;
	protected static RelativeLayout menuBLayout = null;

	protected static ImageView logoImageView = null;
	protected static Button posting = null;
	protected static Button search = null;
	protected static Button patrol = null;
	protected static Button myWeb = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		deviceWidth = displayMetrics.widthPixels;
		deviceHeight = displayMetrics.heightPixels;
		
		//�̹��� �䰡 �� ���̾ƿ�
		menuLayout = (RelativeLayout)findViewById(R.id.menuLayout);
		menuLayout.setBackgroundResource(R.drawable.memory_logo_background_up);
		//menuLayout.setBackgroundColor(Color.WHITE);
		menuLayout.setGravity(Gravity.CENTER_HORIZONTAL);
		LinearLayout.LayoutParams menuParams =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, deviceHeight / 2);
		menuLayout.setLayoutParams(menuParams);
		
		logoImageView = (ImageView)findViewById(R.id.menuImage);
		logoImageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.momory_logo));
		RelativeLayout.LayoutParams imageparams =  new RelativeLayout.LayoutParams(deviceHeight / 2, deviceHeight / 2);
		imageparams.topMargin = deviceHeight / 15;
		logoImageView.setLayoutParams(imageparams);
		
		//��ư���� �� ���̾ƿ�
		menuBLayout  = (RelativeLayout)findViewById(R.id.menuBLayout);
		menuBLayout.setBackgroundResource(R.drawable.memory_logo_background_down);
		//menuBLayout.setBackgroundColor(Color.WHITE);
		LinearLayout.LayoutParams menuBParams =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		menuBLayout.setLayoutParams(menuBParams);

		//��������
		search = (Button) findViewById(R.id.menuBS);
		search.setBackgroundResource(R.drawable.memory_main_search);
		RelativeLayout.LayoutParams searchParams =  new RelativeLayout.LayoutParams(deviceWidth / 6, deviceWidth / 6);
		searchParams.leftMargin = deviceWidth / 8;
		searchParams.topMargin = deviceHeight / 15;
		search.setLayoutParams(searchParams);
		
		//������
		posting = (Button) findViewById(R.id.menuBP);
		posting.setBackgroundResource(R.drawable.memory_main_posting);
		RelativeLayout.LayoutParams postingParams =  new RelativeLayout.LayoutParams(deviceWidth / 6, deviceWidth / 6);
		postingParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		postingParams.rightMargin = deviceWidth / 8;
		postingParams.topMargin = deviceHeight / 15;
		posting.setLayoutParams(postingParams);
		
		//�ѷ�����
		patrol = (Button) findViewById(R.id.menuBD);
		patrol.setBackgroundResource(R.drawable.memory_main_updatesearch);
		RelativeLayout.LayoutParams patrolParams =  new RelativeLayout.LayoutParams(deviceWidth / 6, deviceWidth / 6);
		patrolParams.addRule(RelativeLayout.BELOW, R.id.menuBS);
		patrolParams.leftMargin = deviceWidth / 5;
		patrolParams.topMargin = deviceHeight / 15;
		patrol.setLayoutParams(patrolParams);
		
		//������
		myWeb = (Button) findViewById(R.id.menuBM);
		myWeb.setBackgroundResource(R.drawable.memory_main_myhome);
		RelativeLayout.LayoutParams myWebParams =  new RelativeLayout.LayoutParams(deviceWidth / 6, deviceWidth / 6);
		myWebParams.addRule(RelativeLayout.BELOW, R.id.menuBP);
		myWebParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		myWebParams.rightMargin = deviceWidth / 5;
		myWebParams.topMargin = deviceHeight / 15;
		myWeb.setLayoutParams(myWebParams);

		//��������
		search.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this,
						MainActivity.class);
				startActivity(intent);
			}
		});

		//������
		posting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this,
						PostActivity.class);
				startActivity(intent);
			}
		});
		
		//�ѷ�����
		patrol.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				Intent intent = new Intent(MenuActivity.this,
//						MainActivity.class);
//				startActivity(intent);
			}
		});

		//������
		myWeb.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				Intent intent = new Intent(MenuActivity.this,
//						MainActivity.class);
//				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
}