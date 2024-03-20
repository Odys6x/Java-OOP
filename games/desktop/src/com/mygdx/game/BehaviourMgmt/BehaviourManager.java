package com.mygdx.game.BehaviourMgmt;

import com.mygdx.game.EntityMgmt.Appliances.Appliance;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.EntityMgmt.GameObject;

public class BehaviourManager {
    private final EntityManager entityManager;
    private final Behaviour behaviour;
    private boolean interactPressed;


    public BehaviourManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.behaviour = new Behaviour();

    }

    public void updateBehaviours() {
        // Get the player entity
        GameObject playerEntity = entityManager.getUserControlledEntity();
        Appliance appliance = null;
        interactPressed = getInteractPressed();

        // Find the appliance
        for (GameObject entity : entityManager.getEntities()) {
            if (entity instanceof Appliance) {
                appliance = (Appliance) entity;
                behaviour.updateApplianceBehaviour(appliance, playerEntity, interactPressed);
            }
        }
    }

    public void setInteractPressed(boolean interactPressed) {
        this.interactPressed = interactPressed;
    }

    public boolean getInteractPressed(){
        return interactPressed;
    }
}
