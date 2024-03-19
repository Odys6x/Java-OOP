package com.mygdx.game.RoomMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.EntityMgmt.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String type;
    private EntityManager entityManager;

    public Room() {
        entityManager = new EntityManager();
    }

    public void addEntity(List<GameObject> entities) {
        entityManager.getEntities().addAll(entities);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
