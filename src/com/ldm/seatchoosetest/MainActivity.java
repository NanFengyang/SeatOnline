package com.ldm.seatchoosetest;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.ldm.seatchoosetest.model.CH_seatInfo;
import com.ldm.seatchoosetest.model.SeatStatus;
import com.ldm.seatchoosetest.view.SSThumView;
import com.ldm.seatchoosetest.view.SeatView;

@SuppressLint("ShowToast")
public class MainActivity extends Activity {
	private static final int ROW = 6;
	private static final int column = 6;
	private SeatView mSSView;
	private SSThumView mSSThumView;
	private List<CH_seatInfo> list_CH_seatInfo = new ArrayList<CH_seatInfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		mSSView = (SeatView) this.findViewById(R.id.mSSView);
		mSSThumView = (SSThumView) this.findViewById(R.id.ss_ssthumview);
		SeatiInforData();
		mSSView.init(list_CH_seatInfo, mSSThumView, 10);
		mSSView.setOnSeatClickListener(new OnNewSeatClickListener() {
			@Override
			public boolean unClick(CH_seatInfo seatInfo) {
				// TODO Auto-generated method stub
				int raw = seatInfo.getRaw() + 1;
				int Column = seatInfo.getColumn() + 1;
				Toast.makeText(MainActivity.this, "取消" + raw + "排-" + Column,
						Toast.LENGTH_SHORT).show();
				return false;
			}
			@Override
			public boolean onClick(CH_seatInfo seatInfo) {
				// TODO Auto-generated method stub
				int raw = seatInfo.getRaw() + 1;
				int Column = seatInfo.getColumn() + 1;
				Toast.makeText(MainActivity.this, "选中" + raw + "排-" + Column,
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
				cs.setStatus(SeatStatus.CHOOSE_UN);
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
