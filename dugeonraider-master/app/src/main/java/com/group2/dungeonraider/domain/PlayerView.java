package com.group2.dungeonraider.domain;

/**
 * Created by Rohit on 10/30/2015.
 */



/**
 * Created by Rohit on 10/27/2015.
 */
public class PlayerView {
    private int id;
    private String name;
    private int score;
    private int gold;
    private int time;


    public PlayerView(){

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



}
