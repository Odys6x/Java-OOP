package com.mygdx.game.CollisionMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.EntityMgmt.GameObject;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {
    private EntityManager entityManager;
    private final List<CollisionListener> listeners = new ArrayList<>();

    public CollisionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void update() {
        List<GameObject> entities = entityManager.getCollidableEntities();
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                GameObject c1 = entities.get(i);
                GameObject c2 = entities.get(j);
                if (Collision.checkCollision(c1, c2)) {
                    Collision.applyKnockback(c1, c2);
                    notifyCollision(c1, c2);
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

    protected void notifyCollision(GameObject c1, GameObject c2) {
        for (CollisionListener listener : listeners) {
            listener.onCollision(c1, c2);
        }
    }
}
