package com.man;

import com.man.controller.Controller;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.View;

/**
 * 游戏面板
 * 
 * @author zhaozhantao@21cn.com
 *
 */
public class GameView extends View {

	/**
	 * 控制器
	 */
	private Controller controller;
	
	/**
	 * 处理画面更新的线程
	 */
	private Runnable updateThread;
	
	/**
	 * Handler
	 */
	private Handler handler;
	
	/**
	 * 构造方法
	 * 
	 * @param context
	 */
	public GameView(Context context) {
		super(context);
		
		handler = new Handler();
		
		updateThread = new Runnable() {
			
			@Override
			public void run() {
				//更新画面
				postInvalidate();
				handler.removeCallbacks(updateThread);
			}
		};
	}

	@Override
	protected void onDraw(Canvas canvas) {
		controller.drawAll(canvas);
		super.onDraw(canvas);
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * 刷新面板
	 */
	public void redraw(){
		handler.post(updateThread);
	}
	
}
