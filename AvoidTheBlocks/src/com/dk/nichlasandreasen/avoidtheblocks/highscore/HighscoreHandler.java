package com.dk.nichlasandreasen.avoidtheblocks.highscore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.util.ArrayList;

import android.content.Context;

public class HighscoreHandler {
	
	public static ArrayList<Integer> highscores = new ArrayList<Integer>();
	public final static String highscoreFile = "highscores";
	private Context context;
	
	public HighscoreHandler(Context context) {
		this.context = context;
	}
	
	public void saveHighscore(){
		FileOutputStream fos;
		ObjectOutputStream os;
		try {
			fos = context.openFileOutput(highscoreFile, Context.MODE_PRIVATE);
			os = new ObjectOutputStream(fos);
			os.writeObject(highscores);
			os.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void retrieveHighscore(){
		
		try {
			FileInputStream fis = context.openFileInput(highscoreFile);
			ObjectInputStream is = new ObjectInputStream(fis);
			highscores = (ArrayList<Integer>) is.readObject();
			is.close();
			fis.close();
		} catch (OptionalDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
