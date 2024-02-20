package com.mygdx.game.SceneMgmt;
import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class SceneManager {
    private SceneScreen currentScene;
    private Game game;

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }
        public void setGame(Game game) {
        this.game = game;
    }

    public void setScene(SceneScreen scene) {
        Gdx.app.log("SceneManager", "Attempting to set new scene."); // error handling cos  gamescreen not transitioning
        if (game == null) {
            Gdx.app.log("SceneManager", "Game instance is null. Cannot change screen."); // also error handling
        }
        if (currentScene != null) {
            currentScene.hide(); // Ensure the current screen is properly hidden
            currentScene.dispose(); // Dispose resources of the current screen
        }
        currentScene = scene;
        currentScene.show();
        game.setScreen(currentScene); 
    }
    public void render(float delta){
        if (currentScene != null){
            currentScene.render(delta);
        }
    }
    public void dispose() {
        if (currentScene != null){
            currentScene.dispose();
        }
    }
    private static SceneManager instance;
    private SceneManager() {
        // Private constructor to prevent instantiation outside this class.
    }

}
    

    // private static SceneManager instance;
    // public static final int Menu = 0;
    // public static final int Game = 1;
    // public static final int End = 2;
    // public static final int Gameover = 3;
    // private ArrayList<SceneScreen> scenes; // add the scenes to an array
    // private int currentScene; // this is the current active screen

    // public static SceneManager getInstance() { // to ensure that only one instance of scenemanager exists
    //     if(instance == null){
    //         instance = new SceneManager();
    //     }
    //     return instance;
    // }
    // private SceneManager(){ // init all the screens
    //     scenes = new ArrayList<>();
    //     scenes.add(new menuScreen());
    //     scenes.add(new gameScreen());
    //     scenes.add(new endScreen());
    //     scenes.add(new gameoverScreen());
    //     currentScene = Menu;
    // }
    // public void changeScene(int sceneStage){
    //     scenes.get(currentScene).hide(); // hides current screen
    //     currentScene = sceneStage;
    //     SceneScreen newScreen = scenes.get(sceneStage);
    //     newScreen.init(); 
    //     newScreen.show(); //init and shows the new screen
    // }
    // public SceneScreen getCurrentScene(){
    //     return scenes.get(currentScene);
    // }

