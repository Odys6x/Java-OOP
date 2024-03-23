package com.mygdx.game.EntityMgmt.Appliances;

import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.EntityFactory;

public class ApplianceFactory implements EntityFactory {
    @Override
    public Entity createEntity(String entityType,float x, float y) {
        if ("Microwave".equalsIgnoreCase(entityType)) {
            return new Microwave();
        } else if ("Chicken".equalsIgnoreCase(entityType)) {
            return new Chicken();
        } else {
            // Handle unknown entity types or return null, throw exception, etc.
            return null;
        }
    }

}
