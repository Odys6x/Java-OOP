package com.mygdx.game.SimulationMgmt;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.BehaviourMgmt.AIBehaviour;
import com.mygdx.game.BehaviourMgmt.PlayerBehaviour;
import com.mygdx.game.BehaviourMgmt.BehaviourManager;
import com.mygdx.game.CollisionMgmt.CollisionManager;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.EntityMgmt.Player.Player;
import com.mygdx.game.MapMgmt.Map;
import com.mygdx.game.SceneMgmt.SceneScreen;
import com.mygdx.game.InputMgmt.InputManager;
import com.mygdx.game.InputMgmt.KeyboardInput;
import com.mygdx.game.ScoreMgmt.Score;

public class Simulation {

    private BehaviourManager behaviourManager;
    private SpriteBatch batch;
    private EntityManager entities;
    private int level;
    private int currentLevel;
    private Map map;
    private InputManager inputManager;
    private CollisionManager collisionManager;

    private Score scoreManager;
    private long startTime;
    // for any activity to run within a loop, where condition is game running or not
    private boolean isrunning;
    private AIBehaviour aiBehaviour;
    private PlayerBehaviour playerBehaviour;

    public void initialise() {
        // start making the objects in the game, from create

        entities = new EntityManager();
        batch = new SpriteBatch();
        scoreManager = new Score();
        map = new Map(level);
        collisionManager = new CollisionManager(entities);

        KeyboardInput keyboardInput = new KeyboardInput(entities);
        inputManager = new InputManager(entities, keyboardInput);
        behaviourManager = new BehaviourManager(entities, inputManager,scoreManager);
        map.LoadMap(entities);
        map.LoadPlayers(entities, 1);

        isrunning = true;
        startTime = TimeUtils.nanoTime(); // Initialize the start time

        aiBehaviour = new AIBehaviour(entities);
        Player player = (Player) entities.getUserControlledEntity(); // Cast might be necessary
        playerBehaviour = new PlayerBehaviour(player, inputManager);

    }
    public Simulation(int level) {
        this.level = level;
        initialise(); // Consider removing from here if you need more control over when it's called
    }
    public float getScore() {
        return this.scoreManager.getScore();
    }
    public void update() {
        // render
        entities.draw(batch);
        inputManager.update();
        java.util.List<Integer> pressedKeys = inputManager.getPressedKeys();
        entities.updatePlayerAnimations(pressedKeys);
        collisionManager.update();
        behaviourManager.updateBehaviours(pressedKeys);
        aiBehaviour.updateAIBehaviour2();
        playerBehaviour.update();
        if (getScore() >= 0 && currentLevel != 2) {
            currentLevel = 2; // Update the current level to 2
            map = new Map(currentLevel); // Load the new level configuration
            // Additional logic to re-initialize or transition to the new level
        } else if (currentLevel != 1) { // Assuming you might have more than 2 levels in the future
            currentLevel = 1; // Default to level 1 configuration
            map = new Map(currentLevel);
            // Re-initialize as needed for level 1
        }
 
        //aiControllerManager.moveAIControlledEntities();
        
    }

    public void end() {
        // dispose what was created
        isrunning = false;
        batch.dispose();
        entities.dispose();
        inputManager = null;
        behaviourManager = null;
        map = null;
        collisionManager = null;
        }

    public long getTime() {
        // Calculate elapsed time in milliseconds le
        // time stops when the simulation is disposed in gamemaster
        // to print time, add
        // System.err.println(Simulation.getTime() + "seconds le.");
        // to simulation update
        if (isrunning) {
            long elapsedTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime() - startTime);
            return elapsedTime;
        } else {
            return 0;
        }
    }
}