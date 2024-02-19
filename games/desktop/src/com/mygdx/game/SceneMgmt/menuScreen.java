package com.mygdx.game.SceneMgmt;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;

public class menuScreen extends SceneScreen {
    private Texture backgroundTexture;
    private BitmapFont titleFont, optionFont;
    private String[] options = {"Start", "Quit"};
    private int currentOption = 0;
    private SpriteBatch batch;
    
    public menuScreen(){
        // backgroundTexture = new Texture(""); can add in the future when needed. (can be gif or image)
        titleFont = new BitmapFont();
        optionFont = new BitmapFont();
        batch = new SpriteBatch();

        // Setup fonts (we need to go and add the font files)
        titleFont.getData().setScale(2);
        optionFont.getData().setScale(1.5f);
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // draw the background
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // draw title and options
        titleFont.draw(batch, "Game Title", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 100, 0, Align.center, false);
        for (int i = 0; i < options.length; i++) {
            if (i == currentOption) {
                optionFont.setColor(Color.RED);
            } else {
                optionFont.setColor(Color.WHITE);
            }
            optionFont.draw(batch, options[i], Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 200 - 50 * i, 0, Align.center, false);
        }

        batch.end();
    }

    @Override
    public void dispose() {
        // Dispose resources
        backgroundTexture.dispose();
        titleFont.dispose();
        optionFont.dispose();
        batch.dispose();
    }

    // will add input handling ltr
}
    
