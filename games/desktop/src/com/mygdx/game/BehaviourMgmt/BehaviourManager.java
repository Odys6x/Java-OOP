package com.mygdx.game.BehaviourMgmt;

import com.badlogic.gdx.Input;
import com.mygdx.game.EntityMgmt.Appliances.Appliance;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.EntityMgmt.GameObject;
import com.mygdx.game.InputMgmt.InputManager;
import com.mygdx.game.BehaviourMgmt.*;
import com.mygdx.game.AiControllerMgmt.*;



import java.util.List;


public class BehaviourManager {
    private final EntityManager entityManager;
    private final Behaviour behaviour;
    private final InputManager inputManager;
    private boolean interactPressed;


    public BehaviourManager(EntityManager entityManager, InputManager inputManager) {
        this.entityManager = entityManager;
        this.behaviour = new Behaviour();
        this.inputManager = inputManager;
    }

    public void updateBehaviours(List<Integer> pressedKeys) {
        // Get the player entity
        GameObject playerEntity = entityManager.getUserControlledEntity();
        Appliance appliance = null;
        System.out.println(pressedKeys);
        if (pressedKeys.contains(Input.Keys.E))
        {
        	interactPressed = true;
        }
        else {
        	interactPressed = false;
        }
        
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
    // public void behaviourswitch(AIBehaviour aiBehaviour, AIControllerManager aiControllerManager){
    //     if (AIBehaviour.allAppliancesOn())

    // }
}
