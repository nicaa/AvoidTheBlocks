package com.example.avoidtheblocks;



import com.example.avoidtheblocks.gamepackage.GameActivity;
import com.example.avoidtheblocks.highscore.Highscore;
import com.example.avoidtheblocks.highscore.HighscoreActivity;
import com.example.avoidtheblocks.highscore.HighscoreHandler;
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
	private Bitmap menuText;
	private Bitmap menuInfo;
	private Bitmap highScore;
	private int width;
	private int height;
	private Context context;
	private HighscoreHandler highscoreHandler;
	public MainView(Context context) {
		super(context);
		this.context = context;
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		width = displayMetrics.widthPixels;
		height = displayMetrics.heightPixels;
		setBackgroundColor(Color.DKGRAY);
		//Init bitmaps for later use!
		BitmapHolder bitmapHolder = new BitmapHolder(context, width, height);
		highscoreHandler = new HighscoreHandler(context);
		highscoreHandler.retrieveHighscore();
		initBitmaps();
		

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(playButton, (int)((width * 0.50) - (playButton.getWidth()/2)), (int)((height * 0.20)- (playButton.getHeight()/2)), null);
		canvas.drawBitmap(highScore, (int)((width * 0.50) - (highScore.getWidth()/2)), (int)((height * 0.35)- (highScore.getHeight()/2)), null);
		canvas.drawBitmap(menuText, (int)((width * 0.50) - (menuText.getWidth()/2)),  (int)((height * 0.85)- (menuText.getHeight()/2)), null);
		canvas.drawBitmap(menuInfo, (int)((width * 0.50) - (menuInfo.getWidth()/2)),  (int)((height * 0.57)- (menuInfo.getHeight()/2)), null);
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
	        		Y >((height * 0.20)- (playButton.getHeight()/2)) && Y < ((height * 0.20)- (playButton.getHeight()/2)) + playButton.getHeight() )
	             {
	        		Intent intent = new Intent(context, GameActivity.class);
	 	        	context.startActivity(intent);
	             }
			
			if( X >(int)((width * 0.50) - (highScore.getWidth()/2)) && X <(int)((width * 0.50) - (highScore.getWidth()/2)) + highScore.getWidth() &&
    		Y >((height * 0.35)- (highScore.getHeight()/2)) && Y < ((height * 0.35)- (highScore.getHeight()/2)) + highScore.getHeight() )
		         {
		    		Intent intent = new Intent(context, HighscoreActivity.class);
			        context.startActivity(intent);
				
		         }
			break;
		}
		invalidate();
		return true;
	}
	
	public void initBitmaps(){
		playButton = BitmapFactory.decodeResource(this.getResources(), R.drawable.play_button_up);       
		playButton = Bitmap.createScaledBitmap(playButton,(int)(width * 0.65), (int)(height * 0.15), true);
		
		highScore = BitmapFactory.decodeResource(this.getResources(), R.drawable.highscore_button);       
		highScore = Bitmap.createScaledBitmap(highScore,(int)(width * 0.65), (int)(height * 0.15), true);
		
		menuText = BitmapFactory.decodeResource(this.getResources(), R.drawable.menuscreen);       
		menuText = Bitmap.createScaledBitmap(menuText,(int)(width ), (int)(height * 0.38), true);
		
		menuInfo = BitmapFactory.decodeResource(this.getResources(), R.drawable.menuinfo);       
		menuInfo = Bitmap.createScaledBitmap(menuInfo,(int)(width * 0.60 ), (int)(height * 0.22), true);
		
		
	}
}
