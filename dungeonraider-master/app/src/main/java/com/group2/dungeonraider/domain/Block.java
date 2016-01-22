package com.group2.dungeonraider.domain;

/**
 * Created by Rohit on 10/27/2015.
 */
public class Block {
    private int id;
    private double length;
    private double breadth;
    private Position position;
    private BlockType blockType;
    private BlockState blockState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getBreadth() {
        return breadth;
    }

    public void setBreadth(double breadth) {
        this.breadth = breadth;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public void setBlockState(BlockState blockState) {
        this.blockState = blockState;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Block{");
        sb.append("id=").append(id);
        sb.append(", length=").append(length);
        sb.append(", breadth=").append(breadth);
        sb.append(", position=").append(position);
        sb.append(", blockType=").append(blockType);
        sb.append(", blockState=").append(blockState);
        sb.append('}');
        return sb.toString();
    }
}
