package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity  implements GameObject {
    private float x,y;

    private Texture texture;

    private String path;

    public Entity(){

    }
    public Entity(String path, float x, float y) {
        this.x = x;
        this.y = y;
        this.path = path;
        this.texture = new Texture(path);
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

    public void draw(SpriteBatch batch){}

    void dispose() {
        texture.dispose();
    }

    public float getWidth(){
        return texture.getWidth();
    }

    public float getHeight(){
        return texture.getHeight();
    }
    Texture getTexture() {
        return texture;
    }

}