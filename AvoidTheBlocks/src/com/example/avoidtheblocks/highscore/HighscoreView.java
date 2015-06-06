package com.example.avoidtheblocks.highscore;

import com.example.avoidtheblocks.R;
import com.example.avoidtheblocks.gamepackage.GameActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class HighscoreView extends View {
	
	private int width;
	private int height;
	private Context context;
	private HighscoreHandler highscoreHandler;
	private Paint paint = new Paint();
	private Bitmap trophyscreen;
	private Bitmap backButton;
	
	
	public HighscoreView(Context context) {
		super(context);
		this.context = context;
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		width = displayMetrics.widthPixels;
		height = displayMetrics.heightPixels;
		setBackgroundColor(Color.DKGRAY);
		highscoreHandler = new HighscoreHandler(context);
		highscoreHandler.retrieveHighscore();
		paint.setColor(Color.parseColor("#FF8914"));
		paint.setTextSize((int)(width*0.10));
		paint.setAntiAlias(true);
		System.out.println(HighscoreHandler.highscores.size());
		initBitmaps();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawBitmap(trophyscreen, 0, 0, null);
		for (int i = 0; i < HighscoreHandler.highscores.size(); i++) {
			canvas.drawText("Nr " + (i+1)+ ": " + HighscoreHandler.highscores.get(i), (int)(width*0.30), (int)((height*0.15) * (i+1))+50, paint);
		}
		canvas.drawBitmap(backButton, (int)(width*0.50)- (backButton.getWidth()/2), (int)(height*0.84), null);
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
			if(X >(int)((width * 0.50) - (backButton.getWidth()/2)) && X <(int)((width * 0.50) - (backButton.getWidth()/2)) + backButton.getWidth() &&
		    		Y >(height * 0.84) && Y < (height * 0.84) + backButton.getHeight() )
				         {
							((Activity)context).finish();
				         }
			break;
		}
		invalidate();
		return true;
	}
	
	public void initBitmaps(){
		trophyscreen = BitmapFactory.decodeResource(this.getResources(), R.drawable.trophyscreen);       
		trophyscreen = Bitmap.createScaledBitmap(trophyscreen,(int)(width), (int)(height), true);
		
		backButton = BitmapFactory.decodeResource(this.getResources(), R.drawable.backbutton);       
		backButton = Bitmap.createScaledBitmap(backButton,(int)(width * 0.40), (int)(height * 0.10), true);
	}

}
