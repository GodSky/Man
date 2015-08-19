package com.man.controller;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;

import com.man.GameView;
import com.man.cfg.CFG;
import com.man.entity.Man;
import com.man.entity.PeopleMoveException;
import com.man.entity.UglyWoman;
import com.man.entity.Woman;
import com.man.entity.WomanFactory;
import com.man.msg.TimeMassage;

/**
 * 控制器
 * 
 * @author zhaozhantao@21cn.com
 * 
 */
public class Controller implements Runnable, SensorEventListener {

	/**
	 * 男人
	 */
	private Man man;

	/**
	 * 女人们<br/>
	 * 这可是个复数哦
	 */
	private List<Woman> women;

	/**
	 * 游戏画布
	 */
	private GameView gameView;

	/**
	 * Handler
	 */
	private Handler handler;

	/**
	 * 时间提示器
	 */
	private TimeMassage timeMassage;

	/**
	 * 构造一个控制器
	 */
	public Controller() {
		handler = new Handler();
	}

	/**
	 * 游戏时间<br/>
	 * 单位：秒
	 */
	private float gameTime = 0;

	/**
	 * 游戏是否运行
	 */
	private boolean live = false;

	/**
	 * 画出所有人
	 * 
	 * @param canvas
	 *            画布
	 */
	public void drawAll(Canvas canvas) {

		// 画出男人
		man.drawMe(canvas);

		// 画出女人们
		for (Woman woman : women) {
			woman.drawMe(canvas);
		}

		// 画出时间提示器
		timeMassage.drawMe(canvas);
	}

	public Man getMan() {
		return man;
	}

	public void setMan(Man man) {
		this.man = man;
	}

	public List<Woman> getWomen() {
		return women;
	}

	public void setWomen(List<Woman> women) {
		this.women = women;
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	/**
	 * 这里放置女人移动的方法
	 */
	@Override
	public void run() {
		handler.postDelayed(this, CFG.DELAY_TIME);

		// 计时
		gameTime += CFG.DELAY_TIME / 1000.0;
		timeMassage.setTime((int) gameTime);

		// 需要移除的
		List<Woman> removeWomen = null;
		// 移动女人们
		for (Woman woman : women) {
			try {
				woman.move();
			} catch (PeopleMoveException e) {
				// 如果这个女人出去了
				if (removeWomen == null) {
					removeWomen = new ArrayList<Woman>();
				}
				removeWomen.add(woman);
			}
		}

		if (removeWomen != null) {
			// 移除出界的
			women.removeAll(removeWomen);
		}

		// 再添加几个
		if (women.size() < CFG.GAME_LEVEL[((int) (gameTime / 10))
				% CFG.GAME_LEVEL.length]) {
			int tmpCount = CFG.GAME_LEVEL[((int) (gameTime / 10))
					% CFG.GAME_LEVEL.length]
					- women.size();
			for (int i = 0; i < tmpCount; i++) {
				Woman woman = WomanFactory.getWoman();
				woman.lookPeople(man);
				women.add(woman);
			}
		}

		// 画面重画
		gameView.redraw();

		// 检测碰撞
		checkCollide();

	}

	/**
	 * 对所有的人物检测碰撞
	 */
	private void checkCollide() {

		// 如果丑女人碰撞了男人则游戏结束
		for (Woman woman : women) {
			if (woman.collide(man)) {
				if (woman instanceof UglyWoman) {
					pauseGame();
				}
			}
		}
	}

	/**
	 * 新游戏
	 */
	public void newGame() {

		// 产生男人
		man = new Man();
		man.setLocation(new PointF(120, 150));

		// TODO 应该产生一组女人
		// 产生一个女人
		women = new ArrayList<Woman>();
		Woman woman = WomanFactory.getWoman();
		woman.lookPeople(man); // 让女人看向男人
		women.add(woman);

		// 计时
		gameTime = 0;

		// 创建一个时间提示器
		timeMassage = new TimeMassage();
		timeMassage.setLocation(new PointF(10, 20));
		timeMassage.setTime((int) gameTime);

		// 开始
		startGame();
	}

	/**
	 * 开始游戏
	 */
	public void startGame() {
		handler.removeCallbacks(this);
		handler.post(this);
		live = true;
	}

	/**
	 * 暂停游戏
	 */
	public void pauseGame() {
		handler.removeCallbacks(this);
		live = false;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

	/**
	 * 方向改变<br/>
	 * 事实上无时无刻不在改变
	 * 
	 * @param arg0
	 */
	@Override
	public void onSensorChanged(SensorEvent arg0) {
		if (live) {
			float x = -arg0.values[SensorManager.DATA_X];
			float y = arg0.values[SensorManager.DATA_Y];
			float z = arg0.values[SensorManager.DATA_Z];
			
			//如果屏幕朝下了 暂停游戏
			if (z < -8){
				pauseGame();
			}
			
			PointF direction = new PointF(x, y);
			man.move(direction);
		}
	}

	/**
	 * 是否正在运行
	 * @return
	 */
	public boolean isLive() {
		return live;
	}
	
}
