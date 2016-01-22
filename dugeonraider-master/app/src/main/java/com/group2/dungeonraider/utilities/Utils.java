package com.group2.dungeonraider.utilities;

import com.group2.dungeonraider.domain.Player;

/**
 * Created by Ajinkya on 11/3/2015.
 */
public class Utils {

    public static String getTime(long milliseconds){
        String time = null;


        return time;
    }

    public static boolean clearGameData(){
        boolean result = true;
        Constants.GAME_NO_OF_BOMBS = 0;
        Constants.GAME_NO_OF_POTIONS = 0;
        Constants.GAME_NO_OF_KEYS = 0;
        Constants.PLAYER_GOLD = 0;
        Constants.PLAYER_SCORE = 0;
        return result;
    }
}
