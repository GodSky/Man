package com.man.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * 丑女人<br/>
 * 玩家要躲避丑女人
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
		
		//画丑女
		canvas.drawCircle(location.x, location.y, radius, paint);
	}

}
