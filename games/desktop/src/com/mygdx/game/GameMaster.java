package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.mygdx.game.SceneMgmt.SceneManager;
import com.mygdx.game.SceneMgmt.MenuScreen;
import com.mygdx.game.SimulationMgmt.Simulation;

public class GameMaster extends Game { //changed from application adapter to game
    private Simulation Simulation;
    private SceneManager sceneManager;
    
    @Override
    public void create() {
        Simulation = new Simulation(1);
        Simulation.initialise();
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.setGame(this); // Pass the GameMaster instance to SceneManager
        this.setScreen(new MenuScreen(sceneManager));
    }
    @Override
    public void render() {
        super.render();
    }
    @Override
    public void dispose() {
        Simulation.end();
    }
}
