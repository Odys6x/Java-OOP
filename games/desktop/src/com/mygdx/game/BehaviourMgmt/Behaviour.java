package com.mygdx.game.BehaviourMgmt;

import com.mygdx.game.EntityMgmt.*;
import com.mygdx.game.EntityMgmt.Appliances.Appliance;
import com.badlogic.gdx.math.MathUtils;


public class Behaviour {
    private String type;
    private boolean isOn;
    private float energyConsumption;
    private float activationRange;
    private float score;
    private float interactionTime;
    private float energyLevel;
    private long lastInteractionTime = 0; // Keeps track of the last interaction time
    private static final long COOLDOWN_MILLIS = 200; // Cooldown period in milliseconds (adjust as needed)

    public void interactWithAppliance(Appliance appliance, GameObject entity) {
        // Check if the player is within range to interact with the appliance
        boolean isOn = appliance.getState();

        if (isOn) { // if appliance is on, turn it off

            appliance.turnOff();
            appliance.deactivate();

            System.out.println("Turn off le");
        } else { // if appliance is off, turn it on
            appliance.turnOn();
            appliance.activate();

            System.out.println("Turn on le");
        }

        // Update player's attributes accordingly (e.g., decrease energy level, increase score)
        energyLevel -= appliance.getEnergyConsumption();
        
        score += appliance.getScore();

    }

    protected boolean isWithinInteractionRange2(Appliance appliance, GameObject entity) {
        boolean interacted = false;
        int bound = 35;
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
            System.out.println("in range, " + appliance + " and " + entity);

            // for making appliance "vibrate" when near
            // Calculate random displacement within a small range
            float displacementX = MathUtils.random(-1, 1); // Change this range as needed
            float displacementY = MathUtils.random(-1, 1); // Change this range as needed

            // Apply the displacement to the appliance's position
            appliance.setX(appliance.getX() + displacementX);
            appliance.setY(appliance.getY() + displacementY);
        }

        return interacted;
    }

    public void updateApplianceBehaviour(Appliance appliance, GameObject entity, boolean interactPressed) {
        long currentTime = System.currentTimeMillis(); // Get the current time
        //System.err.println("interactPressed is" + interactPressed);

        // Check if the player is within interaction range and the E key is pressed
        // Gdx.input.isKeyJustPressed(Input.Keys.E)
        if (isWithinInteractionRange2(appliance, entity) && interactPressed == true) {
            // Check if the cooldown period has elapsed or if it's the first interaction
            if (currentTime - lastInteractionTime >= COOLDOWN_MILLIS || lastInteractionTime == 0) {
                interactWithAppliance(appliance, entity);
                lastInteractionTime = currentTime; // Update the last interaction time
                System.out.println("Interacted le");
            }
        } else {

        }

    }

}

/*
 * // disused, we are calculating the bounds and using that to determine
 * // interaction instead
 * private boolean isWithinInteractionRange(Appliance appliance, GameObject
 * entity) {
 * // Calculate the distance between the player and the appliance
 * double distance = Math
 * .sqrt(Math.pow(entity.getX() - appliance.getX(), 2) + Math.pow(entity.getY()
 * - appliance.getY(), 2));
 * // Check if the distance is within the interaction range of the player
 * if (distance <= appliance.getActivationRange()) {
 * System.out.println("in range of" + appliance);
 * }
 * return distance <= appliance.getActivationRange();
 * }
 */
