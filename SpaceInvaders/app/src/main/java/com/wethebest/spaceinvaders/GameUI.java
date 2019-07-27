package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

public class GameUI {

    public int mScore;
    public int mLives;
    private Paint mPaint;

    GameUI() {
        mScore = 0;
        mLives = SimpleCannon.MAX_LIVES;
        mPaint = new Paint();
        mPaint.setTypeface(Typeface.create("Arial",Typeface.BOLD));
        mPaint.setTextSize(100);
        mPaint.setARGB(255, 255,255, 255);


    }


    public void update(int score) {
        mScore = score;
        mLives = SimpleCannon.lives;
    }

    public void draw(Canvas canvas) {
        canvas.drawText(Integer.toString(mScore), 200, 200, mPaint);

    }
}
