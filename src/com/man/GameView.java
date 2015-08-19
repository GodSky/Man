package com.man;

import com.man.controller.Controller;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.View;

/**
 * ��Ϸ���
 * 
 * @author zhaozhantao@21cn.com
 *
 */
public class GameView extends View {

	/**
	 * ������
	 */
	private Controller controller;
	
	/**
	 * ��������µ��߳�
	 */
	private Runnable updateThread;
	
	/**
	 * Handler
	 */
	private Handler handler;
	
	/**
	 * ���췽��
	 * 
	 * @param context
	 */
	public GameView(Context context) {
		super(context);
		
		handler = new Handler();
		
		updateThread = new Runnable() {
			
			@Override
			public void run() {
				//���»���
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
	 * ˢ�����
	 */
	public void redraw(){
		handler.post(updateThread);
	}
	
}
