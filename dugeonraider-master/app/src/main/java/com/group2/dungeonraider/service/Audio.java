package com.group2.dungeonraider.service;

import android.content.Context;
import android.media.MediaPlayer;

import java.net.URI;

/**
 * Created by Ajinkya on 10/27/2015.
 */
public interface Audio {
    boolean mute();
    boolean unmute();
    boolean play(Context context, int resId);
    void stop();
    void setlooping();
}
