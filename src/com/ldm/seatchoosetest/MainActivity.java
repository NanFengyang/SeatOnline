package com.ldm.seatchoosetest;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.ldm.seatchoosetest.model.CH_seatInfo;
import com.ldm.seatchoosetest.view.SSThumView;
import com.ldm.seatchoosetest.view.SatView;

@SuppressLint("ShowToast")
public class MainActivity extends Activity {
	private static final int ROW = 6;
	private static final int column = 6;
	private SatView mSSView;
	private SSThumView mSSThumView;
	private List<CH_seatInfo> list_CH_seatInfo = new ArrayList<CH_seatInfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		mSSView = (SatView) this.findViewById(R.id.mSSView);
		mSSThumView = (SSThumView) this.findViewById(R.id.ss_ssthumview);
		SeatiInforData();
		mSSView.init(list_CH_seatInfo, mSSThumView);
		mSSView.setOnSeatClickListener(new OnNewSeatClickListener() {

			@Override
			public boolean unClick(int position, int Column, int Raw, int status) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,
						"你取消了第" + position + "个座位，" + Raw + "排" + Column + "座，状态：" + status,
						Toast.LENGTH_SHORT).show();
				return false;
			}

			@Override
			public boolean onClick(int position, int Column, int Raw, int status) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,
						"你点击了第" + position + "个座位，" + Raw + "排" + Column + "座，状态：" + status,
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}

	private void SeatiInforData() {
		int index = 0;
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < column; j++) {
				CH_seatInfo cs = new CH_seatInfo();
				cs.setPosition(index);
				cs.setRaw(i);
				cs.setColumn(j);
				cs.setStatus(0);
				list_CH_seatInfo.add(cs);
				index += 1;
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
