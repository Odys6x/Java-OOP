package com.mygdx.game.SceneMgmt;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage; 
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class OptionScreen extends SceneScreen {
    private SceneManager sceneManager;
    private Texture background;
    private Stage stage;
    private Skin skin;
    
    public OptionScreen(SceneManager sceneManager){
        this.sceneManager = sceneManager;
        show();
    }
    public void init(){
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        background = new Texture(Gdx.files.internal("option.png"));

        Image backgroundImage = new Image(background);
        stage.addActor(backgroundImage);
        Gdx.input.setInputProcessor(stage);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton playButton = new TextButton("Let's Play", skin);
        TextButton menuButton = new TextButton("Back To Menu", skin);
        final float buttonWidth = 200;
        final float buttonHeight = 50;
        final float buttonSpacing = 20;
        playButton.setSize(buttonWidth, buttonHeight);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, 
                               Gdx.graphics.getHeight() / 2 + buttonHeight / 2 + buttonSpacing - 100);  
        menuButton.setSize(buttonWidth, buttonHeight);
        menuButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, 
                                Gdx.graphics.getHeight() / 2 - buttonHeight / 2 - 100);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (sceneManager != null) {
                    sceneManager.setScene(new GameScreen(sceneManager)); // Transition to GameScreen
                }
            }
        });
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (sceneManager != null) {
                    sceneManager.setScene(new MenuScreen(sceneManager)); // Transition to GameScreen
                }
            }
        });
        stage.addActor(playButton);
        stage.addActor(menuButton);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(),1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        skin.dispose();
        stage.dispose();
    }
}
