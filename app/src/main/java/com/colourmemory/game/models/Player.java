package com.colourmemory.game.models;

public class Player {

    private boolean hasPlayed;
    private int score;

    public Player() {
        hasPlayed = false;
    }

    public boolean hasPlayed() {
        return hasPlayed;
    }

    public void setPlayingStatus(boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
