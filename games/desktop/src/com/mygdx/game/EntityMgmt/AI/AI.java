package com.mygdx.game.EntityMgmt.AI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.GameObjectType;

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

    @Override
    public GameObjectType getType() {
        return GameObjectType.AI;
    }
    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(getTexture(), getX(), getY(), getWidth(),getHeight());
    }
}
