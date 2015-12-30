package com.ldm.seatchoosetest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SSThumView extends View {
	private Bitmap a = null;
	private Paint b = null;

	public SSThumView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public void a(Bitmap paramBitmap) {
		this.a = paramBitmap;
	}

	protected void onDraw(Canvas paramCanvas) {
		super.onDraw(paramCanvas);
		// Log.i("TAG", "onDraw()...");
		if (this.a != null)
			paramCanvas.drawBitmap(this.a, 0.0F, 0.0F, this.b);
	}
}