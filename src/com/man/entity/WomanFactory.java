package com.man.entity;

import com.man.cfg.CFG;

import android.graphics.PointF;

/**
 * Ů�˹���<br/>
 * ר����Ů��
 * 
 * @author zhaozhantao@21cn.com
 *
 */
public class WomanFactory {

	/**
	 * ����һ��Ů��
	 * @return Ů��
	 */
	public static Woman getWoman(){
		
		//����һ����Ů��
		Woman woman = new UglyWoman();
		
		//����Ů������
		float rnd1 = (float) Math.random();
		float rnd2 = (float) Math.random();
		float rnd3 = (float) Math.random();
		PointF point = new PointF();
		if (rnd1 > 0.5){
			//��������
			point.x = CFG.SCREEN_WIDTH * rnd3;
			point.y = (rnd2 > 0.5)? 0 : CFG.SCREEN_HEIGHT;
		} else {
			//��������
			point.y = CFG.SCREEN_HEIGHT * rnd3;
			point.x = (rnd2 > 0.5) ? 0 : CFG.SCREEN_WIDTH;
		}
		woman.setLocation(point);
		
		//����Ů��
		return woman;
	}
}
