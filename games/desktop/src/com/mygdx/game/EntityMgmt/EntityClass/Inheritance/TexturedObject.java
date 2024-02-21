package com.mygdx.game.EntityMgmt.EntityClass.Inheritance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.EntityMgmt.EntityClass.Entity;

public class TexturedObject extends Entity {
    private Texture tex;
    private String path;
    private boolean isUserControlled;

    public TexturedObject(String path, float x, float y, float speed, Boolean isUserControlled) {
        super(x, y, Color.BLACK, speed);
        this.tex = new Texture(path);
        this.path = path;
        this.isUserControlled = isUserControlled;
    }

    @Override
    public void draw(ShapeRenderer shape) {
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

    public boolean isUserControlled() {
        return isUserControlled;
    }

    public void setUserControlled(boolean isUserControlled) {
        this.isUserControlled = isUserControlled;
    }

    public void update() {
        System.out.println("TexturedObject with texture at " + path + " is at position (" + getX() + ", " + getY() + ")");
    }

    @Override
    public void movement(){
        if (isUserControlled){
            moveUserControlled();
        }
        else {
            moveAIControlled();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(getTexture(), getX(), getY(), getTexture().getWidth(),getTexture().getHeight());
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

	@Override
	public void moveUserControlled() {
		// TODO Auto-generated method stub
		
	}
}

