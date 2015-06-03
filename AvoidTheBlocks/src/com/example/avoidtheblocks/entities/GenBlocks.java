package com.example.avoidtheblocks.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.content.Context;


public class GenBlocks {
	private ArrayList<Block> blockList = new ArrayList<Block>();
	private ArrayList<PowerUpShield> powerUpShieldList = new ArrayList<PowerUpShield>();
	private ArrayList<PowerUpCoin> powerUpCoinList = new ArrayList<PowerUpCoin>();
	private ArrayList<Integer> values = new ArrayList<Integer>();
	private Context context;
	private int width;
	private int height;
	private Random random = new Random();
	private int blockspeed = 10;
	private int count = 0;
	private int score = -1;
	private boolean powerOpBoolean = false;// power up boolean
	
	public GenBlocks(Context context, int width, int height) {
		this.context = context;
		this.width = width;
		this.height = height;
		addValues();
	}
	public void addValues() {
		for (int i = 0; i < 6; i++) {
			values.add(i * (int)(width* 0.168));
		}	
	}

	public void generateBlocks(){	
		
		Collections.shuffle(values);
		int randomValue = random.nextInt((5-3)+1)+3;
		for (int i = 0; i < randomValue; i++) {
			Block block = new Block(context, width, height, values.get(i), (int)(height));
			
			block.setSpeed(getBlockspeed());
			blockList.add(block);
		}
		if (count == 4) {
			if (getBlockspeed() <= 24) {
				setBlockspeed(getBlockspeed() + (int)(height * 0.001));
			}
			
			count = 0;
		}
		count++;
		setScore(getScore() + 1);
		int powerRandom = random.nextInt(7);
		if(powerRandom == 0 && powerUpShieldList.size() != 1) {
			generatePowerUpShield();
		}else if (powerRandom < 5 ) {
			generatePowerUpCoin();
		}
		
		System.out.println(getBlockspeed() + "  Count = " + count + "  Score = " + (score+1));
	}
	
	public void generatePowerUpShield() {
			PowerUpShield powerUpBlock = new PowerUpShield(context, width, height,values.get(0), (int)(height*1.0));
			powerUpShieldList.add(powerUpBlock);
	}
	
	public void generatePowerUpCoin() {
		PowerUpCoin powerUpBlock = new PowerUpCoin(context, width, height,values.get(0), (int)(height*1.0));
		powerUpCoinList.add(powerUpBlock);
}
	
	public ArrayList<Block> getBlockList() {
		return blockList;
	}

	public void setBlockList(ArrayList<Block> blockList) {
		this.blockList = blockList;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public ArrayList<PowerUpShield> getPowerUpList() {
		return powerUpShieldList;
	}
	public void setPowerUpList(ArrayList<PowerUpShield> powerUpList) {
		this.powerUpShieldList = powerUpList;
	}
	public ArrayList<PowerUpCoin> getPowerUpCoinList() {
		return powerUpCoinList;
	}
	public void setPowerUpCoinList(ArrayList<PowerUpCoin> powerUpCoinList) {
		this.powerUpCoinList = powerUpCoinList;
	}
	public int getBlockspeed() {
		return blockspeed;
	}
	public void setBlockspeed(int blockspeed) {
		this.blockspeed = blockspeed;
	}

}
