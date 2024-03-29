package com.mygdx.game.EntityMgmt.AI;

import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.EntityFactory;

public class AIFactory implements EntityFactory {

    @Override
    public Entity createEntity(String entityType,float x, float y) {
        if ("AI".equalsIgnoreCase(entityType)) {
            return new AI("21.png", x, y, 200);
        }
        else {
            return null;
        }
    }

}
