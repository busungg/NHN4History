package com.paar.ch9;

import android.graphics.Canvas;
import android.graphics.Color;

public class Radar {
    public static final float RADIUS = AugmentedActivity.deviceWidth / 10;
    
    //레이더 라인
    private static final int LINE_COLOR = Color.argb(100,255,255,255);
    private static final float PAD_X = AugmentedActivity.deviceWidth / 35;
    private static final float PAD_Y = AugmentedActivity.deviceHeight / 10;
    
    //레이더 확인
    private static final int RADAR_COLOR = Color.argb(255, 50, 50, 50);
    private static final int TEXT_COLOR = Color.rgb(255,255,255);
    private static final int TEXT_SIZE = AugmentedActivity.deviceWidth / 35;

    private static ScreenPositionUtility leftRadarLine = null;
    private static ScreenPositionUtility rightRadarLine = null;
    private static PaintablePosition leftLineContainer = null;
    private static PaintablePosition rightLineContainer = null;
    private static PaintablePosition circleContainer = null;
    
    private static PaintableRadarPoints radarPoints = null;
    private static PaintablePosition pointsContainer = null;
    
    private static PaintableText paintableText = null;
    private static PaintablePosition paintedContainer = null;

    public Radar() {
        if (leftRadarLine==null) leftRadarLine = new ScreenPositionUtility();
        if (rightRadarLine==null) rightRadarLine = new ScreenPositionUtility();
    }

    public void draw(Canvas canvas) {
    	if (canvas==null) throw new NullPointerException();

    	PitchAzimuthCalculator.calcPitchBearing(ARData.getRotationMatrix());
        ARData.setAzimuth(PitchAzimuthCalculator.getAzimuth());
        ARData.setPitch(PitchAzimuthCalculator.getPitch());
        
        drawRadarCircle(canvas);
        drawRadarPoints(canvas);
        drawRadarLines(canvas);
        drawRadarText(canvas);
    }
    
    private void drawRadarCircle(Canvas canvas) {
    	if (canvas==null) throw new NullPointerException();
    	
        if (circleContainer==null) {
            PaintableCircle paintableCircle = new PaintableCircle(RADAR_COLOR,RADIUS,true);
            circleContainer = new PaintablePosition(paintableCircle,PAD_X+RADIUS,PAD_Y+RADIUS,0,1);
        }
        circleContainer.paint(canvas);
    }
    
    private void drawRadarPoints(Canvas canvas) {
    	if (canvas==null) throw new NullPointerException();
    	
        if (radarPoints==null) radarPoints = new PaintableRadarPoints();
        
        if (pointsContainer==null) 
        	pointsContainer = new PaintablePosition( radarPoints, 
                                                     PAD_X, 
                                                     PAD_Y, 
                                                     -ARData.getAzimuth(), 
                                                     1);
        else 
        	pointsContainer.set(radarPoints, 
                    			PAD_X, 
                    			PAD_Y, 
                    			-ARData.getAzimuth(), 
                    			1);
        
        pointsContainer.paint(canvas);
    }
    
    private void drawRadarLines(Canvas canvas) {
    	if (canvas==null) throw new NullPointerException();
    	
        if (leftLineContainer==null) {
            leftRadarLine.set(0, -RADIUS);
            leftRadarLine.rotate(-CameraModel.DEFAULT_VIEW_ANGLE / 2);
            leftRadarLine.add(PAD_X+RADIUS, PAD_Y+RADIUS);

            float leftX = leftRadarLine.getX()-(PAD_X+RADIUS);
            float leftY = leftRadarLine.getY()-(PAD_Y+RADIUS);
            PaintableLine leftLine = new PaintableLine(LINE_COLOR, leftX, leftY);
            
            leftLineContainer = new PaintablePosition(  leftLine, 
                                                        PAD_X+RADIUS, 
                                                        PAD_Y+RADIUS, 
                                                        0, 
                                                        1);
        }
        leftLineContainer.paint(canvas);
        
        if (rightLineContainer==null) {
            rightRadarLine.set(0, -RADIUS);
            rightRadarLine.rotate(CameraModel.DEFAULT_VIEW_ANGLE / 2);
            rightRadarLine.add(PAD_X+RADIUS, PAD_Y+RADIUS);
            
            float rightX = rightRadarLine.getX()-(PAD_X+RADIUS);
            float rightY = rightRadarLine.getY()-(PAD_Y+RADIUS);
            PaintableLine rightLine = new PaintableLine(LINE_COLOR, rightX, rightY);
            rightLineContainer = new PaintablePosition( rightLine, 
                                                        PAD_X+RADIUS, 
                                                        PAD_Y+RADIUS, 
                                                        0, 
                                                        1);
        }
        rightLineContainer.paint(canvas);
    }

    private void drawRadarText(Canvas canvas) {
        
        radarText(  canvas, 
                    formatDist(ARData.getRadius() * 1000), 
                    (PAD_X + RADIUS), 
                    (PAD_Y + RADIUS*2 -10), 
                    false
                 );
    }
    
    private void radarText(Canvas canvas, String txt, float x, float y, boolean bg) {
    	if (canvas==null || txt==null) throw new NullPointerException();
    	
        if (paintableText==null) paintableText = new PaintableText(txt,TEXT_COLOR,TEXT_SIZE,bg);
        else paintableText.set(txt,TEXT_COLOR,TEXT_SIZE,bg);
        
        if (paintedContainer==null) paintedContainer = new PaintablePosition(paintableText,x,y,0,1);
        else paintedContainer.set(paintableText,x,y,0,1);
        
        paintedContainer.paint(canvas);
    }

    private static String formatDist(float meters) {
        if (meters < 1000) {
            return ((int) meters) + "m";
        } else if (meters < 10000) {
            return formatDec(meters / 1000f, 1) + "km";
        } else {
            return ((int) (meters / 1000f)) + "km";
        }
    }

    private static String formatDec(float val, int dec) {
        int factor = (int) Math.pow(10, dec);

        int front = (int) (val);
        int back = (int) Math.abs(val * (factor) ) % factor;

        return front + "." + back;
    }
}