package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.EntityMgmt.AI.AIFactory;
import com.mygdx.game.EntityMgmt.Appliances.ApplianceFactory;
import com.mygdx.game.EntityMgmt.Player.Player;
import com.mygdx.game.EntityMgmt.Player.PlayerFactory;
import com.mygdx.game.EntityMgmt.Furniture.FurnitureFactory;


public class EntityManager{
    private List<Entity> entityList;

    public EntityManager() {
        entityList = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
    }


    public void draw(SpriteBatch batch){
        for (int i = 0; i < entityList.size(); i++){
            batch.begin();
                entityList.get(i).draw(batch);
            batch.end();
        }
    }

    public void setDirection(String direction) {
        for (Entity entity : entityList) {
            if (entity instanceof Player) {
                ((Player) entity).setDirection(direction);
            }
        }
    }
    public List<Entity> getEntityList() {
        return entityList;
    }

    public Entity getUserControlledEntity() {
        for (Entity entity : entityList) {
            if (entity instanceof Player) {
                return entity;
            }
        }
        return null; // Return null if no user-controlled entity is found
    }

    public void createPlayer(String entityType,float x, float y) {
        EntityFactory playerFactory = new PlayerFactory();
        Entity playerEntity = playerFactory.createEntity(entityType,x,y);
        addEntity(playerEntity); // Add the created entity to the entityList
    }

    public void createAppliance(String entityType,float x, float y) {
        EntityFactory applianceFactory = new ApplianceFactory();
        Entity applianceEntity = applianceFactory.createEntity(entityType,x,y);
        addEntity(applianceEntity); // Add the created entity to the entityList
    }

    public void createFurniture(String entityType,float x, float y) {
        EntityFactory furnitureFactory = new FurnitureFactory();
        Entity furnitureFactoryEntity = furnitureFactory.createEntity(entityType,x,y);
        addEntity(furnitureFactoryEntity); // Add the created entity to the entityList
    }


    public void createAI(String entityType,float x, float y) {
        EntityFactory AIFactory = new AIFactory();
        Entity AIEntity = AIFactory.createEntity(entityType,x,y);
        addEntity(AIEntity); // Add the created entity to the entityList
    }

    public float getSpeed() {
        float speed = 0;
        for (Entity entity : entityList) {
            if (entity instanceof Player) {
                speed = ((Player) entity).getSpeed();
            }
        }
        return speed;
    }

    public float getWidth() {
        float width = 0;
        for (Entity entity : entityList) {
            width = entity.getWidth();
        }
        return width;
    }


    public void dispose(){
        for (Entity entity : entityList) {
            entity.dispose();
        }
    }
    public void updateEntityPosition(Entity entity, float deltaX, float deltaY) {
        entity.setX(entity.getX() + deltaX);
        entity.setY(entity.getY() + deltaY);
    }


    public List<GameObject> getEntities() {
        List<GameObject> Entities = new ArrayList<>();

        List<Entity> entities = getEntityList();

        Entities.addAll(entities);

        return Entities;
    }
    public void updatePlayerAnimations(List<Integer> pressedKeys) {
        for (Entity entity : entityList) {
            if (entity instanceof Player) {
                ((Player) entity).updateAnimations(pressedKeys);
            }
        }
    }

}

