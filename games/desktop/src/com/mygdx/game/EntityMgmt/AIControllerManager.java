package com.mygdx.game.EntityMgmt;

import java.util.List;

import com.badlogic.gdx.Gdx;

public class AIControllerManager {
    private EntityManager entityManager;

    public AIControllerManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void moveAIControlledEntities() {
        List<Entity> entityList = entityManager.getEntityList();
        for (Entity entity : entityList) {
            if (entity instanceof TexturedObject && !((TexturedObject) entity).isUserControlled()) {
                moveAIControlled((TexturedObject) entity);
            }
        }
    }

    private void moveAIControlled(TexturedObject texturedObject) {
        float newY = texturedObject.getY() - texturedObject.getSpeed() * Gdx.graphics.getDeltaTime();
        texturedObject.setY(newY);

        if (texturedObject.getY() <= 0) {
            float newSpeed = (float) (Math.random() * texturedObject.getSpeed()) + 50;
            float newX = (float) (Math.random() * Gdx.graphics.getWidth());
            texturedObject.setY(Gdx.graphics.getHeight());
            texturedObject.setX(newX);
            texturedObject.setSpeed(newSpeed);
        }
    }
}