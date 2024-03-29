package com.mygdx.game.BehaviourMgmt;

import com.mygdx.game.EntityMgmt.*;
import com.mygdx.game.EntityMgmt.Appliances.Appliance;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.EntityMgmt.Player.Player;
import com.mygdx.game.ScoreMgmt.Score;


public class Behaviour {
    private EntityManager entityManager; 

    private Score scoreManager;
    private String type;
    private boolean isOn;
    private float energyConsumption;
    private float activationRange;
    private float score;
    private float interactionTime;
    private float energyLevel;

    private Player player;
    private long lastInteractionTime = 0; // Keeps track of the last interaction time
    private static final long COOLDOWN_MILLIS = 200; // Cooldown period in milliseconds (adjust as needed)

    public Behaviour() {

    }
    public Behaviour(Score scoreManager) {
        this.scoreManager = scoreManager;
    }

    public void interactWithAppliance(Appliance appliance, GameObject entity) {
        // Check if the player is within range to interact with the appliance
        boolean isOn = appliance.getState();

        if (isOn) { // if appliance is on, turn it off
            appliance.turnOff();
            appliance.deactivate();
            scoreManager.incrementScore(100);
        } else { // if appliance is off, turn it on
            appliance.turnOn();
            appliance.activate();

        }


        // Update player's attributes accordingly (e.g., decrease energy level, increase score)
        energyLevel -= appliance.getEnergyConsumption();
        score = appliance.getScore();
        System.out.println(scoreManager.getScore());


    }

    protected boolean isWithinInteractionRange2(Appliance appliance, GameObject entity) {
        boolean interacted = false;
        // get the original appliance location so that it does not wander off when vibrating
        float originalX = appliance.getOGX();
        float originalY = appliance.getOGY();
        float bound = appliance.getActivationRange();
        // Calculate bounds for the appliance
        float applianceLeftBound = appliance.getX() - (appliance.getWidth() / 2) - bound;
        float applianceRightBound = appliance.getX() + (appliance.getWidth() / 2) + bound;
        float applianceUpBound = appliance.getY() - (appliance.getHeight() / 2) - bound;
        float applianceDownBound = appliance.getY() + (appliance.getHeight() / 2) + bound;

        // Calculate bounds for the entity
        float entityLeftBound = entity.getX() - (entity.getWidth() / 2) - bound;
        float entityRightBound = entity.getX() + (entity.getWidth() / 2) + bound;
        float entityUpBound = entity.getY() - (entity.getHeight() / 2) - bound;
        float entityDownBound = entity.getY() + (entity.getHeight() / 2) + bound;

        // Check if the bounds overlap
        if (entityRightBound >= applianceLeftBound && entityLeftBound <= applianceRightBound &&
                entityDownBound >= applianceUpBound && entityUpBound <= applianceDownBound) {
            interacted = true;

            // for making appliance "vibrate" when near
            // Calculate random displacement within a small range
            float displacementX = MathUtils.random(-1, 1); // Change this range as needed
            float displacementY = MathUtils.random(-1, 1); // Change this range as needed

            // Apply the displacement to the appliance's position
            appliance.setX(appliance.getX() + displacementX);
            appliance.setY(appliance.getY() + displacementY);
            if (Math.abs(appliance.getX() - originalX) > 1) {
                appliance.setX(originalX);
            }
            if (Math.abs(appliance.getY() - originalY) > 1) {
                appliance.setY(originalY);
            }
        }

        return interacted;
    }

    public void updateApplianceBehaviour(Appliance appliance, GameObject entity, boolean interactPressed) {
        long currentTime = System.currentTimeMillis(); // Get the current time

        // Check if the player is within interaction range and the E key is pressed
        if (isWithinInteractionRange2(appliance, entity) && interactPressed == true) {
            // Check if the cooldown period has elapsed or if it's the first interaction
            if (currentTime - lastInteractionTime >= COOLDOWN_MILLIS || lastInteractionTime == 0) {
                    interactWithAppliance(appliance, entity);
                lastInteractionTime = currentTime; // Update the last interaction time
            }
        } else {

        }

    }

}

