package com.mygdx.game.AiControllerMgmt;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.EntityMgmt.AI.AI;
import com.mygdx.game.EntityMgmt.GameObject;

public class AIMovement {

    private static final float MIN_SPEED = 50f;

    private AIMovement() {} // Enforce private constructor

    public static void updateAIMovement(GameObject entity, GameObject target) {
        if (entity instanceof AI) {
            float targetX = target.getX() + target.getWidth() / 2; // Target chicken's center
            float targetY = target.getY() + target.getHeight() / 2;
            float deltaX = targetX - (entity.getX() + entity.getWidth() / 2);
            float deltaY = targetY - (entity.getY() + entity.getHeight() / 2);

            // Normalize for smoother movement
            float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            if (distance != 0) {
                deltaX /= distance;
                deltaY /= distance;
            }

            float newX = entity.getX() + deltaX * ((AI) entity).getSpeed() * Gdx.graphics.getDeltaTime();
            float newY = entity.getY() + deltaY * ((AI) entity).getSpeed() * Gdx.graphics.getDeltaTime();

            entity.setX(newX);
            entity.setY(newY);
        }
        if (entity instanceof AI) { // Maintain user control check
            float newY = entity.getY() - ((AI) entity).getSpeed() * Gdx.graphics.getDeltaTime();
            entity.setY(newY);

            if (entity.getY() <= 0) {
                resetEntityPositionAndSpeed(entity);
            }
        }
    }

    private static void resetEntityPositionAndSpeed(GameObject entity) {
        float newSpeed = (float) (Math.random() * ((AI) entity).getSpeed() + MIN_SPEED);
        float newX = (float) (Math.random() * Gdx.graphics.getWidth());
        entity.setY(Gdx.graphics.getHeight());
        entity.setX(newX);
        ((AI) entity).setSpeed(newSpeed);
    }
}
