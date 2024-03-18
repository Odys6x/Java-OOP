package com.mygdx.game.InputMgmt;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.EntityMgmt.EntityManager;

public class InputManager {
	private EntityManager entityManager;
    private InputHandler keyboardInput;
    private InputHandler mouseInput;

    public InputManager(EntityManager entityManager, InputHandler keyboardInput, InputHandler mouseInput) {
    	this.entityManager = entityManager;
        this.keyboardInput = keyboardInput;
        this.mouseInput = mouseInput;
    }

    public void update() {
    	// Get user input from input handlers and control user-controlled entities
        if (entityManager.getUserControlledEntity() != null) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                keyboardInput.handleInput(InputCommand.MOVE_LEFT);
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                keyboardInput.handleInput(InputCommand.MOVE_RIGHT);
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                keyboardInput.handleInput(InputCommand.MOVE_UP);
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                keyboardInput.handleInput(InputCommand.MOVE_DOWN);
            } else if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                mouseInput.handleInput(InputCommand.MOUSE_LEFT_CLICK);
            } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                mouseInput.handleInput(InputCommand.MOUSE_RIGHT_CLICK);
            } else {
                // If no input, handle as NONE
                keyboardInput.handleInput(InputCommand.NONE);
                mouseInput.handleInput(InputCommand.NONE);
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
        return pressedKeys;
    }
}

