package com.mygdx.game.ScoreMgmt;

public class Score {
    private int value;

    public Score() {
        this.value = 0; // Initialize score to zero
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increase(int amount) {
        this.value += amount;
    }

    public void decrease(int amount) {
        this.value -= amount;
        if (this.value < 0) {
            this.value = 0; // Ensure score doesn't go negative
        }
    }
}

