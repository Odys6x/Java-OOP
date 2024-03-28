package com.mygdx.game.SceneMgmt;
import com.mygdx.game.SimulationMgmt.Simulation;
import com.mygdx.game.SoundMgmt.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage; 
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameScreen3 extends SceneScreen {
    private SceneManager sceneManager;
    private Simulation simulation; 
    private Texture background;
    private Stage stage;
    private Sound sound = new Sound("games/assets/music.wav");
    private BitmapFont font;
    private SpriteBatch spriteBatch;
    private static final float Target_Score = 2000;


    public GameScreen3(SceneManager sceneManager, Simulation simulation, Sound sound) {
        super();
        this.sceneManager = sceneManager;
        this.simulation = simulation; // Save the simulation instance
        this.sound = sound;
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
        background = new Texture(Gdx.files.internal("gamescreen.png"));
        Image backgroundImage = new Image(background);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);
        Gdx.input.setInputProcessor(stage);
        simulation = new Simulation(3);
        simulation.initialise();
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
            sceneManager.setScene(new EndScreen(sceneManager));
        }
    }
    
    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
        sound.stop();
    }

}