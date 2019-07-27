package com.wethebest.spaceinvaders;

import android.graphics.Canvas;

public class GameUI {

    public int mScore;
    public int mLives;

    GameUI() {
        mScore = 0;
        mLives = SimpleCannon.MAX_LIVES;
    }


    public void update(int score) {
        mScore = score;
        mLives = SimpleCannon.lives;
    }

    public void draw(Canvas canvas) {

    }
}
