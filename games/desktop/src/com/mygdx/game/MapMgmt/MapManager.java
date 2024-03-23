package com.mygdx.game.MapMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;


import java.util.Random;

public class MapManager {

    public void LoadMap(EntityManager entityManager, int numWalls, int numAI, int numAppliances) {
        for (int i = 0; i < numAI; i++) {
            entityManager.createAI("AI", 500, 500);
        }

        for (int i = 0; i < numAppliances; i++) {
            createRandomAppliance(entityManager);
        }

        float x = 0; // Starting x-coordinate for the first row
        float y1 = 0; // Y-coordinate for the first row
        float y2 = 700; // Y-coordinate for the second row

        // Create the first row of walls
        for (int i = 0; i < numWalls; i++) {
            x += 500; // Increment x-coordinate to position walls horizontally
            entityManager.createWalls("WallH", x, y1);
        }

        // Reset x-coordinate for the second row
        x = 0;

        // Create the second row of walls
        for (int i = 0; i < numWalls; i++) {
            x += 500; // Increment x-coordinate to position walls horizontally
            entityManager.createWalls("WallH", x, y2);
        }
    }


    public void LoadPlayers(EntityManager entityManager, int numPlayers) {
        // Use the provided number of walls to create the specified number of walls
        for (int i = 0; i < numPlayers; i++) {
            entityManager.createPlayer("Player",300,0);
        }
    }
    private void createRandomAppliance(EntityManager entityManager) {
        Random random = new Random();
        int randomIndex = random.nextInt(2); // Randomly choose between 0 and 1
        String[] appliances = {"Chicken", "Microwave"};

        // Generate random x and y coordinates within a specified range
        int randomX = random.nextInt(700); // MAX_X is the maximum value for x
        int randomY = random.nextInt(700); // MAX_Y is the maximum value for y

        entityManager.createAppliance(appliances[randomIndex], randomX, randomY);
    }

}
