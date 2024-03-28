package com.mygdx.game.CollisionMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.EntityMgmt.GameObject;

import java.util.List;

public class CollisionManager {
    private EntityManager entityManager;

    public CollisionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void update() {
        List<GameObject> entities = entityManager.getEntities();
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                GameObject c1 = entities.get(i);
                GameObject c2 = entities.get(j);
                if (Collision.checkCollision(c1, c2)) {
                    Collision.resolveCollision(c1, c2);
                }
            }
        }
    }
}
