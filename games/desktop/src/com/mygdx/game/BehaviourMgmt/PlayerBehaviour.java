package com.mygdx.game.BehaviourMgmt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.EntityMgmt.Player.Player;
import com.mygdx.game.InputMgmt.InputManager;
import java.util.List;

public class PlayerBehaviour {

    private Player player;
    private InputManager inputManager;

    public PlayerBehaviour(Player player, InputManager inputManager) {
        this.player = player;
        this.inputManager = inputManager;
    }

    public void update() {
        // Explicitly specify the type of the variable
        List<Integer> pressedKeys = inputManager.getPressedKeys();

        float newX = player.getX();
        float newY = player.getY();

        //increase speed of player
        if (pressedKeys.contains(Keys.LEFT)) {
            newX -= player.getSpeed() * Gdx.graphics.getDeltaTime();
        } else if (pressedKeys.contains(Keys.RIGHT)) {
            newX += player.getSpeed() * Gdx.graphics.getDeltaTime();
        }

        if (pressedKeys.contains(Keys.DOWN)) {
            newY -= player.getSpeed() * Gdx.graphics.getDeltaTime();
        } else if (pressedKeys.contains(Keys.UP)) {
            newY += player.getSpeed() * Gdx.graphics.getDeltaTime();
        }

        // Enforce game boundaries
        newX = Math.max(0, Math.min(newX, Gdx.graphics.getWidth() - player.getWidth()));
        newY = Math.max(0, Math.min(newY, Gdx.graphics.getHeight() - player.getHeight()));

        // Update player position
        player.setX(newX);
        player.setY(newY);
    }
}
