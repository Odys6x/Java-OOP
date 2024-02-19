package com.mygdx.game.SceneMgmt;
import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.graphics.GL20; 
import com.badlogic.gdx.graphics.Texture; 
import com.badlogic.gdx.scenes.scene2d.Stage; 
import com.badlogic.gdx.scenes.scene2d.ui.Image; 
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class endScreen extends SceneScreen {
    private SceneManager SceneManager;
    private Texture background;
    private Stage Stage;

    public endScreen(SceneManager SceneManager){
        this.SceneManager = SceneManager;
        show();
    }
    public void init(){
    }

    @Override
    public void show() {
        Stage = new Stage(new ScreenViewport());
        background = new Texture(Gdx.files.internal("endscreen.jpg"));

        Image backgroundImage = new Image(background);
        Stage.addActor(backgroundImage);

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Stage.act(Math.min(Gdx.graphics.getDeltaTime(),1 / 30f));
        Stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
