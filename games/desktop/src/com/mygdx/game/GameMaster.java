package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color; 
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.EntityMgmt.CircleObject;
import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.TexturedObject;
import com.mygdx.game.EntityMgmt.TriangleObject;

import java.util.ArrayList;
import java.util.List;

public class GameMaster extends ApplicationAdapter {
    private List<Entity> entities;
    private SpriteBatch batch;
    private ShapeRenderer shape;

    @Override
    public void create() {
        entities = new ArrayList<>();
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
    
        for (int i = 0; i < 10; i++) {
            float initialX = (float) (Math.random() * Gdx.graphics.getWidth());
            entities.add(new TexturedObject("droplet.png", initialX, 400, 100));
        }
    
        TexturedObject bucket = new TexturedObject("bucket.png", 300, 0, 0);
        bucket.setUserControlled(true); // Make the bucket user-controlled
        entities.add(bucket);

        entities.add(new CircleObject(50, Color.BLUE, 500, 150, 0));
        entities.add(new TriangleObject(50, 20, 150, 20, 100, 100, Color.RED, 50, 50, 0));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        batch.begin();
        for (Entity entity : entities) {
            if (entity instanceof TexturedObject) {
                TexturedObject texturedEntity = (TexturedObject) entity;
                // Check if the entity is user-controlled 
                if (texturedEntity.isUserControlled()) {
                    texturedEntity.moveUserControlled();
                } else {
                    // sssume all other TexturedObjects are droplets and make them AI-controlled
                    texturedEntity.moveAIControlled();
                }
                texturedEntity.drawTexture(batch);
            }
        }
        batch.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        for (Entity entity : entities) {
            if (entity instanceof CircleObject || entity instanceof TriangleObject) {
                entity.draw(shape);
            }
        }
        shape.end();

        for (Entity entity : entities) {
            entity.moveUserControlled();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        shape.dispose();
        for (Entity entity : entities) {
            if (entity instanceof TexturedObject) {
                ((TexturedObject) entity).dispose();
            }
        }
    }
}
