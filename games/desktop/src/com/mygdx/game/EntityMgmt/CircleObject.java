package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Input;

class CircleObject extends Entity {
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
    void movement() {

    }

    public void moveAIControlled() {
    }

    public void moveUserControlled() {
    }

    CircleObject createCircle(){
        return new CircleObject(50, Color.BLUE, 300, 300, 100);
    }

    float getRadius() {
        return radius;
    }
    void setRadius(float radius) {
        this.radius = radius;
    }
}