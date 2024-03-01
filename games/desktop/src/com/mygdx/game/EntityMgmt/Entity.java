package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Entity{
    private float x,y;
    private float speed;
    private Color color;
    private boolean userControlled;
    public Entity(){

    }
    public Entity(float x, float y, Color color,float speed,boolean userControlled) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.speed = speed;
        this.userControlled = userControlled;
    }
    public Entity(float x, float y, Color color,float speed) {
        this.x = x;
        this.y = y;
        this.color = color;
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

    Color getColor() {
        return color;
    }
    void setColor(Color color) {
        this.color = color;
    }

    void draw(ShapeRenderer shape){};

    void draw(SpriteBatch batch){};

    abstract void update();
    public boolean isUserControlled() {
        return userControlled;
    }
    void setUserControlled(boolean userControlled) {
        this.userControlled = userControlled;
    }
    void movement() {}

    public abstract float getWidth();

    public abstract float getHeight();
}