package com.mygdx.game.MapMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;


import java.util.Random;

public class MapManager {
    private int level;

    public MapManager(){}

    public MapManager(int level){
        this.level = level;
    }

    public void LoadMap(EntityManager entityManager) {
        createEntities(entityManager, "AI", calculateNumEntities("AI",1));
        createEntities(entityManager, "Furniture", calculateNumEntities("Furniture",3));

        entityManager.createAppliance("Basin", 475, 700);
        entityManager.createAppliance("Microwave", 388, 700);
        entityManager.createAppliance("Fridge", 100, 700);
        entityManager.createAppliance("Bathtub", 1000, 300);
    }

    private int calculateNumEntities(String entityType, int numEntities) {
        switch (level) {
            case 1:
                if (entityType.equals("AI")) {
                    return numEntities;
                } else if (entityType.equals("Furniture")) {
                    return numEntities;
                }
                break;
            case 2:
                if (entityType.equals("AI")) {
                    numEntities += 2; // Add 5 to the initial value for level 2 AI
                } else if (entityType.equals("Furniture")) {
                    numEntities += 2; // Add 10 to the initial value for level 2 furniture
                }
                break;
            case 3:
                if (entityType.equals("AI")) {
                    numEntities += 2; // Add 7 to the initial value for level 3 AI
                } else if (entityType.equals("Furniture")) {
                    numEntities += 4; // Add 15 to the initial value for level 3 furniture
                }
                break;
            default:
                break;
        }
        return numEntities;
    }


    private void createEntities(EntityManager entityManager, String entityType, int count) {
        for (int i = 0; i < count; i++) {
            switch (entityType) {
                case "AI":
                    Random random = new Random();
                    int maxX = 1620;
                    int maxY = 600;
                    int randomX, randomY;
                    randomX = random.nextInt(maxX);
                    randomY = random.nextInt(maxY);
                    entityManager.createAI("AI", randomX, randomY);
                    break;
                case "Furniture":
                    createRandomAppliance(entityManager);
                    break;
                // Add cases for other entity types if needed
            }
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
