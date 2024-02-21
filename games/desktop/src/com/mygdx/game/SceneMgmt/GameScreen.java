package com.mygdx.game.SceneMgmt;
import com.mygdx.game.SimulationMgmt.Simulation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage; 
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen extends SceneScreen {
    private SceneManager sceneManager;
    private Simulation simulation; 
    private Texture background;
    private Stage stage;


    public GameScreen(SceneManager sceneManager) {
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

        simulation.initialise(); // starts the simulation logic
        Gdx.input.setInputProcessor(stage); 
        stage.addListener(new ClickListener(){ // adds a listener to the stage for touch events

            // when clicked will transition to the next scene
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.log("MenuScreen", "Screen clicked, attempting to transition to GameScreen.");
                System.out.println("Next Screen (Menu Screen)");
                if (sceneManager != null) {
                    sceneManager.setScene(new EndScreen(sceneManager)); 
                } else {
                    System.out.println("SceneManager is null"); 
                }
                            }
        });
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f)); // renders the bg
        stage.draw();
    
        simulation.update(); // then update and render the simul here
    }
    @Override
    public void dispose() {
        if (simulation != null) {
            simulation.end(); // disposing the resources
        }
        stage.dispose();
        background.dispose();
    }
}