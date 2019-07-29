package com.wethebest.spaceinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

public class GameUI {

    public int mScore;
    public int mLives;
    private Paint mPaint;
    SpaceInvadersApp app;

    GameUI(SpaceInvadersApp app) {
        this.app = app;

        mScore = 0;
        mLives = app.mPlayer.MAX_LIVES;
        mPaint = new Paint();

        Typeface mTypeface = Typeface.createFromAsset(this.app.context.getAssets(), "fonts/Bangers-Regular.ttf");
        mPaint.setTypeface(mTypeface);
        mPaint.setTextSize(100);
        mPaint.setARGB(255, 255,255, 255);
    }


    public void update(int score) {
        mScore = score;
        mLives = app.mPlayer.lives;
    }

    public void draw(Canvas canvas) {
        drawScore(canvas);
        drawLives(canvas);
    }

    public void drawScore(Canvas canvas) {
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Score: " + Integer.toString(mScore), app.mScreenSize.y/12, app.mScreenSize.y/12, mPaint);
    }

    public void drawLives(Canvas canvas) {
        mPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Lives: " + Integer.toString(mLives), app.mScreenSize.x - (app.mScreenSize.y/12), app.mScreenSize.y/12, mPaint);
    }

}
