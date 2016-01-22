package com.group2.dungeonraider.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.group2.dungeonraider.R;
import com.group2.dungeonraider.service.Audio;
import com.group2.dungeonraider.service.AudioImpl;

/**
 * Created by ukara on 10/27/2015.
 */
public class Help extends Activity {

    Audio audio  = new AudioImpl();
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Help", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
    }

    public void backtomain(View v) {
        Log.d("Help", "backtomain called");
        Help.this.finish();
    }

    public void howtoplay(View v) {
        Log.d("Help", "howtoplay called");
        String alertTitle = getResources().getString(R.string.help_how_to_play);
        ;
        String alertMessage = getResources().getString(R.string.help_how_to_play_details);
        displayAlert(alertTitle, alertMessage, false);
        audio.play(getApplicationContext(), R.raw.btn_click);
    }

    public void tips(View view) {
        Log.d("Help", "tips called");
        String alertTitle = getResources().getString(R.string.help_tips);
        String alertMessage = getResources().getString(R.string.help_tips_details);
        displayAlert(alertTitle, alertMessage, false);
        audio.play(getApplicationContext(), R.raw.btn_click);
    }

    public void aboutthegame(View view) {
        Log.d("Help", "aboutthegame called");
        final String alertTitle = getResources().getString(R.string.help_about_the_game);
        final String alertMessage = getResources().getString(R.string.help_about_the_game_details);
        displayAlert(alertTitle, alertMessage, true);
        audio.play(getApplicationContext(), R.raw.btn_click);
    }

    public void displayAlert(String alertTitle, String alertMessage, boolean centerAligned)
    {
        Log.d("Help", "displayAlert called");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage(alertMessage);
        builder.setNeutralButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.show();

        // Must call show() prior to fetching text view
        TextView messageView = (TextView) dialog.findViewById(android.R.id.message);
        if(centerAligned == true)
            messageView.setGravity(Gravity.CENTER);

    }
}
