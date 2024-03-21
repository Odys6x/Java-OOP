package com.mygdx.game.RoomMgmt;

import com.mygdx.game.EntityMgmt.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<GameObject> entities;

    public Room() {
        entities = new ArrayList<>();
    }

    public void addEntity(GameObject entity) {
        entities.add(entity);
    }

    public List<GameObject> getEntities() {
        return entities;
    }
}
