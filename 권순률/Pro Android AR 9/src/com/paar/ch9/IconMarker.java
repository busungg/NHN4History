package com.paar.ch9;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class IconMarker extends Marker {
    private Bitmap bitmap = null;

    public IconMarker(String name, double latitude, double longitude, double altitude, int color, Bitmap bitmap) {
        super(name, latitude, longitude, altitude, color);
        this.bitmap = bitmap;
    }
    
    public IconMarker(String id, String name, double latitude, double longitude, double altitude, int color, Bitmap bitmap) {
        super(id, name, latitude, longitude, altitude, color);
        this.bitmap = bitmap;
    }
    
    //아이콘을 가져오기위해 만든 함수
    public Bitmap getIcon()
    {
    	return bitmap;
    }

    @Override
    public void drawIcon(Canvas canvas) {
    	if (canvas==null || bitmap==null) throw new NullPointerException();

        if (gpsSymbol==null) gpsSymbol = new PaintableIcon(bitmap,96,96);

        textXyzRelativeToCameraView.get(textArray);
        symbolXyzRelativeToCameraView.get(symbolArray);

        float currentAngle = Utilities.getAngle(symbolArray[0], symbolArray[1], textArray[0], textArray[1]);
        float angle = currentAngle + 90;

        //아이콘 크기 수정 0.7
        if (symbolContainer==null) symbolContainer = new PaintablePosition(gpsSymbol, symbolArray[0], symbolArray[1], angle, 0.7f);
        else symbolContainer.set(gpsSymbol, symbolArray[0], symbolArray[1], angle, 0.7f);

        symbolContainer.paint(canvas);
    }
}