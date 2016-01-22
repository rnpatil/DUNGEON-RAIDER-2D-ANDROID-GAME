package com.group2.dungeonraider.domain;


public class PlayerMutator {

    private int mutatorId;
    private int playerId;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMutatorId() {
        return mutatorId;
    }

    public void setMutatorId(int mutatorId) {
        this.mutatorId = mutatorId;
    }

    public PlayerMutator()
    {}

}
