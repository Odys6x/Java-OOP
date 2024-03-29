package com.mygdx.game.InputMgmt;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.EntityMgmt.EntityManager;

public class InputManager {
	private EntityManager entityManager;
    private InputHandler keyboardInput;
    private List<Integer> pressedKeys = new ArrayList<>();

    public InputManager(EntityManager entityManager, InputHandler keyboardInput) {
    	this.entityManager = entityManager;
        this.keyboardInput = keyboardInput;
    }

    public void update() {
    	// Get user input from input handlers and control user-controlled entities
        if (entityManager.getUserControlledEntity() != null) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                keyboardInput.handleInput(InputCommand.MOVE_LEFT);
                
            } if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                keyboardInput.handleInput(InputCommand.MOVE_RIGHT);
                
            } if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                keyboardInput.handleInput(InputCommand.INTERACT);
                
            } if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                keyboardInput.handleInput(InputCommand.MOVE_UP);
                
            } if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            	keyboardInput.handleInput(InputCommand.MOVE_DOWN);
             
               
        }
      }
        
    }
    public List<Integer> getPressedKeys() {
        List<Integer> pressedKeys = new ArrayList<>();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            pressedKeys.add(Input.Keys.LEFT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            pressedKeys.add(Input.Keys.RIGHT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            pressedKeys.add(Input.Keys.UP);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            pressedKeys.add(Input.Keys.DOWN);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            pressedKeys.add(Input.Keys.E);
        }
        return pressedKeys;
    }
}

