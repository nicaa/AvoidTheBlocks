package com.dk.nichlasandreasen.avoidtheblocks.utils;

import com.dk.nichlasandreasen.avoidtheblocks.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapHolder {
	public static Bitmap enemyBlock;
	public static Bitmap powerUpBlock;
	public static Bitmap powerUpCoin;
	public static Bitmap retryButton;
	public static Bitmap quitButton;
	
	public BitmapHolder (Context context, int width, int height){
		
		enemyBlock = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemyblock);       
		enemyBlock = Bitmap.createScaledBitmap(enemyBlock,(int)(width * 0.168), (int)(height * 0.1), true);
		
		powerUpBlock = BitmapFactory.decodeResource(context.getResources(), R.drawable.powerupshieldicon);       
		powerUpBlock = Bitmap.createScaledBitmap(powerUpBlock,(int)(width * 0.168), (int)(height * 0.1), true);
		
		powerUpCoin = BitmapFactory.decodeResource(context.getResources(), R.drawable.powerupcoin);       
		powerUpCoin = Bitmap.createScaledBitmap(powerUpCoin,(int)(width * 0.168), (int)(height * 0.1), true);
		
		retryButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.retrybutton);       
		retryButton = Bitmap.createScaledBitmap(retryButton,(int)(width * 0.50), (int)(height * 0.12), true);
		
		quitButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.quitbutton);       
		quitButton = Bitmap.createScaledBitmap(quitButton,(int)(width * 0.40), (int)(height * 0.10), true);
		
		
		
	}

}
