package com.example.avoidtheblocks.highscore;

import com.example.avoidtheblocks.gamepackage.GameActivity;

import android.content.Context;
import android.content.Intent;
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
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		for (int i = 0; i < 5; i++) {
			canvas.drawText("Nr " + (i+1)+ ": " + HighscoreHandler.highscores.get(i), (int)(width*0.30), (int)(height*0.17) * (i+1), paint);
		}
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
			
			break;
		}
		invalidate();
		return true;
	}

}
