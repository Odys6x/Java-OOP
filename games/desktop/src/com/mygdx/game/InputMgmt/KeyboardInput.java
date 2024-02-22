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
	
	public void handleInput() {
        if (entityManager.getUserControlledEntity() != null) {
            float speed = entityManager.getSpeed(); // Get the speed of the user-controlled entity

            // Calculate delta values based on keyboard input and delta time
            float deltaX = 0, deltaY = 0;
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))    {
                deltaX -= speed * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                deltaX += speed * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                deltaY += speed * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                deltaY -= speed * Gdx.graphics.getDeltaTime();
            }
            // Update entity position using EntityManager
            entityManager.updateEntityPosition(entityManager.getUserControlledEntity(), deltaX, deltaY);
        }
    }


}
