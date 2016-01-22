package com.group2.dungeonraider.domain;

import com.group2.dungeonraider.utilities.Constants;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rohit on 10/27/2015.
 */

public class Player {
    private int id;
    private String name;
    private int score;
    private int gold;
    private int time;
    private List<Item> itemList;
    private String playerCharacter;
    private List<Mutator> mutatorList;
    private int currentLevel;
    private Map<Integer, Room> roomList = new HashMap<Integer, Room>();


    public Map<Integer, Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(Map<Integer, Room> roomList) {
        this.roomList = roomList;
    }

    public List<Mutator> getMutatorList() {
        return mutatorList;
    }

    public void setMutatorList(List<Mutator> mutatorList) {
        this.mutatorList = mutatorList;
    }



    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }












    private  volatile static Player instance;

    private Player()
    {
        if (instance!= null)
        {
            throw new IllegalStateException("Already initialized " + "Instance already created.");
        }
    }

    //Get the only object available

    public static Player getInstance()
    {
        if(instance==null)
        {
            synchronized(Player.class)
            {
                if(instance==null)
                {
                    instance=new Player();
                }
            }

        }
        return instance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerCharacter() { return playerCharacter; }

    public void setPlayerCharacter(String playerCharacter) { this.playerCharacter = playerCharacter;}

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", score=").append(score);
        sb.append(", gold=").append(gold);
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }

    public int getItemCount(String itemName)
    {
        List<Item> lstItem = this.getItemList();
        int count = 0;
        for (Item item: lstItem)
        {
            if(item.getName().equals(itemName))
            {
                count = item.getCount();
                break;
            }
        }
        return count;
    }

    public int setItemCount(String itemName,int itemCount)
    {
        List<Item> lstItem = this.getItemList();
        for (Item item: lstItem)
        {
            if(item.getName().equals(itemName)) {
                item.setCount(itemCount);
                break;
            }
        }
        return itemCount;
    }


}
