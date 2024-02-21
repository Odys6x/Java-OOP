package com.mygdx.game.SceneMgmt;
import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.graphics.GL20; 
import com.badlogic.gdx.graphics.Texture; 
import com.badlogic.gdx.scenes.scene2d.Stage; 
import com.badlogic.gdx.scenes.scene2d.ui.Image; 
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndScreen extends SceneScreen {
    private SceneManager sceneManager;
    private Texture background;
    private Stage stage;

    public EndScreen(SceneManager sceneManager){
        this.sceneManager = sceneManager;
        show();
    }
    public void init(){
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        background = new Texture(Gdx.files.internal("end.png"));

        Image backgroundImage = new Image(background);
        stage.addActor(backgroundImage);

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
    }
}
