package com.mygdx.game.InputMgmt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.EntityMgmt.EntityManager;

public class KeyboardInput implements InputHandler {
	private EntityManager entityManager;
	
	public KeyboardInput() {
        
    }
    public KeyboardInput(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	
	public void handleInput(InputCommand command) {
		if (entityManager.getUserControlledEntity() != null) {
            float speed = entityManager.getSpeed(); // Get the speed of the user-controlled entity

            // Calculate delta values based on InputCommand and delta time
            float deltaX = 0, deltaY = 0;
            switch (command) {
                case MOVE_LEFT:
                    deltaX -= speed * Gdx.graphics.getDeltaTime();
                    break;
                case MOVE_RIGHT:
                    deltaX += speed * Gdx.graphics.getDeltaTime();
                    break;
                default:
                    break;
            }
            // Update entity position using EntityManager
            entityManager.updateEntityPosition(entityManager.getUserControlledEntity(), deltaX, deltaY);
        }
    }


}

