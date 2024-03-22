package com.mygdx.game.MapMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;

public class Map {
    private int numberOfWalls;

    public Map(int numberOfWalls) {
        this.numberOfWalls = numberOfWalls;
    }

    public int getNumberOfWalls() {
        return numberOfWalls;
    }
}

