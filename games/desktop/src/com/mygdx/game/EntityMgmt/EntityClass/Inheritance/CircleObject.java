package com.mygdx.game.EntityMgmt.EntityClass.Inheritance;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Input;
import com.mygdx.game.EntityMgmt.EntityClass.Entity;

public class CircleObject extends Entity {
    private float radius;
    private Color colour;

    public CircleObject(float radius, Color colour, float x, float y, float speed)
    {
        super(x, y, speed);
        this.radius = radius;
        this.colour = colour;
    }

    @Override
    public void draw(ShapeRenderer shape) {
        shape.setColor(colour);
        shape.circle(super.getX(), super.getY(), radius);
    }

    @Override
    public void update() {
        System.out.println("In circle of radius " + radius + " at " + getX() + "," + getY() + " position");
    }

    @Override
    public void moveAIControlled() {
        // filler
    }

    @Override
    public void moveUserControlled() {
        movement(this, this::setX, this::getX, Input.Keys.DOWN, Input.Keys.UP);
    }

    public float getRadius() {
        return radius;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }
    public Color getColour() {
        return colour;
    }
    public void setColour(Color colour) {
        this.colour = colour;
    }
}
