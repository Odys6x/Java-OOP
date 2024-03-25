package com.mygdx.game.AiControllerMgmt;

import java.util.List;


import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.EntityMgmt.GameObject;
import com.mygdx.game.EntityMgmt.AI.AI;
import com.mygdx.game.EntityMgmt.Appliances.Chicken;

public class AIControllerManager {

    private final EntityManager entityManager;

    public AIControllerManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void moveAIControlledEntities() {

        List<GameObject> allEntities = entityManager.getEntities();
        for (GameObject entity : allEntities) {
            if (entity instanceof AI) {
                GameObject targetChicken = findClosestChicken(allEntities);
                if (targetChicken != null) {
                    AIMovement.updateAIMovement(entity, targetChicken); // Pass the targetChicken
                } else {
                    AIMovement.updateAIMovement(entity, targetChicken); // Default movement if no chicken
                }

            }
        }
    }

    private GameObject findClosestChicken(List<GameObject> allEntities) {
        GameObject closestChicken = null;
        GameObject targetChicken = null;
        float closestDistance = Float.MAX_VALUE;

        for (GameObject entity : allEntities) {
            if (entity instanceof Chicken) {
                // Calculate distance between AI and chicken
                float distance = calculateDistance(entity, targetChicken);
                if (distance < closestDistance) {
                    closestChicken = entity;
                    closestDistance = distance;
                }
            }
        }
        return closestChicken;
    }

    // Helper for distance (assuming positions are center-based)
    private float calculateDistance(GameObject entity1, GameObject entity2) {
        float deltaX = entity1.getX() + entity1.getWidth()/2  - (entity2.getX() + entity2.getWidth()/2);
        float deltaY = entity1.getY() + entity1.getHeight()/2 - (entity2.getY() + entity2.getHeight()/2);
        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
