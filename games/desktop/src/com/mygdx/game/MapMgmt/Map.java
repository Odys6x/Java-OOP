package com.mygdx.game.MapMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;


import java.util.Random;

public class Map {
    private int level;

    public Map(){}

    public Map(int level){
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
                    numEntities += 2;
                } else if (entityType.equals("Furniture")) {
                    numEntities += 2;
                }
                break;
            case 3:
                if (entityType.equals("AI")) {
                    numEntities += 2;
                } else if (entityType.equals("Furniture")) {
                    numEntities += 4;
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
            }
        }
    }

    public void LoadPlayers(EntityManager entityManager, int numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            entityManager.createPlayer("Player",300,0);
        }
    }
    private void createRandomAppliance(EntityManager entityManager) {
        Random random = new Random();
        int randomIndex = random.nextInt(3);
        String[] Furnitures = {"Chair", "Sofa","Table"};

        int randomX = random.nextInt(1620);
        int randomY = random.nextInt(600);

        entityManager.createFurniture(Furnitures[randomIndex], randomX, randomY);
    }

}
