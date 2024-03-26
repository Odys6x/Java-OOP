package com.mygdx.game.EntityMgmt.Furniture;

import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.EntityFactory;

public class    FurnitureFactory implements EntityFactory {
    @Override
    public Entity createEntity(String entityType, float x, float y) {
        if ("Chair".equalsIgnoreCase(entityType)) {
            return new Furniture("Chair", "31.png", x, y); // Adjust the path to the chair texture
        } else if ("Sofa".equalsIgnoreCase(entityType)) {
            return new Furniture("Sofa", "30.png", x, y); // Adjust the path to the chair texture
        } else if ("Table".equalsIgnoreCase(entityType)) {
            return new Furniture("Table", "29.png", x, y); // Adjust the path to the chair texture
        }
        else {
            return null;
        }
    }

}
