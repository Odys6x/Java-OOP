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

    public void interactWithAppliance(Appliance appliance, GameObject entity) {
        // Check if the player is within range to interact with the appliance
            // Perform interaction with the appliance 
            appliance.turnOff(); 
            System.out.println("Turn off le");

            // For example, turn off the appliance
            // Update player's attributes accordingly (e.g., decrease energy level, increase score)
            //energyLevel -= appliance.getEnergyConsumption();
            // 
            //score += appliance.getScore();
        
    }

    private boolean isWithinInteractionRange(Appliance appliance, GameObject entity) {
        // Calculate the distance between the player and the appliance
        double distance = Math.sqrt(Math.pow(entity.getX() - appliance.getX(), 2) + Math.pow(entity.getY() - appliance.getY(), 2));
        // Check if the distance is within the interaction range of the player
        System.out.println("Distance is"+ distance);
        System.out.println("Activation range is"+appliance.getActivationRange());
        return distance <= appliance.getActivationRange();
    }

    public void updateApplianceBehaviour(Appliance appliance, GameObject entity) {
        if (isWithinInteractionRange(appliance, entity)) { // Maintain user control check
            interactWithAppliance(appliance, entity);
            System.out.println("Interacted le");
        }
        else{
            System.out.println("Havent interact le");
        }
    }
}
