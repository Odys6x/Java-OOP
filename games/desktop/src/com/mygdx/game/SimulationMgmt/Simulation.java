package com.mygdx.game.SimulationMgmt;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CollisionMgmt.CollisionManager;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.SceneMgmt.SceneScreen;
import com.mygdx.game.InputMgmt.InputManager;
import com.mygdx.game.InputMgmt.KeyboardInput;


public class Simulation {

    private SceneScreen Scenes; // implementing in future
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private EntityManager entities;
    private InputManager inputManager;
    private CollisionManager collisionManager;

    public void initialise() {
        // start making the objects in the game, from create
    	
        entities = new EntityManager();
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        collisionManager = new CollisionManager(entities);
        KeyboardInput keyboardInput = new KeyboardInput(entities);
        inputManager = new InputManager(entities, keyboardInput);

        entities.createText(1);
        entities.createText(2);
        entities.createShape(1);
        entities.createShape(2);
    }

    public void update() {
        //render
        ScreenUtils.clear(0, 0, 0.2f, 1);
        entities.movement();
        entities.draw(batch,shape);
        inputManager.update();
        collisionManager.update();
    }
    public void end() {

        batch.dispose();
        shape.dispose();
        entities.dispose();
    }
}