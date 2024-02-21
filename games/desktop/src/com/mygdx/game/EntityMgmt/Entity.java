package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

abstract class Entity implements IMovable{
    private float x,y;
    private float speed;
    private Color color;
    private boolean userControlled;
    Entity(){

    }
    Entity(float x, float y, Color color,float speed) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.speed = speed;
    }

    float getX() {
        return x;
    }
    void setX(float x) {
        this.x = x;
    }

    float getY() {
        return y;
    }
    void setY(float y) {
        this.y = y;
    }

    float getSpeed() {
        return speed;
    }
    void setSpeed(float speed) {
        this.speed = speed;
    }

    Color getColor() {
        return color;
    }
    void setColor(Color color) {
        this.color = color;
    }

    void movement() {}

    void draw(ShapeRenderer shape){};

    void draw(SpriteBatch batch){};

    abstract void update();
    public boolean isUserControlled() {
        return userControlled;
    }
    public void setUserControlled(boolean userControlled) {
        this.userControlled = userControlled;
    }

    abstract float getWidth();
    
    abstract float getHeight();
}
