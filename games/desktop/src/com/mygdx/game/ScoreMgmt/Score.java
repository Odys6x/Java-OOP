package com.mygdx.game.ScoreMgmt;

public class Score {
    private float score;

    public Score() {
        this.score = 0;
    }

    public void incrementScore(float amount) {
        score += amount;
    }

    public void decrementScore(float amount) {
        score -= amount;
        if (score < 0) {
            score = 0;
        }
    }

    public void resetScore() {
        score = 0;
    }

    public float getScore() {
        return score;
    }
}
