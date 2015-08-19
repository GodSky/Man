package com.man;

import com.man.controller.Controller;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

/**
 * ��activity��
 * 
 * @author zhaozhantao@21cn.com
 * 
 */
public class Man20 extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.newGame:
			//����Ϸ
			controller.newGame();
			break;
		case R.id.startOrPause:
			if (controller.isLive()){
				controller.pauseGame();
			} else {
				controller.startGame();
			}
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	/**
	 * ������
	 */
	private Controller controller;

	/**
	 * ��Ϸ���
	 */
	private GameView gameView;

	/**
	 * ��Ӧ��������
	 */
	private SensorManager sensorManager = null;

	/**
	 * ��Ӧ��
	 */
	private Sensor sensor = null;

	private PowerManager.WakeLock mWakeLock;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initActivity();//ϵͳ��ʼ��
		
		// �����������������󲢹���
		controller = new Controller();
		gameView = new GameView(this);
		controller.setGameView(gameView);
		gameView.setController(controller);

		// ���ٸ�Ӧ�����
		// ���¼�����controller������
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(controller, sensor,
				SensorManager.SENSOR_DELAY_GAME);

		// ����Ϸ
		controller.newGame();

		setContentView(gameView);
	}

	/**
	 * ϵͳ��ʼ��
	 */
	private void initActivity() {
		// �����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ����ȫ��
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//��Ļ��������Ĵ���
		PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
		mWakeLock = pm.newWakeLock(
				PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
		
	}

	@Override
	protected void onPause() {
		mWakeLock.release();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mWakeLock.acquire();
		super.onResume();
	}
}