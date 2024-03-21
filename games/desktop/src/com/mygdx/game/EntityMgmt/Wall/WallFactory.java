package com.mygdx.game.EntityMgmt.Wall;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.EntityFactory;

public class WallFactory implements EntityFactory {
    @Override
    public Entity createEntity(String entityType) {
        if ("WallH".equalsIgnoreCase(entityType)) {
            return new Wall("brickH.jpg", getRandomX(), getRandomY());
        }
        else if("WallV".equalsIgnoreCase(entityType)){
            return new Wall("brickV.jpg", getRandomX(), getRandomY());
        }
        else {
            return null;
        }
    }

    private int getRandomX() {
        return MathUtils.random(0, 500);
    }

    private int getRandomY() {
        return MathUtils.random(0, 500);
    }

}
