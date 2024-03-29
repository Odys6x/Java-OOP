package com.mygdx.game.EntityMgmt.Appliances;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.GameObjectType;

public class Appliance extends Entity {
    private String type;
    private boolean isOn;
    private float energyConsumption;
    private float activationRange;
    private TextureRegion texture;
    private float scoreValue;
    private float interactionTime;
    private boolean isActivated;
    private Texture offTexture;
    private Texture onTexture;
    private float OriginalY;
    private float OriginalX;

    protected Appliance(String type, String pathon, String pathoff, float x, float y, float energyConsumption, float activationRange
            , float scoreValue, float interactionTime) {
        super(pathon, x, y);
        this.type = type;
        this.offTexture = new Texture(pathoff);
        this.onTexture = new Texture(pathon);
        this.energyConsumption = energyConsumption;
        this.activationRange = activationRange;
        this.texture = new TextureRegion(offTexture);
        this.scoreValue = scoreValue;
        this.isOn = false;
        this.isActivated = false;
        this.OriginalX = x;
        this.OriginalY = y;
    }


    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void turnOn() {
        isOn = true;
        this.texture = new TextureRegion(onTexture);
    }

    public void turnOff() {
        isOn = false;
        this.texture = new TextureRegion(offTexture);
    }

    public void activate() {
        isActivated = true;
    }

    public void deactivate() {
        isActivated = false;
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
    public float getX() {
        return super.getX();
    }

    @Override
    public void setX(float x) {
        super.setX(x);
    }

    @Override
    public float getY() {
        return super.getY();
    }

    @Override
    public void setY(float y) {
        super.setY(y);
    }
    @Override
    public GameObjectType getType() {
        return GameObjectType.APPLIANCE;
    }

    public int getWidth(TextureRegion texture){
        return texture.getRegionWidth();
    }
    public int getHeight(TextureRegion texture){
        return texture.getRegionHeight();
    }

    public void AIInteract(Appliance appliance){
        if (appliance.getState() == false){
            appliance.activate();
            appliance.turnOn();
        }
    }

    public float getOGX(){
        return OriginalX;
    }
    public float getOGY(){
        return OriginalY;
    }
    @Override
    public void dispose() {
        offTexture.dispose();
        onTexture.dispose();
    }

}