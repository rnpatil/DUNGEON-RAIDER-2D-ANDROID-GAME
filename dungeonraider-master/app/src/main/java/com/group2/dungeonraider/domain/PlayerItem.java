package com.group2.dungeonraider.domain;

/**
 * Created by Rohit on 10/27/2015.
 */
public class PlayerItem {

    private int itemId;
    private int playerId;
    private int itemQuantity;
    private String itemName;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public PlayerItem()
    {}


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }





    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }



}
