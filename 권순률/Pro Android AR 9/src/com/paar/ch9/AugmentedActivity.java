package com.paar.ch9;

import java.text.DecimalFormat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class AugmentedActivity extends SensorsActivity implements OnTouchListener {
    private static final String TAG = "AugmentedActivity";
    private static final DecimalFormat FORMAT = new DecimalFormat("#.##");
    private static final int END_TEXT_COLOR = Color.WHITE;
    
    //�⺻
    protected static WakeLock wakeLock = null;
    protected static CameraSurface camScreen = null;    
    protected static VerticalSeekBar myZoomBar = null;
    protected static TextView endLabel = null;
    protected static LinearLayout zoomLayout = null;
    protected static AugmentedView augmentedView = null;
    
    //���̴���
    protected static RaderView raderview = null;
    
    //�ڱ���ġ ���̾ƿ�
    protected static LinearLayout whereLayout = null;
    protected static TextView whereTextView = null;
    
    //ī�װ� �� ����Ʈ�� ���̾ƿ�
    protected static LinearLayout menuesLayout = null;
    protected static ArrayAdapter<CharSequence> categoryadspin = null;
    protected static Spinner categorySpinner = null;
    protected static Button listButton = null;
    
    //�ε�ȭ���� ���� ProgressBar
    protected static LinearLayout loadingLayout = null;
    protected static ProgressBar progressbar = null;
    protected static TextView progressText = null;
    
    //������ ���� ���̾ƿ� �� ��ư
    protected static LinearLayout supportLayout = null;
    protected static Button supportButton = null;
    
    //������ �����ִ� ���̾ƿ�
    protected static LinearLayout infoLayout = null;
    protected static int checkInfo = 0;

    //���̴��� ���� ��� ����
    public static final float MAX_ZOOM = 10; //in KM
    public static final float ONE_PERCENT = MAX_ZOOM/10f;
    public static final float TEN_PERCENT = 10f*ONE_PERCENT;
    public static final float TWENTY_PERCENT = 2f*TEN_PERCENT;
    public static final float EIGHTY_PERCENTY = 4f*TWENTY_PERCENT;

    // ���̴� �� �ܹ� Ȯ���� ���� boolean ����
    public static boolean useCollisionDetection = true;
    public static boolean showZoomBar = false;
    
    //ȭ�� �ػ�
    public static int deviceWidth = 0;
    public static int deviceHeight = 0;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ȭ�� �ػ� ��������
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        deviceWidth = displayMetrics.widthPixels;
        deviceHeight =displayMetrics.heightPixels;
        
        camScreen = new CameraSurface(this);
        setContentView(camScreen);
        
        //��Ŀ�� �׸��� ȭ��
        augmentedView = new AugmentedView(this);
        augmentedView.setOnTouchListener(this);
        LayoutParams augLayout = new LayoutParams(
        		LayoutParams.FILL_PARENT, 
        		LayoutParams.FILL_PARENT);
        addContentView(augmentedView,augLayout);
        
        //ZoomLayout
        zoomLayout = new LinearLayout(this);
        zoomLayout.setVisibility((showZoomBar)?LinearLayout.VISIBLE:LinearLayout.GONE);
        zoomLayout.setOrientation(LinearLayout.VERTICAL);
        zoomLayout.setPadding(5, 5, 5, 5);
        zoomLayout.setGravity(Gravity.CENTER);
       
        //ZoomBar �Ÿ� Text view �߰�
        endLabel = new TextView(this);
        endLabel.setTextColor(END_TEXT_COLOR);
        LinearLayout.LayoutParams zoomTextParams =  new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        zoomLayout.addView(endLabel, zoomTextParams);

        //ZoomBar �߰�
        myZoomBar = new VerticalSeekBar(this);
        myZoomBar.setMax(10);
        myZoomBar.setProgress(2);
        myZoomBar.setOnSeekBarChangeListener(myZoomBarOnSeekBarChangeListener);
        LinearLayout.LayoutParams zoomBarParams =  new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 400);
        zoomBarParams.gravity = Gravity.CENTER_HORIZONTAL;
        zoomLayout.addView(myZoomBar, zoomBarParams);
        
        //ZoomLayout �߰�
        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(  LayoutParams.WRAP_CONTENT, 
                                                                                    LayoutParams.FILL_PARENT, 
                                                                                    Gravity.RIGHT);
        addContentView(zoomLayout,frameLayoutParams);
        
        //�ڱ���ġ ��Ÿ���� ����Ʈ ���̾ƿ� ����
        whereLayout = new LinearLayout(this);
        whereLayout.setVisibility(LinearLayout.VISIBLE);
        whereLayout.setOrientation(LinearLayout.HORIZONTAL);
        whereLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_background));
        
        whereTextView = new TextView(this);
        whereTextView.setTextColor(Color.BLACK);
        whereTextView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams whereTextParams =  new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT ,LayoutParams.FILL_PARENT);
        whereTextParams.gravity = Gravity.CENTER;
        whereLayout.addView(whereTextView, whereTextParams);
        
        FrameLayout.LayoutParams frameWhereLayoutParams = new FrameLayout.LayoutParams(  
        		LayoutParams.FILL_PARENT, 
        		deviceHeight / 10, 
                Gravity.RIGHT);
        addContentView(whereLayout,frameWhereLayoutParams);
        
        //ī�װ� �� ����Ʈ �� ���̾ƿ� ����
        menuesLayout = new LinearLayout(this);
        menuesLayout.setVisibility(LinearLayout.VISIBLE);
        menuesLayout.setOrientation(LinearLayout.HORIZONTAL);
        menuesLayout.setGravity(Gravity.RIGHT);
        menuesLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_menues_layout));
        
        //���ǳʿ� ��� �̹��� ����
        categorySpinner = new Spinner(this);
        //categorySpinner.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_button_n));
        
        //���ǳ� ����� ����
        categoryadspin = ArrayAdapter.createFromResource(this, R.array.categorylist, 
        		android.R.layout.simple_spinner_item);
        categoryadspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryadspin); 
        
        LinearLayout.LayoutParams categoryParams =  new LinearLayout.LayoutParams(
        				LayoutParams.WRAP_CONTENT ,
        				LayoutParams.WRAP_CONTENT
        		);
        categoryParams.setMargins(0, 5, 10, 0);
        menuesLayout.addView(categorySpinner, categoryParams);
              
        //list ��ư �߰�
        listButton = new Button(this);
        listButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_list_n));
        LinearLayout.LayoutParams listParams =  new LinearLayout.LayoutParams(
        		LayoutParams.WRAP_CONTENT,
        		LayoutParams.WRAP_CONTENT
        		);
        listParams.setMargins(0, 5, 10, 0);
        menuesLayout.addView(listButton, listParams);
                
        FrameLayout.LayoutParams frameMenuesLayoutParams = new FrameLayout.LayoutParams(  
        		LayoutParams.FILL_PARENT,
        		LayoutParams.WRAP_CONTENT,
                Gravity.RIGHT);
        frameMenuesLayoutParams.setMargins(0, deviceHeight / 10, 0, 0);
        addContentView(menuesLayout,frameMenuesLayoutParams);
        
        //���̴��� �׸���.
        raderview = new RaderView(this);
        addContentView(raderview,augLayout);
        
        //Loading ���̾ƿ�
        loadingLayout = new LinearLayout(this);
        loadingLayout.setGravity(Gravity.CENTER);
        loadingLayout.setVisibility(LinearLayout.VISIBLE);
        
        //Loading ���α׷�����
        progressbar = new ProgressBar(this);
        progressbar.setId(1);
        progressbar.setVisibility(View.INVISIBLE);
        LinearLayout.LayoutParams progressParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        loadingLayout.addView(progressbar, progressParams);        
        
        //Loading ���̾ƿ� �߰�
        FrameLayout.LayoutParams frameLoadingLayoutParams = new FrameLayout.LayoutParams(   
        		LayoutParams.WRAP_CONTENT, 
                LayoutParams.WRAP_CONTENT, 
                Gravity.CENTER);
        addContentView(loadingLayout,frameLoadingLayoutParams);
        
        //����Ʈ ���̾ƿ�
        supportLayout = new LinearLayout(this);
        supportLayout.setGravity(Gravity.RIGHT);
        //supportLayout.setBackgroundColor(Color.DKGRAY);
        
        //����Ʈ ��ư
        supportButton = new Button(this);
        supportButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_icon_info_n));
        LinearLayout.LayoutParams supportParams = new LinearLayout.LayoutParams(
        		deviceWidth / 10,
        		deviceWidth / 10
        		);
        supportParams.gravity = Gravity.CENTER_VERTICAL;
        supportParams.rightMargin = deviceWidth / 20;
        supportLayout.addView(supportButton, supportParams);
        
        //����Ʈ ���̾ƿ��� �߰��Ѵ�.
        FrameLayout.LayoutParams frameSupportParams = new FrameLayout.LayoutParams(   
        		LayoutParams.FILL_PARENT, 
        		deviceHeight / 20 * 2,
                Gravity.BOTTOM);
        addContentView(supportLayout,frameSupportParams);
        
        //������ �����ִ� ���̾ƿ�
        infoLayout = new LinearLayout(this);
        infoLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_info));
        infoLayout.setVisibility(LinearLayout.INVISIBLE);
        
        //���� ���̾ƿ��� �߰��Ѵ�.
        FrameLayout.LayoutParams frameInfoParams = new FrameLayout.LayoutParams(   
        		deviceWidth/10 * 7, 
        		deviceHeight/2,
                Gravity.CENTER);
        addContentView(infoLayout,frameInfoParams);
        
        
        //����
        updateDataOnZoom();

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "DimScreen");
        
        //���ǳ� üũ Ȯ��
        categorySpinner.setOnItemSelectedListener(new OnItemSelectedListener() 
        {
        	public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        	{
        		if(categoryadspin.getItem(position).equals(MainActivity.categoryString))
        			return;
        		
        		//���������� �Ǵ��Ѵ�.
        		MainActivity.categoryString = (String) categoryadspin.getItem(position);
        		NaverDataSource.check_icon = position;
        		ListViewActivity.check_icon = position;
        		
        		MainActivity.changeCategory();
        	}
        	
        	public void onNothingSelected(AdapterView<?> parent){   		
        	}
		});
        
        //����Ʈ ��ư Ŭ�� Ȯ��
        listButton.setOnClickListener(new View.OnClickListener()
        {
           public void onClick(View v)
           {
        	   Intent intent = new Intent(AugmentedActivity.this, ListViewActivity.class);
               startActivity(intent);
           }
        });
        
        listButton.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				
				if(event.getAction() == MotionEvent.ACTION_DOWN)
					listButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_list_c));
				
				else
					listButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_list_n));
				
				return false;
			}
		});
        
        //���� Ȯ�ι�ư Ŭ�� Ȯ��
        supportButton.setOnClickListener(new View.OnClickListener()
        {
           public void onClick(View v)
           {
        	   if(checkInfo == 0)
        	   {
        		   infoLayout.setVisibility(LinearLayout.VISIBLE);
        		   supportButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_icon_info_c));
        		   checkInfo = 1;
        	   }
        	   
        	   else
        	   {
        		   infoLayout.setVisibility(LinearLayout.INVISIBLE);
        		   supportButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.memory_icon_info_n));
        		   checkInfo = 0;
        	   }
           }
        });  
    }

	@Override
	public void onResume() {
		super.onResume();

		wakeLock.acquire();
	}

	@Override
	public void onPause() {
		super.onPause();

		wakeLock.release();
	}
	
	
	@Override
    public void onSensorChanged(SensorEvent evt) {
        super.onSensorChanged(evt);

        if (    evt.sensor.getType() == Sensor.TYPE_ACCELEROMETER || 
                evt.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
        {
        	augmentedView.postInvalidate();
        }
    }
    
    private OnSeekBarChangeListener myZoomBarOnSeekBarChangeListener = new OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateDataOnZoom();
            camScreen.invalidate();
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            //Not used
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            updateDataOnZoom();
            camScreen.invalidate();
        }
    };

    private static float calcZoomLevel(){
        int myZoomLevel = myZoomBar.getProgress();
        float out = 0;

        float percent = 0;
        if (myZoomLevel <= 25) {
            percent = myZoomLevel/25f;
            out = ONE_PERCENT*percent;
        } else if (myZoomLevel > 25 && myZoomLevel <= 50) {
            percent = (myZoomLevel-25f)/25f;
            out = ONE_PERCENT+(TEN_PERCENT*percent);
        } else if (myZoomLevel > 50 && myZoomLevel <= 75) {
            percent = (myZoomLevel-50f)/25f;
            out = TEN_PERCENT+(TWENTY_PERCENT*percent);
        } else {
            percent = (myZoomLevel-75f)/25f;
            out = TWENTY_PERCENT+(EIGHTY_PERCENTY*percent);
        }
        return out;
    }

    protected void updateDataOnZoom() {
        float zoomLevel = calcZoomLevel();
        endLabel.setText(FORMAT.format(zoomLevel)+" km");
        ARData.setRadius(zoomLevel);
        ARData.setZoomLevel(FORMAT.format(zoomLevel));
        ARData.setZoomProgress(myZoomBar.getProgress());
    }

	public boolean onTouch(View view, MotionEvent me) {
	    for (Marker marker : ARData.getMarkers()) {
	        if (marker.handleClick(me.getX(), me.getY())) {
	            if (me.getAction() == MotionEvent.ACTION_UP) markerTouched(marker);
	            return true;
	        }
	    }
		return super.onTouchEvent(me);
	};
	
	protected void markerTouched(Marker marker) {
		Log.w(TAG,"markerTouched() not implemented.");
	}
}