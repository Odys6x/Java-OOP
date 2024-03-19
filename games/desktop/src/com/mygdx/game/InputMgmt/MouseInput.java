package com.mygdx.game.InputMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;

public class MouseInput implements InputHandler {
    private EntityManager entityManager;

    public MouseInput(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void handleInput(InputCommand command) {
        // Handle mouse input commands here
    }
}
