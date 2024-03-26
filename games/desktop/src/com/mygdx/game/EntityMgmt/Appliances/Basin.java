//package com.mygdx.game.EntityMgmt.Appliances;
//
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//
//public class Basin extends Appliance {
//    private Texture offTexture;
//    private Texture onTexture;
//    private TextureRegion texture;
//
//    public Basin(String type, String pathon,String pathoff, float x, float y, float energyConsumption, float activationRange,
//                 TextureRegion texture, float scoreValue, float interactionTime) {
//        super(type, path, x, y, energyConsumption, activationRange, texture, scoreValue, interactionTime);
//        offTexture = new Texture(pathoff);
//        onTexture = new Texture(pathon);
//        this.texture = new TextureRegion(offTexture);
//    }
//
//
//    public void draw(SpriteBatch batch) {
//        batch.draw(texture, getX(), getY());
//    }
//
//    public void turnOn() {
//        this.texture = new TextureRegion(onTexture);
//    }
//
//    public void turnOff() {
//        this.texture = new TextureRegion(offTexture);
//    }
//    /*
//    public void dispose() {
//        offTexture.dispose();
//        onTexture.dispose();
//    }
//     */
//    public int getWidth(TextureRegion texture){
//        return texture.getRegionWidth();
//    }
//    public int getHeight(TextureRegion texture){
//        return texture.getRegionHeight();
//    }
//}
