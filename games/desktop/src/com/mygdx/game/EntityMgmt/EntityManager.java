package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.EntityMgmt.EntityClass.Entity;

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
}
