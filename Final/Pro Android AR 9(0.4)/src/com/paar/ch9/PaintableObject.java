package com.paar.ch9;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

public abstract class PaintableObject {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public PaintableObject() {
        if (paint==null) {
            paint = new Paint();
            paint.setTextSize(16);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
        }
    }

    public abstract float getWidth();

    public abstract float getHeight();

    public abstract void paint(Canvas canvas);

    public void setFill(boolean fill) {
        if (fill)
            paint.setStyle(Paint.Style.FILL);
        else
            paint.setStyle(Paint.Style.STROKE);
    }

    public void setColor(int c) {
        paint.setColor(c);
    }

    public void setStrokeWidth(float w) {
        paint.setStrokeWidth(w);
    }

    public float getTextWidth(String txt) {
    	if (txt==null) throw new NullPointerException();
        return paint.measureText(txt);
    }

    public float getTextAsc() {
        return -paint.ascent();
    }

    public float getTextDesc() {
        return paint.descent();
    }

    public void setFontSize(float size) {
        paint.setTextSize(size);
    }

    public void paintLine(Canvas canvas, float x1, float y1, float x2, float y2) {
    	if (canvas==null) throw new NullPointerException();
    	
    	//라인 두깨 수정
    	paint.setStrokeWidth(2);
        canvas.drawLine(x1, y1, x2, y2, paint);
    }

    public void paintRect(Canvas canvas, float x, float y, float width, float height) {
    	if (canvas==null) throw new NullPointerException();
    	
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
    
    //레이더 위에 마커를 표시
    public void paintMarkerOnRadar(Canvas canvas, float x, float y, float width, float height)
    {
    	if (canvas==null) throw new NullPointerException();
    	
    	//레이더 마커 위치 표시 색 수정
    	paint.setColor(Color.GREEN);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public void paintRoundedRect(Canvas canvas, float x, float y, float width, float height) {
        if (canvas==null) throw new NullPointerException();

        RectF rect = new RectF(x, y, x + width, y + height);
        canvas.drawRoundRect(rect, 15F, 15F, paint);
    }

    public void paintBitmap(Canvas canvas, Bitmap bitmap, Rect src, Rect dst) {
        if (canvas==null || bitmap==null) throw new NullPointerException();
        
        canvas.drawBitmap(bitmap, src, dst, paint);
    }
 
    public void paintBitmap(Canvas canvas, Bitmap bitmap, float left, float top) {
    	if (canvas==null || bitmap==null) throw new NullPointerException();
    	
        canvas.drawBitmap(bitmap, left, top, paint);
    }
    
    public void paintCircle(Canvas canvas, float x, float y, float radius) {
    	if (canvas==null) throw new NullPointerException();
    	
    	//레이더 부분 원 그리기
    	canvas.drawCircle(x, y, radius, paint);
    	
    	//선 그리기
    	paint.setStyle(Paint.Style.STROKE);
    	
    	paint.setStrokeWidth(3);
    	paint.setColor(Color.WHITE);
    	canvas.drawCircle(x, y, radius, paint);
    	
    	paint.setStrokeWidth(2);
    	paint.setColor(Color.GRAY);
    	canvas.drawCircle(x, y, radius - (radius / 3) * 2, paint);
    	canvas.drawCircle(x, y, radius - (radius / 3), paint);
    	
    	//사용자 빨간색으로 표현
    	paint.setStyle(Paint.Style.FILL);
    	paint.setColor(Color.RED);
    	canvas.drawCircle(x, y, 1, paint);
        
    }

    public void paintText(Canvas canvas, float x, float y, String text) {
    	if (canvas==null || text==null) throw new NullPointerException();
    	
        canvas.drawText(text, x, y, paint);
    }

    public void paintObj(	Canvas canvas, PaintableObject obj, 
    						float x, float y, 
    						float rotation, float scale) 
    {
    	if (canvas==null || obj==null) throw new NullPointerException();
    	
        canvas.save();
        canvas.translate(x+obj.getWidth()/2, y+obj.getHeight()/2);
        canvas.rotate(rotation);
        canvas.scale(scale,scale);
        canvas.translate(-(obj.getWidth()/2), -(obj.getHeight()/2));
        obj.paint(canvas);
        canvas.restore();
    }

    public void paintPath(	Canvas canvas, Path path, 
    						float x, float y, float width, 
    						float height, float rotation, float scale) 
    {
    	if (canvas==null || path==null) throw new NullPointerException();
    	
    	canvas.save();
        canvas.translate(x + width / 2, y + height / 2);
        canvas.rotate(rotation);
        canvas.scale(scale, scale);
        canvas.translate(-(width / 2), -(height / 2));
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}