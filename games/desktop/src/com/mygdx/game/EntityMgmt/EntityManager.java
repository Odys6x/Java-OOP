package com.mygdx.game.EntityMgmt;

import com.mygdx.game.EntityMgmt.EntityClass.Entity;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> entityList;

    public EntityManager() {
        entityList = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public void updateEntities() {
        for (Entity entity : entityList) {
            entity.update();
        }
    }
    public List<Entity> getEntityList() {
        return entityList;
    }
}
