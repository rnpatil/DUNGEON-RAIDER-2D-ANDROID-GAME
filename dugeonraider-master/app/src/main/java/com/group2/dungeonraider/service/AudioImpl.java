package com.group2.dungeonraider.service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.group2.dungeonraider.R;
import com.group2.dungeonraider.controller.MainActivity;
import com.group2.dungeonraider.utilities.Constants;

/**
 * Created by Ajinkya on 10/27/2015.
 */
public class AudioImpl extends Activity implements Audio {

    private static final String LOG = AudioImpl.class.getSimpleName();

    @Override
    public boolean mute() {


        Log.d(LOG, "mute() -> Mute game.");
        return false;

    }

    @Override
    public boolean unmute() {
        return false;
    }

    @Override
    public boolean play(Context context, int resId) {
        Constants.MP.setAudioStreamType(AudioManager.STREAM_MUSIC);
        if(Constants.VOLUME_MODE == 1) {

            Constants.MP.create(context, resId).start();

        }
        return true;
    }
    @Override
    public void stop(){
        Constants.MP.stop();

    }
    @Override
    public void setlooping(){

        Constants.MP.setLooping(true);
    }
}
