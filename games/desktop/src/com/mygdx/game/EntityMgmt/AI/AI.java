package com.mygdx.game.EntityMgmt.AI;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.GameObjectType;
import com.badlogic.gdx.math.Vector2;

public class AI extends Entity {
    private float speed;
    private Animation<TextureRegion> walkAnimationForward;
    private List<Vector2> walkingLocations;
    private float stateTime;

    public AI(){}

    public AI(String path, float x, float y, float speed){
        super(path, x, y);
        this.speed = speed;
        walkingLocations = new ArrayList<>();
        initialiseAI();
    }

    private void initialiseAI() {
        Texture spriteSheet = new Texture(getPath());
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 2, spriteSheet.getHeight());
        TextureRegion[] frames = new TextureRegion[2]; // Frames for animation
        System.arraycopy(tmp[0], 0, frames, 0, 2); // Copy both frames

        // Assign frames to animation
        walkAnimationForward = new Animation<>(0.1f, frames);
    }


    protected float getSpeed() {
        return speed;
    }
    protected void setSpeed(float speed) {
        this.speed = speed;
    }
    protected List<Vector2> getWalkingLocations() {
        return walkingLocations;
    }
    protected void setWalkingLocations(List<Vector2> walkingLocations) {
        this.walkingLocations = walkingLocations;
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
        return GameObjectType.AI;
    }
    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = null;

            currentFrame = walkAnimationForward.getKeyFrame(stateTime, true);

        batch.draw(currentFrame, getX(), getY());
    }
    @Override
    public void dispose() {
        walkAnimationForward = null;
    }
}