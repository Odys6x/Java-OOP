package com.mygdx.game.EntityMgmt;

public interface EntityFactory {
    Entity createEntity(String entityType,float x, float y);
}
