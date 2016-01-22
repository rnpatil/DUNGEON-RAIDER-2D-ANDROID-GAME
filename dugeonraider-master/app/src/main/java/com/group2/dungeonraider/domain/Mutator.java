package com.group2.dungeonraider.domain;

/**
 * Created by Rohit on 10/27/2015.
 */
public class Mutator
{
    private int id;
    private String name;

    public String getAlreadyPurchased() {
        return alreadyPurchased;
    }

    public void setAlreadyPurchased(String alreadyPurchased) {
        this.alreadyPurchased = alreadyPurchased;
    }

    private String alreadyPurchased;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;
    private String color;

    public String getName() {
        return name;
    }


    public  Mutator() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }



}
