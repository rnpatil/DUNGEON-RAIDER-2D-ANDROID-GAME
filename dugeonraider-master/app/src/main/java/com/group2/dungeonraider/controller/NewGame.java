package com.group2.dungeonraider.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.group2.dungeonraider.R;
import com.group2.dungeonraider.data.DatabaseHelper;
import com.group2.dungeonraider.domain.Item;
import com.group2.dungeonraider.domain.Mutator;
import com.group2.dungeonraider.domain.Player;
import com.group2.dungeonraider.domain.PlayerItem;
import com.group2.dungeonraider.domain.PlayerMutator;
import com.group2.dungeonraider.service.Audio;
import com.group2.dungeonraider.service.AudioImpl;
import com.group2.dungeonraider.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Amruta on 10/27/2015.
 */
public class NewGame extends Activity {
    private EditText NameEditText;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    Boolean openFlag=false;
    DatabaseHelper db;
    Player player = Player.getInstance();
    List<Item> itemList = new ArrayList<Item>();
    List<Item> playerItemList = new ArrayList<Item>();
    List<Mutator> playerMutatorList= new ArrayList<Mutator>();
    List<Mutator> mutatorList= new ArrayList<Mutator>();
    Audio audio = new AudioImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constants.GAME_LEVEL = 0;
        setContentView(R.layout.activity_newgame);
        Constants.appContext = getApplicationContext();
        NameEditText = (EditText) findViewById(R.id.txt_name);

    }

    public void gotolevel(View v)
    {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupCharacter);
        audio.play(getApplicationContext(), R.raw.btn_click);
        final String Name=NameEditText.getText().toString();
        if(Name.isEmpty())
        {
            Toast.makeText(this,"Please enter valid Player Name to proceed", Toast.LENGTH_SHORT).show();
        }
        else {

            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(this, "Select a Character to proceed ", Toast.LENGTH_SHORT).show();
            } else {
                if (selectedId == R.id.radioCharacter1) {
                    //save characarer 1

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("DUNGEON", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putInt("character", 1);
                    editor.commit();
                    Constants.CHARACTER_SELECTED = 1;

                    player.setPlayerCharacter(Constants.PLAYER_A);

                } else if (selectedId == R.id.radioCharacter2) {


                    SharedPreferences pref = getApplicationContext().getSharedPreferences("DUNGEON", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putInt("character", 2);
                    editor.commit();
                    Constants.CHARACTER_SELECTED = 2;

                    player.setPlayerCharacter(Constants.PLAYER_B);
                }


                db = new DatabaseHelper(Constants.appContext);

                if (db.checkIfPlayerExists(Name)) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            NewGame.this);

                    alertDialogBuilder.setTitle(getResources().getString(R.string.loadProfile));


                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    player =  db.loadProfile(Name);
                                    playerItemList =  db.loadPlayerItems(player.getId());
                                    player.setItemList(playerItemList);
                                    db.deletePlayerRoomDetails();
                                    player.getRoomList().clear();
                                    Constants.GAME_LEVEL = 0;
                                    playerMutatorList=db.loadPlayerMutators(player.getId());
                                    player.setMutatorList(playerMutatorList);
                                    player.setCurrentLevel(0);
                                    launchIntent();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });


                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {

                    player.setName(Name);
                    player.setScore(0);
                    player.setTime(0);
                    player.setGold(Constants.INITIAL_GOLD);
                    player.getRoomList().clear();
                    long returnValue = db.addPlayerDetail(player);


                    if (returnValue == -1) {
                        Toast.makeText(this, "Player Already Exists", Toast.LENGTH_SHORT).show();
                    } else if (returnValue == -2 || returnValue == -3) {
                        Toast.makeText(this, "Error occurred while creating player", Toast.LENGTH_SHORT).show();
                    } else {
                        player =  db.loadProfile(Name);
                        itemList=db.getAllItems();
                        if (!itemList.isEmpty()) {
                            for (Item i : itemList)
                            {
                                PlayerItem player_item= new PlayerItem();
                                player_item.setPlayerId(player.getId());
                                player_item.setItemId(i.getId());
                                player_item.setItemQuantity(0);
                                db.insertDefaultPlayerItem(player_item);
                            }
                        }


                        playerItemList =  db.loadPlayerItems(player.getId());
                        player.setItemList(playerItemList);


                        //mutators

                        mutatorList=db.getAllMutators();
                        if (!mutatorList.isEmpty()) {
                            for (Mutator m : mutatorList) {

                                if (((m.getName().equals(Constants.MUTATOR_CAP)) && (m.getColor().equals(Constants.COLOR_BROWN))) ||((m.getName().equals(Constants.MUTATOR_SHIRT)) && (m.getColor().equals(Constants.COLOR_BLUE))) ||((m.getName().equals(Constants.MUTATOR_SKIN)) && (m.getColor().equals(Constants.COLOR_WHITE)))||((m.getName().equals(Constants.MUTATOR_PANT)) && (m.getColor().equals(Constants.COLOR_PINK)))) {

                                    PlayerMutator player_mutator = new PlayerMutator();
                                    player_mutator.setPlayerId(player.getId());
                                    player_mutator.setMutatorId(m.getId());
                                    db.insertPlayerMutator(player_mutator);

                                }
                            }
                        }

                        playerMutatorList =  db.loadPlayerMutators(player.getId());
                        player.setMutatorList((playerMutatorList));
                        Intent i = new Intent(this, Level.class);
                        openFlag=false;
                        Constants.GAME_LEVEL = 0;
                        Constants.IS_PLAYER_LEVEL = false;
                        startActivity(i);
                    }


                }

            }
        }
    }

    public void backtomainnewgame(View v)
    {
        NewGame.this.finish();
    }

    public void onBackPressed() {
        Log.d("Newgame", "onBackPressed Called");

        NewGame.this.finish();

    }
    private void launchIntent() {
        Intent i = new Intent(this, Level.class);
        openFlag=false;
        startActivity(i);
    }

    public void onResume ()
    {
        super.onResume();
        Player player = Player.getInstance();
        NameEditText = (EditText) findViewById(R.id.txt_name);

        NameEditText.setText("");
    }

}
