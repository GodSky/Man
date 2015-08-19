package com.man.entity;

import com.man.cfg.CFG;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * 男人<br/>
 * 玩家
 * 
 * @author zhaozhantao@21cn.com
 * 
 */
public class Man extends People {

	/**
	 * 向一个方向移动
	 * 
	 * @param direction
	 *            一个二坐标<br/>
	 *            是一个增量而不是移动的目的地
	 */
	public void move(PointF direction){
		
		//到边界了就不移动，也不抛异常
		float x = location.x + direction.x;
		float y = location.y + direction.y;
		if (x > 0 && x < CFG.SCREEN_WIDTH){
			location.x = x;
		}
			
		if (y > 0 && y < CFG.SCREEN_HEIGHT){
			location.y = y;
		}
		
	}

	@Override
	public boolean collide(People people) {
		// TODO 检测碰撞
		return false;
	}

	@Override
	public void drawMe(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		
		//画男人
		canvas.drawCircle(location.x, location.y, radius, paint);
		
	}

}
