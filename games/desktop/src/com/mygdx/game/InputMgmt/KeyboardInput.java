package com.mygdx.game.InputMgmt;

import com.mygdx.game.BehaviourMgmt.Behaviour;
import com.mygdx.game.BehaviourMgmt.BehaviourManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.EntityMgmt.EntityManager;

public class KeyboardInput implements InputHandler {
	private EntityManager entityManager;
    private BehaviourManager behaviour;
	
	public KeyboardInput() {
        
    }
    public KeyboardInput(EntityManager entityManager, BehaviourManager behaviour) {
        this.entityManager = entityManager;
        this.behaviour = behaviour;
    }
	
	public void handleInput(InputCommand command) {
		if (entityManager.getUserControlledEntity() != null) {
            float speed = entityManager.getSpeed(); // Get the speed of the user-controlled entity

            // Calculate delta values based on InputCommand and delta time
            float deltaX = 0, deltaY = 0;
            boolean ePressed = false;
            switch (command) {
                case MOVE_LEFT:
                    deltaX -= speed * Gdx.graphics.getDeltaTime();
                    break;
                case MOVE_RIGHT:
                    deltaX += speed * Gdx.graphics.getDeltaTime();
                    break;
                case INTERACT:
                	ePressed = true;
                	break;
                default:
                    break;
            }
            // Update entity position using EntityManager
            entityManager.updateEntityPosition(entityManager.getUserControlledEntity(), deltaX, deltaY);
            behaviour.setInteractPressed(ePressed);
        }
    }


}

