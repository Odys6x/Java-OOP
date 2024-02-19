package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.mygdx.game.SceneMgmt.SceneManager;
import com.mygdx.game.SceneMgmt.menuScreen;
import com.mygdx.game.SimulationMgmt.Simulation;

public class GameMaster extends Game { //changed from application adapter to game
    private Simulation Simulation;
    private SceneManager SceneManager;
    @Override
    public void create() {
        Simulation = new Simulation();
        Simulation.initialise();
        this.setScreen(new menuScreen(SceneManager));
    }
    @Override
    public void render() {
        super.render();
        // Simulation.update();
        // SceneManager.render();
    }
    @Override
    public void dispose() {
        Simulation.end();
        SceneManager.dispose();
    }
}
