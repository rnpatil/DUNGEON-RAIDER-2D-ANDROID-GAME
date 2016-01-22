package com.group2.dungeonraider.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.group2.dungeonraider.R;
import com.group2.dungeonraider.data.Play;
import com.group2.dungeonraider.service.Audio;
import com.group2.dungeonraider.service.AudioImpl;
import com.group2.dungeonraider.utilities.Constants;

/**
 * Created by Amruta on 10/27/2015.
 */
public class Level  extends Activity {


    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    Audio audio = new AudioImpl();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.appContext = getApplicationContext();
        setContentView(R.layout.activity_level);
    }

    public void onBackPressed() {
        Log.d("Level", "onBackPressed Called");

        Level.this.finish();

    }

    public void backtonewgame(View v)
    {
        Level.this.finish();

    }

    public void go(View v)
    {

        radioGroup = (RadioGroup) findViewById(R.id.radioGrouplevel);

        audio.play(getApplicationContext(), R.raw.btn_click);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if(selectedId==R.id.radiostore)
        {
            Intent i=new Intent(this,Store.class);
            startActivity(i);
        }



        else if(selectedId==R.id.radioeasy){

            Intent i=new Intent(this,Play.class);
            startActivity(i);

        }
        else if(selectedId==R.id.radiohard || selectedId==R.id.radiomedium || selectedId==R.id.radiotutorial){

            Toast.makeText(this, "This option will be available in the future releases", Toast.LENGTH_SHORT).show();

        }

    }
}
