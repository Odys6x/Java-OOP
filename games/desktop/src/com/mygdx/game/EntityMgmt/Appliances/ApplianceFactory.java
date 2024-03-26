package com.mygdx.game.EntityMgmt.Appliances;

import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.EntityFactory;

public class ApplianceFactory implements EntityFactory {
    @Override
    public Entity createEntity(String entityType, float x,  float y) {
        if ("Microwave".equalsIgnoreCase(entityType)) {
            return new Microwave("Microwave", "13.png", x, y, 20, 2, null, 0, 0);
        } else if ("Basin".equalsIgnoreCase(entityType)) {
            return new Basin("Basin", "15.png", x, y, 20,2, null, 0, 0);
        } else if ("Fridge".equalsIgnoreCase(entityType)) {
            return new Fridge("Fridge", "19.png", x, y, 20,2, null, 0, 0);
        }
        else if ("Bathtub".equalsIgnoreCase(entityType)) {
            return new Bathtub("Bathtub", "17.png", x, y, 20,2, null, 0, 0);
        } else {
            // Handle unknown entity types or return null, throw exception, etc.
            return null;
        }
    }

}
