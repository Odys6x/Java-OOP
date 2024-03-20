package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.EntityMgmt.AI.AI;
import com.mygdx.game.EntityMgmt.Appliances.ApplianceFactory;
import com.mygdx.game.EntityMgmt.Player.Player;
import com.mygdx.game.EntityMgmt.Player.PlayerFactory;


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

    public Entity createPlayer(String entityType) {
        EntityFactory playerFactory = new PlayerFactory();
        Entity playerEntity = playerFactory.createEntity(entityType);
        addEntity(playerEntity); // Add the created entity to the entityList
        return playerEntity; // Return the created entity
    }

    public Entity createAppliance(String entityType) {
        EntityFactory applianceFactory = new ApplianceFactory();
        Entity applianceEntity = applianceFactory.createEntity(entityType);
        addEntity(applianceEntity); // Add the created entity to the entityList
        return applianceEntity; // Return the created entity
    }

    public void createAI() {
        AI text = new AI();
        addEntity(text.createAI());

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

