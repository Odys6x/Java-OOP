package com.mygdx.game.EntityMgmt.EntityClass.Inheritance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.EntityMgmt.EntityClass.Entity;

import java.util.Vector;

public class TriangleObject extends Entity {
    Vector2 point1 = new Vector2();
    Vector2 point2= new Vector2();
    Vector2 point3= new Vector2();

    public TriangleObject()
    {

    }

    public TriangleObject(Color colour, float x, float y, float speed)
    {
        super(x, y,colour, speed);
    }

    @Override
    public void draw(ShapeRenderer shape) {
        point1 = new Vector2(-25+getX(),-25+getY());
        point2 = new Vector2(25+getX(),-25+getY());
        point3 = new Vector2(0+getX(),25+getY());
        shape.setColor(getColor());
        shape.triangle(point1.x,point1.y,point2.x,point2.y,point3.x,point3.y);
    }
    public void update() {
        System.out.println("In triangle at " + getX() + "," + getY() + " position");
    }

    @Override
    public void movement() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            setX(getX() - getSpeed() * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            setX(getX() + getSpeed() * Gdx.graphics.getDeltaTime());

        }
    }

    public void moveAIControlled() {

    }

    public void moveUserControlled() {

    }


}
