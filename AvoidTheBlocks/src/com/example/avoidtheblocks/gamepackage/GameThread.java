package com.example.avoidtheblocks.gamepackage;




import java.util.Collections;

import com.example.avoidtheblocks.entities.GenBlocks;
import com.example.avoidtheblocks.entities.Player;
import com.example.avoidtheblocks.highscore.Highscore;
import com.example.avoidtheblocks.highscore.HighscoreActivity;
import com.example.avoidtheblocks.highscore.HighscoreHandler;
import com.example.avoidtheblocks.utils.BitmapHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class GameThread extends Thread{
	
	boolean running;
	private GameScreen gameScreen;
	private Context context;
	private SurfaceHolder surfaceHolder;
	private Paint paint = new Paint();
	private Paint paintRed = new Paint();
	private Paint paintGrey = new Paint();
	
	private boolean debug = false; // <-- used for easier testing in game! true = debug functions enable
	private boolean test = false;
	private boolean dead = false;
	private int width;
	private int height;
	private Player player;
	private GenBlocks genBlocks;
	private HighscoreHandler highscoreHandler;
	private Bitmap retryButton;
	private Bitmap quitButton;

	public GameThread(SurfaceHolder surfaceHolder, GameScreen GameScreen) {
		this.gameScreen = GameScreen;
		this.surfaceHolder = surfaceHolder;
		context = GameScreen.getContext();
		DisplayMetrics display = GameScreen.getResources().getDisplayMetrics();
		width = display.widthPixels;
		height = display.heightPixels;	
		player = new Player(context, width, height);
		paint.setColor(Color.parseColor("#FF8914"));
		paint.setTextSize((int)(width*0.10));
		paint.setAntiAlias(true);
		
		paintRed.setColor(Color.parseColor("#FF8914"));
		paintRed.setTextSize((int)(width*0.15));
		paintRed.setAntiAlias(true);
		genBlocks = new GenBlocks(context, width, height);
		paintGrey.setColor(Color.GRAY);
		genBlocks.generateBlocks();
		highscoreHandler = new HighscoreHandler(context);
		
		retryButton = BitmapHolder.retryButton;
		quitButton = BitmapHolder.quitButton;
		
	}
	
	

	public void setRunning(boolean running){
		this.running = running;
	}
	
	@Override
	public void run() {
		while (running) {
			Canvas c = new Canvas();
			try {
				c = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					if (c != null) {
						c.drawColor(Color.DKGRAY);
						Collision();
						doDraw(c);
						moveBlocks();
					}
				}// end of synchronized
			} catch (Exception e) {                 
                e.printStackTrace();
            
			} finally {
				if (c != null) {
					surfaceHolder.unlockCanvasAndPost(c); 
				}
			}
		}
	}
	public void doDraw(Canvas canvas){
		canvas.drawBitmap(player.getSprite(), player.getX(), player.getY(), null);
		
		for (int i = 0; i < genBlocks.getPowerUpList().size(); i++) {
			canvas.drawBitmap(genBlocks.getPowerUpList().get(i).getSprite(), genBlocks.getPowerUpList().get(i).getX(), genBlocks.getPowerUpList().get(i).getY(), null);
		}
		for (int i = 0; i < genBlocks.getPowerUpCoinList().size(); i++) {
			canvas.drawBitmap(genBlocks.getPowerUpCoinList().get(i).getSprite(), genBlocks.getPowerUpCoinList().get(i).getX(), genBlocks.getPowerUpCoinList().get(i).getY(), null);
		}

		for (int i = 0; i < genBlocks.getBlockList().size(); i++) {
			canvas.drawBitmap(genBlocks.getBlockList().get(i).getSprite(), genBlocks.getBlockList().get(i).getX(), genBlocks.getBlockList().get(i).getY(), null);
			System.out.println(genBlocks.getBlockList().get(i).getY() + " count = " + i);
		}
		player.drawPowerUps(canvas);
		player.drawPowerUpFrame(canvas);
		
		if (debug) {
			canvas.drawRect(player.getRect(), paint);	
			for (int i = 1; i < 6; i++) {
				canvas.drawLine((int)(width*0.168)*i, 0, (int)(width*0.168)*i, height, paintGrey);
			}
		}
		canvas.drawText(genBlocks.getScore()+"", (int)(width*0.45), (int)(height*0.05), paint);
		if (!running) {
			canvas.drawBitmap(retryButton, (int)(width*0.50)- (retryButton.getWidth()/2), (int)(height*0.70), null);
			canvas.drawBitmap(quitButton, (int)(width*0.50)- (quitButton.getWidth()/2), (int)(height*0.82), null);
			canvas.drawText("You lost!", (int)(width * 0.20), (int)(height*0.45), paintRed);
			canvas.drawText("Score: " + genBlocks.getScore(), (int)(width * 0.20), (int)(height*0.55), paintRed);
		}
		
		
	}
	public void moveBlocks(){
		for (int i = 0; i < genBlocks.getBlockList().size(); i++) {
			if(genBlocks.getBlockList().get(i).getY() < 0 - (int)(height* 0.15)){
				
				genBlocks.getBlockList().clear();		
				genBlocks.generateBlocks();
			}
			/*if (genBlocks.getBlockList().get(0).getY() == 0 - genBlocks.getBlockspeed()) {
				
				
			}*/
			genBlocks.getBlockList().get(i).moveUp();
		}
		
		
		for (int i = 0; i < genBlocks.getPowerUpList().size(); i++) {
			genBlocks.getPowerUpList().get(i).moveUp();
			if(genBlocks.getPowerUpList().get(i).getY() < 0 - (int)(height* 0.15)){
				genBlocks.getPowerUpList().remove(i);
			}
		}
		
		for (int i = 0; i < genBlocks.getPowerUpCoinList().size(); i++) {
			genBlocks.getPowerUpCoinList().get(i).moveUp();
			if(genBlocks.getPowerUpCoinList().get(i).getY() < 0 - (int)(height* 0.15)){
				genBlocks.getPowerUpCoinList().remove(i);
			}
		}
	}
	
	
	public boolean onTouchEvent(MotionEvent event) {
		synchronized (surfaceHolder) {
			int eventAction = event.getAction();
			int X = (int) event.getX();
			int Y = (int) event.getY();
			
			switch (eventAction) {
				case MotionEvent.ACTION_DOWN:
					
					break;
				case MotionEvent.ACTION_UP:
					if (X < width / 2 && player.getX()>0) {
						player.moveLeft();
					}else if(X > width / 2 && player.getX() + player.getSprite().getWidth() < width){
						player.moveRight();
					}
					
					if(!running && X >(int)((width * 0.50) - (retryButton.getWidth()/2)) && X <(int)((width * 0.50) - (retryButton.getWidth()/2)) + retryButton.getWidth() &&
		    		Y >(height * 0.70) && Y < (height * 0.70) + retryButton.getHeight() )
				         {
							((Activity)context).finish();
				    		Intent intent = new Intent(context, GameActivity.class);
			 	        	context.startActivity(intent);
				    		
				         }
					if(!running && X >(int)((width * 0.50) - (quitButton.getWidth()/2)) && X <(int)((width * 0.50) - (quitButton.getWidth()/2)) + quitButton.getWidth() &&
		    		Y >(height * 0.82) && Y < (height * 0.82) + quitButton.getHeight() )
				         {
							((Activity)context).finish();
						
				         }
					
					break;
				case MotionEvent.ACTION_MOVE:
					
					break;
				}
			 
			return true;
		}

	}
	
	
	public void Collision(){
		for (int i = 0; i < genBlocks.getBlockList().size(); i++) {
			if(player.collision(player.getRect(), genBlocks.getBlockList().get(i).getRect())&& player.getPowerUpList().size()>0){
				genBlocks.getBlockList().remove(i);
				player.getPowerUpList().remove(0);
			}else if(player.collision(player.getRect(), genBlocks.getBlockList().get(i).getRect())){
				
				insertHighscore();
				setRunning(false);
			}
		}
		for (int i = 0; i < genBlocks.getPowerUpList().size(); i++) {
			if(player.collision(player.getRect(), genBlocks.getPowerUpList().get(i).getRect()) && player.getPowerUpList().size()!=2){
				genBlocks.getPowerUpList().remove(i);
				player.getPowerUpList().add(player.getPowerUp());
			}
		}
		for (int i = 0; i < genBlocks.getPowerUpCoinList().size(); i++) {
			if(player.collision(player.getRect(), genBlocks.getPowerUpCoinList().get(i).getRect())){
				genBlocks.getPowerUpCoinList().remove(i);
				genBlocks.setScore(genBlocks.getScore() + 1);
			}
		}
		
	}
	
	public void insertHighscore(){
		highscoreHandler.retrieveHighscore();
		HighscoreHandler.highscores.add(genBlocks.getScore());
		Collections.sort(HighscoreHandler.highscores, Collections.reverseOrder());
		
		for (int i = 0; i < HighscoreHandler.highscores.size(); i++) {
			if(i >= 5){
				HighscoreHandler.highscores.remove(i);
			}
		}
		
		highscoreHandler.saveHighscore();
		
		
	}
	
	public void dead(){
		genBlocks.getBlockList().clear();
		genBlocks.getPowerUpCoinList().clear();
		genBlocks.getPowerUpList().clear();
		genBlocks.generateBlocks();
		genBlocks.setScore(0);
	}
}
