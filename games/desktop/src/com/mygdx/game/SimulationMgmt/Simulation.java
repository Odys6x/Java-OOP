package com.mygdx.game.SimulationMgmt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CollisionMgmt.CollisionManager;
import com.mygdx.game.EntityMgmt.EntityClass.Entity;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.SceneMgmt.sceneScreen;
import com.mygdx.game.CollisionMgmt.Collision;
import com.mygdx.game.EntityMgmt.EntityClass.Inheritance.CircleObject;
import com.mygdx.game.EntityMgmt.EntityClass.Inheritance.TexturedObject;
import com.mygdx.game.EntityMgmt.EntityClass.Inheritance.TriangleObject;

public class Simulation {

    private boolean gameRunning; // is game running or not
    private sceneScreen Scenes; // yet to implement, will see in the future
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private EntityManager entities;
    private CollisionManager collisionManager;
    public Simulation() {
        gameRunning = false;
    }

    Entity Circle;
    Entity Triangle;

    public void initialise() {
        // start making the objects in the game, from create

        entities = new EntityManager();
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        collisionManager = new CollisionManager();
        for (int i = 0; i < 10; i++) {
            float initialX = (float) (Math.random() * Gdx.graphics.getWidth());
            entities.addEntity(new TexturedObject("droplet.png", initialX, 400, 400,false));
        }

        TexturedObject bucket = new TexturedObject("bucket.png", 300, 0, 200,true);
        entities.addEntity(bucket);

        Circle = new CircleObject(50, Color.BLUE, 500, 150, 0);
        Triangle = new TriangleObject(Color.RED, 100, 100, 200);

        entities.addEntity(Circle);
        entities.addEntity(Triangle);
        gameRunning = true;

    }

    public void update() {
        //render part
        ScreenUtils.clear(0, 0, 0.2f, 1);
        entities.movement();
        entities.draw(batch,shape);
        entities.update();

        // collision
        for (int i = 0; i < entities.getEntityList().size(); i++) {
            for (int j = i + 1; j < entities.getEntityList().size(); j++) {
                Entity entity1 = entities.getEntityList().get(i);
                Entity entity2 = entities.getEntityList().get(j);
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
        for (Entity entity : entities.getEntityList()) {
            if (entity instanceof TexturedObject) {
                ((TexturedObject) entity).dispose();
            }
        }
    }
}
