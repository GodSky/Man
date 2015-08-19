package com.man.entity;

import com.man.cfg.CFG;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * ����<br/>
 * ���
 * 
 * @author zhaozhantao@21cn.com
 * 
 */
public class Man extends People {

	/**
	 * ��һ�������ƶ�
	 * 
	 * @param direction
	 *            һ��������<br/>
	 *            ��һ�������������ƶ���Ŀ�ĵ�
	 */
	public void move(PointF direction){
		
		//���߽��˾Ͳ��ƶ���Ҳ�����쳣
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
		// TODO �����ײ
		return false;
	}

	@Override
	public void drawMe(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		
		//������
		canvas.drawCircle(location.x, location.y, radius, paint);
		
	}

}
