package com.mygdx.game.BehaviourMgmt;

import java.util.List;


import com.mygdx.game.AiControllerMgmt.AIMovement;
import com.mygdx.game.EntityMgmt.Appliance;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.EntityMgmt.GameObject;


public class BehaviourManager {
    private final EntityManager entityManager;
    private final Behaviour behaviour; // Add this line


    public BehaviourManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.behaviour = new Behaviour();
    }

 
    public void updateBehaviours() {
        for (GameObject entity : entityManager.getEntities()) {
            if (entity instanceof Appliance) {
                Appliance appliance = (Appliance) entity;
                behaviour.updateApplianceBehaviour(appliance, entity);
            }
        }
    }
}
