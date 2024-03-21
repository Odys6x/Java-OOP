package com.mygdx.game.EntityMgmt.Appliances;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Chicken extends Appliance {
    private Texture offTexture;
    private Texture onTexture;
    private TextureRegion texture;
    
    public Chicken(String type, String path, float x, float y, float energyConsumption, float activationRange,
            TextureRegion texture, float scoreValue, float interactionTime) {
        super(type, path, x, y, energyConsumption, activationRange, texture, scoreValue, interactionTime);
    }

    public Chicken() {
        // Call the constructor of the superclass with default values
        // can disuse the activation range, this behaviour handled in behaviour
        super("Chicken", "cock.jpg", 1000, 50, 20,200, null, 0, 0);
        offTexture = new Texture("cock_off.jpg");
        onTexture = new Texture("cock.jpg");
        this.texture = new TextureRegion(offTexture);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, getX(), getY());
    }

    public void turnOn() {
        this.texture = new TextureRegion(onTexture);
    }

    public void turnOff() {
        this.texture = new TextureRegion(offTexture);
    }
    /*
    public void dispose() {
        offTexture.dispose();
        onTexture.dispose();
    }
     */
    public int getWidth(TextureRegion texture){
        return texture.getRegionWidth();
    }
    public int getHeight(TextureRegion texture){
        return texture.getRegionHeight();
    }
}
