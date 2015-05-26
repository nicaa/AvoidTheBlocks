package com.example.avoidtheblocks;



import com.example.avoidtheblocks.gamepackage.GameActivity;
import com.example.avoidtheblocks.utils.BitmapHolder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class MainView extends View {
	private Bitmap playButton;
	private int width;
	private int height;
	private Context context;
	public MainView(Context context) {
		super(context);
		this.context = context;
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		width = displayMetrics.widthPixels;
		height = displayMetrics.heightPixels;
		setBackgroundColor(Color.GRAY);
		BitmapHolder bitmapHolder = new BitmapHolder(context, width, height);
		initBitmaps();
		

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawBitmap(playButton, (int)((width * 0.50) - (playButton.getWidth()/2)), (int)((height * 0.30)- (playButton.getHeight()/2)), null);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int eventAction = event.getAction();
		int X = (int) event.getX();
		int Y = (int) event.getY();

		switch (eventAction) {
		case MotionEvent.ACTION_DOWN:

			break;

		case MotionEvent.ACTION_MOVE:
			break;

		case MotionEvent.ACTION_UP:
			if( X >(int)((width * 0.50) - (playButton.getWidth()/2)) && X <(int)((width * 0.50) - (playButton.getWidth()/2)) + playButton.getWidth() &&
	        		Y >((height * 0.30)- (playButton.getHeight()/2)) && Y < ((height * 0.30)- (playButton.getHeight()/2)) + playButton.getHeight() )
	             {
	        		Intent intent = new Intent(context, GameActivity.class);
	 	        	context.startActivity(intent);
	        		System.out.println("Hej");
	             }
			break;
		}
		invalidate();
		return true;
	}
	
	public void initBitmaps(){
		playButton = BitmapFactory.decodeResource(this.getResources(), R.drawable.play_button_up);       
		playButton = Bitmap.createScaledBitmap(playButton,(int)(width * 0.65), (int)(height * 0.20), true);
	}
}
