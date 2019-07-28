package com.wethebest.spaceinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

public class GameUI {

    public int mScore;
    public int mLives;
    private Paint mPaint;
    SpaceInvadersApp mApp;

    GameUI(SpaceInvadersApp app) {
        mScore = 0;
        mLives = SimpleCannon.MAX_LIVES;
        mPaint = new Paint();
        mApp = app;

        Typeface mTypeface = Typeface.createFromAsset(mApp.context.getAssets(), "fonts/Bangers-Regular.ttf");
        mPaint.setTypeface(mTypeface);
        mPaint.setTextSize(100);
        mPaint.setARGB(255, 255,255, 255);
    }


    public void update(int score) {
        mScore = score;
        mLives = SimpleCannon.lives;
    }

    public void draw(Canvas canvas) {
        canvas.drawText(Integer.toString(mScore), mApp.mScreenSize.y/12, mApp.mScreenSize.y/12, mPaint);

    }
}
