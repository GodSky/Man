package com.man.entity;

import com.man.cfg.CFG;

import android.graphics.Canvas;
import android.graphics.PointF;

/**
 * ��<br/>
 * ��Ϸ�е�����Լ�����
 * 
 * @author zhaozhantao@21cn.com
 * 
 */
public abstract class People {

	/**
	 * λ��
	 */
	protected PointF location;

	/**
	 * ��С<br/>
	 * �뾶
	 */
	protected float radius = CFG.PEOPLE_RADIUS;

	/**
	 * ���Լ����ڻ�����
	 * 
	 * @param canvas
	 *            ����
	 */
	public abstract void drawMe(Canvas canvas);

	/**
	 * �������һ�����Ƿ���ײ
	 * 
	 * @param people
	 *            �Է�
	 * @return �Ƿ���ײ
	 */
	public abstract boolean collide(People people);

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public PointF getLocation() {
		return location;
	}

	public void setLocation(PointF location) {
		this.location = location;
	}
	
	
}
