package com.mygdx.game.CollisionMgmt;

import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.EntityManager;

import java.util.List;

public class CollisionManager {
    private EntityManager entityManager;

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
}