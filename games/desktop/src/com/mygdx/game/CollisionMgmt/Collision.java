package com.mygdx.game.CollisionMgmt;
import com.mygdx.game.EntityMgmt.Entity;


public class Collision {
    private Entity entity1;
    private Entity entity2;
    
    public Collision(Entity entity1, Entity entity2) {
        this.entity1 = entity1;
        this.entity2 = entity2;
    }

    public Entity getEntity1() {
        return this.entity1;
    }
    
    public Entity getEntity2() {
        return this.entity2;
    }
    
    
    public boolean checkCollision() {
        float dx = entity1.getX() - entity2.getX();
        float dy = entity1.getY() - entity2.getY();
        float distance = (float)Math.sqrt(dx * dx + dy * dy);
        return distance <= (entity1.getRadius() + entity2.getRadius());
    }

    
    
    public void notifyCollisionListener(CollisionListener listener) {
        if(checkCollision()) {
            listener.onCollision(this);
        }
    }
}
