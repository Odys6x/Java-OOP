package com.mygdx.game.InputMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;

public class InputManager {
	private EntityManager entityManager;
    private InputHandler inputHandler;

    public InputManager(EntityManager entityManager, InputHandler inputHandler) {
        this.entityManager = entityManager;
        this.inputHandler = inputHandler;
    }

    public void update() {
        // Get user input from input handler and control user-controlled entities
        if (entityManager.getUserControlledEntity() != null) {
            inputHandler.handleInput();
        }
    }
}

