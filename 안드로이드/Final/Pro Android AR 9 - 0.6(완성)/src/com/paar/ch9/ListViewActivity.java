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

	// ����Ʈ�� ����
	private ListView listview = null;

	// �˻��� ��
	private EditText searchText = null;

	// �����͸� ������ Adapter
	private DataAdapter adapter = null;

	// �����͸� ���� �ڷᱸ��
	private static ArrayList<CData> alist = null;
	private static List<Marker> collection = null;

	// �����ּ�
	private static final String urlNaver = "http://map.naver.com/local/siteview.nhn?code=";

	// �Ÿ� ����
	private static final DecimalFormat FORMAT = new DecimalFormat("#");

	// ������
	public static int check_icon = 0;
	
	//�ؽ�Ʈ
	public static TextView text = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.listview);

		// ������ ����Ʈ�信 ����� �����俬��
		listview = (ListView) findViewById(R.id.customList);
		searchText = (EditText) findViewById(R.id.searchText);
		
		text = (TextView) findViewById(R.id.kindtext);
		
		//� �������� �����ش�.
		kind();

		// ��ü�� �����մϴ�
		alist = new ArrayList<CData>();

		// �����͸� �ޱ����� �����;���� ��ü ����
		adapter = new DataAdapter(this, alist);

		// ����Ʈ�信 ����� ����
		listview.setAdapter(adapter);

		// ArrayAdapter�� ���ؼ� ArrayList�� �ڷ� ����
		// �ϳ��� String���� �����ϴ� ���� CDataŬ������ ��ü�� �����ϴ������� ����
		// CData ��ü�� �����ڿ� ����Ʈ ǥ�� �ؽ�Ʈ��1�� ����� �ؽ�Ʈ��2�� ���� �׸��� �����̹������� ����Ϳ� ����

		// CDataŬ������ ���鶧�� ������� �ش� �μ����� �Է�
		// ���� ������ ����Ʈ�信 �ѷ��� ���� �����̶�� �����ϸ� �˴ϴ�.

		// ��Ŀ���� �޾ƿ´�.
		try {
			AddData("");
		} catch (Exception e) {
			Log.d("Error", e.toString());
		}

		// ����Ʈ ��ư Ŭ��
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
			
			//����Ʈ�� �ѹ� �ʱ�ȭ�Ѵ�.
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
		// ���̾ƿ� XML�� �о���̱� ���� ��ü
		private LayoutInflater mInflater;

		public DataAdapter(Context context, ArrayList<CData> object) {

			// ���� Ŭ������ �ʱ�ȭ ����
			// context, 0, �ڷᱸ��
			super(context, 0, object);
			mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		}

		// �������� ��Ÿ���� �ڽ��� ���� xml�� ���̱� ���� ����
		@Override
		public View getView(int position, View v, ViewGroup parent) {
			View view = null;

			// ���� ����Ʈ�� �ϳ��� �׸� ���� ��Ʈ�� ���
			if (v == null) {

				// XML ���̾ƿ��� ���� �о ����Ʈ�信 ����
				view = mInflater.inflate(R.layout.listview_row, null);
			} else {

				view = v;
			}

			// �ڷḦ �޴´�.
			final CData data = this.getItem(position);

			if (data != null) {
				// ȭ�� ���
				TextView title = (TextView) view
						.findViewById(R.id.item_row_title);
				TextView description = (TextView) view
						.findViewById(R.id.item_row_description);

				// �ؽ�Ʈ��1�� getLabel()�� ��� �� ù��° �μ���
				title.setText(data.getLabel());
				// �ؽ�Ʈ��2�� getData()�� ��� �� �ι�° �μ���
				description.setText(data.getData());

				// �̹��� ��
				ImageView iv = (ImageView) view
						.findViewById(R.id.item_row_draw);

				// �̹����信 �ѷ��� �ش� �̹������� ���� �� ����° �μ���
				iv.setImageResource(data.getDraw());

			}

			return view;

		}

	}

	// CData�ȿ� ���� ���� ���� �Ҵ�
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
			// �׸�
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
			text.setText("�Ĵ�");
			return R.drawable.memory_eat_n;

		case 1:
			text.setText("ī��");
			return R.drawable.memory_cafe_n;

		case 2:
			text.setText("����");
			return R.drawable.memory_drink_n;

		case 3:
			text.setText("����");
			return R.drawable.memory_shopping_n;

		case 4:
			text.setText("������");
			return R.drawable.memory_sports_n;

		case 5:
			text.setText("����");
			return R.drawable.memory_sleep_n;

		case 6:
			text.setText("�Ƿ�");
			return R.drawable.memory_hospital_n;

		case 7:
			text.setText("����");
			return R.drawable.memory_money_n;

		case 8:
			text.setText("������");
			return R.drawable.memory_government_n;

		case 9:
			text.setText("�̿�");
			return R.drawable.memory_beauty_n;

		case 10:
			text.setText("pc��");
			return R.drawable.memory_pc_n;

		case 11:
			text.setText("������");
			return R.drawable.memory_gasstation_n;
		}

		return 0;
	}
	
	private void kind() {
		switch (check_icon) {
		case 0:
			text.setText("�Ĵ�");
			break;

		case 1:
			text.setText("ī��");
			break;
		
		
		case 2:
			text.setText("����");
			break;
		
		case 3:
			text.setText("����");
			break;
		
		case 4:
			text.setText("������");
			break;
		
		case 5:
			text.setText("����");
			break;
		
		case 6:
			text.setText("�Ƿ�");
			break;
		
		case 7:
			text.setText("����");
			break;
		
		case 8:
			text.setText("������");
			break;
		
		case 9:
			text.setText("�̿�");
			break;
		
		case 10:
			text.setText("pc��");
			break;
		
		case 11:
			text.setText("������");
			break;
		}
	}
}
