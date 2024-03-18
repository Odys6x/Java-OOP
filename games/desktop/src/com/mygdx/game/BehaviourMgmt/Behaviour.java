package com.mygdx.game.BehaviourMgmt;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.AiControllerMgmt.AIControllerManager;
import com.mygdx.game.CollisionMgmt.CollisionManager;
import com.mygdx.game.EntityMgmt.Appliance;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.SceneMgmt.SceneScreen;
import com.mygdx.game.InputMgmt.InputManager;
import com.mygdx.game.InputMgmt.KeyboardInput;

public class Behaviour {
    private String type;
    private boolean isOn;
    private float energyConsumption;
    private float activationRange;
    private TextureRegion texture;
    private float score;
    private float interactionTime;
    private boolean isActivated;


}
