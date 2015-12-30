package com.ldm.seatchoosetest;

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
	public abstract boolean unClick(int position, int Column, int Raw, int status);

	/**
	 * 点击选择
	 * 
	 * @param position
	 * @param Column
	 * @param Raw
	 * @param status
	 * @return
	 */
	public abstract boolean onClick(int position, int Column, int Raw, int status);
}