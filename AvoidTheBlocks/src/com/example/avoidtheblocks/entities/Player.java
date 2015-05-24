package com.example.avoidtheblocks.entities;

import com.example.avoidtheblocks.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Player extends Entity{
	private Bitmap player;
	private Context context;

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
	}
	
	public void moveLeft() {
		setX(getX() - player.getWidth());
		getRect().set(getX(), getY(),  getX() + getSprite().getWidth(),getY() + getSprite().getHeight());
	}
	
	public void moveRight() {
		setX(getX() + player.getWidth());
		getRect().set(getX(), getY(),  getX() + getSprite().getWidth(),getY() + getSprite().getHeight());
	}
}
