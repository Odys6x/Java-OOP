package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;


public class EntityManager {
    private List<Entity> entityList;
    
    

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
            addEntity(circle.create());
        }
        else if(number == 2){
            TriangleObject triangle = new TriangleObject();
            addEntity(triangle.createTriangle());
        }
    }

    public void createText(int number) {
        if (number == 1){
            TexturedObject text = new TexturedObject();
            addEntity(text.createText());
        }
        else if (number == 2){
            TexturedObject text = new TexturedObject();
            for (int i = 0; i < 10; i++) {
                addEntity(text.createDrop());
            }
        }
    }

    public List<Float> getDimensions(int entityType) {
        List<Float> dimension = new ArrayList<>();
        for (Entity entity : entityList) {
            if (entityType == 1 && entity instanceof CircleObject) {
                CircleObject circle = (CircleObject) entity;
                dimension.add(circle.getRadius());
            } else if (entityType == 2 && entity instanceof TexturedObject) {
                TexturedObject textured = (TexturedObject) entity;
                dimension.add((float) textured.getTexture().getHeight());
                dimension.add((float) textured.getTexture().getWidth());
            }
        }
        return dimension;
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

    public void checkCollisionAndApplyKnockback() {
        for (Entity e1 : entityList) {
            for (Entity e2 : entityList) {
                if (e1 != e2 && checkCollision(e1, e2)) {
                    applyKnockback(e1, e2);
                }
            }
        }
    }

    private boolean checkCollision(Entity e1, Entity e2) {
        float e1Left = e1.getX();
        float e1Right = e1.getX() + e1.getWidth();
        float e1Top = e1.getY() + e1.getHeight();
        float e1Bottom = e1.getY();

        float e2Left = e2.getX();
        float e2Right = e2.getX() + e2.getWidth();
        float e2Top = e2.getY() + e2.getHeight();
        float e2Bottom = e2.getY();

        return e1Right >= e2Left && e1Left <= e2Right && e1Top >= e2Bottom && e1Bottom <= e2Top;
    }

    private void applyKnockback(Entity e1, Entity e2) {
        float knockbackDistance = 10.0f; 
        float dx = e2.getX() - e1.getX();
        float dy = e2.getY() - e1.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        float knockbackX = knockbackDistance * (dx / distance);
        float knockbackY = knockbackDistance * (dy / distance);
    
        e1.setX(e1.getX() - knockbackX);
        e1.setY(e1.getY() - knockbackY);
        e2.setX(e2.getX() + knockbackX);
        e2.setY(e2.getY() + knockbackY);
    }
    
    
    
}

