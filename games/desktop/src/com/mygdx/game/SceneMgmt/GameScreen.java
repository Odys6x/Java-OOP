package com.mygdx.game.SceneMgmt;
import com.mygdx.game.SimulationMgmt.Simulation;
import com.mygdx.game.SoundMgmt.Sound;
import com.mygdx.game.EntityMgmt.EntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage; 
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ScoreMgmt.ScoreManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameScreen extends SceneScreen {
    private SceneManager sceneManager;
    private Simulation simulation; 
    private Texture background;
    private Stage stage;
    private Sound sound = new Sound("music.wav");
    private BitmapFont font;
    private ScoreManager scoreManager;
    private SpriteBatch spriteBatch;
    private static final float Target_Score = 1000;



    public GameScreen(SceneManager sceneManager, Simulation simulation) {
        super();
        this.sceneManager = sceneManager;
        this.simulation = simulation; // Save the simulation instance
        sound.play();
        sound.setVolume(0.2f);
    }
    public void init(){
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        font = new BitmapFont();
        spriteBatch = new SpriteBatch();
        scoreManager = new ScoreManager();
        background = new Texture(Gdx.files.internal("gamescreen.png"));
        Image backgroundImage = new Image(background);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);
        Gdx.input.setInputProcessor(stage);
        Sound sound = this.sound;
        
        simulation = new Simulation();
        simulation.initialise();
        stage.addListener(new ClickListener(){ // adds a listener to the stage for touch events

            // when clicked will transition to the next scene
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.log("GameScreen", "Screen clicked, attempting to transition to EndScreen.");
                System.out.println("Next Screen (End Screen)");
                if (sceneManager != null) {
                    sceneManager.setScene(new GameScreen2(sceneManager, simulation, sound)); 
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
        spriteBatch.begin();
        font.draw(spriteBatch, "Score: " + simulation.getScore(), 20, Gdx.graphics.getHeight() - 20);
        spriteBatch.end();
        simulation.update(); // then update and render the simul here
        if (simulation.getScore() == Target_Score) {
            sceneManager.setScene(new GameScreen2(sceneManager, simulation, this.sound));
        }
    }
    @Override
    public void dispose() {
        if (simulation != null) {
            simulation.end(); // Make sure to dispose of the simulation resources
            simulation = null;
        }
        // if (sound != null) {
        //     sound.stop(); // Make sure to close the sound
        // }
        if (spriteBatch != null) {
            spriteBatch.dispose(); // Dispose of spriteBatch
        }
        if (font != null) {
            font.dispose(); // Dispose of font
        }
        if (background != null) {
            background.dispose(); // Dispose of background texture
        }
        if (stage != null) {
            stage.dispose(); // Dispose of stage
        }
    }
}

