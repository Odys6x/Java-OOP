package com.mygdx.game.CollisionMgmt;

import com.mygdx.game.EntityMgmt.GameObject;
import com.mygdx.game.EntityMgmt.Player;
import com.mygdx.game.EntityMgmt.AI;

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

    public static void stopAtCollision(GameObject movingEntity, GameObject staticEntity) {
        // Calculate current overlaps
        float overlapLeft = movingEntity.getX() + movingEntity.getWidth() - staticEntity.getX();
        float overlapRight = staticEntity.getX() + staticEntity.getWidth() - movingEntity.getX();
        float overlapTop = staticEntity.getY() + staticEntity.getHeight() - movingEntity.getY();
        float overlapBottom = movingEntity.getY() + movingEntity.getHeight() - staticEntity.getY();

        // Determine the direction of the smallest overlap
        float minOverlap = Math.min(Math.min(overlapLeft, overlapRight), Math.min(overlapTop, overlapBottom));

        // Check if the teardrop is colliding with the player from above
        if ((movingEntity instanceof AI && staticEntity instanceof Player) || (staticEntity instanceof AI && movingEntity instanceof Player)) {
            AI teardrop = (movingEntity instanceof AI) ? (AI)movingEntity : (AI)staticEntity;
            GameObject player = (staticEntity instanceof Player) ? staticEntity : movingEntity;
            
            // Only stop the teardrop if the collision is from above
            if (minOverlap == overlapBottom) {
                stopTeardropAtPlayer(teardrop, player);
                return; // Prevent further position adjustments after handling teardrop-player collision
            }
        }
        
        // General collision resolution for all entities, including teardrop from sides
        if (minOverlap == overlapLeft) {
            movingEntity.setX(staticEntity.getX() - movingEntity.getWidth());
        } else if (minOverlap == overlapRight) {
            movingEntity.setX(staticEntity.getX() + staticEntity.getWidth());
        } else if (minOverlap == overlapTop) {
            movingEntity.setY(staticEntity.getY() + staticEntity.getHeight());
        } else if (minOverlap == overlapBottom) {
            movingEntity.setY(staticEntity.getY() - movingEntity.getHeight());
        }
    }

    private static void stopTeardropAtPlayer(AI teardrop, GameObject player) {
        // Adjust the teardrop's Y position to stop just above the player, not altering its position for side collisions
        float newYPosition = player.getY() + player.getHeight();
        teardrop.setY(newYPosition);
    }
}
