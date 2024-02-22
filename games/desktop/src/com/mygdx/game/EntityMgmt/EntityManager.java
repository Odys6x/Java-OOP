package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;
import com.mygdx.game.CollisionMgmt.CollisionManager;


public class EntityManager {
    private List<Entity> entityList;

    CollisionManager collisionManager;

    public EntityManager() {
        entityList = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public void updateEntities() {
        for (Entity entity : entityList) {
            entity.update();
        }
    }

    public void draw(SpriteBatch batch, ShapeRenderer shape){
        for (int i = 0; i < entityList.size(); i++){
            batch.begin();
                entityList.get(i).draw(batch);
            batch.end();
            shape.begin(ShapeRenderer.ShapeType.Filled);
                entityList.get(i).draw(shape);
            shape.end();

        }
    }
    public void movement(){
        for (int i=0;i<entityList.size();i++){
            entityList.get(i).movement();
        }
    }
    public void update(){
        for (int i=0;i<entityList.size();i++){
            entityList.get(i).update();
        }
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public Entity getUserControlledEntity() {
        for (Entity entity : entityList) {
            if (entity.isUserControlled()) {
                return entity;
            }
        }
        return null; // Return null if no user-controlled entity is found
    }
    public void createShape(int number) {
        if (number == 1){
            CircleObject circle = new CircleObject();
            addEntity(circle.createShape());
        }
        else if(number == 2){
            TriangleObject triangle = new TriangleObject();
            addEntity(triangle.createShape());
        }
    }

    public void createText(int number) {
        if (number == 1){
            TexturedObject text = new TexturedObject();
            addEntity(text.createTexture(number));
        }
        else if (number == 2){
            TexturedObject text = new TexturedObject();
            for (int i = 0; i < 10; i++) {
                addEntity(text.createTexture(number));
            }
        }
    }

    public float getSpeed() {
        float speed = 0;
        for (Entity entity : entityList) {
            if (entity.isUserControlled()) {
                speed = entity.getSpeed();
            }
        }
        return speed;
    }

    public void dispose(){
        for (Entity entity : entityList) {
            if (entity instanceof TexturedObject) {
                ((TexturedObject) entity).dispose();
            }
        }
    }
    public void updateEntityPosition(Entity entity, float deltaX, float deltaY) {
        entity.setX(entity.getX() + deltaX);
        entity.setY(entity.getY() + deltaY);
    }



}

