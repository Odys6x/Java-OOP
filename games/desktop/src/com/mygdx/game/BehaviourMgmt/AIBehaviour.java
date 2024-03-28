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
import java.util.HashMap;
import java.util.Map;

public class AIBehaviour extends Behaviour {
    private List<Appliance> appliances;
    private List<AI> AIControlled;
    private EntityManager entities;
    private Vector2 currentTargetLocation;
    private float aiSpeed;
    private boolean appliancesOn;
    private float waitTime;
    private AI closestAI;
    private Map<AI, Vector2> aiTargetLocations;

    public AIBehaviour(EntityManager entityManager) {
        this.entities = entityManager;
        this.appliances = new ArrayList<>();
        this.AIControlled = new ArrayList<>();
        closestAI = null;
        aiTargetLocations = new HashMap();
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
    }

    public void updateAIBehaviour2() {
        for (AI ai : AIControlled) {
            Appliance nearestAppliance = null;
            double nearestDistance = Double.MAX_VALUE;
            closestAI = null;

            Vector2 targetLocation = aiTargetLocations.getOrDefault(ai,getNextTargetLocation(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
            aiTargetLocations.putIfAbsent(ai, targetLocation);
            System.out.println(aiTargetLocations);

            for (Appliance appliance : appliances) {
                if (!appliance.getState()) { // Check if the appliance is off
                    double distance = calculateDistance(appliance, ai);
                    if (distance < nearestDistance) {
                        nearestDistance = distance;
                        closestAI = ai;
                        nearestAppliance = appliance;
                    }
                }
            }
    
            if (nearestAppliance != null) {
            	if (waitTime < 1.0f) {
                    // AI is waiting, decrement the wait time
                    waitTime += Gdx.graphics.getDeltaTime();
                    //System.err.println("I am waiting");
                } else {
                    // Wait time is over, move towards the appliance
                    moveTowardsAppliance(nearestAppliance, closestAI);
                    if (isWithinInteractionRange2(nearestAppliance, closestAI)) {
                        nearestAppliance.AIInteract(nearestAppliance);
                        waitTime = 0;
                    }
                }
            } else {
            	if (waitTime < 0.8f) {
                    // AI is waiting, decrement the wait time
                    waitTime += Gdx.graphics.getDeltaTime();
            	}
            	else
            	{
            	// Wait time is over, move towards the current target location
                moveTowardsTargetLocation(targetLocation, ai);
                // Check if AI reaches the target location
                if (isAtTargetLocation(targetLocation, ai)) {
                    // AI reached the target location, reset current target location
                    targetLocation = getNextTargetLocation(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                    aiTargetLocations.put(ai, targetLocation);
                    waitTime = 0;
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

    public Vector2 getNextTargetLocation(float mapWidth, float mapHeight) {
        // Generate random coordinates within the map boundaries
        float randomX = MathUtils.random(0, mapWidth);
        float randomY = MathUtils.random(0, mapHeight);

        return new Vector2(randomX, randomY);
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
