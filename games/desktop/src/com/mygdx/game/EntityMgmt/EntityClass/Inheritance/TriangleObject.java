package com.mygdx.game.EntityMgmt.EntityClass.Inheritance;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.EntityMgmt.EntityClass.Entity;

public class TriangleObject extends Entity {
    private float p1x;
    private float p1y;
    private float p2x;
    private float p2y;
    private float p3x;
    private float p3y;
    private Color colour;

    public TriangleObject(float p1x, float p1y, float p2x, float p2y, float p3x, float p3y, Color colour, float x, float y, float speed)
    {
        super(x, y, speed);
        this.p1x = p1x;
        this.p1y = p1y;
        this.p2x = p2x;
        this.p2y = p2y;
        this.p3x = p3x;
        this.p3y = p3y;
        this.colour = colour;
    }

    @Override
    public void draw(ShapeRenderer shape) {
        shape.setColor(colour);
        shape.triangle(p1x, p1y, p2x, p2y, p3x, p3y);
    }

    @Override
    public void update() {
        System.out.println("In triangle at " + getX() + "," + getY() + " position");
    }

    @Override
    public void moveAIControlled() {
    }

    @Override
    public void moveUserControlled() {
        movement(this, this::setP1x, this::getP1x, Input.Keys.A, Input.Keys.D);
        movement(this, this::setP2x, this::getP2x, Input.Keys.A, Input.Keys.D);
        movement(this, this::setP3x, this::getP3x, Input.Keys.A, Input.Keys.D);
    }
    
    
    public float getP1x() {
        return p1x;
    }
    public void setP1x(float p1x) {
        this.p1x = p1x;
    }

    public float getP1y() {
        return p1y;
    }
    public void setP1y(float p1y) {
        this.p1y = p1y;
    }

    public float getP2x() {
        return p2x;
    }
    public void setP2x(float p2x) {
        this.p2x = p2x;
    }

    public float getP2y() {
        return p2y;
    }
    public void setP2y(float p2y) {
        this.p2y = p2y;
    }

    public float getP3x() {
        return p3x;
    }
    public void setP3x(float p3x) {
        this.p3x = p3x;
    }

    public float getP3y() {
        return p3y;
    }
    public void setP3y(float p3y) {
        this.p3y = p3y;
    }

    public Color getColour() {
        return colour;
    }
    public void setColour(Color colour) {
        this.colour = colour;
    }
}
