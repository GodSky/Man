package com.man.entity;

import android.graphics.Canvas;

/**
 * 美女<br/>
 * 玩家碰到即可得分
 * 
 * @author zhaozhantao@21cn.com
 *
 */
public class Peri extends Woman {

	
	@Override
	public boolean collide(People people) {
		// TODO 检测碰撞
		return false;
	}

	@Override
	public void drawMe(Canvas canvas) {
		// TODO 画美女

	}

}
