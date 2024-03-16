package com.mygdx.game.CollisionMgmt;

import com.mygdx.game.EntityMgmt.GameObject;

public interface CollisionListener {
    void onCollision(GameObject c1, GameObject c2);
}
