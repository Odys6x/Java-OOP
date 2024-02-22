package com.mygdx.game.SceneMgmt;

import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.graphics.GL20; 
import com.badlogic.gdx.graphics.Texture; 
import com.badlogic.gdx.scenes.scene2d.InputEvent; 
import com.badlogic.gdx.scenes.scene2d.Stage; 
import com.badlogic.gdx.scenes.scene2d.ui.Image; 
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener; 
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen extends SceneScreen{
    private SceneManager sceneManager;
    private Texture background;
    private Stage stage;
    public MenuScreen (SceneManager sceneManager){
        super();
        this.sceneManager = sceneManager.getInstance();
    }
    
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        background = new Texture(Gdx.files.internal("start.png"));

        Image backgroundImage = new Image(background);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(backgroundImage); // adds the background image to stage

        Gdx.input.setInputProcessor(stage); // sets the input processor to handle input events for actors

        stage.addListener(new ClickListener(){ // adds a listener to the stage for touch events

            // when clicked will transition to the next scene
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.log("MenuScreen", "Screen clicked, attempting to transition to GameScreen.");
                System.out.println("Next Screen (Menu Screen)");
                if (sceneManager != null) {
                    sceneManager.setScene(new GameScreen(sceneManager)); // supposed to transition here. but not working
                } else {
                    System.out.println("SceneManager is null"); // error handling cos game screen not transitioning..
                }
                            }
        });
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear screen
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw(); // draws the stage and the actors
    }

    @Override
    public void dispose(){
        background.dispose();
    }

}