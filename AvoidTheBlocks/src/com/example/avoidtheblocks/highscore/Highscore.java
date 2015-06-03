package com.example.avoidtheblocks.highscore;

import java.io.Serializable;

public class Highscore implements Serializable {

	private static final long serialVersionUID = 1L;
	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
