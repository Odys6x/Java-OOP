package com.mygdx.game.EntityMgmt.Appliances;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.GameObjectType;
import com.mygdx.game.EntityMgmt.Player.Player;

public class Appliance extends Entity {
    private String type;
    private boolean isOn;
    private float energyConsumption;
    private float activationRange;
    private TextureRegion texture;
    private float scoreValue;
    private float interactionTime;
    private boolean isActivated;

    public Appliance(String type, String path, float x, float y, float energyConsumption, float activationRange,
                     TextureRegion texture, float scoreValue, float interactionTime) {
        super(path, x, y);
        this.type = type;
        this.energyConsumption = energyConsumption;
        this.activationRange = activationRange;
        this.texture = texture;
        this.scoreValue = scoreValue;
        this.interactionTime = interactionTime;
        this.isOn = false;
        this.isActivated = false;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, getX(), getY());
    }

    public void turnOn() {
        isOn = true;
        // Start animation, play sound effect, etc.
    }

    public void turnOff() {
        isOn = false;
        // Start animation, play sound effect, etc.
    }

    public void activate() {
        isActivated = true;
        // Start interaction timer, perform any other necessary actions
    }

    public void deactivate() {
        isActivated = false;
        // Reset interaction timer, perform any other necessary actions
    }

    public void interact(Player player) {
        if (isWithinRange(player)) {
            activate();
            // Wait for interaction time before completing the interaction
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    performInteraction(player);
                    deactivate();
                }
            }, interactionTime);
        }
    }

    private void performInteraction(Player player) {
        // Interaction logic based on appliance type
        if ("Chicken".equals(type)) {
            player.incrementScore(scoreValue);
        } else if ("Microwave".equals(type)) {
            player.interactWithAppliance(this);
        } else if ("Fridge".equals(type)) {
            player.turnOffAppliance(this);
        }
    }

    private boolean isWithinRange(Player player) {
        float distance = (float) Math.sqrt(Math.pow(player.getX() - getX(), 2) + Math.pow(player.getY() - getY(), 2));
        return distance <= activationRange;
    }

    public float getEnergyConsumption() {
        return energyConsumption;
    }

    public float getScore() {
        return scoreValue;
    }

    public float getActivationRange() {
        return activationRange;
    }

    public boolean getState() {
        return isActivated;
    }
    @Override
    public GameObjectType getType() {
        return GameObjectType.APPLIANCE;
    }

}
