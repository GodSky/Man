package com.man.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * ��Ů��<br/>
 * ���Ҫ��ܳ�Ů��
 * 
 * @author zhaozhantao@21cn.com
 *
 */
public class UglyWoman extends Woman {


	
	@Override
	public void drawMe(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.GREEN);
		
		//����Ů
		canvas.drawCircle(location.x, location.y, radius, paint);
	}

}
