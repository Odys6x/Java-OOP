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
import com.mygdx.game.SoundMgmt.Sound;
import com.mygdx.game.ScoreMgmt.ScoreManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameScreen2 extends SceneScreen {
    private SceneManager sceneManager;
    private Simulation simulation; 
    private Texture background;
    private Stage stage;
    private Sound sound = new Sound("games/assets/music.wav");
    private BitmapFont font;
    private ScoreManager scoreManager;
    private SpriteBatch spriteBatch;



    public GameScreen2(SceneManager sceneManager) {
        super();
        this.sceneManager = sceneManager;
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
        spriteBatch.begin();
        String scoreText = "Score: " + scoreManager.getScore();
        font.draw(spriteBatch, "Score: " + scoreManager.getScore(), 20, Gdx.graphics.getHeight() - 20);
        spriteBatch.end();
        System.out.println(scoreText); 
        simulation.update(); // then update and render the simul here
    }
    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
        sound.close();
    }

}