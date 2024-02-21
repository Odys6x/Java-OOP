package com.mygdx.game.InputMgmt;

import com.mygdx.game.EntityMgmt.EntityClass.Entity;
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
        Entity userControlledEntity = entityManager.getUserControlledEntity();
        if (userControlledEntity != null) {
            inputHandler.handleInput();
        }
    }
}
