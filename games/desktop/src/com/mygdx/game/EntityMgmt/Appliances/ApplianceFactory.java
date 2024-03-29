package com.mygdx.game.EntityMgmt.Appliances;

import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.EntityFactory;

public class ApplianceFactory implements EntityFactory {
    @Override
    public Entity createEntity(String entityType, float x,  float y) {
        if ("Microwave".equalsIgnoreCase(entityType)) {
            return new Appliance("Microwave", "13.png", "12.png",x, y, 20, 5, 30, 0);
        } else if ("Basin".equalsIgnoreCase(entityType)) {
            return new Appliance("Basin", "15.png", "14.png",x, y, 20, 5, 20, 0);
        } else if ("Fridge".equalsIgnoreCase(entityType)) {
            return new Appliance("Fridge", "19.png", "18.png",x, y, 20, 5, 10, 0);
        }
        else if ("Bathtub".equalsIgnoreCase(entityType)) {
            return new Appliance("Bathtub", "17.png", "16.png",x, y, 20, 15, 40, 0);
        } else {
            return null;
        }
    }

}