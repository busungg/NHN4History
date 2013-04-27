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

	// 화면 해상도
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
		
		//이미지 뷰가 들어간 레이아웃
		menuLayout = (RelativeLayout)findViewById(R.id.menuLayout);
		menuLayout.setBackgroundColor(Color.WHITE);
		menuLayout.setGravity(Gravity.CENTER_HORIZONTAL);
		LinearLayout.LayoutParams menuParams =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, deviceHeight / 3);
		menuLayout.setLayoutParams(menuParams);
		
		logoImageView = (ImageView)findViewById(R.id.menuImage);
		logoImageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.image_insert));
		RelativeLayout.LayoutParams imageparams =  new RelativeLayout.LayoutParams(deviceHeight / 2, deviceHeight / 4);
		imageparams.topMargin = deviceHeight / 15;
		logoImageView.setLayoutParams(imageparams);
		
		//버튼들이 들어간 레이아웃
		menuBLayout  = (RelativeLayout)findViewById(R.id.menuBLayout);
		menuBLayout.setBackgroundColor(Color.WHITE);
		LinearLayout.LayoutParams menuBParams =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		menuBLayout.setLayoutParams(menuBParams);

		//증강현실
		search = (Button) findViewById(R.id.menuBS);
		RelativeLayout.LayoutParams searchParams =  new RelativeLayout.LayoutParams(deviceWidth / 4, deviceWidth / 4);
		searchParams.leftMargin = deviceWidth / 8;
		searchParams.topMargin = deviceHeight / 15;
		search.setLayoutParams(searchParams);
		
		//포스팅
		posting = (Button) findViewById(R.id.menuBP);
		RelativeLayout.LayoutParams postingParams =  new RelativeLayout.LayoutParams(deviceWidth / 4, deviceWidth / 4);
		postingParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		postingParams.rightMargin = deviceWidth / 8;
		postingParams.topMargin = deviceHeight / 15;
		posting.setLayoutParams(postingParams);
		
		//둘러보기
		patrol = (Button) findViewById(R.id.menuBD);
		RelativeLayout.LayoutParams patrolParams =  new RelativeLayout.LayoutParams(deviceWidth / 4, deviceWidth / 4);
		patrolParams.addRule(RelativeLayout.BELOW, R.id.menuBS);
		patrolParams.leftMargin = deviceWidth / 5;
		patrolParams.topMargin = deviceHeight / 15;
		patrol.setLayoutParams(patrolParams);
		
		//마이웹
		myWeb = (Button) findViewById(R.id.menuBM);
		RelativeLayout.LayoutParams myWebParams =  new RelativeLayout.LayoutParams(deviceWidth / 4, deviceWidth / 4);
		myWebParams.addRule(RelativeLayout.BELOW, R.id.menuBP);
		myWebParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		myWebParams.rightMargin = deviceWidth / 5;
		myWebParams.topMargin = deviceHeight / 15;
		myWeb.setLayoutParams(myWebParams);

		//증강현실
		search.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this,
						MainActivity.class);
				startActivity(intent);
			}
		});

		//포스팅
		posting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this,
						PostActivity.class);
				startActivity(intent);
			}
		});
		
		//둘러보기
		patrol.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				Intent intent = new Intent(MenuActivity.this,
//						MainActivity.class);
//				startActivity(intent);
			}
		});

		//마이웹
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