package com.example.avoidtheblocks.gamepackage;


import com.example.avoidtheblocks.entities.GenBlocks;

import advertisement.AdActivity;
import android.R.color;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameScreen extends SurfaceView implements SurfaceHolder.Callback{
	
	private GameThread gameThread;
	private Context context;
	private Canvas canvas;
	private int width;
	private int height;
	
	

	public GameScreen(Context context) {
		super(context);
		this.context = context;
		
		getHolder().addCallback(this);
		gameThread = new GameThread(getHolder(), this);// henter SurfaceHolder
								// og denne classe.
		
		setFocusable(true);
	}

	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		postInvalidate();
		return gameThread.onTouchEvent(event);
		
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		gameThread.setRunning(true);
		gameThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
		this.height = height;
		this.height = width;
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		gameThread.setRunning(false);
		boolean retry = true;

		while (retry)
		{
			try
			{
				gameThread.join();
				retry = false;
				
			}
			catch (Exception e)
			{
				Log.v("Exception Occured", e.getMessage());
			}
		}
	}
	
}
