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
        for (Entity e1 : entityManager.getEntityList()) {
            for (Entity e2 : entityManager.getEntityList()) {
                if (e1 != e2 && checkCollision(e1, e2)) {
                    applyKnockback(e1, e2);
                }
            }
        }
    }

    public boolean checkCollision(Entity e1, Entity e2) {
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

    public void applyKnockback(Entity e1, Entity e2) {
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