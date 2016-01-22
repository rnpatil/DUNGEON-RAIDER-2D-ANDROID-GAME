package com.group2.dungeonraider.controller;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.group2.dungeonraider.R;
import com.group2.dungeonraider.data.DatabaseHelper;
import com.group2.dungeonraider.domain.PlayerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amruta on 10/27/2015.
 */
public class ScoreCard extends Activity{
    TableLayout tl;
    TableRow tr;
    TextView playerTV,lootTV,timeTV;
    List<PlayerView> playerViewListlist = new ArrayList<PlayerView>();
    DatabaseHelper db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);
        tl = (TableLayout) findViewById(R.id.maintable);

        addData();
    }

    public void backtomainmenuscoreboard(View v)
    {
        ScoreCard.this.finish();
    }

    public void onBackPressed() {
        Log.d("Scorecard", "onBackPressed Called");

        ScoreCard.this.finish();
    }

    public void addHeaders(){

        tr = new TableRow(this);

        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));


        TextView playerTV = new TextView(this);
        playerTV.setText("Player Name");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            playerTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        playerTV.setTextSize((float) 28.0);
        playerTV.setTextColor(Color.parseColor("#4C0000"));
        playerTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        playerTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        playerTV.setPadding(5, 5, 5, 0);
        tr.addView(playerTV);


        TextView lootTV = new TextView(this);
        lootTV.setText(" Loot Value");
        lootTV.setTextSize((float) 28.0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            lootTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        lootTV.setTextColor(Color.parseColor("#4C0000"));
        lootTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        lootTV.setPadding(5, 5, 5, 0);
        lootTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(lootTV);

        TextView timeTV = new TextView(this);
        timeTV.setText(" Time Taken");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            timeTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        timeTV.setTextSize((float) 28.0);
        timeTV.setTextColor(Color.parseColor("#4C0000"));;
        timeTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        timeTV.setPadding(5, 5, 5, 0);
        timeTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(timeTV);


        tl.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));


    }
    /** This function add the data to the table **/
    public void addData() {


        db = new DatabaseHelper(getApplicationContext());
        playerViewListlist = db.getAllPlayerListDescendingScore();

        if (!playerViewListlist.isEmpty()) {
            addHeaders();
            for (PlayerView p : playerViewListlist) {

                tr = new TableRow(this);
                tr.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));


                playerTV = new TextView(this);
                playerTV.setText(p.getName());
                playerTV.setTextSize((float) 22.0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    playerTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                }
                playerTV.setTextColor(Color.BLACK);
                playerTV.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                playerTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                playerTV.setPadding(5, 5, 5, 5);
                tr.addView(playerTV);


                lootTV = new TextView(this);
                lootTV.setText(String.valueOf(p.getScore()));
                lootTV.setTextColor(Color.BLACK);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    lootTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                }
                lootTV.setTextSize((float) 22.0);
                lootTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                lootTV.setPadding(5, 5, 5, 5);
                lootTV.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                tr.addView(lootTV);


                timeTV = new TextView(this);
                timeTV.setText(String.valueOf(p.getTime()));
                timeTV.setTextColor(Color.BLACK);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    timeTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                }
                timeTV.setTextSize((float) 22.0);
                timeTV.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                timeTV.setPadding(5, 5, 5, 5);
                timeTV.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
                tr.addView(timeTV);


                tl.addView(tr, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

            }

        }
    }
}
