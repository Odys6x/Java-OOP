package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


class CircleObject extends Entity implements CreateShape{
    private float radius;

    CircleObject()
    {

    }

    CircleObject(float radius, Color colour, float x, float y, float speed)
    {
        super(x, y, colour ,speed);
        this.radius = radius;
    }

    @Override
    void draw(ShapeRenderer shape) {
        shape.setColor(getColor());
        shape.circle(super.getX(), super.getY(), radius);
    }

    void update() {
        System.out.println("In circle of radius " + radius + " at " + getX() + "," + getY() + " position");
    }


    @Override
    public CircleObject createShape(){
        return new CircleObject(50, Color.BLUE, 300, 300, 100);
    }

    float getRadius() {
        return radius;
    }
    void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public float getWidth() {
        return 2 * radius;
    }
    
    // Implementation of getHeight() for CircleObject
    @Override
    public float getHeight() {
        return 2 * radius;
    }
}