package com.group2.dungeonraider.controller;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.group2.dungeonraider.R;
import com.group2.dungeonraider.domain.Player;
import com.group2.dungeonraider.service.Audio;
import com.group2.dungeonraider.service.AudioImpl;
import com.group2.dungeonraider.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amruta on 10/23/2015.
 */

public class Setting extends Activity implements AdapterView.OnItemSelectedListener {
    Switch ch,ch1;
    private RadioGroup radioGroup;
    private RadioButton radioButton1,radioButton2;
    Player player = Player.getInstance();
    Audio audio = new AudioImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //volume


        ch=(Switch) findViewById(R.id.switch_volume);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("DUNGEON", MODE_PRIVATE);

        Constants.VOLUME_MODE=preferences.getInt("volume", 0);
        Constants.THEME_MODE=preferences.getString("theme", "BLACK");
        Constants.CHARACTER_SELECTED=preferences.getInt("character", 0);


        // checkbox check
        if(Constants.VOLUME_MODE==1){
            ch.setChecked(true);
        }

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner_theme);
        radioButton1=(RadioButton)findViewById(R.id.radioCharacterSettings1);
        radioButton2=(RadioButton)findViewById(R.id.radioCharacterSettings2);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("BLACK");
        categories.add("PURPLE");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        if (!Constants.THEME_MODE.equals(null)) {
            int spinnerPosition = dataAdapter.getPosition(Constants.THEME_MODE);
            spinner.setSelection(spinnerPosition);
        }


        if(Constants.CHARACTER_SELECTED==1) {

            radioButton1.setChecked(true);
            //radio group
        }
        else if(Constants.CHARACTER_SELECTED==2)
        {
            radioButton2.setChecked(true);
        }

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupCharacterSettings);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.radioCharacterSettings1) {

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("DUNGEON", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putInt("character", 1);
                    editor.commit();
                    Constants.CHARACTER_SELECTED=1;
                    player.setPlayerCharacter(Constants.PLAYER_A);

                }
                else if(checkedId== R.id.radioCharacterSettings2) {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("DUNGEON", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putInt("character", 2);
                    editor.commit();
                    Constants.CHARACTER_SELECTED=2;
                    player.setPlayerCharacter(Constants.PLAYER_B);
                }

            }
        });


    }

    public void changeVolume(View v) {

        ch1 = (Switch)v;
        SharedPreferences pref = getApplicationContext().getSharedPreferences("DUNGEON", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(ch1.isChecked())
        {
            editor.putInt("volume", 1);
            editor.commit();
            audio.play(getApplicationContext(), R.raw.btn_click);
            Toast.makeText(this, "Volume on", Toast.LENGTH_SHORT).show();
            Constants.VOLUME_MODE = 1;
        }
        else
        {
            editor.putInt("volume", 0);
            Toast.makeText(this, "Volume off", Toast.LENGTH_SHORT).show();
            Constants.VOLUME_MODE = 0;
            editor.commit();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        // On selecting a spinner item

        String item = parent.getItemAtPosition(position).toString();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("DUNGEON", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("theme", item);
        editor.commit();
        Constants.THEME_MODE=item;


    }





    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void onBackPressed() {
        Log.d("Settings", "onBackPressed Called");

        Setting.this.finish();

    }

    public void backtomaingame(View v)
    {
        Setting.this.finish();

    }



}
