package com.mygdx.game.SceneMgmt;
import com.mygdx.game.SimulationMgmt.Simulation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage; 
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen extends SceneScreen {
    private SceneManager sceneManager;
    private Simulation simulation; 
    private Texture background;
    private Stage stage;


    public GameScreen(SceneManager sceneManager) {
        super();
        this.sceneManager = sceneManager;
    }
    public void init(){
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        background = new Texture(Gdx.files.internal("gamescreen.png"));
        Image backgroundImage = new Image(background);
        // Manually set the image size to match the screen dimensions
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Optionally, center the image if needed (not necessary if filling the screen)
        backgroundImage.setPosition(0, 0);
        stage.addActor(backgroundImage);
        Gdx.input.setInputProcessor(stage);
        System.out.println("Screen Width: " + Gdx.graphics.getWidth() + ", Height: " + Gdx.graphics.getHeight());
        System.out.println("Background Image Width: " + backgroundImage.getWidth() + ", Height: " + backgroundImage.getHeight());
        
        simulation = new Simulation();
        simulation.initialise();
        stage.addListener(new ClickListener(){ // adds a listener to the stage for touch events

            // when clicked will transition to the next scene
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.log("GameScreen", "Screen clicked, attempting to transition to EndScreen.");
                System.out.println("Next Screen (End Screen)");
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
        Gdx.gl.glClearColor(0, 0, 0, 1); // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        simulation.update(); // then update and render the simul here
    }
    @Override
    public void dispose() {
        if (simulation != null) {
            simulation.end(); // disposing the resources
            simulation = null;
        }
        stage.dispose();
        background.dispose();
    }

}