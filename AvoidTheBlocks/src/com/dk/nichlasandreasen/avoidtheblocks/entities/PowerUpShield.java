package com.dk.nichlasandreasen.avoidtheblocks.entities;

import com.dk.nichlasandreasen.avoidtheblocks.utils.BitmapHolder;
import com.dk.nichlasandreasen.avoidtheblocks.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class PowerUpShield extends Entity{
	
	private Context context;
	private Bitmap powerUp;
	private int speed; 
	
	
	public PowerUpShield(Context context, int width, int height, int x , int y){
		this.context = context;
		setX(x);
		setY(y);
		setHeight(height);
		setWidth(width);
		speed = (int)(width*0.007);
		initBitmaps();
		setSprite(powerUp);
		setRect(new Rect(getX(), getY(),  getX() + getSprite().getWidth(),getX() + getSprite().getHeight()));
	}
	
	
	public void initBitmaps(){
		powerUp = BitmapHolder.powerUpBlock;
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
