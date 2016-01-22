package com.group2.dungeonraider.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.group2.dungeonraider.R;
import com.group2.dungeonraider.data.DatabaseHelper;
import com.group2.dungeonraider.domain.Mutator;
import com.group2.dungeonraider.domain.Player;
import com.group2.dungeonraider.domain.PlayerMutator;
import com.group2.dungeonraider.service.Audio;
import com.group2.dungeonraider.service.AudioImpl;
import com.group2.dungeonraider.utilities.Constants;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Amruta on 10/29/2015.
 */
public class Purchase_Mutator extends Activity {

    String filename;
    char cap,tshirt,pant,skin,character;
    String imagename;
    TextView mutator_cost,goldview;
    List<Mutator> mutatorList=new ArrayList<Mutator>();

    RadioGroup radioGroup;
    DatabaseHelper db=new DatabaseHelper(Constants.appContext);
    List<Mutator> playerMutatorList= new ArrayList<Mutator>();
    Integer mutatorCost=0;
    Audio audio = new AudioImpl();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView img;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_character_items);
        img =  (ImageView) findViewById(R.id.imgview_mutator);
        Player player = Player.getInstance();
        goldview = (TextView) findViewById(R.id.textView_gold_purchasemutators);
        goldview.setText(String.valueOf(player.getGold()));


        filename = player.getPlayerCharacter();
        int res = getResources().getIdentifier(filename, "drawable", "com.group2.dungeonraider");
        img.setImageResource(res);
        //img.setTag("bbppa");

        radioGroup = (RadioGroup) findViewById(R.id.radio_selection_group);
        //radioGroup.setOnCheckedChangeListener(this);
        List<Mutator> playerownedmutators=player.getMutatorList();



        playerMutatorList=db.getAllMutators();

        //disable player owned mutators

        if((playerownedmutators != null) &&!playerownedmutators.isEmpty()) {
            for (Mutator mutator : playerMutatorList) {
                 for (Mutator mutatorowned : playerownedmutators) {
                     if (mutator.getId() == mutatorowned.getId()) {

                         mutator.setAlreadyPurchased("YES");
                    }
                }
             }
         }


        for (Mutator mutator : playerMutatorList) {

            if (((mutator.getName().equals(Constants.MUTATOR_CAP)) && (mutator.getColor().equals(Constants.COLOR_BROWN))) ||((mutator.getName().equals(Constants.MUTATOR_SHIRT)) && (mutator.getColor().equals(Constants.COLOR_BLUE))) ||((mutator.getName().equals(Constants.MUTATOR_SKIN)) && (mutator.getColor().equals(Constants.COLOR_WHITE)))||((mutator.getName().equals(Constants.MUTATOR_PANT)) && (mutator.getColor().equals(Constants.COLOR_PINK)))) {

            //do nothing
            }
            else
            {
                RadioButton rb = new RadioButton(this);
                rb.setLayoutParams
                        (new RadioGroup.LayoutParams
                                (RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));

                if ("YES".equals(mutator.getAlreadyPurchased())) {
                    rb.setChecked(false);
                    rb.setEnabled(false);
                }

                rb.setText(mutator.getName() + "-" + mutator.getColor());

                rb.setId(mutator.getId());
                //rb.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                rb.setTextSize((float) 18.0);
                rb.setTextColor(Color.parseColor("#4C0000"));
                rb.setTag(mutator.getName() + "-" + mutator.getColor());
                //   rb.setOnClickListener(first_radio_listener);

                radioGroup.addView(rb);


            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                RadioButton rb = (RadioButton) findViewById(checkedId);
                if (rb != null){
                    if (rb.getText().equals("CAP-YELLOW")) {
                        ImageView img;
                        TextView mutator_cost;

                        img = (ImageView) findViewById(R.id.imgview_mutator);
                        mutator_cost = (TextView) findViewById(R.id.txt_mutatorcost);

                        //filename = (String) img.getTag();
                        tshirt = filename.charAt(1);
                        pant = filename.charAt(2);
                        skin = filename.charAt(3);
                        character = filename.charAt(4);
                        imagename = "y" + tshirt + pant + skin + character;
                        int res = getResources().getIdentifier(imagename, "drawable", "com.group2.dungeonraider");
                        img.setImageResource(res);
                        // img.setTag(imagename);
                        TextView mutator_costText;
                        mutator_costText = (TextView) findViewById(R.id.textViewCOST);
                        mutator_costText.setVisibility(View.VISIBLE);
                        mutator_cost.setText(String.valueOf(getMutatorValue(Constants.MUTATOR_CAP)));
                        mutatorCost = getMutatorValue(Constants.MUTATOR_CAP);
                    } else if (rb.getText().equals("CAP-BROWN")) {
                        ImageView img;
                        TextView mutator_cost;
                        img = (ImageView) findViewById(R.id.imgview_mutator);
                       mutator_cost = (TextView) findViewById(R.id.txt_mutatorcost);

                        // filename = (String) img.getTag();
                        tshirt = filename.charAt(1);
                        pant = filename.charAt(2);
                        skin = filename.charAt(3);
                        character = filename.charAt(4);
                        imagename = "b" + tshirt + pant + skin + character;
                        int res = getResources().getIdentifier(imagename, "drawable", "com.group2.dungeonraider");
                        img.setImageResource(res);
                        // img.setTag(imagename);
                        TextView mutator_costText;
                        mutator_costText = (TextView) findViewById(R.id.textViewCOST);
                        mutator_costText.setVisibility(View.VISIBLE);
                        mutator_cost.setText(String.valueOf(getMutatorValue(Constants.MUTATOR_CAP)));
                        mutatorCost = getMutatorValue(Constants.MUTATOR_CAP);
                    } else if (rb.getText().equals("SHIRT-BLUE")) {
                        ImageView img;
                        TextView mutator_cost;
                        img = (ImageView) findViewById(R.id.imgview_mutator);
                        mutator_cost = (TextView) findViewById(R.id.txt_mutatorcost);

                        // filename = (String) img.getTag();
                        cap = filename.charAt(0);
                        pant = filename.charAt(2);
                        skin = filename.charAt(3);
                        character = filename.charAt(4);
                        imagename = cap + "b" + pant + skin + character;
                        int res = getResources().getIdentifier(imagename, "drawable", "com.group2.dungeonraider");
                        img.setImageResource(res);
                        // img.setTag(imagename);
                        TextView mutator_costText;
                        mutator_costText = (TextView) findViewById(R.id.textViewCOST);
                        mutator_costText.setVisibility(View.VISIBLE);
                        mutator_cost.setText(String.valueOf(getMutatorValue(Constants.MUTATOR_SHIRT)));
                        mutatorCost = getMutatorValue(Constants.MUTATOR_SHIRT);
                    } else if (rb.getText().equals("SHIRT-GREEN")) {
                        ImageView img;
                        TextView mutator_cost;
                        img = (ImageView) findViewById(R.id.imgview_mutator);
                        mutator_cost = (TextView) findViewById(R.id.txt_mutatorcost);

                        //filename = (String) img.getTag();
                        cap = filename.charAt(0);
                        pant = filename.charAt(2);
                        skin = filename.charAt(3);
                        character = filename.charAt(4);
                        imagename = cap + "g" + pant + skin + character;
                        int res = getResources().getIdentifier(imagename, "drawable", "com.group2.dungeonraider");
                        img.setImageResource(res);
                        // img.setTag(imagename);
                        TextView mutator_costText;
                        mutator_costText = (TextView) findViewById(R.id.textViewCOST);
                        mutator_costText.setVisibility(View.VISIBLE);
                        mutator_cost.setText(String.valueOf(getMutatorValue(Constants.MUTATOR_SHIRT)));
                        mutatorCost = getMutatorValue(Constants.MUTATOR_SHIRT);
                    } else if (rb.getText().equals("PANT-RED")) {
                        ImageView img;
                        TextView mutator_cost;
                        img = (ImageView) findViewById(R.id.imgview_mutator);
                       mutator_cost = (TextView) findViewById(R.id.txt_mutatorcost);

                        // filename = (String) img.getTag();
                        tshirt = filename.charAt(1);
                        cap = filename.charAt(0);
                        skin = filename.charAt(3);
                        character = filename.charAt(4);
                        imagename = "" + cap + tshirt + "r" + skin + character;
                        int res = getResources().getIdentifier(imagename, "drawable", "com.group2.dungeonraider");
                        img.setImageResource(res);
                        // img.setTag(imagename);
                        TextView mutator_costText;
                        mutator_costText = (TextView) findViewById(R.id.textViewCOST);
                        mutator_costText.setVisibility(View.VISIBLE);
                        mutator_cost.setText(String.valueOf(getMutatorValue(Constants.MUTATOR_PANT)));
                        mutatorCost = getMutatorValue(Constants.MUTATOR_PANT);
                    } else if (rb.getText().equals("PANT-PINK")) {
                        ImageView img;
                        TextView mutator_cost;
                        img = (ImageView) findViewById(R.id.imgview_mutator);
                        mutator_cost = (TextView) findViewById(R.id.txt_mutatorcost);

                        // filename = (String) img.getTag();
                        tshirt = filename.charAt(1);
                        cap = filename.charAt(0);
                        skin = filename.charAt(3);
                        character = filename.charAt(4);
                        imagename = "" + cap + tshirt + "p" + skin + character;
                        int res = getResources().getIdentifier(imagename, "drawable", "com.group2.dungeonraider");
                        img.setImageResource(res);
                        //  img.setTag(imagename);
                        TextView mutator_costText;
                        mutator_costText = (TextView) findViewById(R.id.textViewCOST);
                        mutator_costText.setVisibility(View.VISIBLE);
                        mutatorCost = getMutatorValue(Constants.MUTATOR_PANT);
                        mutator_cost.setText(String.valueOf(mutatorCost));

                    } else if (rb.getText().equals("SKIN-PINK")) {
                        ImageView img;
                        TextView mutator_cost;
                        img = (ImageView) findViewById(R.id.imgview_mutator);
                        mutator_cost = (TextView) findViewById(R.id.txt_mutatorcost);

                        // filename = (String) img.getTag();
                        tshirt = filename.charAt(1);
                        pant = filename.charAt(2);
                        cap = filename.charAt(0);
                        character = filename.charAt(4);
                        imagename = "" + cap + tshirt + pant + "p" + character;
                        int res = getResources().getIdentifier(imagename, "drawable", "com.group2.dungeonraider");
                        img.setImageResource(res);
                        //   img.setTag(imagename);
                        TextView mutator_costText;
                        mutator_costText = (TextView) findViewById(R.id.textViewCOST);
                        mutator_costText.setVisibility(View.VISIBLE);
                        mutator_cost.setText(String.valueOf(getMutatorValue(Constants.MUTATOR_SKIN)));
                        mutatorCost = getMutatorValue(Constants.MUTATOR_SKIN);
                    }
            }

            }
        });


    }





    public void buymutator(View v) {


        // if this button is clicked, close
        // current activity

        //start
        audio.play(getApplicationContext(), R.raw.btn_click);
        final RadioGroup radiogroup;
        final int selectedId;
        final Player player = Player.getInstance();
        radiogroup = (RadioGroup) findViewById(R.id.radio_selection_group);
        final int playergold = player.getGold();

        selectedId = radiogroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select a mutator to buy", Toast.LENGTH_SHORT).show();
        } else {
            if (playergold < mutatorCost) {
                Toast.makeText(this, "You do not have enough gold to buy this mutator", Toast.LENGTH_SHORT).show();
            } else {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        Purchase_Mutator.this);

                // set title
                alertDialogBuilder.setTitle("Are you sure you want to buy the mutator?");

                // audio.play(getApplicationContext(), R.raw.btn_click);
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity

                                //start

                                RadioButton rb = (RadioButton) findViewById(selectedId);

                                if (rb.getText().equals("CAP-YELLOW")) {


                                    Integer mutatorid = getMutatorId(Constants.MUTATOR_CAP, Constants.COLOR_YELLOW);
                                    PlayerMutator playerMutator = new PlayerMutator();
                                    playerMutator.setMutatorId(mutatorid);
                                    player.setGold(playergold - mutatorCost);
                                    playerMutator.setPlayerId(player.getId());
                                    db.insertPlayerMutator(playerMutator);
                                    //update player gold

                                    rb.setChecked(false);
                                   rb.setEnabled(false);
                                    //set texview =""
                                    //set cost not visible

                                } else if (rb.getText().equals("CAP-BROWN")) {
                                    Integer mutatorid = getMutatorId(Constants.MUTATOR_CAP, Constants.COLOR_BROWN);
                                    PlayerMutator playerMutator = new PlayerMutator();
                                    playerMutator.setMutatorId(mutatorid);
                                    player.setGold(playergold - mutatorCost);
                                    playerMutator.setPlayerId(player.getId());
                                    db.insertPlayerMutator(playerMutator);
                                    //update player gold

                                    rb.setChecked(false);
                                    rb.setEnabled(false);


                                } else if (rb.getText().equals("SHIRT-BLUE")) {

                                    Integer mutatorid = getMutatorId(Constants.MUTATOR_SHIRT, Constants.COLOR_BLUE);
                                    PlayerMutator playerMutator = new PlayerMutator();
                                    playerMutator.setMutatorId(mutatorid);
                                    player.setGold(playergold - mutatorCost);
                                    playerMutator.setPlayerId(player.getId());
                                    db.insertPlayerMutator(playerMutator);
                                    //update player gold

                                    rb.setChecked(false);
                                    rb.setEnabled(false);
                                } else if (rb.getText().equals("SHIRT-GREEN")) {

                                    Integer mutatorid = getMutatorId(Constants.MUTATOR_SHIRT, Constants.COLOR_GREEN);
                                    PlayerMutator playerMutator = new PlayerMutator();
                                    playerMutator.setMutatorId(mutatorid);
                                    player.setGold(playergold - mutatorCost);
                                    playerMutator.setPlayerId(player.getId());
                                    db.insertPlayerMutator(playerMutator);
                                    //update player gold

                                    rb.setChecked(false);
                                    rb.setEnabled(false);
                                } else if (rb.getText().equals("PANT-RED")) {

                                    Integer mutatorid = getMutatorId(Constants.MUTATOR_PANT, Constants.COLOR_RED);
                                    PlayerMutator playerMutator = new PlayerMutator();
                                    playerMutator.setMutatorId(mutatorid);
                                    player.setGold(playergold - mutatorCost);
                                    playerMutator.setPlayerId(player.getId());
                                    db.insertPlayerMutator(playerMutator);
                                    //update player gold

                                    rb.setChecked(false);
                                    rb.setEnabled(false);
                                } else if (rb.getText().equals("PANT-PINK")) {

                                    Integer mutatorid = getMutatorId(Constants.MUTATOR_PANT, Constants.COLOR_PINK);
                                    PlayerMutator playerMutator = new PlayerMutator();
                                    playerMutator.setMutatorId(mutatorid);
                                    player.setGold(playergold - mutatorCost);
                                    playerMutator.setPlayerId(player.getId());
                                    db.insertPlayerMutator(playerMutator);
                                    //update player gold

                                    rb.setChecked(false);
                                    rb.setEnabled(false);
                                } else if (rb.getText().equals("SKIN-PINK")) {

                                    Integer mutatorid = getMutatorId(Constants.MUTATOR_SKIN, Constants.COLOR_PINK);
                                    PlayerMutator playerMutator = new PlayerMutator();
                                    playerMutator.setMutatorId(mutatorid);
                                    player.setGold(playergold - mutatorCost);
                                    playerMutator.setPlayerId(player.getId());
                                    db.insertPlayerMutator(playerMutator);


                                    rb.setChecked(false);
                                   rb.setEnabled(false);
                                }
                                db.updatePlayerGold();

                                mutatorList = db.loadPlayerMutators(player.getId());
                                player.setMutatorList(mutatorList);
                                goldview = (TextView) findViewById(R.id.textView_gold_purchasemutators);
                                goldview.setText(String.valueOf(player.getGold()));

                               radiogroup.clearCheck();
                            }


                            //end

                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();

                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }


        }
    }

    public int getMutatorValue(String name) {

        int value=0;
        for (Mutator item : playerMutatorList) {
            if (item.getName().equals(name)){
                value = item.getValue();
                break;
            }
        }
        return value;
    }

    public int getMutatorId(String name,String color) {

        int value=0;
        for (Mutator item : playerMutatorList) {
            if (item.getName().equals(name) && item.getColor().equals(color)){
                value = item.getId();
                break;
            }
        }
        return value;
    }





    public void onBackPressed() {
        Log.d("Settings", "onBackPressed Called");

        Purchase_Mutator.this.finish();

    }

    public void backtostore(View v)
    {
        Purchase_Mutator.this.finish();

    }



}


