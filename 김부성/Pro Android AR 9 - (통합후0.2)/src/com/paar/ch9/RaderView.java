package com.paar.ch9;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class RaderView extends View {
	
	private static final Radar radar = new Radar();

	public RaderView(Context context) {
        super(context);
    }

	@Override
    protected void onDraw(Canvas canvas) {
		
		radar.draw(canvas);    
	}
}
