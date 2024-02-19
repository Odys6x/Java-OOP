package com.mygdx.game.CollisionMgmt;
import com.mygdx.game.EntityMgmt.EntityClass.Entity;

public class CollisionManager {
    public void applyCollisionEffects(Collision collision) {
        Entity entity1 = collision.getEntity1();
        Entity entity2 = collision.getEntity2();
        
        // Calculate knockback direction
        float dx = entity2.getX() - entity1.getX();
        float dy = entity2.getY() - entity1.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        float knockbackDistance = 10; 
        
        // Normalize direction
        dx /= distance;
        dy /= distance;
        
        // Apply knockback by adjusting positions
        entity1.setX(entity1.getX() - dx * knockbackDistance);
        entity1.setY(entity1.getY() - dy * knockbackDistance);
        entity2.setX(entity2.getX() + dx * knockbackDistance);
        entity2.setY(entity2.getY() + dy * knockbackDistance);
}

}
