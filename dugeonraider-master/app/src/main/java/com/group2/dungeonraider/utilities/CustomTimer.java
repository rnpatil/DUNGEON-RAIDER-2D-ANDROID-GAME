package com.group2.dungeonraider.utilities;

import android.os.CountDownTimer;

/**
 * Created by Ajinkya on 11/20/2015.
 */
public class CustomTimer extends CountDownTimerPausable{

    private long startPauseTime;
    CountDownTimerPausable cntDownTimer;
    int t = 0;

    private long pauseTime = 0L;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CustomTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        if(Constants.TIMER != null){
            Constants.TIMER.cancel();
            Constants.TIMER = null;
        }
        Constants.TIMER = super.start();
    }
//    public static CountDownTimerPausable getTimer(){
//        return cntDownTimer;
//    }


    public void resume(){
        super.start();
        //pauseTime += System.currentTimeMillis()-startPauseTime;
    }

    @Override
    public void onTick(long millisUntilFinished) {

        Constants.TE = millisUntilFinished;
        if(Constants.IS_SLOW_DOWN_TIMER || Constants.IS_PAUSE){
            Constants.DELAY_LAST_TIME++;
        }
    }

    @Override
    public void onFinish() {

    }

}
