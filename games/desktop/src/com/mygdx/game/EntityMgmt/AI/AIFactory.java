package com.mygdx.game.EntityMgmt.AI;

import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.EntityFactory;

public class AIFactory implements EntityFactory {

    public AIFactory(){

    }
    @Override
    public Entity createEntity(String entityType) {
        if ("AI".equalsIgnoreCase(entityType)) {
            return new AI("droplet.png", 300, 400, 200);
        }
        else {
            // Handle unknown entity types or return null, throw exception, etc.
            return null;
        }
    }

}
