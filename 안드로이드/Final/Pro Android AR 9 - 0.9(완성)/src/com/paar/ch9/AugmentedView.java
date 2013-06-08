package com.paar.ch9;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class AugmentedView extends View {
    private static final AtomicBoolean drawing = new AtomicBoolean(false);

    private static final float[] locationArray = new float[3];
    private static final List<Marker> cache = new ArrayList<Marker>(); 
    private static final TreeSet<Marker> updated = new TreeSet<Marker>();
    private static final int COLLISION_ADJUSTMENT = 100;
    public static boolean checkRedraw = false;
    
    public AugmentedView(Context context) {
        super(context);
    }

	@Override
    protected void onDraw(Canvas canvas) {
		
    	if (canvas==null) return;

        if (drawing.compareAndSet(false, true)) { 
	        List<Marker> collection = ARData.getMarkers();

            cache.clear();
           
        	for (Marker m : collection) {
        	m.update(canvas, 0, 0);
            if (m.isOnRadar()) cache.add(m);
        	}
            collection = cache;

            //충돌관련된것
	        //if (AugmentedActivity.useCollisionDetection) adjustForCollisions(canvas,collection);

	        ListIterator<Marker> iter = collection.listIterator(collection.size());
	        while (iter.hasPrevious()) {
	            Marker marker = iter.previous();
	            marker.draw(canvas);
	        }
	        
	        //전에 존재하던 마커들을 지운다
	        if(checkRedraw)
	        {
	        	this.invalidate();
	        	checkRedraw = false;
	        }
	        
	        
	        drawing.set(false);
	        
        }
    }

	private static void adjustForCollisions(Canvas canvas, List<Marker> collection) {
	    updated.clear();
        for (Marker marker1 : collection) {
            if (updated.contains(marker1) || !marker1.isInView()) continue;

            int collisions = 1;
            for (Marker marker2 : collection) {
                if (marker1.equals(marker2) || updated.contains(marker2) || !marker2.isInView()) continue;

                if (marker1.isMarkerOnMarker(marker2)) {
                    /*marker2.getLocation().get(locationArray);
                    float y = locationArray[1];
                    float h = collisions*COLLISION_ADJUSTMENT;
                    locationArray[1] = y+h;
                    marker2.getLocation().set(locationArray);
                    marker2.update(canvas, 0, 0);
                    collisions++;
                    updated.add(marker2);*/
                	
                	//겹치는 것은 레이더와 뷰를 없앤다.
                	marker2.isOnRadar = false;
                	marker2.isInView = false;
                	updated.add(marker2);
                }
            }
            updated.add(marker1);
        }
	}
}