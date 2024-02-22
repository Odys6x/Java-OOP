package com.mygdx.game.CollisionMgmt;
import com.mygdx.game.EntityMgmt.Entity;

public interface CollisionListener {
    void onCollision(Entity entity1, Entity entity2);
}

