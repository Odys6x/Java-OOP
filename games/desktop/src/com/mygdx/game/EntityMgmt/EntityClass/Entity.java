package com.mygdx.game.EntityMgmt.EntityClass;

import java.util.function.Consumer;
import java.util.function.Supplier;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.EntityMgmt.EntityClass.Interface.IMovable;

public abstract class Entity implements IMovable{
    private float x,y;
    private float speed;
    private Color color;
    public Entity(){

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

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public void movement() {}

    public void draw(ShapeRenderer shape){};

    public void draw(SpriteBatch batch){};

    public abstract void update();
    public void applyKnockback(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }
    
    
}

