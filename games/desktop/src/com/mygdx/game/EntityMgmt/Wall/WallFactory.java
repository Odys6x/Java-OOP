package com.mygdx.game.EntityMgmt.Wall;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.EntityFactory;

public class WallFactory implements EntityFactory {
    @Override
    public Entity createEntity(String entityType,float x, float y) {
        if ("WallH".equalsIgnoreCase(entityType)) {
            return new Wall("brickH.jpg", x, y);
        }
        else if("WallV".equalsIgnoreCase(entityType)){
            return new Wall("brickV.jpg", x, y);
        }
        else {
            return null;
        }
    }


}
