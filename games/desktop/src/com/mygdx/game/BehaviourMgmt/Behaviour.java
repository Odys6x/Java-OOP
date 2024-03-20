package com.mygdx.game.BehaviourMgmt;

import com.mygdx.game.EntityMgmt.*;
import com.mygdx.game.InputMgmt.InputCommand;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public class Behaviour {
    private String type;
    private boolean isOn;
    private float energyConsumption;
    private float activationRange;
    private float score;
    private float interactionTime;
    private float energyLevel;
    private long lastInteractionTime = 0; // Keeps track of the last interaction time
    private static final long COOLDOWN_MILLIS = 500; // Cooldown period in milliseconds (adjust as needed)

    public void interactWithAppliance(Appliance appliance, GameObject entity) {
        // Check if the player is within range to interact with the appliance
        // Perform interaction with the appliance
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

        // For example, turn off the appliance
        //for later implementation
        // Update player's attributes accordingly (e.g., decrease energy level, increase
        // score)
        // energyLevel -= appliance.getEnergyConsumption();
        //
        // score += appliance.getScore();

    }

    private boolean isWithinInteractionRange(Appliance appliance, GameObject entity) {
        // Calculate the distance between the player and the appliance
        double distance = Math
                .sqrt(Math.pow(entity.getX() - appliance.getX(), 2) + Math.pow(entity.getY() - appliance.getY(), 2));
        // Check if the distance is within the interaction range of the player
        if (distance <= appliance.getActivationRange()) {
            System.out.println("in range le");
        }
        return distance <= appliance.getActivationRange();
    }

    public void updateApplianceBehaviour(Appliance appliance, GameObject entity) {
        long currentTime = System.currentTimeMillis(); // Get the current time

        if (isWithinInteractionRange(appliance, entity) && Gdx.input.isKeyPressed(Input.Keys.E)) { // Maintain user
            if (currentTime - lastInteractionTime >= COOLDOWN_MILLIS) { // Check if the cooldown period has elapsed
                // control check
            interactWithAppliance(appliance, entity);
            lastInteractionTime = currentTime; // Update the last interaction time
            System.out.println("Interacted le");
            }
        }

    }
    
}
