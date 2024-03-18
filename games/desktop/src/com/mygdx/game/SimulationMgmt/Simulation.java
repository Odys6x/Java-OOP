package com.mygdx.game.SimulationMgmt;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.AiControllerMgmt.AIControllerManager;
import com.mygdx.game.CollisionMgmt.CollisionManager;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.SceneMgmt.SceneScreen;
import com.mygdx.game.InputMgmt.InputManager;
import com.mygdx.game.InputMgmt.KeyboardInput;


public class Simulation {

    private SceneScreen Scenes; // implementing in future
    private SpriteBatch batch;
    private EntityManager entities;
    private InputManager inputManager;
    private CollisionManager collisionManager;
    private AIControllerManager aiControllerManager;
    private long startTime; 
    //for any activity to run within a loop, where condition is game running or not
    private boolean isrunning;

    public void initialise() {
        // start making the objects in the game, from create
        entities = new EntityManager();
        batch = new SpriteBatch();
        collisionManager = new CollisionManager(entities);
        KeyboardInput keyboardInput = new KeyboardInput(entities);
        inputManager = new InputManager(entities, keyboardInput);
        aiControllerManager = new AIControllerManager(entities);
        entities.createPlayer();
        entities.createAI();
        System.out.println(entities.getEntityList());
        isrunning = true;
        startTime = TimeUtils.nanoTime(); // Initialize the start time
    }
    public void update() {
        //render
        ScreenUtils.clear(0, 0, 0.2f, 1);
        entities.draw(batch);
        inputManager.update();
        collisionManager.update();
        aiControllerManager.moveAIControlledEntities();

    }
    public void end() {
        //dispose what was created
        isrunning = false;
        batch.dispose();
        entities.dispose();
    }
    public long getTime(){
        // Calculate elapsed time in milliseconds le
        //time stops when the simulation is disposed in gamemaster
        // to print time, add         
        //System.err.println(Simulation.getTime() + "seconds le.");
        // to simulation update
        if (isrunning){
            long elapsedTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime() - startTime); 
            return elapsedTime;
        }
        else{
            return 0;
        }
    }
}