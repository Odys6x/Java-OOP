package com.mygdx.game.BehaviourMgmt;

import com.badlogic.gdx.*;
import com.mygdx.game.EntityMgmt.AI.AI;
import com.mygdx.game.EntityMgmt.Appliances.Appliance;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.EntityMgmt.GameObject;
import com.mygdx.game.EntityMgmt.Entity;
import com.badlogic.gdx.math.*;

import java.util.ArrayList;
import java.util.List;

public class AIBehaviour extends Behaviour {
    private List<Appliance> appliances;
    private List<AI> AIControlled;
    private EntityManager entities;
    private float aiSpeed;
    private boolean appliancesOn;
    private float changeDirectionTime = 0;
    private float directionX = 0;
    private float directionY = 0;

    public AIBehaviour(EntityManager entityManager) {
        this.entities = entityManager;
        this.appliances = new ArrayList<>();
        this.AIControlled = new ArrayList<>();
        // modify this to control the speed the ai moves towards the appliance
        // slower speeds mean it is easier to keep everything on
        aiSpeed = 200f;
        for (GameObject entity : entities.getEntities()) {
            switch (entity.getType()) {
                case APPLIANCE:
                    appliances.add((Appliance) entity);
                    break;
                case AI:
                    AIControlled.add((AI) entity);
                    break;
                // handle other types if needed
            }
        }

        System.err.println("Appliances are" + appliances);
        System.err.println("AI are" + AIControlled);

    }

    public void updateAIBehaviour2() {
        boolean allAppliancesOn = true;
        for (Appliance appliance : appliances) {
            if (!appliance.getState()) {
                allAppliancesOn = false;
            }
        }
        if (!allAppliancesOn) {
            for (AI ai : AIControlled) {
                for (Appliance appliance : appliances) {
                    if (!appliance.getState()) {
                        moveTowardsAppliance(appliance, ai);
                        if (isWithinInteractionRange2(appliance, ai) && !appliance.getState()) {
                            interactWithAppliance(appliance, ai);
                            appliance.activate();
                        }
                    }
                }
            }
        } else {
            for (AI ai : AIControlled) {
                performWanderingBehavior(ai);
            }
        }
    }

    private void moveTowardsAppliance(Appliance appliance, Entity entity) {
        System.err.println(entity + "is moving to" + appliance);
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

    public boolean allAppliancesOn() {
        return appliancesOn;
    }

    private void performWanderingBehavior(AI ai) {

        int mapWidth = Gdx.graphics.getWidth();
        int mapHeight = Gdx.graphics.getHeight();
        float deltaTime = Gdx.graphics.getDeltaTime();
        changeDirectionTime -= deltaTime;
    
        // If it's time to change direction
        if (changeDirectionTime <= 0) {
            // Choose a random direction
            directionX = MathUtils.random(-1f, 1f);
            directionY = MathUtils.random(-1f, 1f);
    
            // Normalize the direction vector to ensure consistent speed
            float len = (float) Math.sqrt(directionX * directionX + directionY * directionY);
            directionX /= len;
            directionY /= len;
    
            // Choose a random time until the next direction change (between 1 and 3 seconds)
            changeDirectionTime = MathUtils.random(2f, 6f);
        }
    
        // Calculate the new position
        float newX = ai.getX() + directionX * ai.getSpeed() * deltaTime;
        float newY = ai.getY() + directionY * ai.getSpeed() * deltaTime;
    
        // Check if the new position is within the map boundaries
        if (newX < 0) newX = 0;
        if (newY < 0) newY = 0;
        if (newX > mapWidth) newX = mapWidth;
        if (newY > mapHeight) newY = mapHeight;
    
        // Update the AI's position
        entities.updateEntityPosition(ai, newX - ai.getX(), newY - ai.getY());
    }

}
