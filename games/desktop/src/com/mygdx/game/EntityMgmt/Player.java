package com.mygdx.game.EntityMgmt;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity implements PlayerManagement{
    private float speed;
    private Animation<TextureRegion> walkAnimation;
    private float stateTime;

    public Player(){
        stateTime = 0f;
    }

    public Player(String path, float x, float y, float speed){
        super(path, x, y);
        this.speed = speed;
        Texture spriteSheet = new Texture("Player.png");
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 10, spriteSheet.getHeight() / 2);
        TextureRegion[] frames = new TextureRegion[9]; // Assuming 10 frames in each row
        System.arraycopy(tmp[0], 1, frames, 0, 9); // Assuming the first row contains the frames for the walk animation
        walkAnimation = new Animation<>(0.5f, frames);
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
        return new Player("Player.png", initialX, initialY, playerSpeed);
    }
    @Override
    public void draw(SpriteBatch batch) {
        // Get the current frame of the animation
        stateTime += Gdx.graphics.getDeltaTime(); // Update the state time
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);

        // Draw the current frame
        batch.draw(currentFrame, getX(), getY());
    }

}
