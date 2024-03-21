package com.mygdx.game.MapMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;

import java.util.Random;

public class MapManager {
    private Map map;

    public void LoadMap(EntityManager entityManager, int numWalls,int numAI,int numAppliances) {
        for (int i = 0; i < numAI; i++) {
            entityManager.createAI();
        }

        for (int i = 0; i < numAppliances; i++) {
            createRandomAppliance(entityManager);
        }
        // Use the provided number of walls to create the specified number of walls
        for (int i = 0; i < numWalls; i++) {
            entityManager.createWalls("WallH");
        }
    }
    public void LoadPlayers(EntityManager entityManager, int numPlayers) {
        // Use the provided number of walls to create the specified number of walls
        for (int i = 0; i < numPlayers; i++) {
            entityManager.createPlayer("Player");
        }
    }
    private void createRandomAppliance(EntityManager entityManager) {
        Random random = new Random();
        int randomIndex = random.nextInt(2); // Randomly choose between 0 and 1
        String[] appliances = {"Chicken", "Microwave"};
        entityManager.createAppliance(appliances[randomIndex]);
    }
}
