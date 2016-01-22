package com.group2.dungeonraider.domain;

/**
 * Created by Rohit on 10/27/2015.
 */
public class Room {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private int playerStartX ;
    private int playerStartY ;
    private String puzzleStruct;

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    private long timeTaken;

    public int getPlayerStartX() {
        return playerStartX;
    }

    public void setPlayerStartX(int playerStartX) {
        this.playerStartX = playerStartX;
    }

    public int getPlayerStartY() {
        return playerStartY;
    }

    public void setPlayerStartY(int playerStartY) {
        this.playerStartY = playerStartY;
    }

    public String getPuzzleStruct() {
        return puzzleStruct;
    }

    public void setPuzzleStruct(String puzzleStruct) {
        this.puzzleStruct = puzzleStruct;
    }




}
