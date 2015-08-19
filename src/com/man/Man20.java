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
 * 主activity类
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
			//新游戏
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
	 * 控制器
	 */
	private Controller controller;

	/**
	 * 游戏面板
	 */
	private GameView gameView;

	/**
	 * 感应器管理器
	 */
	private SensorManager sensorManager = null;

	/**
	 * 感应器
	 */
	private Sensor sensor = null;

	private PowerManager.WakeLock mWakeLock;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initActivity();//系统初始化
		
		// 创建控制器及面板对象并关连
		controller = new Controller();
		gameView = new GameView(this);
		controller.setGameView(gameView);
		gameView.setController(controller);

		// 加速感应器相关
		// 将事件交由controller捕获处理
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(controller, sensor,
				SensorManager.SENSOR_DELAY_GAME);

		// 新游戏
		controller.newGame();

		setContentView(gameView);
	}

	/**
	 * 系统初始化
	 */
	private void initActivity() {
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//屏幕常亮所需的代码
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