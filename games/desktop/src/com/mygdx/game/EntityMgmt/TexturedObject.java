package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TexturedObject extends Entity {
    private Texture tex;
    private String path;
    private boolean isUserControlled = false;

    public TexturedObject(String path, float x, float y, float speed) {
        super(x, y, speed);
        this.tex = new Texture(path);
        this.path = path; 
    }

    @Override
    public void draw(ShapeRenderer shape) {
        // filler
    }

    public boolean isUserControlled() {
        return isUserControlled;
    }

    public void setUserControlled(boolean isUserControlled) {
        this.isUserControlled = isUserControlled;
    }

    @Override
public void moveAIControlled() {
    float newY = getY() - getSpeed() * Gdx.graphics.getDeltaTime(); 
    setY(newY);

    if (getY() <= 0) {
        float newSpeed = (float)(Math.random() * 100) + 50; 
        float newX = (float)(Math.random() * Gdx.graphics.getWidth()); 
        setY(Gdx.graphics.getHeight()); 
        setX(newX);
        setSpeed(newSpeed); 
    }
}


    @Override
    public void moveUserControlled() {
        if (isUserControlled) {
            float delta = Gdx.graphics.getDeltaTime();
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                setX(getX() - 200 * delta);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                setX(getX() + 200 * delta);
            }
        }
    }

    public void update() {
        System.out.println("TexturedObject with texture at " + path + " is at position (" + getX() + ", " + getY() + ")");
    }

    public void drawTexture(SpriteBatch batch) {
        batch.draw(tex, getX(), getY());
    }
    public Texture getTexture() {
        return tex;
    }
    void setTexture(Texture tex) {
        this.tex = tex;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public void dispose() {
        tex.dispose();
    }
}

