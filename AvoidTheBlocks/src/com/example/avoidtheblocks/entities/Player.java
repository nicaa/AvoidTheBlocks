package com.example.avoidtheblocks.entities;

import java.util.ArrayList;

import com.example.avoidtheblocks.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player extends Entity{
	private Bitmap player;
	private Bitmap powerUp;
	private Bitmap powerUpFrame;
	private Context context;
	private ArrayList<Bitmap> powerUpList = new ArrayList<Bitmap>();
	

	public Player(Context context, int width, int height) {
		super();
		this.context = context;
		
		setHeight(height);
		setWidth(width);
		setY((int)(height*0.10));
		initBitmaps();
		setSprite(player);
		setRect(new Rect(getX(), getY(),  getX() + getSprite().getWidth(),getX() + getSprite().getHeight()));
	}
	
	
	public void initBitmaps(){
		player = BitmapFactory.decodeResource(context.getResources(), R.drawable.playerblock);       
		player = Bitmap.createScaledBitmap(player,(int)(getWidth() * 0.168), (int)(getHeight() * 0.10), true);
		
		setPowerUp(BitmapFactory.decodeResource(context.getResources(), R.drawable.powerupshieldicon));       
		setPowerUp(Bitmap.createScaledBitmap(getPowerUp(),(int)(getWidth() * 0.12), (int)(getHeight() * 0.08), true));
		
		setPowerUpFrame((BitmapFactory.decodeResource(context.getResources(), R.drawable.powerupframe)));       
		setPowerUpFrame((Bitmap.createScaledBitmap(getPowerUpFrame(),(int)(getWidth() * 0.24), (int)(getHeight() * 0.08), true)));
	}
	
	public void moveLeft() {
		setX(getX() - player.getWidth());
		getRect().set(getX(), getY(),  getX() + getSprite().getWidth(),getY() + getSprite().getHeight());
	}
	
	public void moveRight() {
		setX(getX() + player.getWidth());
		getRect().set(getX(), getY(),  getX() + getSprite().getWidth(),getY() + getSprite().getHeight());
	}

	public void drawPowerUps(Canvas canvas){
		for (int i = 0; i < powerUpList.size(); i++) {
			canvas.drawBitmap(powerUpList.get(i), (int)((getWidth() * 0.005)+ (getPowerUp().getWidth()*i)), (int)(getHeight() * 0.002), null);
		}
	}
	public void drawPowerUpFrame(Canvas canvas){
		canvas.drawBitmap(powerUpFrame, (int)(getWidth() * 0.005), (int)(getHeight() * 0.002), null);
	}
	
	public ArrayList<Bitmap> getPowerUpList() {
		return powerUpList;
	}
	
	public void setPowerUpList(ArrayList<Bitmap> powerUpList) {
		this.powerUpList = powerUpList;
	}


	public Bitmap getPowerUp() {
		return powerUp;
	}


	public void setPowerUp(Bitmap powerUp) {
		this.powerUp = powerUp;
	}


	public Bitmap getPowerUpFrame() {
		return powerUpFrame;
	}


	public void setPowerUpFrame(Bitmap powerUpFrame) {
		this.powerUpFrame = powerUpFrame;
	}
}
