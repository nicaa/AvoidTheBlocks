package com.example.avoidtheblocks.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.content.Context;


public class GenBlocks {
	private ArrayList<Block> blockList = new ArrayList<Block>();
	private ArrayList<Integer> values = new ArrayList<Integer>();
	private Context context;
	private int width;
	private int height;
	private Random random = new Random();
	private int blockspeed = 8;
	private int count = 0;
	private int score = -1;
	
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
		int randomValue = random.nextInt(5)+1;
		for (int i = 0; i < randomValue; i++) {
			Block block = new Block(context, width, height, values.get(i), (int)(height*1.20));
			block.setSpeed(blockspeed);
			blockList.add(block);
		}
		if (count == 4) {
			if (blockspeed <= 24) {
				blockspeed+=(int)(height * 0.001);
			}
			
			count = 0;
		}
		count++;
		setScore(getScore() + 1);
		System.out.println(blockspeed + "  Count = " + count);
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

}
