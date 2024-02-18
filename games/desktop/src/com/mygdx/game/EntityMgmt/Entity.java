package com.mygdx.game.EntityMgmt;

import java.util.function.Consumer;
import java.util.function.Supplier;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Entity implements IMovable{
    private float x;
    private float y;
    private float speed;

    public abstract void draw(ShapeRenderer shape);

    public abstract void update();

    public abstract void moveAIControlled();

    public abstract void moveUserControlled();

    public Entity(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void movement(Object instance, Consumer<Float> setMethod, Supplier<Float> getMethod, int leftKey, int rightKey) {
        float delta = Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(leftKey)) {
            setMethod.accept(getMethod.get() - 200 * delta);
        }
        if (Gdx.input.isKeyPressed(rightKey)) {
            setMethod.accept(getMethod.get() + 200 * delta);
        }
    }
    
}

