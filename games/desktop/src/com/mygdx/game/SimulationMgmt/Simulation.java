package com.mygdx.game.SimulationMgmt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
// import com.mygdx.game.CollisionMgmt.CollisionManager;
// import com.mygdx.game.EntityMgmt.EntityClass.Entity;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.SceneMgmt.SceneScreen;
// import com.mygdx.game.CollisionMgmt.Collision;
import com.mygdx.game.InputMgmt.InputManager;
// import com.mygdx.game.EntityMgmt.EntityClass.Inheritance.CircleObject;
// import com.mygdx.game.EntityMgmt.EntityClass.Inheritance.TexturedObject;
// import com.mygdx.game.EntityMgmt.EntityClass.Inheritance.TriangleObject;
import com.mygdx.game.InputMgmt.KeyboardInput;
// import com.mygdx.game.SceneMgmt.sceneScreen;

import java.util.List;

public class Simulation {

    private boolean gameRunning;
    private SceneScreen Scenes; // yet to implement, will see in the future
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private EntityManager entities;
    private InputManager inputManager;
    // private CollisionManager collisionManager;
    public Simulation() {
        gameRunning = false;
    }

    public void initialise() {
        // start making the objects in the game, from create
    	
        entities = new EntityManager();
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        /*
        collisionManager = new CollisionManager();

         */
        KeyboardInput keyboardInput = new KeyboardInput(entities);
        inputManager = new InputManager(entities, keyboardInput);

        entities.createText(1);
        entities.createText(2);
        entities.createShape(1);
        entities.createShape(2);

        System.out.println(entities.getDimensions(2));

        gameRunning = true;

    }

    public void update() {
        //render part
        ScreenUtils.clear(0, 0, 0.2f, 1);
        entities.movement();
        entities.draw(batch,shape);
        inputManager.update();

        /*
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
         */

    }
    public void end() {
        //this was from the dispose part of gamemaster
        gameRunning = false;
        batch.dispose();
        shape.dispose();
        entities.dispose();
    }
}