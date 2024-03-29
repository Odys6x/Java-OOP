package com.mygdx.game.EntityMgmt.Furniture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.GameObjectType;

public class Furniture extends Entity {
    private String type;
    private Texture texture;

    protected Furniture(String type, String path, float x, float y) {
        super(path, x, y);
        this.type = type;
        this.texture = new Texture(path);
    }

    @Override
    public float getX() {
        return super.getX();
    }

    @Override
    public void setX(float x) {
        super.setX(x);
    }

    @Override
    public float getY() {
        return super.getY();
    }

    @Override
    public void setY(float y) {
        super.setY(y);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }


    @Override
    public GameObjectType getType() {
        return GameObjectType.FURNITURE;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

}
