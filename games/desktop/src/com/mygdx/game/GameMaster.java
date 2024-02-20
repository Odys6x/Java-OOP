    package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.game.SimulationMgmt.Simulation;

public class GameMaster extends ApplicationAdapter {
    private Simulation Simulation;
    @Override
    public void create() {
        Simulation = new Simulation();
        Simulation.initialise();
    }
    @Override
    public void render() {
        Simulation.update();
    }
    @Override
    public void dispose() {
        Simulation.end();
    }
}
