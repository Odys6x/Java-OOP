package com.mygdx.game.BehaviourMgmt;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.EntityMgmt.AI.AI;
import com.mygdx.game.EntityMgmt.Appliances.Appliance;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.EntityMgmt.GameObject;
import com.mygdx.game.EntityMgmt.Entity;

import java.util.ArrayList;
import java.util.List;

public class AIBehaviour extends Behaviour {
    private List<Appliance> appliances;
    private List<AI> AIControlled;
    private EntityManager entities;
    private float aiSpeed;
    private boolean appliancesOn;

    public AIBehaviour(EntityManager entityManager) {
        this.entities = entityManager;
        this.appliances = new ArrayList<>();
        this.AIControlled = new ArrayList<>();
        //modify this to control the speed the ai moves towards the appliance
        //slower speeds mean it is easier to keep everything on
        aiSpeed = 500f;
        

        for (GameObject entity : entities.getEntities()) {
            if (entity instanceof Appliance) {
                appliances.add((Appliance) entity);
            } else if (entity instanceof AI) {
                AIControlled.add((AI) entity);

            }
        }
        System.err.println("Appliances are"+appliances);
        System.err.println("AI are"+AIControlled    );


    }

    public void updateAIBehaviour2() {
        boolean allAppliancesOn = true;
        for (Appliance appliance : appliances) {
            if (!appliance.getState()) {
                allAppliancesOn = false;

            }

        
        } 
        if(!allAppliancesOn){
            for (AI ai : AIControlled) {
                for (Appliance appliance : appliances) {
                    if(!appliance.getState()){
                        moveTowardsAppliance(appliance, ai);

                        if (isWithinInteractionRange2(appliance, ai) && !appliance.getState()) {
                            interactWithAppliance(appliance, ai);
                           // continue;
    
                        }
                    }
                   

             
                }
            }

        }
    }

    
    private void moveTowardsAppliance(Appliance appliance, Entity entity) {
        System.err.println(entity+"is moving to"+appliance);
        float targetX = appliance.getX();
        float targetY = appliance.getY();
        float entityX = entity.getX();
        float entityY = entity.getY();

        float deltaX = targetX - entityX;
        float deltaY = targetY - entityY;

        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        float movementSpeed = aiSpeed; // Adjust this value as needed

        if (distance > 0) {
            float normalizedDeltaX = deltaX / distance;
            float normalizedDeltaY = deltaY / distance;

            float moveX = normalizedDeltaX * movementSpeed * Gdx.graphics.getDeltaTime();
            float moveY = normalizedDeltaY * movementSpeed * Gdx.graphics.getDeltaTime();

            entities.updateEntityPosition(entity, moveX, moveY);
            
        }
    }

    public boolean allAppliancesOn(){
        return appliancesOn;
    }

    // public void updateAIBehaviour() {
    // for (AI ai : AIControlled) {
    // for (Appliance appliance : appliances) {
    // if (isWithinInteractionRange2(appliance, ai)) {
    // if (getWanderTimer() <= 0f) {
    // if (!appliance.getState()) {
    // appliance.turnOn();
    // }
    // setWanderTimer(maxWanderTimer);
    // } else {
    // setWanderTimer(getWanderTimer() - Gdx.graphics.getDeltaTime());
    // }
    // break; // Exit loop after interacting with the first appliance in range
    // }else{
    // moveTowardsAppliance(appliances.get(0), ai);

    // }
    // }
    // }
    // }
}
