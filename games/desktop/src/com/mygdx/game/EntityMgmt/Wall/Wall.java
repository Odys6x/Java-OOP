package com.mygdx.game.EntityMgmt.Wall;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.GameObjectType;


public class Wall extends Entity {
    private Texture texture;

    public Wall(String path, float x, float y) {
        super(path, x, y);
        this.texture = new Texture(path);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, getX(), getY());
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.WALL;
    }

}
