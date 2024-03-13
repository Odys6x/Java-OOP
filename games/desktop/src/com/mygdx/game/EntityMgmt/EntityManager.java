package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;
import com.mygdx.game.CollisionMgmt.CollisionManager;


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

    public void createPlayer() {
        Player text = new Player();
        addEntity(text.createPlayer());

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

    public void dispose(){
        for (Entity entity : entityList) {
            entity.dispose();
        }
    }
    public void updateEntityPosition(Entity entity, float deltaX, float deltaY) {
        entity.setX(entity.getX() + deltaX);
        entity.setY(entity.getY() + deltaY);
    }



}

