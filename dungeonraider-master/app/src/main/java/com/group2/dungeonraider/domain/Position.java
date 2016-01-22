package com.group2.dungeonraider.domain;

/**
 * Created by Rohit on 10/27/2015.
 */
public class Position {
    private int id;
    private double xpos;
    private double ypos;

    public Position(int id, double xpos, double ypos) {
        this.id = id;
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getXpos() {
        return xpos;
    }

    public void setXpos(double xpos) {
        this.xpos = xpos;
    }

    public double getYpos() {
        return ypos;
    }

    public void setYpos(double ypos) {
        this.ypos = ypos;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Position{");
        sb.append("id=").append(id);
        sb.append(", xpos=").append(xpos);
        sb.append(", ypos=").append(ypos);
        sb.append('}');
        return sb.toString();
    }
}
