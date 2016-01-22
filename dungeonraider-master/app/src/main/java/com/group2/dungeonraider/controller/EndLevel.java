package com.group2.dungeonraider.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.group2.dungeonraider.R;
import com.group2.dungeonraider.data.Play;
import com.group2.dungeonraider.domain.Player;

/**
 * Created by Rohit on 11/7/2015.
 */
public class EndLevel extends Activity {


    TextView score,time;
    String filename;
    ImageView img;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);

        Player player = Player.getInstance();
        score =  (TextView) findViewById(R.id.endlevelscore);
        time =  (TextView) findViewById(R.id.endleveltime);

        score.setText(String.valueOf(player.getScore()));
        time.setText(String.valueOf(player.getTime()));



        img =  (ImageView) findViewById(R.id.imgendscreen);
        filename = player.getPlayerCharacter();
        int res = getResources().getIdentifier(filename, "drawable", "com.group2.dungeonraider");
        img.setImageResource(res);

    }

    public void backtolevelfromend(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        EndLevel.this.finish();

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        EndLevel.this.finish();
    }
}
