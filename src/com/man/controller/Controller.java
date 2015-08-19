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
 * ������
 * 
 * @author zhaozhantao@21cn.com
 * 
 */
public class Controller implements Runnable, SensorEventListener {

	/**
	 * ����
	 */
	private Man man;

	/**
	 * Ů����<br/>
	 * ����Ǹ�����Ŷ
	 */
	private List<Woman> women;

	/**
	 * ��Ϸ����
	 */
	private GameView gameView;

	/**
	 * Handler
	 */
	private Handler handler;

	/**
	 * ʱ����ʾ��
	 */
	private TimeMassage timeMassage;

	/**
	 * ����һ��������
	 */
	public Controller() {
		handler = new Handler();
	}

	/**
	 * ��Ϸʱ��<br/>
	 * ��λ����
	 */
	private float gameTime = 0;

	/**
	 * ��Ϸ�Ƿ�����
	 */
	private boolean live = false;

	/**
	 * ����������
	 * 
	 * @param canvas
	 *            ����
	 */
	public void drawAll(Canvas canvas) {

		// ��������
		man.drawMe(canvas);

		// ����Ů����
		for (Woman woman : women) {
			woman.drawMe(canvas);
		}

		// ����ʱ����ʾ��
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
	 * �������Ů���ƶ��ķ���
	 */
	@Override
	public void run() {
		handler.postDelayed(this, CFG.DELAY_TIME);

		// ��ʱ
		gameTime += CFG.DELAY_TIME / 1000.0;
		timeMassage.setTime((int) gameTime);

		// ��Ҫ�Ƴ���
		List<Woman> removeWomen = null;
		// �ƶ�Ů����
		for (Woman woman : women) {
			try {
				woman.move();
			} catch (PeopleMoveException e) {
				// ������Ů�˳�ȥ��
				if (removeWomen == null) {
					removeWomen = new ArrayList<Woman>();
				}
				removeWomen.add(woman);
			}
		}

		if (removeWomen != null) {
			// �Ƴ������
			women.removeAll(removeWomen);
		}

		// ����Ӽ���
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

		// �����ػ�
		gameView.redraw();

		// �����ײ
		checkCollide();

	}

	/**
	 * �����е���������ײ
	 */
	private void checkCollide() {

		// �����Ů����ײ����������Ϸ����
		for (Woman woman : women) {
			if (woman.collide(man)) {
				if (woman instanceof UglyWoman) {
					pauseGame();
				}
			}
		}
	}

	/**
	 * ����Ϸ
	 */
	public void newGame() {

		// ��������
		man = new Man();
		man.setLocation(new PointF(120, 150));

		// TODO Ӧ�ò���һ��Ů��
		// ����һ��Ů��
		women = new ArrayList<Woman>();
		Woman woman = WomanFactory.getWoman();
		woman.lookPeople(man); // ��Ů�˿�������
		women.add(woman);

		// ��ʱ
		gameTime = 0;

		// ����һ��ʱ����ʾ��
		timeMassage = new TimeMassage();
		timeMassage.setLocation(new PointF(10, 20));
		timeMassage.setTime((int) gameTime);

		// ��ʼ
		startGame();
	}

	/**
	 * ��ʼ��Ϸ
	 */
	public void startGame() {
		handler.removeCallbacks(this);
		handler.post(this);
		live = true;
	}

	/**
	 * ��ͣ��Ϸ
	 */
	public void pauseGame() {
		handler.removeCallbacks(this);
		live = false;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

	/**
	 * ����ı�<br/>
	 * ��ʵ����ʱ�޿̲��ڸı�
	 * 
	 * @param arg0
	 */
	@Override
	public void onSensorChanged(SensorEvent arg0) {
		if (live) {
			float x = -arg0.values[SensorManager.DATA_X];
			float y = arg0.values[SensorManager.DATA_Y];
			float z = arg0.values[SensorManager.DATA_Z];
			
			//�����Ļ������ ��ͣ��Ϸ
			if (z < -8){
				pauseGame();
			}
			
			PointF direction = new PointF(x, y);
			man.move(direction);
		}
	}

	/**
	 * �Ƿ���������
	 * @return
	 */
	public boolean isLive() {
		return live;
	}
	
}
