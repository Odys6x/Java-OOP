package com.mygdx.game.AiControllerMgmt;

import java.util.List;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.EntityMgmt.GameObject;

public class AIControllerManager {

    private final EntityManager entityManager;

    public AIControllerManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void moveAIControlledEntities() {
        List<GameObject> allEntities = entityManager.getEntities();
        for (GameObject entity : allEntities) {
            AIMovement.updateAIMovement(entity);
        }
    }
}
