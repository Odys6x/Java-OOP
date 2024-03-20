package com.mygdx.game.BehaviourMgmt;

import java.util.List;


import com.mygdx.game.AiControllerMgmt.AIMovement;
import com.mygdx.game.EntityMgmt.Appliance;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.EntityMgmt.GameObject;
import com.mygdx.game.EntityMgmt.Microwave;


public class BehaviourManager {
    private final EntityManager entityManager;
    private final Behaviour behaviour; // Add this line


    public BehaviourManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.behaviour = new Behaviour();

    }

 
    public void updateBehaviours() {
        // Get the player entity
        GameObject playerEntity = entityManager.getUserControlledEntity();

        // Find the microwave appliance
        GameObject microwaveAppliance = null;
        for (GameObject entity : entityManager.getEntities()) {
            if (entity instanceof Microwave) {
                microwaveAppliance = entity;
                break;
            }
        }

        // If both the player and microwave exist, update the appliance behaviour
        if (playerEntity != null && microwaveAppliance != null) {
            behaviour.updateApplianceBehaviour((Appliance) microwaveAppliance, playerEntity);
        }
    }
}
