package com.dk.nichlasandreasen.avoidtheblocks.sound;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.dk.nichlasandreasen.avoidtheblocks.R;

public class Effects {
	public boolean sound = true;
	private Context context;
	private MediaPlayer mp = new MediaPlayer();
	

	public Effects(Context context) {
		this.context = context;
	}

	public void picupCoinEffect() {
		if (sound) {
			mp = MediaPlayer.create(context, R.raw.pickup_coin);
			mp.start();
			onComplete();
		}
		
	}

	public void dieEffect() {
		if (sound) {
			mp = MediaPlayer.create(context, R.raw.die);
			mp.start();
			onComplete();
		}
		
	}

	public void hit_hurtEffect() {
		if (sound) {
			mp = MediaPlayer.create(context, R.raw.hit_hurt);
			mp.start();
			onComplete();
		}
		
	}
	public void shield_pickupEffect() {
		if (sound) {
			mp = MediaPlayer.create(context, R.raw.shield_pickup);
			mp.start();
			onComplete();
		}
		
	}

	public void onComplete() {
		mp.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});
	}

}
