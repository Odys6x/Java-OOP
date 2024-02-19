package com.mygdx.game.SceneMgmt;
import java.util.ArrayList;

public class SceneManager {
    private static SceneManager instance;
    public static final int Menu = 0;
    public static final int Game = 1;
    public static final int End = 2;
    public static final int Gameover = 3;

    private ArrayList<SceneScreen> scenes; // add the scenes to an array
    private int currentScene; // this is the current active screen

    public static SceneManager getInstance() { // to ensure that only one instance of scenemanager exists
        if(instance == null){
            instance = new SceneManager();
        }
        return instance;
    }
    private SceneManager(){ // init all the screens
        scenes = new ArrayList<>();
        scenes.add(new menuScreen());
        scenes.add(new gameScreen());
        scenes.add(new endScreen());
        scenes.add(new gameoverScreen());
        currentScene = Menu;
    }
    public void changeScene(int sceneStage){
        scenes.get(currentScene).hide(); // hides current screen
        currentScene = sceneStage;
        SceneScreen newScreen = scenes.get(sceneStage);
        newScreen.init(); 
        newScreen.show(); //init and shows the new screen
    }
    public SceneScreen getCurrentScene(){
        return scenes.get(currentScene);
    }
}   
