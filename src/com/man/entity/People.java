package com.man.entity;

import com.man.cfg.CFG;

import android.graphics.Canvas;
import android.graphics.PointF;

/**
 * 人<br/>
 * 游戏中的玩家以及敌人
 * 
 * @author zhaozhantao@21cn.com
 * 
 */
public abstract class People {

	/**
	 * 位置
	 */
	protected PointF location;

	/**
	 * 大小<br/>
	 * 半径
	 */
	protected float radius = CFG.PEOPLE_RADIUS;

	/**
	 * 将自己画在画布上
	 * 
	 * @param canvas
	 *            画布
	 */
	public abstract void drawMe(Canvas canvas);

	/**
	 * 检测与另一个人是否碰撞
	 * 
	 * @param people
	 *            对方
	 * @return 是否碰撞
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
