package com.mygdx.game.CollisionMgmt;

import com.mygdx.game.EntityMgmt.GameObject;
import com.mygdx.game.EntityMgmt.GameObjectType;
import com.mygdx.game.EntityMgmt.Player.Player;
import com.mygdx.game.EntityMgmt.AI.AI;

public class Collision {

    public static boolean checkCollision(GameObject c1, GameObject c2) {
        // Collision detection logic
        float c1Left = c1.getX();
        float c1Right = c1.getX() + c1.getWidth();
        float c1Top = c1.getY() + c1.getHeight();
        float c1Bottom = c1.getY();

        float c2Left = c2.getX();
        float c2Right = c2.getX() + c2.getWidth();
        float c2Top = c2.getY() + c2.getHeight();
        float c2Bottom = c2.getY();

        return c1Right > c2Left && c1Left < c2Right && c1Top > c2Bottom && c1Bottom < c2Top;
    }


    // *note* currently all entities are not movable, should we want to make entities movable, it can be done 
    // in case we wna change as player might get stuck if unbale to phase through certain obejcts
    // logic added to adjust position of entities in case of collision as well

    public static void resolveCollision(GameObject c1, GameObject c2) {
        // Check if both entities are movable.
        boolean c1Movable = isMovable(c1);
        boolean c2Movable = isMovable(c2);

        if (c1Movable && !c2Movable) {
            adjustPosition(c1, c2);
        } else if (!c1Movable && c2Movable) {
            adjustPosition(c2, c1);
        } else if (c1Movable && c2Movable) {
        }
    }

    private static boolean isMovable(GameObject entity) {
        return entity.getType() != GameObjectType.FURNITURE && entity.getType() != GameObjectType.APPLIANCE;
    }
    

    private static void adjustPosition(GameObject movable, GameObject immovable) {
        // Calculate overlap in both x and y directions.
        float overlapX = Math.min(movable.getX() + movable.getWidth(), immovable.getX() + immovable.getWidth()) - Math.max(movable.getX(), immovable.getX());
        float overlapY = Math.min(movable.getY() + movable.getHeight(), immovable.getY() + immovable.getHeight()) - Math.max(movable.getY(), immovable.getY());

        
        if (overlapX < overlapY) {
            // Horizontal collision
            if (movable.getX() < immovable.getX()) {
                movable.setX(movable.getX() - overlapX);
            } else {
                movable.setX(movable.getX() + overlapX);
            }
        } else {
            // Vertical collision
            if (movable.getY() < immovable.getY()) {
                movable.setY(movable.getY() - overlapY);
            } else {
                movable.setY(movable.getY() + overlapY);
            }
        }
    }
}
