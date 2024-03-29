package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Entity implements GameObject {
    protected float x,y;

    protected Texture texture;

    protected String path;

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

    public abstract void draw(SpriteBatch batch);

    public abstract void dispose();

    public float getWidth(){
        return texture.getWidth();
    }

    public float getHeight(){
        return texture.getHeight();
    }
    public Texture getTexture() {
        return texture;
    }

    public String getPath(){ return path;}

}