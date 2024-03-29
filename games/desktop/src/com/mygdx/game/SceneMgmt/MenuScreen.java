package com.mygdx.game.SceneMgmt;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.graphics.GL20; 
import com.badlogic.gdx.graphics.Texture; 
import com.badlogic.gdx.scenes.scene2d.InputEvent; 
import com.badlogic.gdx.scenes.scene2d.Stage; 
import com.badlogic.gdx.scenes.scene2d.ui.Image; 
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener; 
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.SimulationMgmt.Simulation;


public class MenuScreen extends SceneScreen{
    private SceneManager sceneManager;
    private Simulation simulation;
    private Texture background;
    private Stage stage;
    private Skin skin;

    public MenuScreen (SceneManager sceneManager){
        super();
        this.sceneManager = sceneManager;
    }
    
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    
        background = new Texture(Gdx.files.internal("start.png"));
        Image backgroundImage = new Image(background);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);
    
        skin = new Skin(Gdx.files.internal("uiskin.json"));
    
        // creating buttons here
        TextButton playButton = new TextButton("Play", skin);
        TextButton optionButton = new TextButton("Option", skin);
        TextButton exitButton = new TextButton("Exit", skin);
    
        final float buttonWidth = 200;
        final float buttonHeight = 50;
        final float buttonSpacing = 20;
    
        // positioning the buttons
        playButton.setSize(buttonWidth, buttonHeight);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, 
                               Gdx.graphics.getHeight() / 2 + buttonHeight / 2 + buttonSpacing);
        optionButton.setSize(buttonWidth, buttonHeight);
        optionButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, 
                                 Gdx.graphics.getHeight() / 2 - buttonHeight / 2);
        exitButton.setSize(buttonWidth, buttonHeight);
        exitButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, 
                               Gdx.graphics.getHeight() / 2 - 3 * buttonHeight / 2 - buttonSpacing);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (sceneManager != null) {
                    
                    sceneManager.setScene(new GameScreen(sceneManager, simulation)); // Transition to GameScreen
                }
            }
        });
        optionButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (sceneManager != null){
                    sceneManager.setScene(new OptionScreen(sceneManager));
                }
            }
        });
            exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit(); 
            }
        });
        stage.addActor(playButton);
        stage.addActor(optionButton);
        stage.addActor(exitButton);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose(){
        background.dispose();
        skin.dispose();
        stage.dispose();
    }

}