package com.mygdx.game.CollisionMgmt;


import com.mygdx.game.EntityMgmt.GameObject;

public class Collision {
    public static boolean checkCollision(GameObject c1, GameObject c2) {
        float c1Left = c1.getX();
        float c1Right = c1.getX() + c1.getWidth();
        float c1Top = c1.getY() + c1.getHeight();
        float c1Bottom = c1.getY();

        float c2Left = c2.getX();
        float c2Right = c2.getX() + c2.getWidth();
        float c2Top = c2.getY() + c2.getHeight();
        float c2Bottom = c2.getY();

        return c1Right >= c2Left && c1Left <= c2Right && c1Top >= c2Bottom && c1Bottom <= c2Top;
    }

    public static void applyKnockback(GameObject c1, GameObject c2) {
        float knockbackDistance = 10.0f;
        float dx = c2.getX() - c1.getX();
        float dy = c2.getY() - c1.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        float knockbackX = knockbackDistance * (dx / distance);
        float knockbackY = knockbackDistance * (dy / distance);

        c1.setX(c1.getX() - knockbackX);
        c1.setY(c1.getY() - knockbackY);
        c2.setX(c2.getX() + knockbackX);
        c2.setY(c2.getY() + knockbackY);
    }

}
