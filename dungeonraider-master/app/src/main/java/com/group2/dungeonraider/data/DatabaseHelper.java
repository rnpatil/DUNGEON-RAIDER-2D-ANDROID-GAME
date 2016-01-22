package com.group2.dungeonraider.data;

/**
 * Created by Rohit on 10/30/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.group2.dungeonraider.R;
import com.group2.dungeonraider.domain.Item;
import com.group2.dungeonraider.domain.Mutator;
import com.group2.dungeonraider.domain.Player;
import com.group2.dungeonraider.domain.PlayerItem;
import com.group2.dungeonraider.domain.PlayerMutator;
import com.group2.dungeonraider.domain.PlayerView;
import com.group2.dungeonraider.domain.Room;
import com.group2.dungeonraider.utilities.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.provider.BaseColumns._ID;


/**
 * Created by Rohit on 10/28/2015.
 */



public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Name
    public static String DATABASE_NAME = "DUNGEON_DATABASE";

    // Current version of database
    private static final int DATABASE_VERSION = 1;

    // Name of table
    private static final String TABLE_PLAYER = "PLAYER";
    private static final String TABLE_ITEMS = "ITEMS";
    private static final String TABLE_PLAYER_ITEM = "PLAYER_ITEMS";
    private static final String TABLE_MUTATOR = "MUTATOR";
    private static final String TABLE_PLAYER_MUTATOR = "PLAYER_MUTATOR";
    private static final String TABLE_LEVEL = "LEVEL";
    private static final String TABLE_ROOM = "ROOM";
    private static final String TABLE_PLAYER_ROOM = "PLAYER_ROOM";
    private static final String TABLE_LEVEL_ROOM = "LEVEL_ROOM";

    // All Keys used in  tables
    //PLAYER
    private static final String KEY_ID = "ID";

    private static final String KEY_USERNAME = "USERNAME";
    private static final String KEY_GOLD = "GOLD";
    private static final String KEY_SCORE = "SCORE";
    private static final String KEY_TIME = "TIME";
    //ITEM
    private static final String KEY_PURCHASE_VALUE = "PURCHASE_VALUE";
    private static final String KEY_ITEM_NAME = "ITEM_NAME";
    // PLAYER_ITEM
    private static final String KEY_PLAYER_ID = "PLAYER_ID";
    private static final String KEY_ITEM_ID="ITEM_ID";
    private static final String KEY_ITEM_QUANTITY = "ITEM_QUANTITY";
    //MUTATOR
    private static final String KEY_MUTATOR_ID = "MUTATOR_ID";
    private static final String KEY_MUTATOR_NAME="MUTATOR_NAME";
    private static final String KEY_MUTATOR_VALUE = "MUTATOR_VALUE";
    private static final String KEY_MUTATOR_COLOR = "MUTATOR_COLOR";
    //ROOM
    private static final String KEY_ROOM_ID = "ROOM_ID";
    private static final String KEY_PUZZLE_STRUCT = "PUZZLE_STRUCT";
    private static final String KEY_LEVEL_ID = "LEVEL_ID";
    private static final String KEY_ROOM_NUMBER = "ROOM_NUMBER";
    private static final String KEY_TOTAL_GOLD = "TOTAL_GOLD";
    private static final String KEY_NUMBER_BOMBS = "NUMBER_BOMBS";
    private static final String KEY_DESIRED_TIME = "DESIRED_TIME";
    private static final String KEY_PLAYER_START_TILE_X="playerStartTileX";
    private static final String KEY_PLAYER_START_TILE_Y="playerStartTileY";

    //LEVEL
    private static final String KEY_LEVEL_NAME = "LEVEL_NAME";
    private static final String KEY_NUMBER_ROOMS = "NUMBER_ROOMS";

    //PLAYER_ROOM
    private static final String KEY_GOLD_EARN = "GOLD_EARN";
    private static final String KEY_BOMB_EARN = "BOMB_EARN";
    private static final String KEY_TIME_TAKEN = "TIME_TAKEN";


    public static String TAG = "tag";

    //GAME DAO
    // Create table statements

    /**
     * The table containing the definitions of each available game tile type.
     */
    private static final String CREATE_TABLE_GAME_TILES = "CREATE TABLE " + GameTileData.TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY, "
            + GameTileData.NAME + " STRING,"
            + GameTileData.TYPE + " INTEGER DEFAULT 0,"
            + GameTileData.DRAWABLE + " INTEGER DEFAULT 0,"
            + GameTileData.VISIBLE + " INTEGER DEFAULT 1"
            + ");";

    /**
     * The table containing the definitions of each level.
     */
    private static final String CREATE_TABLE_GAME_LEVEL_TILES = "CREATE TABLE " + GameLevelTileData.TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + GameLevelTileData.STAGE + " INTEGER DEFAULT 0,"
            + GameLevelTileData.LEVEL + " INTEGER DEFAULT 0,"
            + GameLevelTileData.PLAYER_START_TILE_X + " INTEGER DEFAULT 0,"
            + GameLevelTileData.PLAYER_START_TILE_Y + " INTEGER DEFAULT 0,"
            + GameLevelTileData.DESIRED_TIME +" INTEGER NOT NULL,"
            + GameLevelTileData.TILE_DATA + " TEXT NOT NULL"
            + ");";

    // Populate table statements

    /**
     * Populates the game tile definition table. Each row contains:
     * - A unique ID, specified instead of generated by AUTOINCREMENT so tile definitions
     * 		are easier to reference when populating the level data table
     * - The tile name.
     * - The tile type ID.
     * - The tile drawable resource ID.
     * - The tile visibility option (1 = visible, 0 = invisible.)
     */
    private static final String[] POPULATE_TABLE_GAME_TILES = {
            "INSERT INTO " + GameTileData.TABLE_NAME + " VALUES "
                    + "(1,\"Tile 01\"," + Constants.BlockType.WALL.getValue() + "," + R.drawable.tile_01 + ",1);",

            "INSERT INTO " + GameTileData.TABLE_NAME + " VALUES "
                    + "(2,\"Tile 02\"," + Constants.BlockType.BREAKABLEWALL.getValue() + "," + R.drawable.tile_breakable + ",1);",

            "INSERT INTO " + GameTileData.TABLE_NAME + " VALUES "
                    + "(3,\"Tile 03\"," + Constants.BlockType.BOMB.getValue() + "," + R.drawable.tile_bomb + ",1);",

            "INSERT INTO " + GameTileData.TABLE_NAME + " VALUES "
                    + "(4,\"Tile 04\"," + Constants.BlockType.FIRE.getValue() + "," + R.drawable.tile_fire + ",1);",

            "INSERT INTO " + GameTileData.TABLE_NAME + " VALUES "
                    + "(5,\"Tile 05\"," + Constants.BlockType.CHEST.getValue() + "," + R.drawable.tile_coins + ",1);",

            "INSERT INTO " + GameTileData.TABLE_NAME + " VALUES "
                    + "(6,\"Tile 06\"," + Constants.BlockType.ENTRANCESTART.getValue() + "," + R.drawable.tile_roomstart + ",1);",

            "INSERT INTO " + GameTileData.TABLE_NAME + " VALUES "
                    + "(7,\"Tile 07\"," + Constants.BlockType.SLIDING.getValue() + "," + R.drawable.tile_07 + ",1);",

            "INSERT INTO " + GameTileData.TABLE_NAME + " VALUES "
                    + "(8,\"Dangerous Tile 01\"," + Constants.BlockType.KEY.getValue() + "," + R.drawable.tile_keys+ ",1);",

            "INSERT INTO " + GameTileData.TABLE_NAME + " VALUES "
                    + "(9,\"Exit Tile\"," + Constants.BlockType.EXITSOLVE.getValue() + "," + R.drawable.tile_doorclosed + ",1);",

            "INSERT INTO " + GameTileData.TABLE_NAME + " VALUES "
                    + "(10,\"Goal Tile\"," + Constants.BlockType.FINISH.getValue() + "," + R.drawable.tile_goal + ",1);",

            "INSERT INTO " + GameTileData.TABLE_NAME + " VALUES "
                    + "(11,\"Weight Switch\"," + Constants.BlockType.WEIGHTSWITCH.getValue() + "," + R.drawable.tile_switch + ",1);",

            "INSERT INTO " + GameTileData.TABLE_NAME + " VALUES "
                    + "(12,\"Exit Tile\"," + Constants.BlockType.DOOROPEN.getValue() + "," + R.drawable.tile_dooropen + ",1);",
            "INSERT INTO " + GameTileData.TABLE_NAME + " VALUES "
                    + "(13,\"Dungeon Finish\"," + Constants.BlockType.DUNGEONFINISH.getValue() + "," + R.drawable.tile_dungeonfinish + ",1);"
    };



    //PLAYER DETAILS

    private static final String CREATE_TABLE_PLAYER = "CREATE TABLE "
            + TABLE_PLAYER + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME + " TEXT NOT NULL UNIQUE ,"
            + KEY_GOLD + " INTEGER,"+   KEY_SCORE + " INTEGER,"+KEY_TIME + " INTEGER );";

    /**
     * Populates the level data definition table. Each row contains:
     * - An automatically generated unique ID.
     * - The stage ID.
     * - The level ID.
     * - The player start tile X (horizontal) location.
     * - The player start tile Y (vertical) location.
     * - The level tile data.
     * 		Level tile data consists of rows of comma-delimited game tile IDs.
     * 		The tile IDs used correspond to the unique IDs found in the game
     * 		tile definition table.
     *
     * 		The position of each game tile ID corresponds to the position the
     * 		tile will be drawn in the game.
     */
    private static final String[] POPULATE_TABLE_GAME_LEVEL_TILES = {

            //first puzzle

            "INSERT INTO " + GameLevelTileData.TABLE_NAME + " VALUES "
                    + "(null,1,1,2,9,40,\""
                    // 1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
		/* 1  */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 2  */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 3  */+ "01,01,05,00,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 4  */+ "01,01,01,00,00,00,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 5  */+ "01,01,01,01,01,00,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 6  */+ "01,01,01,01,01,00,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 7  */+ "01,01,01,01,01,00,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 8  */+ "01,01,01,01,01,00,01,01,01,01,01,00,00,00,10" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 9  */+ "06,00,00,07,00,00,00,01,01,01,01,00,00,00,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 10 */+ "01,00,00,01,01,00,01,01,01,01,01,00,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 11 */+ "01,01,01,01,01,00,01,01,01,01,01,09,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 12 */+ "01,01,01,01,01,00,01,01,01,01,01,00,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 13 */+ "01,00,00,00,00,00,01,01,01,01,01,00,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 14 */+ "01,00,01,00,01,01,01,01,01,01,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 15 */+ "01,00,01,00,01,01,01,01,01,01,07,07,07,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 16 */+ "01,08,01,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 17 */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
                    + "\");",


            //second puzzle


            "INSERT INTO " + GameLevelTileData.TABLE_NAME + " VALUES "
                    + "(null,1,2,2,9,125,\""
                    // 1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
		/* 1  */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 2  */+ "01,03,00,01,01,01,00,00,00,00,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 3  */+ "01,01,00,01,01,01,00,01,01,00,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 4  */+ "01,01,00,01,01,01,00,01,01,00,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 5  */+ "01,01,00,01,01,01,00,01,01,00,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 6  */+ "01,01,00,01,01,01,00,01,01,00,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 7  */+ "01,01,00,01,01,01,00,01,01,00,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 8  */+ "01,01,00,01,01,01,00,00,01,00,00,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 9  */+ "06,00,00,02,02,00,00,07,01,00,00,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 10 */+ "01,00,00,01,01,01,11,05,00,01,00,00,00,09,10" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 11 */+ "01,00,00,01,01,01,01,01,00,01,00,01,00,00,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 12 */+ "01,00,01,01,01,01,01,01,00,01,00,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 13 */+ "01,00,07,11,01,01,01,01,00,01,00,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 14 */+ "01,00,01,01,01,01,01,01,00,01,00,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 15 */+ "01,00,00,01,01,01,01,01,00,01,00,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 16 */+ "01,00,00,09,00,05,03,01,00,00,00,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 17 */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
                    + "\");",


            // third puzzle

            "INSERT INTO " + GameLevelTileData.TABLE_NAME + " VALUES "
                    + "(null,1,3,2,9,150,\""
                    // 1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
		/* 1  */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 2  */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 3  */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 4  */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 5  */+ "01,00,00,00,07,00,00,01,00,00,00,00,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 6  */+ "01,00,07,05,07,00,07,00,00,00,05,00,07,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 7  */+ "01,00,01,11,01,11,01,11,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 8  */+ "01,00,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 9  */+ "06,00,00,04,04,04,04,04,04,00,00,05,00,05,10" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 10 */+ "01,00,00,01,01,01,01,01,01,01,01,01,00,00,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 11 */+ "01,00,00,01,11,01,11,01,11,02,00,00,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 12 */+ "01,00,07,00,00,07,00,07,02,07,07,00,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 13 */+ "01,00,00,00,00,00,00,00,00,00,00,00,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 14 */+ "01,03,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 15 */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 16 */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 17 */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
                    + "\");",

            // end room
            "INSERT INTO " + GameLevelTileData.TABLE_NAME + " VALUES "
                    + "(null,1,4,2,9,0,\""
                    // 1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
		/* 1  */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 2  */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 3  */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 4  */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 5  */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 6  */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 7  */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 8  */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 9  */+ "06,00,00,00,00,00,00,00,00,00,13,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 10 */+ "01,00,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 11 */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 12 */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 13 */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 14 */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 15 */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 16 */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 17 */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
                    + "\");",


            //start room
            "INSERT INTO " + GameLevelTileData.TABLE_NAME + " VALUES "
                    + "(null,1,0,2,9,0,\""
                    // 1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
		/* 1  */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 2  */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 3  */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 4  */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 5  */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 6  */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 7  */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 8  */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 9  */+ "06,00,00,00,00,00,00,00,00,00,00,00,00,10,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 10 */+ "01,00,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 11 */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 12 */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 13 */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 14 */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 15 */+ "01,01,00,00,00,00,00,00,00,00,00,00,00,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 16 */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
		/* 17 */+ "01,01,01,01,01,01,01,01,01,01,01,01,01,01,01" + GameLevelTileData.TILE_DATA_LINE_BREAK
                    + "\");"


    };
    private static final String CREATE_TABLE_PLAYER_ITEMS = "CREATE TABLE "
            + TABLE_PLAYER_ITEM + "(" + KEY_PLAYER_ID
            + " INTEGER NOT NULL," + KEY_ITEM_ID + " INTEGER NOT NULL , "
            + KEY_ITEM_QUANTITY + " INTEGER, FOREIGN KEY("
            + KEY_PLAYER_ID + ") REFERENCES " + TABLE_PLAYER +"(" +KEY_ID +"), FOREIGN KEY("
            + KEY_ITEM_ID   + ") REFERENCES " + TABLE_ITEMS +"(" +KEY_ID +"), "
            + "PRIMARY KEY (" + KEY_PLAYER_ID + ","+ KEY_ITEM_ID +" ));";


    private static final String CREATE_TABLE_ITEMS = "CREATE TABLE "
            + TABLE_ITEMS + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_ITEM_NAME + " TEXT NOT NULL UNIQUE ,"
            + KEY_PURCHASE_VALUE+ " INTEGER );";


    //-------------URMIL

    private static final String CREATE_TABLE_LEVEL = "CREATE TABLE "
            + TABLE_LEVEL + "(" + KEY_LEVEL_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_LEVEL_NAME + " TEXT NOT NULL UNIQUE ,"
            + KEY_NUMBER_ROOMS + " INTEGER );";

    private static final String CREATE_TABLE_MUTATOR = "CREATE TABLE "
            + TABLE_MUTATOR + "(" + KEY_MUTATOR_ID
            + " INTEGER PRIMARY KEY ," + KEY_MUTATOR_NAME + " TEXT NOT NULL ,"
            + KEY_MUTATOR_VALUE + " INTEGER ," + KEY_MUTATOR_COLOR + " TEXT NOT NULL);";

    private static final String CREATE_TABLE_LEVEL_ROOM = "CREATE TABLE "
            + TABLE_LEVEL_ROOM + "(" + KEY_LEVEL_ID
            + " INTEGER NOT NULL," + KEY_ROOM_ID + " INTEGER NOT NULL, FOREIGN KEY("
            + KEY_LEVEL_ID + ") REFERENCES " + TABLE_LEVEL +"(" +KEY_LEVEL_ID +"), FOREIGN KEY("
            + KEY_ROOM_ID   + ") REFERENCES " + TABLE_ROOM +"(" +KEY_ROOM_ID +"), "
            + "PRIMARY KEY (" + KEY_LEVEL_ID + ","+ KEY_ROOM_ID +" ));";

    private static final String CREATE_TABLE_ROOM = "CREATE TABLE "
            + TABLE_ROOM + "(" + KEY_ROOM_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_TOTAL_GOLD + " INTEGER,"
            + KEY_NUMBER_BOMBS + " INTEGER,"
            + KEY_LEVEL_ID + " INTEGER,"
            + KEY_DESIRED_TIME + " INTEGER NOT NULL,"
            + KEY_ROOM_NUMBER + " INTEGER NOT NULL,"
            + KEY_PUZZLE_STRUCT + " TEXT NOT NULL, FOREIGN KEY("
            + KEY_LEVEL_ID + ") REFERENCES " + TABLE_LEVEL +"(" +KEY_LEVEL_ID +"));";

    private static final String CREATE_TABLE_PLAYER_MUTATOR = "CREATE TABLE "
            + TABLE_PLAYER_MUTATOR + "(" + KEY_PLAYER_ID
            + " INTEGER NOT NULL," + KEY_MUTATOR_ID + " INTEGER NOT NULL, FOREIGN KEY("
            + KEY_PLAYER_ID + ") REFERENCES " + TABLE_PLAYER +"(" +KEY_ID +"), FOREIGN KEY("
            + KEY_MUTATOR_ID   + ") REFERENCES " + TABLE_ITEMS +"(" +KEY_MUTATOR_ID +"), "
            + "PRIMARY KEY (" + KEY_PLAYER_ID + ","+ KEY_MUTATOR_ID +" ));";

    private static final String CREATE_TABLE_PLAYER_ROOM = "CREATE TABLE "
            + TABLE_PLAYER_ROOM + "(" + KEY_PLAYER_ID
            + " INTEGER NOT NULL," + KEY_ROOM_ID + " INTEGER NOT NULL,"
            + KEY_PLAYER_START_TILE_X + " INTEGER DEFAULT 2,"
            + KEY_PLAYER_START_TILE_Y + " INTEGER DEFAULT 9,"
            + KEY_TIME_TAKEN + " INTEGER ,"
            + KEY_PUZZLE_STRUCT + " TEXT NOT NULL, FOREIGN KEY("
            + KEY_PLAYER_ID + ") REFERENCES " + TABLE_PLAYER + "(" + KEY_ID + "), FOREIGN KEY("
            + KEY_ROOM_ID + ") REFERENCES " + GameLevelTileData.TABLE_NAME + "(" + GameLevelTileData.LEVEL + "), "
            + "PRIMARY KEY (" + KEY_PLAYER_ID + "," + KEY_ROOM_ID + " ));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * This method is called by system if the database is accessed but not yet
     * created.
     */

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(CREATE_TABLE_GAME_TILES);
        db.execSQL(CREATE_TABLE_GAME_LEVEL_TILES);

        // Populate game tables

        Log.d("Tile Game Example", "Populating DB tables");

        for (String query : POPULATE_TABLE_GAME_TILES)
        {
            db.execSQL(query);
        }

        for (String query : POPULATE_TABLE_GAME_LEVEL_TILES)
        {
            db.execSQL(query);
        }

        db.execSQL(CREATE_TABLE_PLAYER);         // create player table
        db.execSQL(CREATE_TABLE_ITEMS);          // create items table
        db.execSQL(CREATE_TABLE_PLAYER_ITEMS);   // create player_items table

        //-------------------Urmil
        db.execSQL(CREATE_TABLE_LEVEL);   // create player_items table
        db.execSQL(CREATE_TABLE_ROOM);   // create player_items table
        db.execSQL(CREATE_TABLE_LEVEL_ROOM);   // create player_items table
        db.execSQL(CREATE_TABLE_PLAYER_ROOM);   // create player_items table
        db.execSQL(CREATE_TABLE_MUTATOR);   // create player_items table
        db.execSQL(CREATE_TABLE_PLAYER_MUTATOR);   // create player_items table

        ///INSERT ITEMS
        ContentValues valuesitem = new ContentValues();
        valuesitem.put(KEY_ITEM_NAME, Constants.ITEM_POTION);
        valuesitem.put(KEY_PURCHASE_VALUE, Constants.ITEM_POTION_VALUE);
        db.insert(TABLE_ITEMS, null, valuesitem);

        valuesitem.put(KEY_ITEM_NAME, Constants.ITEM_BOMB);
        valuesitem.put(KEY_PURCHASE_VALUE, Constants.ITEM_BOMB_VALUE);
        db.insert(TABLE_ITEMS, null, valuesitem);


        valuesitem.put(KEY_ITEM_NAME, Constants.ITEM_MAP);
        valuesitem.put(KEY_PURCHASE_VALUE, Constants.ITEM_MAP_VALUE);
        db.insert(TABLE_ITEMS, null, valuesitem);


        valuesitem.put(KEY_ITEM_NAME, Constants.ITEM_KEY);
        valuesitem.put(KEY_PURCHASE_VALUE, Constants.ITEM_KEY_VALUE);
        db.insert(TABLE_ITEMS, null, valuesitem);

        valuesitem.put(KEY_ITEM_NAME, Constants.ITEM_TIME);
        valuesitem.put(KEY_PURCHASE_VALUE, Constants.ITEM_TIME_VALUE);
        db.insert(TABLE_ITEMS, null, valuesitem);



        //INSERT MUTATOR VALUES

        //cap

        ContentValues values = new ContentValues();
        values.put(KEY_MUTATOR_NAME, Constants.MUTATOR_CAP);
        values.put(KEY_MUTATOR_ID, 1);
        values.put(KEY_MUTATOR_COLOR, Constants.COLOR_BROWN);
        values.put(KEY_MUTATOR_VALUE, Constants.CAP_VALUE);
        db.insert(TABLE_MUTATOR, null, values);

        values.put(KEY_MUTATOR_NAME, Constants.MUTATOR_CAP);
        values.put(KEY_MUTATOR_ID,2);
        values.put(KEY_MUTATOR_COLOR,Constants.COLOR_YELLOW);
        values.put(KEY_MUTATOR_VALUE, Constants.CAP_VALUE);
        db.insert(TABLE_MUTATOR, null, values);

        //shirt
        values.put(KEY_MUTATOR_NAME,Constants.MUTATOR_SHIRT);
        values.put(KEY_MUTATOR_ID,3);
        values.put(KEY_MUTATOR_COLOR,Constants.COLOR_BLUE);
        values.put(KEY_MUTATOR_VALUE, Constants.SHIRT_VALUE);
        db.insert(TABLE_MUTATOR, null, values);
//
        values.put(KEY_MUTATOR_NAME,Constants.MUTATOR_SHIRT);
        values.put(KEY_MUTATOR_ID,4);
        values.put(KEY_MUTATOR_COLOR,Constants.COLOR_GREEN);
        values.put(KEY_MUTATOR_VALUE,Constants.SHIRT_VALUE);
        db.insert(TABLE_MUTATOR, null, values);


        //skin

        values.put(KEY_MUTATOR_NAME,Constants.MUTATOR_SKIN);
        values.put(KEY_MUTATOR_ID,5);
        values.put(KEY_MUTATOR_COLOR,Constants.COLOR_PINK);
        values.put(KEY_MUTATOR_VALUE,Constants.SKIN_VALUE);
        db.insert(TABLE_MUTATOR, null, values);

        values.put(KEY_MUTATOR_NAME,Constants.MUTATOR_SKIN);
        values.put(KEY_MUTATOR_ID,6);
        values.put(KEY_MUTATOR_COLOR,Constants.COLOR_WHITE);
        values.put(KEY_MUTATOR_VALUE,Constants.SKIN_VALUE);
        db.insert(TABLE_MUTATOR, null, values);


        //pant

        values.put(KEY_MUTATOR_NAME,Constants.MUTATOR_PANT);
        values.put(KEY_MUTATOR_ID,7);
        values.put(KEY_MUTATOR_COLOR,Constants.COLOR_RED);
        values.put(KEY_MUTATOR_VALUE,Constants.PANT_VALUE);
        db.insert(TABLE_MUTATOR, null, values);

        values.put(KEY_MUTATOR_NAME,Constants.MUTATOR_PANT);
        values.put(KEY_MUTATOR_ID,8);
        values.put(KEY_MUTATOR_COLOR,Constants.COLOR_PINK);
        values.put(KEY_MUTATOR_VALUE,Constants.PANT_VALUE);
        db.insert(TABLE_MUTATOR, null, values);




    }

    /**
     * This method is called when any modifications in database are done like
     * version is updated or database schema is changed
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        //game dao

        db.execSQL("DROP TABLE IF EXISTS " + GameTileData.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GameLevelTileData.TABLE_NAME);


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER); // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_ITEM); // drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS); // drop table if


        //-------------------Urmil
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEVEL); // drop table if
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM); // drop table if
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEVEL_ROOM); // drop table if
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_ROOM); // drop table if
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MUTATOR); // drop table if
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_MUTATOR); // drop table if




        onCreate(db);
    }


    public long addPlayerDetail(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, player.getName());
        values.put(KEY_TIME, player.getTime());
        values.put(KEY_GOLD, player.getGold());
        values.put(KEY_SCORE, player.getScore());

        // insert row in player table
        try {
            long insert = db.insert(TABLE_PLAYER, null, values);
            return insert;
        }

        catch(SQLiteConstraintException e)
        {
            Log.e(TAG, "Error inserting SQLConstraintException " + values, e);
            return -1; // Error message for integrity constraint violation
        }
        catch (SQLException e) {
            Log.e(TAG, "Error inserting SQLException " + values, e);
            return -2;
        }
        catch (Exception e) {
            Log.e(TAG, "Error inserting  Exception" + values, e);
            return -3;
        }


    }

    public List<PlayerView> getAllPlayerListDescendingScore() {
        List<PlayerView> playerArrayList = new ArrayList<PlayerView>();
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYER+"  ORDER BY " +KEY_SCORE + " DESC" + ","+ KEY_TIME + " DESC";
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                PlayerView player= new PlayerView();

                player.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                player.setName(c.getString(c.getColumnIndex(KEY_USERNAME)));
                player.setScore(c.getInt(c.getColumnIndex(KEY_SCORE)));
                player.setGold(c.getInt(c.getColumnIndex(KEY_GOLD)));
                player.setTime(c.getInt(c.getColumnIndex(KEY_TIME)));



                playerArrayList.add(player);
            } while (c.moveToNext());
        }

        c.close();
        return playerArrayList;
    }

    public Boolean checkIfPlayerExists(String name) {
        SQLiteDatabase db = this.getReadableDatabase();


        SQLiteStatement sqLiteStatement = db.compileStatement("select count(1) from PLAYER where USERNAME='" + name + "'; ");

        long count = sqLiteStatement.simpleQueryForLong();
        return count == 1;

    }
    public Player loadProfile(String name) {
        SQLiteDatabase db = this.getReadableDatabase();



        String selectQuery = "SELECT  * FROM " + TABLE_PLAYER + " WHERE "
                + KEY_USERNAME + " = '" + name +"'";
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Player player =Player.getInstance();

        player.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        player.setScore(c.getInt(c.getColumnIndex(KEY_SCORE)));
        player.setGold(c.getInt(c.getColumnIndex(KEY_GOLD)));
        player.setTime(c.getInt(c.getColumnIndex(KEY_TIME)));
        player.setName(c.getString(c.getColumnIndex(KEY_USERNAME)));

        c.close();
        return player;
    }



    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<Item>();
        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS+ ";";
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                Item item= new Item();

                item.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                item.setName(c.getString(c.getColumnIndex(KEY_ITEM_NAME)));
                item.setValue(c.getInt(c.getColumnIndex(KEY_PURCHASE_VALUE)));



                itemList.add(item);
            } while (c.moveToNext());
        }

        c.close();
        return itemList;
    }


    public long insertDefaultPlayerItem(PlayerItem playerItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_PLAYER_ID, playerItem.getPlayerId());
        values.put(KEY_ITEM_ID, playerItem.getItemId());
        values.put(KEY_ITEM_QUANTITY, playerItem.getItemQuantity());

        // insert row in player table
        try {
            long insert = db.insert(TABLE_PLAYER_ITEM, null, values);
            return insert;
        }

        catch(SQLiteConstraintException e)
        {
            Log.e(TAG, "Error inserting SQLConstraintException " + values, e);
            return -1; // Error message for integrity constraint violation
        }
        catch (SQLException e) {
            Log.e(TAG, "Error inserting SQLException " + values, e);
            return -2;
        }
        catch (Exception e) {
            Log.e(TAG, "Error inserting  Exception" + values, e);
            return -3;
        }
    }


    public List<Item> loadPlayerItems(Integer id) {
        List<Item> playerItems = new ArrayList<Item>();
        String selectQuery = "SELECT PI.ITEM_ID,I.PURCHASE_VALUE, PI.ITEM_QUANTITY,I.ITEM_NAME FROM PLAYER_ITEMS PI,ITEMS I WHERE PI.PLAYER_ID ="+
                id +" AND PI.ITEM_ID=I.ID ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);


        if (c.moveToFirst()) {
            do {

                Item item= new Item();
                item.setId(c.getInt(c.getColumnIndex(KEY_ITEM_ID)));
                item.setValue(c.getInt(c.getColumnIndex(KEY_PURCHASE_VALUE)));
                item.setName(c.getString(c.getColumnIndex(KEY_ITEM_NAME)));
                item.setCount(c.getInt(c.getColumnIndex(KEY_ITEM_QUANTITY)));
                playerItems.add(item);
            } while (c.moveToNext());
        }

        c.close();

        return playerItems;
    }

    public List<Mutator> loadPlayerMutators(Integer id) {
        List<Mutator> playerMutatorList = new ArrayList<Mutator>();

        String selectQuery = "SELECT PM.MUTATOR_ID, M.MUTATOR_NAME, M.MUTATOR_COLOR FROM PLAYER_MUTATOR PM, MUTATOR M WHERE PM.PLAYER_ID ="+
                id +" AND PM.MUTATOR_ID=M.MUTATOR_ID ";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);


        if (c.moveToFirst()) {
            do {
                Mutator mutator= new Mutator();

                mutator.setId(c.getInt(c.getColumnIndex(KEY_MUTATOR_ID)));
                mutator.setName(c.getString(c.getColumnIndex(KEY_MUTATOR_NAME)));
                mutator.setColor(c.getString(c.getColumnIndex(KEY_MUTATOR_COLOR)));

                playerMutatorList.add(mutator);
            } while (c.moveToNext());
        }

        c.close();
        return playerMutatorList;
    }

    public List<Mutator> getAllMutators()
    {
        List<Mutator> mutatorList = new ArrayList<Mutator>();
        String selectQuery = "SELECT  * FROM " + TABLE_MUTATOR + ";";
        Log.d(TAG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Mutator mutator= new Mutator();
                mutator.setId(c.getInt(c.getColumnIndex(KEY_MUTATOR_ID)));
                mutator.setName(c.getString(c.getColumnIndex(KEY_MUTATOR_NAME)));
                mutator.setColor(c.getString(c.getColumnIndex(KEY_MUTATOR_COLOR)));
                mutator.setValue(c.getInt(c.getColumnIndex(KEY_MUTATOR_VALUE)));
                mutatorList.add(mutator);
            } while (c.moveToNext());
        }

        c.close();
        return mutatorList;
    }


    public long insertPlayerMutator(PlayerMutator playerMutator) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_PLAYER_ID, playerMutator.getPlayerId());
        values.put(KEY_MUTATOR_ID, playerMutator.getMutatorId());

        // insert row in player table
        try {
            long insert = db.insert(TABLE_PLAYER_MUTATOR, null, values);
            return insert;
        }

        catch(SQLiteConstraintException e)
        {
            Log.e(TAG, "Error inserting SQLConstraintException " + values, e);
            return -1; // Error message for integrity constraint violation
        }
        catch (SQLException e) {
            Log.e(TAG, "Error inserting SQLException " + values, e);
            return -2;
        }
        catch (Exception e) {
            Log.e(TAG, "Error inserting  Exception" + values, e);
            return -3;
        }
    }


    public void updatePlayerGold() {
        SQLiteDatabase db = this.getWritableDatabase();
        Player player=Player.getInstance();
        // Creating content values
        String strSQL = "UPDATE PLAYER SET GOLD ="+player.getGold()+" WHERE ID = " + player.getId();

        db.execSQL(strSQL);

    }
    public int updatePlayerGoldValue(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues valuesPlayer = new ContentValues();
        valuesPlayer.put(KEY_GOLD, player.getGold());


        return db.update(TABLE_PLAYER, valuesPlayer, KEY_ID + " = ?",
                new String[] { String.valueOf(player.getId()) });
    }

    public void updatePlayerItemCount(String itemName,int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        Player player=Player.getInstance();
        String strSQL = "UPDATE PLAYER_ITEMS SET ITEM_QUANTITY ="+player.getItemCount(itemName)+" WHERE "+ KEY_PLAYER_ID + "="+ player.getId()+" AND "+KEY_ITEM_ID+"="+id;

        db.execSQL(strSQL);

    }

    public void saveProfile() {
        SQLiteDatabase db = this.getWritableDatabase();
        Player player = Player.getInstance();
        // Creating content values
        List<Item> itemList=new ArrayList<Item>();
        itemList=player.getItemList();
        for (Item item:itemList)
        {
            String strSQL = "UPDATE PLAYER_ITEMS SET ITEM_QUANTITY = "+item.getCount()+" WHERE "+ KEY_PLAYER_ID + "="+ player.getId()+" AND "+KEY_ITEM_ID+ "=" +item.getId();

            Log.d(TAG, strSQL);
            db.execSQL(strSQL);
        }

        String strSQLSave = "UPDATE PLAYER SET GOLD =" + player.getGold() + ", SCORE =" + player.getScore() + ", TIME =" + player.getTime() + " WHERE ID = " + player.getId();
        Log.d(TAG, strSQLSave);
        db.execSQL(strSQLSave);
    }

    public void savePlayerRoomDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        Player player = Player.getInstance();

        Map<Integer, Room> playerRoomList;

        playerRoomList=player.getRoomList();
        if((playerRoomList != null) && !playerRoomList.isEmpty())
        {
            for (Map.Entry<Integer, Room> entry : playerRoomList.entrySet()) {
                SQLiteStatement sqLiteStatement = db.compileStatement("select count(1) from PLAYER_ROOM where PLAYER_ID=" + player.getId() + " and ROOM_ID=" + entry.getValue().getId() + ";");

                long count = sqLiteStatement.simpleQueryForLong();
                if (count == 1) {
                    //UPDATE
                    String strSQLupdate = "UPDATE " + TABLE_PLAYER_ROOM + " SET TIME_TAKEN = " + entry.getValue().getTimeTaken() + ", playerStartTileX = " + entry.getValue().getPlayerStartX() + ", playerStartTileY = " + entry.getValue().getPlayerStartY() + ", PUZZLE_STRUCT='" + entry.getValue().getPuzzleStruct() + "' where PLAYER_ID=" + player.getId() + " and ROOM_ID=" + entry.getValue().getId() +";";
                    db.execSQL(strSQLupdate);
                    Log.d(TAG, strSQLupdate);



                } else {
                    String strSQLinsert = "INSERT INTO " + TABLE_PLAYER_ROOM + " VALUES(" + player.getId() + "," + entry.getValue().getId()
                            + "," + entry.getValue().getPlayerStartX() + "," + entry.getValue().getPlayerStartY()+ "," + entry.getValue().getTimeTaken() + ",'" + entry.getValue().getPuzzleStruct() + "');";
                    db.execSQL(strSQLinsert);
                    Log.d(TAG, strSQLinsert);

                }
            }
        }

    }
    public void deletePlayerRoomDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        Player player = Player.getInstance();


        String strSQLupdate = "DELETE FROM " + TABLE_PLAYER_ROOM + " WHERE PLAYER_ID  =" + player.getId();
        db.execSQL(strSQLupdate);

    }

    public void deletePlayerCurrentRoom(int roomId) {


        SQLiteDatabase db = this.getWritableDatabase();
        Player player = Player.getInstance();
        //UPDATE

        String strSQLupdate = "DELETE FROM " + TABLE_PLAYER_ROOM + " WHERE PLAYER_ID  =" + player.getId()+ " and ROOM_ID = "+roomId;
        db.execSQL(strSQLupdate);
        Player.getInstance().getRoomList().remove(roomId);
        Log.d(TAG, strSQLupdate);



    }



}
