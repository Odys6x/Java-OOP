package com.mygdx.game.SoundMgmt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Sound {

    private Music music;

    public Sound(String filepath) {
        music = Gdx.audio.newMusic(Gdx.files.internal(filepath));
        music.setLooping(true);
    }

    public void play() {
        if (music != null && !music.isPlaying()) {
            music.play();
        }
    }

    public void stop() {
        if (music != null && music.isPlaying()) {
            music.stop();
        }
    }

    public void setVolume(float volume) {
        if (music != null) {
            if (volume < 0.0f || volume > 1.0f) {
                throw new IllegalArgumentException("Volume should be between 0.0 and 1.0");
            }
            music.setVolume(volume);
        }
    }

    public void dispose() {
        if (music != null) {
            music.dispose();
        }
    }
}
