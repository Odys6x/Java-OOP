package com.mygdx.game.SceneMgmt;

import com.mygdx.game.EntityMgmt.EntityManager;
import com.mygdx.game.SimulationMgmt.Simulation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage; 
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class gameScreen extends SceneScreen {
    private SceneManager sceneManager;
    private Simulation simulation; // Assuming this is your game's simulation class
    private Texture background;
    private Stage stage;


    public gameScreen(SceneManager sceneManager) {
        super();
        this.sceneManager = sceneManager.getInstance();
        // this.SceneManager = SceneManager;
        // show();
    }
    public void init(){
        
    }

    @Override
    public void show() {
        simulation = new Simulation();
        simulation.initialise();
        stage = new Stage(new ScreenViewport());
        background = new Texture(Gdx.files.internal("gamescreen.jpg"));
        Image backgroundImage = new Image(background);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        // Start or resume the simulation logic
        simulation.initialise();
        Gdx.input.setInputProcessor(stage); 
        stage.addListener(new ClickListener(){ // adds a listener to the stage for touch events

            // when clicked will transition to the next scene
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.log("menuScreen", "Screen clicked, attempting to transition to gameScreen.");
                System.out.println("Next Screen (Menu Screen)");
                if (sceneManager != null) {
                    sceneManager.setScene(new endScreen(sceneManager)); // supposed to transition here. but not working
                } else {
                    System.out.println("SceneManager is null"); // error handling cos game screen not transitioning..
                }
                            }
        });
    }
    
    @Override
    public void render(float delta) {
            // Clear the screen
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
            // Update and render your simulation
            simulation.update(); // Assuming an update method exists
    
            // Draw the stage
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
    }
    @Override
    public void dispose() {
        if (simulation != null) {
            simulation.end(); // Dispose of your simulation resources
        }
        stage.dispose();
        background.dispose();
    }
}