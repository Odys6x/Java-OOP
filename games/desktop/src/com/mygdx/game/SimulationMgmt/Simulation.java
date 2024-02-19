package com.mygdx.game.SimulationMgmt;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Single;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CollisionMgmt.CollisionManager;
import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.SceneMgmt.SceneScreen;
import com.mygdx.game.CollisionMgmt.Collision;
import com.mygdx.game.EntityMgmt.CircleObject;
import com.mygdx.game.EntityMgmt.TexturedObject;
import com.mygdx.game.EntityMgmt.TriangleObject;

public class Simulation {

    private boolean gameRunning;
    private SceneScreen Scenes; // yet to implement, will see in the future
    private List<Entity> entities;
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private CollisionManager collisionManager;

    public Simulation() {
        gameRunning = false;
    }

    public void initialise() {
        // start making the objects in the game, from create

        entities = new ArrayList<>();
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        collisionManager = new CollisionManager();
        for (int i = 0; i < 10; i++) {
            float initialX = (float) (Math.random() * Gdx.graphics.getWidth());
            entities.add(new TexturedObject("droplet.png", initialX, 400, 100));
        }

        TexturedObject bucket = new TexturedObject("bucket.png", 300, 0, 0);
        bucket.setUserControlled(true); // Make the bucket user-controlled
        entities.add(bucket);

        entities.add(new CircleObject(50, Color.BLUE, 500, 150, 0));
        entities.add(new TriangleObject(50, 20, 150, 20, 100, 100, Color.RED, 50, 50, 0));
        gameRunning = true;

    }

    public void update() {
        //render part
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

        // collision
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                Entity entity1 = entities.get(i);
                Entity entity2 = entities.get(j);
                Collision collision = new Collision(entity1, entity2);
                if (collision.checkCollision()) {
                    collisionManager.applyCollisionEffects(collision);
                }
            }
        }

    }

    public void end() {
        //this was from the dispose part of gamemaster 
        gameRunning = false;
        batch.dispose();
        shape.dispose();
        for (Entity entity : entities) {
            if (entity instanceof TexturedObject) {
                ((TexturedObject) entity).dispose();
            }
        }
    }
}
