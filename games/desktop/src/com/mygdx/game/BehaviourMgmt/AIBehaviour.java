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
    private Vector2 currentTargetLocation;
    private float aiSpeed;
    private boolean appliancesOn;
    private float waitTime;

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

        // System.err.println("Appliances are" + appliances);
        // System.err.println("AI are" + AIControlled);

    }

    public void updateAIBehaviour2() {
        for (AI ai : AIControlled) {
            Appliance nearestAppliance = null;
            double nearestDistance = Double.MAX_VALUE;
            if (currentTargetLocation == null)
            {
                currentTargetLocation = ai.getNextTargetLocation(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }
            for (Appliance appliance : appliances) {
                if (!appliance.getState()) { // Check if the appliance is off
                    double distance = calculateDistance(appliance, ai);
                    if (distance < nearestDistance) {
                        nearestDistance = distance;
                        nearestAppliance = appliance;
                    }
                }
            }
    
            if (nearestAppliance != null) {
            	if (waitTime > 0) {
                    // AI is waiting, decrement the wait time
                    waitTime -= Gdx.graphics.getDeltaTime();
                    System.err.println("I am waiting");
                } else {
                    // Wait time is over, move towards the appliance
                    moveTowardsAppliance(nearestAppliance, ai);
                    System.err.println("I am moving towards the appliance");
                    if (isWithinInteractionRange2(nearestAppliance, ai)) {
                        nearestAppliance.AIInteract(nearestAppliance);
                        System.err.println("Ghost turn on le");
                    }
                }
            } else {
            	if (waitTime > 0) {
                    // AI is waiting, decrement the wait time
                    waitTime -= Gdx.graphics.getDeltaTime();
                    System.err.println("I am waiting");
            	}
            	else
            	{
            	// Wait time is over, move towards the current target location
                moveTowardsTargetLocation(currentTargetLocation, ai);
                System.err.println("I am moving towards the target location");
                // Check if AI reaches the target location
                if (isAtTargetLocation(currentTargetLocation, ai)) {
                    // AI reached the target location, reset current target location
                    currentTargetLocation = null;
                }
            	}
            }
            }
        }

    private void moveTowardsAppliance(Appliance appliance, Entity entity) {
        // System.err.println(entity + "is moving to" + appliance);
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

    
    public double calculateDistance(Appliance appliance, AI ai) {
        double xDistance = appliance.getX() - ai.getX();
        double yDistance = appliance.getY() - ai.getY();
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    private void moveTowardsTargetLocation(Vector2 targetLocation, Entity entity) {
        if (targetLocation != null) {
            // Calculate movement towards the target location
            float entityX = entity.getX();
            float entityY = entity.getY();

            float deltaX = targetLocation.x - entityX;
            float deltaY = targetLocation.y - entityY;

            float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            float movementSpeed = aiSpeed;

            if (distance > 0) {
                float normalizedDeltaX = deltaX / distance;
                float normalizedDeltaY = deltaY / distance;

                float moveX = normalizedDeltaX * movementSpeed * Gdx.graphics.getDeltaTime();
                float moveY = normalizedDeltaY * movementSpeed * Gdx.graphics.getDeltaTime();

                entities.updateEntityPosition(entity, moveX, moveY);
            }
        }
    }
    private boolean isAtTargetLocation(Vector2 targetLocation, Entity entity) {
        float distanceThreshold = 10; // Adjust as needed
        float distance = targetLocation.dst(entity.getX(), entity.getY());
        return distance <= distanceThreshold;
    }

}
