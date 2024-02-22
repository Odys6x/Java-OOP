package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Null;
import org.w3c.dom.Text;

class TexturedObject extends Entity{
    private Texture tex;
    private String path;


    TexturedObject(String path, float x, float y, float speed, Boolean userControlled) {
        super(x, y, Color.BLACK, speed,userControlled);
        this.tex = new Texture(path);
        this.path = path;
    }

    @Override
    float getWidth() {
        return tex.getWidth();
    }

    @Override
    float getHeight() {
        return tex.getHeight();
    }

    @Override
    void draw(ShapeRenderer shape) {
    }


    public void moveAIControlled() {
        float newY = getY() - getSpeed() * Gdx.graphics.getDeltaTime();
        setY(newY);

        if (getY() <= 0) {
            float newSpeed = (float)(Math.random() * getSpeed()) + 50;
            float newX = (float)(Math.random() * Gdx.graphics.getWidth());
            setY(Gdx.graphics.getHeight());
            setX(newX);
            setSpeed(newSpeed);
        }
    }

    public void moveUserControlled() {
    }


    void update() {
        System.out.println("TexturedObject with texture at " + path + " is at position (" + getX() + ", " + getY() + ")");
    }

    @Override
    void movement(){
        moveAIControlled();
    }

    @Override
    void draw(SpriteBatch batch) {
        batch.draw(getTexture(), getX(), getY(), getTexture().getWidth(),getTexture().getHeight());
    }
    Texture getTexture() {
        return tex;
    }

    TexturedObject CreateDrop(){
        return new TexturedObject("bucket.png",0,500,100,false);}
    }


    void setTexture(Texture tex) {
        this.tex = tex;
    }

    String getPath() {
        return path;
    }
    void setPath(String path) {
        this.path = path;
    }

    void dispose() {
        tex.dispose();
    }

    
}
