package com.mygdx.game.CollisionMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;

public class CollisionManager {
    private EntityManager entityManager;

    public CollisionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void update() {
        entityManager.checkCollisionAndApplyKnockback();
    }
}