package com.man.entity;

import com.man.cfg.CFG;

import android.graphics.PointF;

/**
 * Ů��<br/>
 * �����
 * 
 * @author zhaozhantao@21cn.com
 *
 */
public abstract class Woman extends People{

	/**
	 * ����
	 */
	protected PointF direction;

	/**
	 * ����һ����<br/>
	 * һ�������ڸոճ�����ʱ�򣬽��Լ��ķ���������
	 * 
	 * @param people
	 * 				�ˣ�һ��ָ����
	 */
	public void lookPeople(People people){
		
		if (this.direction == null){
			this.direction = new PointF();
		}
		float dtx = people.location.x - this.location.x;
		float dty = people.location.y - this.location.y;
		
		this.direction.x = (float) (dtx / Math.sqrt(dtx * dtx + dty * dty) * CFG.WOMAN_SPEED);// TODO ��������Ż�
		this.direction.y = (float) (dty / Math.sqrt(dtx * dtx + dty * dty) * CFG.WOMAN_SPEED);
		
	}

	/**
	 * �ƶ�<br/>
	 * ����������
	 */
	public void move() throws PeopleMoveException{
		//������˱߽�����쳣
		float x = location.x + direction.x;
		float y = location.y + direction.y;
		if ((x < 0 || x > CFG.SCREEN_WIDTH) || (y < 0 || y > CFG.SCREEN_HEIGHT)){
			throw new PeopleMoveException();
		}
		location.x = x;
		location.y = y;
	}

	/**
	 * �����ײ
	 */
	@Override
	public boolean collide(People people) {
		//�������Բ�Ƿ��ཻ
		float x = people.location.x - this.location.x;
		float y = people.location.y - this.location.y;
		float distance = (float) Math.sqrt(x * x + y * y);
		return distance < people.radius + this.radius;
	}
	
	
}
