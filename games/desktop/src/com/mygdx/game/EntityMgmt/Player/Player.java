package com.mygdx.game.EntityMgmt.Player;


import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.EntityMgmt.Appliances.Appliance;
import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.GameObjectType;

public class Player extends Entity {
    private float speed;
    private Animation<TextureRegion> walkAnimationForward,walkAnimationBackward,standAnimation;
    private float stateTime;
    private String direction;
    private int energyLevel;


    public Player(){

    }

    protected Player(String path, float x, float y, float speed,String direction){
        super(path, x, y);
        this.speed = speed;
        this.direction = direction;
        this.energyLevel = 100;
        this.stateTime = 0f;
        initialisePlayer();
    }
    public void updateAnimations(List<Integer> pressedKeys) {
        if (pressedKeys.contains(Keys.LEFT)) {
            direction = "LEFT";
        } else if (pressedKeys.contains(Keys.RIGHT)) {
            direction = "RIGHT";
        } else if (pressedKeys.contains(Keys.DOWN)) {
            direction = "DOWN";
        } else if (pressedKeys.contains(Keys.UP)) {
            direction = "UP";
        } else {
            direction = null;
        }
    }

    public void initialisePlayer(){
        Texture spriteSheet = new Texture(getPath());
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 10, spriteSheet.getHeight() / 2);
        TextureRegion[] framesForward = new TextureRegion[9]; // Frames for walking forward
        TextureRegion[] framesBackward = new TextureRegion[9]; // Frames for walking backward
        System.arraycopy(tmp[0], 1, framesForward, 0, 9); // Start from index 1 for walking forward
        System.arraycopy(tmp[1], 1, framesBackward, 0, 9); // Start from index 1 for walking backward
        walkAnimationForward = new Animation<>(0.1f, framesForward); // Animation for walking forward
        walkAnimationBackward = new Animation<>(0.1f, framesBackward);
        standAnimation = new Animation<>(0.1f, tmp[0][0]); // Standing frame
    }

    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getDirection(){
        return direction;
    }

    public void setDirection(String direction){
        this.direction = direction;
    }

    public float getEnergy(){
        return energyLevel;
    }
    public void setEnergy(float energy){
        energyLevel -= energy;
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
    public float getHeight() {
        return super.getHeight()/2;
    }

    @Override
    public float getWidth() {
        return super.getWidth()/10;
    }


    @Override
    public void draw(SpriteBatch batch) {
        // Get the current frame of the animation
        stateTime += Gdx.graphics.getDeltaTime(); // Update the state time
        TextureRegion currentFrame = null;
        if(direction == "RIGHT"){
            currentFrame = walkAnimationForward.getKeyFrame(stateTime, true);
            //System.out.println("RIGHT ANI");
        }
        else if(direction == "LEFT"){
            currentFrame = walkAnimationBackward.getKeyFrame(stateTime, true);
            //System.out.println("LEFT ANI");
        }
        else {
            currentFrame = standAnimation.getKeyFrame(stateTime, true);
        }
        // Draw the current frame
        batch.draw(currentFrame, getX(), getY());
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.PLAYER;
    }
    @Override
    public void dispose() {
        // Dispose of the textures used for animations
        walkAnimationForward = null;
        walkAnimationBackward = null;
        standAnimation = null;
    }
}
