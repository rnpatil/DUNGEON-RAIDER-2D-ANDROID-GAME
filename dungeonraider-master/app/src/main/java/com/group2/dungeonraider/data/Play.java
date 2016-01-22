package com.group2.dungeonraider.data;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;

import com.group2.dungeonraider.R;
import com.group2.dungeonraider.domain.Player;
import com.group2.dungeonraider.domain.Room;
import com.group2.dungeonraider.service.Audio;
import com.group2.dungeonraider.service.AudioImpl;
import com.group2.dungeonraider.utilities.Constants;
import com.group2.dungeonraider.utilities.CustomTimer;
import com.group2.dungeonraider.utilities.Utils;

public class Play extends Activity
{
	private GameView mGameView = null;

	private DisplayMetrics mMetrics = new DisplayMetrics();
	private float mScreenDensity;
	public Play getPlayeActivity(){
		return  this;
	}
	//public static Play end;
	Audio audio = new AudioImpl();
	MediaPlayer stereo= null;
	Room room = new Room();
	DatabaseHelper db=new DatabaseHelper(Constants.appContext);

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Context mContext = getApplicationContext();
		Constants.appContext = getApplicationContext();

		if(Constants.VOLUME_MODE == 1) {

			if(stereo!=null)
			{
				stereo.reset();
				stereo.release();
			}

			stereo = MediaPlayer.create(Constants.appContext, R.raw.game);
			stereo.start();
			stereo.setLooping(true);

		}
	//	audio.play(Constants.appContext, R.raw.game);
	//	audio.setloop(Constants.appContext, R.raw.game);
				/**
		 * Get the screen density that all pixel values will be based on.
		 * This allows scaling of pixel values over different screen sizes.
		 * 
		 * See: http://developer.android.com/reference/android/util/DisplayMetrics.html
		 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
	    mScreenDensity = mMetrics.density;

		Log.d("Tile Game Example", "Starting game at stage: " + Constants.STAGE + ", level: " + Constants.GAME_LEVEL);
		if(Constants.IS_PLAYER_LEVEL){
			Constants.GAME_START_TIME = System.currentTimeMillis();
			Constants.IS_NEW = true;
			mGameView = new GameView(mContext, this, mScreenDensity);
		}else{
			Utils.clearGameData();
			Constants.IS_PAUSE = false;
			Constants.CUR_LEVEL_REMAINING_TIME = 0;
			Constants.GAME_NO_OF_POTIONS = Player.getInstance().getItemCount(Constants.ITEM_POTION);
			Constants.GAME_NO_OF_MAP = Player.getInstance().getItemCount(Constants.ITEM_MAP);
			Constants.GAME_NO_OF_BOMBS = Player.getInstance().getItemCount(Constants.ITEM_BOMB);
			Constants.GAME_NO_OF_KEYS = Player.getInstance().getItemCount(Constants.ITEM_KEY);
			Constants.PLAYER_GOLD = Player.getInstance().getGold();
			Constants.PLAYER_SCORE = Player.getInstance().getScore();
			Constants.GAME_START_TIME = System.currentTimeMillis();
			Constants.IS_NEW = true;
			mGameView = new GameView(mContext, this, Constants.STAGE, Constants.GAME_LEVEL, mScreenDensity);
		}
		new CustomTimer(1500000, 1);
		setContentView(mGameView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();

		
		return true;
	}

	/**
	 * Invoked when the Activity loses user focus.
	 */


	@Override
	public void onResume() {

		super.onResume();
		if(Constants.VOLUME_MODE == 1) {

			if(stereo!=null)
			{
				stereo.reset();
				stereo.release();

			}
			stereo = MediaPlayer.create(Constants.appContext, R.raw.game);
			stereo.start();
			stereo.setLooping(true);


		}

	}

	@Override
	protected void onPause() {

		super.onPause();
		if(Constants.VOLUME_MODE ==1) {
			stereo.stop();
		}

	}
	@Override
	public void onBackPressed() {


		Constants.IS_PAUSE = true;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				Play.this);

		alertDialogBuilder.setTitle("Are you sure you want to leave the game? All game data for this level will be lost.");


		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						db.deletePlayerCurrentRoom(Constants.GAME_LEVEL);
						Constants.IS_PLAYER_LEVEL = false;
						Play.this.finish();

						if(Constants.VOLUME_MODE ==1) {
							stereo.stop();
							//Toast.makeText(this, "volume play", Toast.LENGTH_SHORT).show();
						}

						//launchIntent();
					}
				})

				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
						Constants.IS_PAUSE = false;
					}
				});


		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();




	}


	}

