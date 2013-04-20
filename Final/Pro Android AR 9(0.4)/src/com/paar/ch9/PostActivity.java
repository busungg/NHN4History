package com.paar.ch9;

import org.apache.cordova.DroidGap;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class PostActivity extends DroidGap {
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d("PostActivity", "PostActivity 진입");
    	//this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	super.onCreate(savedInstanceState);
    	//폰갭에서 전체화면 모드 사용하기 - phonegap fullscreen
    	//http://j1act.com/%ED%8F%B0%EA%B0%AD%EC%97%90%EC%84%9C-%EC%A0%84%EC%B2%B4-%ED%99%94%EB%A9%B4-%EB%AA%A8%EB%93%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-phonegap-cordova-fullscreen/
    	super.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    	super.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	
    	super.loadUrl("file:///android_asset/www/login.html");
        
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // super.appView.setVerticalScrollBarEnabled(true);
       // super.appView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //super.appView.setHorizontalScrollBarEnabled(false);
        
    }
}