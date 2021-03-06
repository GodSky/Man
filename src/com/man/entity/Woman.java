package com.man.entity;

import com.man.cfg.CFG;

import android.graphics.PointF;

/**
 * 女人<br/>
 * 非玩家
 * 
 * @author zhaozhantao@21cn.com
 *
 */
public abstract class Woman extends People{

	/**
	 * 方向
	 */
	protected PointF direction;

	/**
	 * 看向一个人<br/>
	 * 一般用于在刚刚出来的时候，将自己的方向朝向男人
	 * 
	 * @param people
	 * 				人，一般指男人
	 */
	public void lookPeople(People people){
		
		if (this.direction == null){
			this.direction = new PointF();
		}
		float dtx = people.location.x - this.location.x;
		float dty = people.location.y - this.location.y;
		
		this.direction.x = (float) (dtx / Math.sqrt(dtx * dtx + dty * dty) * CFG.WOMAN_SPEED);// TODO 这里可以优化
		this.direction.y = (float) (dty / Math.sqrt(dtx * dtx + dty * dty) * CFG.WOMAN_SPEED);
		
	}

	/**
	 * 移动<br/>
	 * 根据自身方向
	 */
	public void move() throws PeopleMoveException{
		//如果出了边界就抛异常
		float x = location.x + direction.x;
		float y = location.y + direction.y;
		if ((x < 0 || x > CFG.SCREEN_WIDTH) || (y < 0 || y > CFG.SCREEN_HEIGHT)){
			throw new PeopleMoveException();
		}
		location.x = x;
		location.y = y;
	}

	/**
	 * 检测碰撞
	 */
	@Override
	public boolean collide(People people) {
		//检测两个圆是否相交
		float x = people.location.x - this.location.x;
		float y = people.location.y - this.location.y;
		float distance = (float) Math.sqrt(x * x + y * y);
		return distance < people.radius + this.radius;
	}
	
	
}
