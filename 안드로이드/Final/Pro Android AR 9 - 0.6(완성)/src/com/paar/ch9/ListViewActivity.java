package com.paar.ch9;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends Activity {

	// 리스트뷰 선언
	private ListView listview = null;

	// 검색용 바
	private EditText searchText = null;

	// 데이터를 연결할 Adapter
	private DataAdapter adapter = null;

	// 데이터를 담을 자료구조
	private static ArrayList<CData> alist = null;
	private static List<Marker> collection = null;

	// 연결주소
	private static final String urlNaver = "http://map.naver.com/local/siteview.nhn?code=";

	// 거리 포맷
	private static final DecimalFormat FORMAT = new DecimalFormat("#");

	// 아이콘
	public static int check_icon = 0;
	
	//텍스트
	public static TextView text = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.listview);

		// 선언한 리스트뷰에 사용할 리스뷰연결
		listview = (ListView) findViewById(R.id.customList);
		searchText = (EditText) findViewById(R.id.searchText);
		
		text = (TextView) findViewById(R.id.kindtext);
		
		//어떤 상태인지 보여준다.
		kind();

		// 객체를 생성합니다
		alist = new ArrayList<CData>();

		// 데이터를 받기위해 데이터어댑터 객체 선언
		adapter = new DataAdapter(this, alist);

		// 리스트뷰에 어댑터 연결
		listview.setAdapter(adapter);

		// ArrayAdapter를 통해서 ArrayList에 자료 저장
		// 하나의 String값을 저장하던 것을 CData클래스의 객체를 저장하던것으로 변경
		// CData 객체는 생성자에 리스트 표시 텍스트뷰1의 내용과 텍스트뷰2의 내용 그리고 사진이미지값을 어댑터에 연결

		// CData클래스를 만들때의 순서대로 해당 인수값을 입력
		// 한줄 한줄이 리스트뷰에 뿌려질 한줄 한줄이라고 생각하면 됩니다.

		// 마커들을 받아온다.
		try {
			AddData("");
		} catch (Exception e) {
			Log.d("Error", e.toString());
		}

		// 리스트 버튼 클릭
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int num,
					long arg3) {

				String id = adapter.getItem(num).getId();

				LocationView.marker_id = ((id.length() == 9) ? id.substring(1,
						9) : id.substring(2, 10));
				LocationView.check_icon = NaverDataSource.check_icon;
				Intent intent = new Intent(ListViewActivity.this,
						LocationView.class);

				startActivity(intent);
			}
		});

		TextWatcher textWatcherInput = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				
				try {
					AddData(s.toString());
				} catch (Exception e) {
					Log.d("Error", e.toString());
				}
			}
		};

		searchText.addTextChangedListener(textWatcherInput);
	}

	private void AddData(String text) {
		try {
			
			//리스트를 한번 초기화한다.
			adapter.clear();
			collection = ARData.getMarkers();
			
			if (text == null) 
			{
				for (Marker marker : collection) 
				{
					adapter.add(new CData(getApplicationContext(), marker.getId() ,marker
							.getName(), FORMAT.format(marker.getDistance())
							+ " m", reicon()));
				}
			}

			else 
			{
				for (Marker marker : collection) 
				{
					if (marker.getName().contains(text)) 
					{
						adapter.add(new CData(getApplicationContext(), marker.getId() ,marker
								.getName(), FORMAT.format(marker.getDistance())
								+ " m", reicon()));
					}
				}
			}
		} catch (Exception e) {
			Log.d("Error", e.toString());
		}
	}

	private class DataAdapter extends ArrayAdapter<CData> {
		// 레이아웃 XML을 읽어들이기 위한 객체
		private LayoutInflater mInflater;

		public DataAdapter(Context context, ArrayList<CData> object) {

			// 상위 클래스의 초기화 과정
			// context, 0, 자료구조
			super(context, 0, object);
			mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		}

		// 보여지는 스타일을 자신이 만든 xml로 보이기 위한 구문
		@Override
		public View getView(int position, View v, ViewGroup parent) {
			View view = null;

			// 현재 리스트의 하나의 항목에 보일 컨트롤 얻기
			if (v == null) {

				// XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
				view = mInflater.inflate(R.layout.listview_row, null);
			} else {

				view = v;
			}

			// 자료를 받는다.
			final CData data = this.getItem(position);

			if (data != null) {
				// 화면 출력
				TextView title = (TextView) view
						.findViewById(R.id.item_row_title);
				TextView description = (TextView) view
						.findViewById(R.id.item_row_description);

				// 텍스트뷰1에 getLabel()을 출력 즉 첫번째 인수값
				title.setText(data.getLabel());
				// 텍스트뷰2에 getData()을 출력 즉 두번째 인수값
				description.setText(data.getData());

				// 이미지 뷰
				ImageView iv = (ImageView) view
						.findViewById(R.id.item_row_draw);

				// 이미지뷰에 뿌려질 해당 이미지값을 연결 즉 세번째 인수값
				iv.setImageResource(data.getDraw());

			}

			return view;

		}

	}

	// CData안에 받은 값을 직접 할당
	class CData {

		private String m_szId;
		private String m_szLabel;
		private String m_szData;
		private int m_draw;

		public CData(Context context, String p_szId, String p_szLabel, String p_szDataFile,
				int p_draw) {

			m_szId = p_szId;
			m_szLabel = p_szLabel;
			m_szData = p_szDataFile;
			// 그림
			m_draw = p_draw;

		}
		
		public String getId(){
			return m_szId;
		}

		public String getLabel() {
			return m_szLabel;
		}

		public String getData() {
			return m_szData;
		}

		public int getDraw() {
			return m_draw;
		}
	}

	private int reicon() {
		switch (check_icon) {
		case 0:
			text.setText("식당");
			return R.drawable.memory_eat_n;

		case 1:
			text.setText("카페");
			return R.drawable.memory_cafe_n;

		case 2:
			text.setText("술집");
			return R.drawable.memory_drink_n;

		case 3:
			text.setText("쇼핑");
			return R.drawable.memory_shopping_n;

		case 4:
			text.setText("스포츠");
			return R.drawable.memory_sports_n;

		case 5:
			text.setText("숙박");
			return R.drawable.memory_sleep_n;

		case 6:
			text.setText("의료");
			return R.drawable.memory_hospital_n;

		case 7:
			text.setText("은행");
			return R.drawable.memory_money_n;

		case 8:
			text.setText("관공서");
			return R.drawable.memory_government_n;

		case 9:
			text.setText("미용");
			return R.drawable.memory_beauty_n;

		case 10:
			text.setText("pc방");
			return R.drawable.memory_pc_n;

		case 11:
			text.setText("주유소");
			return R.drawable.memory_gasstation_n;
		}

		return 0;
	}
	
	private void kind() {
		switch (check_icon) {
		case 0:
			text.setText("식당");
			break;

		case 1:
			text.setText("카페");
			break;
		
		
		case 2:
			text.setText("술집");
			break;
		
		case 3:
			text.setText("쇼핑");
			break;
		
		case 4:
			text.setText("스포츠");
			break;
		
		case 5:
			text.setText("숙박");
			break;
		
		case 6:
			text.setText("의료");
			break;
		
		case 7:
			text.setText("은행");
			break;
		
		case 8:
			text.setText("관공서");
			break;
		
		case 9:
			text.setText("미용");
			break;
		
		case 10:
			text.setText("pc방");
			break;
		
		case 11:
			text.setText("주유소");
			break;
		}
	}
}
