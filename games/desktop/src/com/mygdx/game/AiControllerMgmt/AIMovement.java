package com.mygdx.game.AiControllerMgmt;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.EntityMgmt.Entity;

public class AIMovement {

    private static final float MIN_SPEED = 50f;

    private AIMovement() {} // Enforce private constructor

    public static void updateAIMovement(Entity entity) {
        if (!entity.isUserControlled()) { // Maintain user control check
            float newY = entity.getY() - entity.getSpeed() * Gdx.graphics.getDeltaTime();
            entity.setY(newY);

            if (entity.getY() <= 0) {
                resetEntityPositionAndSpeed(entity);
            }
        }
    }

    private static void resetEntityPositionAndSpeed(Entity entity) {
        float newSpeed = (float) (Math.random() * entity.getSpeed()) + MIN_SPEED;
        float newX = (float) (Math.random() * Gdx.graphics.getWidth());
        entity.setY(Gdx.graphics.getHeight());
        entity.setX(newX);
        entity.setSpeed(newSpeed);
    }
}
