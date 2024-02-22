package com.mygdx.game.CollisionMgmt;

import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {
    private EntityManager entityManager;
    private final List<CollisionListener> listeners = new ArrayList<>();

    public CollisionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void update() {
        List<Entity> entities = entityManager.getEntityList();
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                Entity e1 = entities.get(i);
                Entity e2 = entities.get(j);
                if (Collision.checkCollision(e1, e2)) {
                    Collision.applyKnockback(e1, e2);
                }
            }
        }
    }

    public void addCollisionListener(CollisionListener listener) {
        listeners.add(listener);
    }

    public void removeCollisionListener(CollisionListener listener) {
        listeners.remove(listener);
    }

    protected void notifyCollision(Entity entity1, Entity entity2) {
        for (CollisionListener listener : listeners) {
            listener.onCollision(entity1, entity2);
        }
    }
}