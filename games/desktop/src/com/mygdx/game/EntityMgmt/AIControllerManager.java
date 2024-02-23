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
            if (!entity.isUserControlled()) {
                moveAIControlled(entity);
            }
        }
    }

    private void moveAIControlled(Entity entity) {
        float newY = entity.getY() - entity.getSpeed() * Gdx.graphics.getDeltaTime();
        entity.setY(newY);

        if (entity.getY() <= 0) {
            float newSpeed = (float) (Math.random() * entity.getSpeed()) + 50;
            float newX = (float) (Math.random() * Gdx.graphics.getWidth());
            entity.setY(Gdx.graphics.getHeight());
            entity.setX(newX);
            entity.setSpeed(newSpeed);
        }
    }
}