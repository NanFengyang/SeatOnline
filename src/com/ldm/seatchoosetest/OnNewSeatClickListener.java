package com.ldm.seatchoosetest;

import com.ldm.seatchoosetest.model.CH_seatInfo;

public abstract interface OnNewSeatClickListener {
	/**
	 * 取消选择
	 * 
	 * @param position
	 * @param Column
	 * @param Raw
	 * @param status
	 * @return
	 */
	public abstract boolean unClick(CH_seatInfo seatInfo);

	/**
	 * 点击选择
	 * 
	 * @param position
	 * @param Column
	 * @param Raw
	 * @param status
	 * @return
	 */
	public abstract boolean onClick(CH_seatInfo seatInfo);
}