package com.mygdx.game.AiControllerMgmt;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.EntityMgmt.AI.AI;
import com.mygdx.game.EntityMgmt.GameObject;

public class AIMovement {
    private static final float MIN_SPEED = 50f;

    private AIMovement() {}

    public static void updateAIMovement(GameObject entity) {
        if (entity instanceof AI) {
            AI aiEntity = (AI) entity;

            // Movement Logic
            float delta = Gdx.graphics.getDeltaTime();
            float newSpeed = aiEntity.getSpeed() * delta;

            float newX = entity.getX();
            float newY = entity.getY();

            // Access the AI's desired direction (You'll need to implement this in your AI class)
            String desiredDirection = aiEntity.getCurrentDirection();

            switch (desiredDirection) {
                case "UP":     newY += newSpeed; break;
                case "DOWN":   newY -= newSpeed; break;
                case "LEFT":   newX -= newSpeed; break;
                case "RIGHT":  newX += newSpeed; break;
            }

            // Screen Boundary Checks
            if (newX < 0) newX = 0;
            if (newX > Gdx.graphics.getWidth()) newX = Gdx.graphics.getWidth();
            if (newY < 0) newY = 0;
            if (newY > Gdx.graphics.getHeight()) newY = Gdx.graphics.getHeight(); // Now allows going up

            entity.setX(newX);
            entity.setY(newY);
        }
    }
}
