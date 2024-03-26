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

public class EndScreen extends SceneScreen {
    private SceneManager sceneManager;
    private Texture background;
    private Stage stage;
    private Skin skin;

    public EndScreen(SceneManager sceneManager){
        super();
        this.sceneManager = sceneManager;
    }
    public void init(){
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        background = new Texture(Gdx.files.internal("end.png"));

        Image backgroundImage = new Image(background);
        stage.addActor(backgroundImage);
        Gdx.input.setInputProcessor(stage);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton playButton = new TextButton("Play Again?", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        final float buttonWidth = 200;
        final float buttonHeight = 50;
        final float buttonSpacing = 20;
        playButton.setSize(buttonWidth, buttonHeight);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, 
                               Gdx.graphics.getHeight() / 2 + buttonHeight / 2 + buttonSpacing);  
        exitButton.setSize(buttonWidth, buttonHeight);
        exitButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, 
                            Gdx.graphics.getHeight() / 2 - buttonHeight / 2);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (sceneManager != null) {
                    sceneManager.setScene(new MenuScreen(sceneManager)); // Transition to GameScreen
                }
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit(); // Exits the application
            }
        });
        stage.addActor(playButton);
        stage.addActor(exitButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(),1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
        skin.dispose();
    }
}
