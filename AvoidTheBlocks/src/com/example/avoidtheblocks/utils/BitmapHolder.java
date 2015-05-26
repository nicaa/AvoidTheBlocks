package com.example.avoidtheblocks.utils;

import com.example.avoidtheblocks.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapHolder {
	public static Bitmap enemyBlock;
	public static Bitmap powerUpBlock;
	public static Bitmap powerUpCoin;
	
	public BitmapHolder (Context context, int width, int height){
		
		enemyBlock = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemyblock);       
		enemyBlock = Bitmap.createScaledBitmap(enemyBlock,(int)(width * 0.168), (int)(height * 0.1), true);
		
		powerUpBlock = BitmapFactory.decodeResource(context.getResources(), R.drawable.powerupshieldicon);       
		powerUpBlock = Bitmap.createScaledBitmap(powerUpBlock,(int)(width * 0.168), (int)(height * 0.1), true);
		
		powerUpCoin = BitmapFactory.decodeResource(context.getResources(), R.drawable.powerupcoin);       
		powerUpCoin = Bitmap.createScaledBitmap(powerUpCoin,(int)(width * 0.168), (int)(height * 0.1), true);
		
		
	}

}
