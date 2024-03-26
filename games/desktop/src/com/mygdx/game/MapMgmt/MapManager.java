package com.mygdx.game.MapMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;


import java.util.Random;

public class MapManager {

    public void LoadMap(EntityManager entityManager, int numAI,int numFurn) {
        for (int i = 0; i < numAI; i++) {
            entityManager.createAI("AI", 500, 500);
        }

        entityManager.createAppliance("Basin",475,700);
        entityManager.createAppliance("Microwave",388,700);
        entityManager.createAppliance("Fridge",100,700);
        entityManager.createAppliance("Bathtub", 1000,300);
        for (int i = 0; i < numFurn; i++) {
            createRandomAppliance(entityManager);
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
        int randomIndex = random.nextInt(3); // Randomly choose between 0 and 1
        String[] Furnitures = {"Chair", "Sofa","Table"};

        // Generate random x and y coordinates within a specified range
        int randomX = random.nextInt(1620); // MAX_X is the maximum value for x
        int randomY = random.nextInt(600); // MAX_Y is the maximum value for y

        entityManager.createFurniture(Furnitures[randomIndex], randomX, randomY);
    }

}
