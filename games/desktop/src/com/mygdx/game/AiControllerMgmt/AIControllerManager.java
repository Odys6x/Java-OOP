package com.mygdx.game.AiControllerMgmt;

import java.util.List;

import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.EntityManager;

public class AIControllerManager {

    private final EntityManager entityManager;

    public AIControllerManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void moveAIControlledEntities() {
        List<Entity> allEntities = entityManager.getEntityList();
        for (Entity entity : allEntities) {
            AIMovement.updateAIMovement(entity); // Call AI movement logic for all entities
        }
    }
}
