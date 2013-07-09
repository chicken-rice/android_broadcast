package com.blogspot.mohammedari.receivebroadcast;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GraphicView extends View {
    float x, y, power, lastX, lastY;
    int icon_id;

    public void iconMove(float x, float y) {
        this.x += x * 0.5;
        this.y += y * 0.5;
        this.lastX = x;
        this.lastY = y;
        
        invalidate();
    }
    
    public void iconJump(float z) {
    	if(Math.abs(z) > 12.0) {
    	    this.x += this.lastX * 2.0;
    	    this.y += this.lastY * 2.0;
    	}
    }
    
	public GraphicView(Context context, AttributeSet attrs) {
		super(context, attrs);
		x = 250.f;
		y = 250.f;
		power = 1.f;
		icon_id = R.drawable.ic_launcher;
	}
	@Override
	public void onDraw(Canvas canvas) {

		Resources r = getResources();
//		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		Bitmap bitmap = BitmapFactory.decodeResource(r, icon_id);
		canvas.drawBitmap(bitmap, x, y, null);
		
		
		Paint paint = new Paint();
		paint.setColor(Color.argb(75, 0, 0, 0));
		paint.setStrokeWidth(1);
		for (int y = 0; y < 1600; y = y + 10) {
			canvas.drawLine(0, y, 999, y, paint);
		}
		for (int x = 0; x < 1000; x = x + 10) {
			canvas.drawLine(x, 0, x, 1599, paint);
		}


	}

}