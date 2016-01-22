package com.group2.dungeonraider.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.group2.dungeonraider.R;
import com.group2.dungeonraider.data.DatabaseHelper;
import com.group2.dungeonraider.domain.Mutator;
import com.group2.dungeonraider.domain.Player;
import com.group2.dungeonraider.service.Audio;
import com.group2.dungeonraider.service.AudioImpl;
import com.group2.dungeonraider.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rohit on 11/4/2015.
 */
public class EquipMutator extends Activity {

    String filename;
    char cap,tshirt,pant,skin,character;
    String imagename;
    Player player = Player.getInstance();
    DatabaseHelper db=new DatabaseHelper(Constants.appContext);
    List<Mutator> playerMutatorList= new ArrayList<Mutator>();
    RadioGroup radioGroup;
    Integer mutatorCost=0;
    TextView goldview;
    Audio audio = new AudioImpl();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equip_character_items);
        radioGroup = (RadioGroup) findViewById(R.id.radio_selection_group);
        List<Mutator> playerownedmutators=player.getMutatorList();

        ImageView img;
        img =  (ImageView) findViewById(R.id.imgview_mutator);
        filename = player.getPlayerCharacter();
        int res = getResources().getIdentifier(filename, "drawable", "com.group2.dungeonraider");
        img.setImageResource(res);


      /*  if((playerownedmutators != null) && !playerownedmutators.isEmpty()) {
            for (Mutator mutator : playerMutatorList) {
                for (Mutator mutatorowned : playerownedmutators) {
                    if (mutator.getId() == mutatorowned.getId()) {

                        mutator.setAlreadyPurchased("YES");
                    }
                }
            }
        }*/


        for (Mutator mutator : playerownedmutators)
        {
            RadioButton rb = new RadioButton(this);

            rb.setLayoutParams
                    (new RadioGroup.LayoutParams
                            (RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));


//            if(!"YES".equals(mutator.getAlreadyPurchased())) {

                rb.setText(mutator.getName() + "-" + mutator.getColor());

                rb.setId(mutator.getId());
                //rb.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                rb.setTextSize((float) 18.0);
                rb.setTextColor(Color.parseColor("#4C0000"));
                rb.setTag(mutator.getName() + "-" + mutator.getColor());
                //   rb.setOnClickListener(first_radio_listener);

                radioGroup.addView(rb);
//            }

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // checkedId is the RadioButton selected
                    RadioButton rb = (RadioButton) findViewById(checkedId);
                    if (rb.getText().equals("CAP-YELLOW")) {
                        ImageView img;
                        img = (ImageView) findViewById(R.id.imgview_mutator);
                        filename = player.getPlayerCharacter();
                        //filename = (String) img.getTag();
                        tshirt = filename.charAt(1);
                        pant = filename.charAt(2);
                        skin = filename.charAt(3);
                        character = filename.charAt(4);
                        imagename = "y" + tshirt + pant + skin + character;
                        int res = getResources().getIdentifier(imagename, "drawable", "com.group2.dungeonraider");
                        img.setImageResource(res);
                        mutatorCost = getMutatorValue(Constants.MUTATOR_CAP);
                    } else if (rb.getText().equals("CAP-BROWN")) {
                        ImageView img;
                        img = (ImageView) findViewById(R.id.imgview_mutator);
                        //mutator_cost = (TextView) findViewById(R.id.txt_mutatorcost);

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
                     //   mutator_costText = (TextView) findViewById(R.id.textViewCOST);
                      //  mutator_costText.setVisibility(View.VISIBLE);
                       // mutator_cost.setText(String.valueOf(getMutatorValue(Constants.MUTATOR_CAP)));
                        mutatorCost = getMutatorValue(Constants.MUTATOR_CAP);
                    } else if (rb.getText().equals("SHIRT-BLUE")) {
                        ImageView img;
                        TextView mutator_cost;
                        img = (ImageView) findViewById(R.id.imgview_mutator);
                        //mutator_cost = (TextView) findViewById(R.id.txt_mutatorcost);
                        filename = player.getPlayerCharacter();
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
                       // mutator_costText = (TextView) findViewById(R.id.textViewCOST);
                      //  mutator_costText.setVisibility(View.VISIBLE);
                       // mutator_cost.setText(String.valueOf(getMutatorValue(Constants.MUTATOR_SHIRT)));
                        mutatorCost = getMutatorValue(Constants.MUTATOR_SHIRT);
                    } else if (rb.getText().equals("SHIRT-GREEN")) {
                        ImageView img;
                        TextView mutator_cost;
                        img = (ImageView) findViewById(R.id.imgview_mutator);
                       // mutator_cost = (TextView) findViewById(R.id.txt_mutatorcost);
                        filename = player.getPlayerCharacter();
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
                     //   mutator_costText = (TextView) findViewById(R.id.textViewCOST);
                      //  mutator_costText.setVisibility(View.VISIBLE);
                       // mutator_cost.setText(String.valueOf(getMutatorValue(Constants.MUTATOR_SHIRT)));
                        mutatorCost = getMutatorValue(Constants.MUTATOR_SHIRT);
                    } else if (rb.getText().equals("PANT-RED")) {
                        ImageView img;
                        TextView mutator_cost;
                        img = (ImageView) findViewById(R.id.imgview_mutator);
                        //mutator_cost = (TextView) findViewById(R.id.txt_mutatorcost);
                        filename = player.getPlayerCharacter();
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
                     //   mutator_costText = (TextView) findViewById(R.id.textViewCOST);
                     //   mutator_costText.setVisibility(View.VISIBLE);
                       // mutator_cost.setText(String.valueOf(getMutatorValue(Constants.MUTATOR_PANT)));
                        mutatorCost = getMutatorValue(Constants.MUTATOR_PANT);
                    } else if (rb.getText().equals("PANT-PINK")) {
                        ImageView img;
                        TextView mutator_cost;
                        img = (ImageView) findViewById(R.id.imgview_mutator);
                        //mutator_cost = (TextView) findViewById(R.id.txt_mutatorcost);
                        filename = player.getPlayerCharacter();
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
                       // mutator_costText = (TextView) findViewById(R.id.textViewCOST);
                       // mutator_costText.setVisibility(View.VISIBLE);
                        mutatorCost = getMutatorValue(Constants.MUTATOR_PANT);
                       // mutator_cost.setText(String.valueOf(mutatorCost));

                    } else if (rb.getText().equals("SKIN-PINK")) {
                        ImageView img;
                        TextView mutator_cost;
                        img = (ImageView) findViewById(R.id.imgview_mutator);
                        //mutator_cost = (TextView) findViewById(R.id.txt_mutatorcost);
                        filename = player.getPlayerCharacter();
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
                     //   mutator_costText = (TextView) findViewById(R.id.textViewCOST);
                     //   mutator_costText.setVisibility(View.VISIBLE);
                       // mutator_cost.setText(String.valueOf(getMutatorValue(Constants.MUTATOR_SKIN)));
                        mutatorCost = getMutatorValue(Constants.MUTATOR_SKIN);
                    }


                }

                public int getMutatorValue(String name) {

                    int value = 0;
                    for (Mutator item : playerMutatorList) {
                        if (item.getName().equals(name)) {
                            value = item.getValue();
                            break;
                        }
                    }
                    return value;
                }




            });
        }
    }

    public void equipmutator(View v) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                EquipMutator.this);
        audio.play(getApplicationContext(), R.raw.btn_click);
        alertDialogBuilder.setTitle("Are you sure you want to equip the mutator?");


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        player.setPlayerCharacter(imagename);

                        //launchIntent();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();




    }

    public void backtostorefromequip(View v)
    {
        EquipMutator.this.finish();


    }
}