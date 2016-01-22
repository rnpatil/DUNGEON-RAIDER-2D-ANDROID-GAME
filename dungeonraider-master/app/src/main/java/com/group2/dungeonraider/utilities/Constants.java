package com.group2.dungeonraider.utilities;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rohit on 10/27/2015.
 */
public class Constants {

    private static final String LOG = Constants.class.getSimpleName();
    public static String  LEVEL_TILE_DATA = "";
    public static int DESIRED_LEVEL_TIME = 0;
    public static boolean IS_NEW = false;
    public static boolean IS_PAUSE =false;


    // public static FPropsLoader drProps = new FPropsLoader("DRGame");

    public static boolean GAME_STARTED = false;
    public static long TE;
    public static int VOLUME_MODE;
    public static String THEME_MODE;
    public static int CHARACTER_SELECTED;

    public static long CUR_LEVEL_REMAINING_TIME;
    public static String ITEM_POTION="POTION";
    public static String ITEM_KEY="KEY";
    public static String ITEM_BOMB="BOMB";
    public static String ITEM_MAP="MAP";
    public static String ITEM_TIME ="TIME";
    public static int ITEM_POTION_VALUE=10;
    public static int ITEM_BOMB_VALUE=30;
    public static int ITEM_MAP_VALUE=40;
    public static int ITEM_TIME_VALUE =50;
    public static int ITEM_KEY_VALUE=35;
    public static int INITIAL_GOLD=100;

    public static int CHEST_PRIZE=100;

    public static boolean IS_PLAYER_LEVEL = false ;
    public static boolean IS_NEXT_ROOM = false;
    public static final int PUZZLE_WIDTH = 15;

    public static int PLAYER_GOLD;
    public static int PLAYER_SCORE;

    public static String MUTATOR_SHIRT="SHIRT";
    public static String MUTATOR_PANT="PANT";
    public static String MUTATOR_CAP="CAP";
    public static String MUTATOR_SKIN="SKIN";

    public static String COLOR_RED="RED";
    public static String COLOR_BLUE="BLUE";
    public static String COLOR_GREEN="GREEN";
    public static String COLOR_YELLOW="YELLOW";
    public static String COLOR_PINK="PINK";
    public static String COLOR_BROWN="BROWN";
    public static String COLOR_WHITE="WHITE";
    public static String DOLLAR="$";
    public static String OWNED="Owned : ";


    public static String PLAYER_A="bbpwa";
    public static String PLAYER_B="bbpwb";

    public static String PLAYER_RIGHT="r";
    public static String PLAYER_LEFT="l";

    public static int CAP_VALUE=20;
    public static int SHIRT_VALUE=40;
    public static int PANT_VALUE=50;
    public static int SKIN_VALUE=100;

    public static final int STAGE = 1;
    public static final int START_ROOM = 0;
    public static final int EXIT_ROOM = 4;


    public static Context appContext;
    public static int SLIDE_TILE_BY_DP = 10;
    public static int GAME_LEVEL = 0;
    public static long GAME_START_TIME = System.currentTimeMillis();
    public static long LAST_TIME = 0;
    public static long DELAY_LAST_TIME = 0;
    public static boolean IS_SLOW_DOWN_TIMER = false;
    public static int TICK_COUNTER_FOR_DELAY = 0;
    public static int MAX_TICK_COUNTER_FOR_DELAY = 5;
    public static int TIME_DELAY = 0;
    public static int MAX_TIME_DELAY = 1000;
    public static long LAST_CURR_TIME = 0;
    public static long LAST_TE = 0;
    public static MediaPlayer MP = new MediaPlayer();
    public static int CURRENT_LEVEL_DESIRED_TIME = 0;
    public static int INCREASE_TIME_BY_SECONDS = 30;
    public static int GAME_NO_OF_BOMBS;
    public static int GAME_NO_OF_POTIONS;
    public static int GAME_NO_OF_KEYS;
    public static int GAME_NO_OF_MAP;
    public static int GAME_NO_OF_TIME;

    public static CountDownTimerPausable TIMER;
    public static HashMap<Integer, ArrayList<Integer>> LEVEL_DATA = null;
    public static long TIME_ELAPSED = 0;

//    /*
//    DB details
//     */
//   /* public static String DB_NAME = drProps.getValue("db.name");
//    public static String DB_SERVER = drProps.getValue("db.server");
//    public static String DB_PORT = drProps.getValue("db.port");
//    public static String DB_PASSWORD = drProps.getValue("db.password");
//
//    /*
//    item type
//     */
//    public static String IT_KEY = drProps.getValue("it.key");
//    public static String IY_BOMB = drProps.getValue("it.bfomb");
//    public static String IT_POTION = drProps.getValue("it.potion");
//    public static String IT_MAP = drProps.getValue("it.map");
//
//    /*
//    direction
//     */
//    public static String DIR_UP = drProps.getValue("dir.up");
//    public static String DIR_DOWN = drProps.getValue("dir.down");
//    public static String DIR_LEFT = drProps.getValue("dir.left");
//    public static String DIR_RIGHT = drProps.getValue("dir.right");
//
//    /*
//    levels
//     */
//    public static String LVL_TUTORIAL = drProps.getValue("lvl.tutorial");
//    public static String LVL_EASY = drProps.getValue("lvl.easy");
//    public static String LVL_MEDIUM = drProps.getValue("lvl.medium");
//    public static String LVL_HARD = drProps.getValue("lvl.hard");
//
//    /*
//    mutator type
//     */
//    public static String MT_HAIR = drProps.getValue("mt.hair");
//    public static String MT_SKIN= drProps.getValue("mt.skin");
//    public static String MT_SHIRT = drProps.getValue("mt.shirt");
//    public static String MT_PANTS = drProps.getValue("mt.pants");
//
//    /*
//    move results
//     */
//    public static String MR_SUCCESS = drProps.getValue("mr.success");
//    public static String MR_FAILURE = drProps.getValue("mr.failure");
//    public static String MR_KEY_RECEIVED = drProps.getValue("mr.key.received");
//    public static String MR_SWITCH_ROOMS = drProps.getValue("mr.switch.rooms");
//    public static String MR_BOMB_RECEIVED = drProps.getValue("mr.cbomb.received");
//
//    /*
//    block type
//     */
//    public static String BT_EMPTY = drProps.getValue("bt.empty");
//    public static String BT_WALL = drProps.getValue("bt.wall");
//    public static String BT_SLIDING = drProps.getValue("bt.sliding");
//    public static String BT_DOOR = drProps.getValue("bt.door");
//    public static String BT_KEY = drProps.getValue("bt.key");
//    public static String BT_BREAKABLEWALL = drProps.getValue("bt.breakable.wall");
//    public static String BT_CHEST = drProps.getValue("bt.chest");
//    public static String BT_WEIGHT_SWITCH = drProps.getValue("bt.weight.switch");
//    public static String BT_FIRE = drProps.getValue("bt.fire");
//    public static String BT_SPIKE = drProps.getValue("bt.spike");
//    public static String BT_ENTRANCE_START = drProps.getValue("bt.entrance.start");
//    public static String BT_EXIT_SOLVE = drProps.getValue("bt.exit.solve");
//    public static String BT_FINISH = drProps.getValue("bt.finish");
//
//    /*
//    block state
//     */
//    public static String BS_MOVING = drProps.getValue("bs.moving");
//    public static String BS_STILL = drProps.getValue("bs.still");
//    public static String BS_OPEN_TEMPORARILY = drProps.getValue("bs.open.temporarily");
//    public static String BS_OPEN_ALWAYS = drProps.getValue("bs.open.always");
//    public static String BS_CLOSED = drProps.getValue("bs.closed");
//    public static String BS_ON = drProps.getValue("bs.on");
//    public static String BS_OFF = drProps.getValue("bs.off");

    public enum Direction {
        UP(1), DOWN(2), LEFT(3), RIGHT(4);
        private int value;

        Direction(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static Direction dir;
    public enum ItemType {
        KEY(1), BOMB(2), POTION(3), MAP(4);
        private int value;

        ItemType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public enum LEVEL {
        TUTORIAL(1), EASY(2), MEDIUM(3), HARD(4);
        private int value;

        LEVEL(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }


    public enum MutatorType {
        HAIR(1), SHIRT(2), SKIN(3), PANTS(4);
        private int value;

        MutatorType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }


    public enum MoveResult {
        SUCCESS(1), FAILURE(2), KEYRECEIVED(3), BOMBRECEIVED(4),
        SWITCHROOMS(5);
        private int value;

        MoveResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public enum BlockType {
        EMPTY(0), WALL(1),BREAKABLEWALL(2), BOMB(3), FIRE(4),
        CHEST(5), ENTRANCESTART(6), SLIDING(7), KEY(8),
        EXITSOLVE(9), FINISH(10), WEIGHTSWITCH(11),DOOROPEN(12),DUNGEONFINISH(13);

        private int value;

        BlockType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public enum BlockState {
        MOVING(1), STILL(2), OPENTEPORARILY(3), OPENALWAYS(4),
        CLOSED(5), ON(6), OFF(7);
        private int value;

        BlockState(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

}
