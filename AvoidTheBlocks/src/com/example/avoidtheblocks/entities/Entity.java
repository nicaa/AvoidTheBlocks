package com.example.avoidtheblocks.entities;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Entity {

	public Entity() {

	}

	private int x = 0;
	private int y = 0;
	private int width;
	private int height;
	private Bitmap sprite;
	private Rect rect;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Bitmap getSprite() {
		return sprite;
	}

	public void setSprite(Bitmap sprite) {
		this.sprite = sprite;
	}

	public Rect getRect() {
		return rect;
	}

	public void setRect(Rect rect) {
		this.rect = rect;
	}
	public boolean collision(Rect rect1, Rect rect2){
		if (rect1.intersect(rect2)) {
			return true;
		}else{
			return false;
		}
	}

}
