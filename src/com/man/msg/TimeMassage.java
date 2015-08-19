package com.man.msg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * ʱ����ʾ
 * 
 * @author zhaozhantao@21cn.com
 *
 */
public class TimeMassage {

	/**
	 * λ��
	 */
	private PointF location;
	
	/**
	 * ʱ��
	 */
	private int time;
	
	/**
	 * ���Լ�
	 * 
	 * @param canvas
	 */
	public void drawMe(Canvas canvas){
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.YELLOW);
		
		canvas.drawText("" + time, location.x, location.y, paint);
	}

	public PointF getLocation() {
		return location;
	}

	public void setLocation(PointF location) {
		this.location = location;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
}
