package com.paar.ch9;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		 // ��ư�� ��� �����Ѵ�.
        Button button = (Button) findViewById(R.id.button1);
         
        // ��ư�� Ŭ�� �����ʸ� ����Ѵ�.
        // ��ϵ� Ŭ�� �����ʴ� ��ư�� Ŭ�� �̺�Ʈ�� �߻������� ó���� �� �ִ�.
        button.setOnClickListener(new OnClickListener() {
			
        	// Ŭ�� �̺�Ʈ�� ó���Ѵ�.
            public void onClick(View v)
            {
                // ������ ���� ���� �ϴ� ����Ʈ�� �����Ѵ�.
                // FirstActivity���� SecondActivity�� �̵� �Ұ��� �����Ͽ���.
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                 
                // ����Ʈ�� �ִ� ������� ��Ƽ��Ƽ�� �����Ѵ�.
                startActivity(intent);
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
