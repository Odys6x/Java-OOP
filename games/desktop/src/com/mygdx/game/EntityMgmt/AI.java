package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AI extends Entity {
    private float speed;

    public AI(){}

    public AI(String path, float x, float y, float speed){
        super(path, x, y);
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public AI createAI() {
        return new AI("droplet.png", 300, 400, 200);
    }
    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(getTexture(), getX(), getY(), getWidth(),getHeight());
    }
}
