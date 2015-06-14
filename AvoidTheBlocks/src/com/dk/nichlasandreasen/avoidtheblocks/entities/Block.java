package com.dk.nichlasandreasen.avoidtheblocks.entities;

import com.dk.nichlasandreasen.avoidtheblocks.utils.BitmapHolder;
import com.dk.nichlasandreasen.avoidtheblocks.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Block extends Entity{
	private Context context;
	private Bitmap block;
	private int speed;
	
	public Block(Context context, int width, int height, int x , int y) {
		super();
		this.context = context;
		setX(x);
		setY(y);
		
		setHeight(height);
		setWidth(width);
		//speed = (int)(getHeight() * 0.006);
		initBitmaps();
		setSprite(block);
		setRect(new Rect(getX(), getY(),  getX() + getSprite().getWidth(),getX() + getSprite().getHeight()));
		
	}
	public void initBitmaps(){
		block = BitmapHolder.enemyBlock;
	}
	public void placeBlock(int x, int y){
		setX(x);
		setY(y);
		getRect().set(getX(), getY(),  getX() + getSprite().getWidth(),getY() + getSprite().getHeight());
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
