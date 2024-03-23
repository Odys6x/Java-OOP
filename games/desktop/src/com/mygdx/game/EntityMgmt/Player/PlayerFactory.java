package com.mygdx.game.EntityMgmt.Player;

import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.EntityFactory;

public class PlayerFactory implements EntityFactory {
    @Override
    public Entity createEntity(String entityType,float x, float y) {
        if ("Player".equalsIgnoreCase(entityType)) {
            // Create a Player instance with default properties
            return new Player("Player.png", x, y, 200, null);
        } else {
            // Handle unknown entity types or return null, throw exception, etc.
            return null;
        }
    }

    // You can add more methods or functionality specific to the PlayerFactory here
}
