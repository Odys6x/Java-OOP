package com.mygdx.game.EntityMgmt.Furniture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.GameObjectType;
import com.mygdx.game.EntityMgmt.Player.Player;

public class Furniture extends Entity {
    private String type;
    private Texture texture;

    public Furniture(String type, String path, float x, float y) {
        super(path, x, y);
        this.type = type;
        this.texture = new Texture(path);
    }


    public void draw(SpriteBatch batch) {
        batch.draw(texture, getX(), getY());
    }


    @Override
    public GameObjectType getType() {
        return GameObjectType.FURNITURE;
    }

}
