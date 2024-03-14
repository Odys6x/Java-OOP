package com.mygdx.game.EntityMgmt;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity implements PlayerManagement{
    private float speed;
    private Animation<TextureRegion> walkAnimationForward,walkAnimationBackward,standAnimation;

    private float stateTime;

    private String direction;

    public Player(){
        stateTime = 0f;
    }

    public Player(String path, float x, float y, float speed,String direction){
        super(path, x, y);
        this.speed = speed;
        this.direction = direction;
        Texture spriteSheet = new Texture("Player.png");
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

    @Override
    public float getHeight() {
        return super.getHeight()/2;
    }

    @Override
    public float getWidth() {
        return super.getWidth()/10;
    }

    @Override
    public Player createPlayer() {
        // Create a player with specific properties
        float initialX = 300;
        float initialY = 0;
        float playerSpeed = 200;

        return new Player("Player.png", initialX, initialY, playerSpeed,null);
    }
    @Override
    public void draw(SpriteBatch batch) {
        // Get the current frame of the animation
        stateTime += Gdx.graphics.getDeltaTime(); // Update the state time
        TextureRegion currentFrame = null;
        if(direction == "RIGHT"){
            currentFrame = walkAnimationForward.getKeyFrame(stateTime, true);
        }
        else if(direction == "LEFT"){
            currentFrame = walkAnimationBackward.getKeyFrame(stateTime, true);
        }
        else {
            currentFrame = standAnimation.getKeyFrame(stateTime, true);
        }
        // Draw the current frame
        batch.draw(currentFrame, getX(), getY());
    }

}
