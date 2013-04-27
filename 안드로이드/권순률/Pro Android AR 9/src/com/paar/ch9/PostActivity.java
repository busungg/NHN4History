package com.paar.ch9;

import org.apache.cordova.DroidGap;

import android.os.Bundle;
import android.util.Log;

public class PostActivity extends DroidGap {
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d("PostActivity", "PostActivity ¡¯¿‘");
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.loadUrl("file:///android_asset/www/login.html");
    }
}