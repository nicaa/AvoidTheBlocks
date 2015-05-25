package com.example.avoidtheblocks.gamepackage;




import com.example.avoidtheblocks.entities.GenBlocks;
import com.example.avoidtheblocks.entities.Player;

import android.content.Context;
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
	private int width;
	private int height;
	private Player player;
	private GenBlocks genBlocks;

	public GameThread(SurfaceHolder surfaceHolder, GameScreen GameScreen) {
		this.gameScreen = GameScreen;
		this.surfaceHolder = surfaceHolder;
		context = GameScreen.getContext();
		DisplayMetrics display = GameScreen.getResources().getDisplayMetrics();
		width = display.widthPixels;
		height = display.heightPixels;	
		player = new Player(context, width, height);
		paint.setColor(Color.GREEN);
		paint.setTextSize((int)(width*0.10));
		paintRed.setColor(Color.RED);
		paintRed.setTextSize((int)(width*0.18));
		genBlocks = new GenBlocks(context, width, height);
		paintGrey.setColor(Color.GRAY);
		genBlocks.generateBlocks();
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
				// Syncronized så der kun er en der kan tegne af gangen da vi
				// arbejder med threads
				synchronized (surfaceHolder) {
					if (c != null) {
						c.drawColor(Color.DKGRAY);
						Collision(c);
						doDraw(c);
						moveBlocks();
					}
				}// end of synchronized
			} catch (Exception e) {                 
                e.printStackTrace();
            
			} finally {
				if (c != null) {
					surfaceHolder.unlockCanvasAndPost(c); // unlocker så der kan
					// tegnes igen.
				}
			}
		}
	}
	public void doDraw(Canvas canvas){
		canvas.drawBitmap(player.getSprite(), player.getX(), player.getY(), null);
		canvas.drawText(genBlocks.getScore()+"", (int)(width*0.45), (int)(height*0.05), paint);
		for (int i = 0; i < genBlocks.getBlockList().size(); i++) {
			canvas.drawBitmap(genBlocks.getBlockList().get(i).getSprite(), genBlocks.getBlockList().get(i).getX(), genBlocks.getBlockList().get(i).getY(), null);
		}
		for (int i = 0; i < genBlocks.getPowerUpList().size(); i++) {
			canvas.drawBitmap(genBlocks.getPowerUpList().get(i).getSprite(), genBlocks.getPowerUpList().get(i).getX(), genBlocks.getPowerUpList().get(i).getY(), null);
		}
		
		player.drawPowerUps(canvas);
		player.drawPowerUpFrame(canvas);
		
		if (debug) {
			canvas.drawRect(player.getRect(), paint);	
			for (int i = 1; i < 6; i++) {
				canvas.drawLine((int)(width*0.168)*i, 0, (int)(width*0.168)*i, height, paintGrey);
			}
		}
		
	}
	public void moveBlocks(){
		
		for (int i = 0; i < genBlocks.getBlockList().size(); i++) {
			genBlocks.getBlockList().get(i).moveUp();
			if(genBlocks.getBlockList().get(i).getY() < 0 - (int)(height* 0.15)){
				genBlocks.getBlockList().clear();
				genBlocks.generateBlocks();
			}
		}
		
		for (int i = 0; i < genBlocks.getPowerUpList().size(); i++) {
			genBlocks.getPowerUpList().get(i).moveUp();
			if(genBlocks.getPowerUpList().get(i).getY() < 0 - (int)(height* 0.15)){
				genBlocks.getPowerUpList().remove(i);
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
					//genBlocks.getBlockList().clear(); Use this in special move!!!
					//genBlocks.generateBlocks();
				}else if(X > width / 2 && player.getX() + player.getSprite().getWidth() < width){
					player.moveRight();
				}
				
				
				break;
			case MotionEvent.ACTION_MOVE:
				
				break;
			}
			 
			return true;
		}

	}
	
	
	public void Collision(Canvas canvas){
		for (int i = 0; i < genBlocks.getBlockList().size(); i++) {
			if(player.collision(player.getRect(), genBlocks.getBlockList().get(i).getRect())&& player.getPowerUpList().size()>0){
				genBlocks.getBlockList().remove(i);
				player.getPowerUpList().remove(0);
			}else if(player.collision(player.getRect(), genBlocks.getBlockList().get(i).getRect())){
				System.out.println("Collision!!");
				canvas.drawText("You lost!", (int)(width * 0.15), (int)(height*0.45), paintRed);
				setRunning(false);
			}
		}
		for (int i = 0; i < genBlocks.getPowerUpList().size(); i++) {
			if(player.collision(player.getRect(), genBlocks.getPowerUpList().get(i).getRect()) && player.getPowerUpList().size()!=2){
				genBlocks.getPowerUpList().remove(i);
				player.getPowerUpList().add(player.getPowerUp());
			}
		}
		
	}
	
	
	
	

}
