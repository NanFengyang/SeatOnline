package com.ldm.seatchoosetest.view;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.ldm.seatchoosetest.model.CH_seatInfo;
import com.ldm.seatchoosetest.model.SeatStatus;

class SeatGestureListener extends GestureDetector.SimpleOnGestureListener {
	private SeatView mSatView;

	SeatGestureListener(SeatView paramSatView) {
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

	public boolean onFling(MotionEvent paramMotionEvent1,
			MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2) {
		return false;
	}

	public void onLongPress(MotionEvent paramMotionEvent) {
	}

	public boolean onScroll(MotionEvent paramMotionEvent1,
			MotionEvent paramMotionEvent2, float x_scroll_distance,
			float y_scroll_distance) {
		// 是否可以移动和点�?
		if (!SeatView.a(mSatView)) {
			return false;
		}
		boolean bool1 = true;
		boolean bool2 = true;
		if ((SeatView.seatViewWidth(mSatView) < mSatView.getMeasuredWidth())
				&& (0.0F == SeatView.seatView_X(mSatView))) {
			bool1 = false;
		}
		if ((SeatView.seatViewHeight(mSatView) < mSatView.getMeasuredHeight())
				&& (0.0F == SeatView.seatView_Y(mSatView))) {
			bool2 = false;
		}

		if (bool1 | bool2) {
			// 显示缩略�?
			SeatView.a(mSatView, true);
		}
		if (bool1) {

			int k = Math.round(x_scroll_distance);
			Log.i("TAG_rect", k + "=k");
			// 修改排数x轴的偏移�?
			// SeatView.c(mSatView, (float) k);
			// Log.i("TAG", SatView.v(mSatView)+"");
			// 修改座位距离排数的横向距�?
			SeatView.k(mSatView, k);
			// Log.i("TAG", SatView.r(mSatView)+"");
			if (SeatView.getSeatViewRawDistance(mSatView) < 0) {
				// 滑到�?�?
				SeatView.i(mSatView, 0);
				SeatView.a(mSatView, 0.0F);
			}

			if (SeatView.getSeatViewRawDistance(mSatView)
					+ mSatView.getMeasuredWidth() > SeatView
						.seatViewWidth(mSatView)) {
				// 滑到�?�?
				SeatView.i(mSatView, SeatView.seatViewWidth(mSatView)
						- mSatView.getMeasuredWidth());
				SeatView.a(mSatView,
						(float) (mSatView.getMeasuredWidth() - SeatView
								.seatViewWidth(mSatView)));

			}
		}

		if (bool2) {

			// 上负下正- �?下滑则减
			int j = Math.round(y_scroll_distance);
			// 修改排数y轴的偏移�?
			SeatView.d(mSatView, (float) j);
			// 修改可视座位距离顶端的距�?
			SeatView.l(mSatView, j);
			Log.i("TAG", SeatView.t(mSatView) + "");
			if (SeatView.t(mSatView) < 0) {
				// 滑到�?
				SeatView.j(mSatView, 0);
				SeatView.b(mSatView, 0.0F);
			}

			if (SeatView.t(mSatView) + mSatView.getMeasuredHeight() > SeatView
					.seatViewHeight(mSatView)) {
				// 滑到�?
				SeatView.j(mSatView, SeatView.seatViewHeight(mSatView)
						- mSatView.getMeasuredHeight());
				SeatView.b(mSatView,
						(float) (mSatView.getMeasuredHeight() - SeatView
								.seatViewHeight(mSatView)));
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
		int i = SeatView.a(mSatView, (int) paramMotionEvent.getX());
		// 排数
		int j = SeatView.b(mSatView, (int) paramMotionEvent.getY());
		for (int i1 = 0; i1 < SeatView.list_SeatInfo(mSatView).size(); i1++) {
			CH_seatInfo ch_s = SeatView.list_SeatInfo(mSatView).get(i1);
			if (ch_s.getColumn() == i && ch_s.getRaw() == j) {
				switch (ch_s.getStatus()) {
				case SeatStatus.CHOOSE_OK:// 已�?�中
					ch_s.setStatus(SeatStatus.CHOOSE_UN);
					SeatView.list_SeatInfo(mSatView).set(i1, ch_s);
					if (SeatView.d(mSatView) != null) {
						SeatView.d(mSatView).unClick(ch_s);
					}
					break;
				case SeatStatus.CHOOSE_UN:// 未�?�中
					ch_s.setStatus(SeatStatus.CHOOSE_OK);
					SeatView.list_SeatInfo(mSatView).set(i1, ch_s);
					if (SeatView.d(mSatView) != null) {
						SeatView.d(mSatView).onClick(ch_s);
					}
					break;
				default:
					break;
				}
			}

		}

		// 显示缩略�?
		SeatView.a(mSatView, true);
		mSatView.invalidate();
		return false;
	}

}