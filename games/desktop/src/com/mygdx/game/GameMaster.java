package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

import com.mygdx.game.SimulationMgmt.Simulation;


public class GameMaster extends ApplicationAdapter {
    private Simulation SimulationMgmt;
    @Override
    public void create() {
        SimulationMgmt.initialise();
    }

    @Override
    public void render() {
        SimulationMgmt.update();
    }

    @Override
    public void dispose() {
        SimulationMgmt.end();
    }
}
