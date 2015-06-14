package com.dk.nichlasandreasen.avoidtheblocks.entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

import com.dk.nichlasandreasen.avoidtheblocks.utils.BitmapHolder;

public class PowerUpCoin extends Entity{
	
	private Context context;
	private Bitmap powerUp;
	private int speed; 
	
	
	public PowerUpCoin(Context context, int width, int height, int x , int y){
		this.context = context;
		setX(x);
		setY(y);
		setHeight(height);
		setWidth(width);
		speed = (int)(width*0.006);
		initBitmaps();
		setSprite(powerUp);
		setRect(new Rect(getX(), getY(),  getX() + getSprite().getWidth(),getX() + getSprite().getHeight()));
	}
	
	
	public void initBitmaps(){
		powerUp = BitmapHolder.powerUpCoin;
	}
	
	public void moveUp() {
		setY(getY() - speed);
		getRect().set(getX(), getY(),  getX() + getSprite().getWidth(),getY() + getSprite().getHeight());
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}