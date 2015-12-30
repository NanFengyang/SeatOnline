package com.ldm.seatchoosetest.view;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.ldm.seatchoosetest.model.CH_seatInfo;
import com.ldm.seatchoosetest.model.SeatStatus;

class SeatGestureListener extends GestureDetector.SimpleOnGestureListener {
	private SatView mSatView;

	SeatGestureListener(SatView paramSatView) {
		mSatView = paramSatView;
	}

	public boolean onDoubleTap(MotionEvent paramMotionEvent) {
		return super.onDoubleTap(paramMotionEvent);
	}

	public boolean onDoubleTapEvent(MotionEvent paramMotionEvent) {
		return super.onDoubleTapEvent(paramMotionEvent);
	}

	public boolean onDown(MotionEvent paramMotionEvent) {
		return false;
	}

	public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2,
			float paramFloat1, float paramFloat2) {
		return false;
	}

	public void onLongPress(MotionEvent paramMotionEvent) {
	}

	public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2,
			float x_scroll_distance, float y_scroll_distance) {
		// 是否可以移动和点击
		if (!SatView.a(mSatView)) {
			return false;
		}
		// 显示缩略图
		SatView.a(mSatView, true);
		boolean bool1 = true;
		boolean bool2 = true;

		if ((SatView.seatViewWidth(mSatView) < mSatView.getMeasuredWidth())
				&& (0.0F == SatView.seatView_X(mSatView))) {
			bool1 = false;
		}

		if ((SatView.seatViewHeight(mSatView) < mSatView.getMeasuredHeight())
				&& (0.0F == SatView.seatView_Y(mSatView))) {
			bool2 = false;
		}

		if (bool1) {
			int k = Math.round(x_scroll_distance);
			Log.i("TAG_rect", k + "=k");
			// 修改排数x轴的偏移量
			SatView.c(mSatView, (float) k);
			// Log.i("TAG", SatView.v(mSatView)+"");
			// 修改座位距离排数的横向距离
			SatView.k(mSatView, k);
			// Log.i("TAG", SatView.r(mSatView)+"");
			if (SatView.getSeatViewRawDistance(mSatView) < 0) {
				// 滑到最左
				SatView.i(mSatView, 0);
				SatView.a(mSatView, 0.0F);
			}

			if (SatView.getSeatViewRawDistance(mSatView) + mSatView.getMeasuredWidth() > SatView
					.seatViewWidth(mSatView)) {
				// 滑到最右
				SatView.i(mSatView, SatView.seatViewWidth(mSatView) - mSatView.getMeasuredWidth());
				SatView.a(mSatView,
						(float) (mSatView.getMeasuredWidth() - SatView.seatViewWidth(mSatView)));
			}
		}

		if (bool2) {
			// 上负下正- 往下滑则减
			int j = Math.round(y_scroll_distance);
			// 修改排数y轴的偏移量
			SatView.d(mSatView, (float) j);
			// 修改可视座位距离顶端的距离
			SatView.l(mSatView, j);
			Log.i("TAG", SatView.t(mSatView) + "");
			if (SatView.t(mSatView) < 0) {
				// 滑到顶
				SatView.j(mSatView, 0);
				SatView.b(mSatView, 0.0F);
			}

			if (SatView.t(mSatView) + mSatView.getMeasuredHeight() > SatView
					.seatViewHeight(mSatView)) {
				// 滑到底
				SatView.j(mSatView, SatView.seatViewHeight(mSatView) - mSatView.getMeasuredHeight());
				SatView.b(mSatView,
						(float) (mSatView.getMeasuredHeight() - SatView.seatViewHeight(mSatView)));
			}
		}

		mSatView.invalidate();

		// Log.i("GestureDetector", "onScroll----------------------");
		return false;
	}

	public void onShowPress(MotionEvent paramMotionEvent) {
	}

	public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent) {
		return false;
	}

	public boolean onSingleTapUp(MotionEvent paramMotionEvent) {
		// 列数
		int i = SatView.a(mSatView, (int) paramMotionEvent.getX());
		// 排数
		int j = SatView.b(mSatView, (int) paramMotionEvent.getY());
		for (int i1 = 0; i1 < SatView.list_SeatInfo(mSatView).size(); i1++) {
			CH_seatInfo ch_s = SatView.list_SeatInfo(mSatView).get(i1);
			if (ch_s.getColumn() == i && ch_s.getRaw() == j) {
				switch (ch_s.getStatus()) {
				case 1:// 已选中
					ch_s.setStatus(SeatStatus.CHOOSE_UN);
					SatView.list_SeatInfo(mSatView).set(i1, ch_s);
					if (SatView.d(mSatView) != null) {
						SatView.d(mSatView).onClick(i1, i + 1, j + 1, ch_s.getStatus());
					}

					break;
				case 0:// 可选
					ch_s.setStatus(SeatStatus.CHOOSE_OK);
					SatView.list_SeatInfo(mSatView).set(i1, ch_s);
					if (SatView.d(mSatView) != null) {
						SatView.d(mSatView).unClick(i1, i + 1, j + 1, ch_s.getStatus());
					}
					break;
				default:
					break;
				}
			}

		}

		// 显示缩略图
		SatView.a(mSatView, true);
		mSatView.invalidate();
		return false;
	}

}