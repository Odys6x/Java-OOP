package com.mygdx.game.RoomMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.EntityMgmt.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private EntityManager entityManager;
    private List<GameObject> entities;

    public Room() {
        entities = entityManager.getEntities();
    }

    public void addEntity(GameObject entity) {
        entities.add(entity);
    }

    public List<GameObject> getEntities() {
        return entities;
    }
}
