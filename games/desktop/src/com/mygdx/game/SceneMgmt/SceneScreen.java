package com.mygdx.game.SceneMgmt;
import com.badlogic.gdx.ScreenAdapter;

public abstract class SceneScreen extends ScreenAdapter {

    public abstract void render(float delta);
    
    public abstract void dispose();
    
    public abstract void show();
}