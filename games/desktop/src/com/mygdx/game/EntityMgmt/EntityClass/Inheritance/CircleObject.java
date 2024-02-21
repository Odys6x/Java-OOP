package com.mygdx.game.EntityMgmt.EntityClass.Inheritance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Null;
import com.mygdx.game.EntityMgmt.EntityClass.Entity;

public class CircleObject extends Entity {
    private float radius;

    public CircleObject()
    {

    }

    public CircleObject(float radius, Color colour, float x, float y, float speed)
    {
        super(x, y, colour ,speed);
        this.radius = radius;
    }

    @Override
    public void draw(ShapeRenderer shape) {
        shape.setColor(getColor());
        shape.circle(super.getX(), super.getY(), radius);
    }

    public void update() {
        System.out.println("In circle of radius " + radius + " at " + getX() + "," + getY() + " position");
    }

    public void moveAIControlled() {
    }

    public void moveUserControlled() {
    }

    public float getRadius() {
        return radius;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }
}
