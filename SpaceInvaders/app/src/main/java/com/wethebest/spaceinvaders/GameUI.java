package com.wethebest.spaceinvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;

public class GameUI {
    private final int PAUSE_BUTTON_SPRITE_ID = R.drawable.pause_button;
    public int mScore;
    public int mLives;
    private Paint mPaint;
    private Bitmap mBitmap;
    private RectF mRect;
    private SpaceInvadersApp app;

    GameUI(SpaceInvadersApp app) {
        this.app = app;

        mScore = 0;
        mLives = app.mPlayer.MAX_LIVES;
        mPaint = new Paint();
        mRect = new RectF();
        Typeface mTypeface = Typeface.createFromAsset(this.app.context.getAssets(), "fonts/Bangers-Regular.ttf");
        mPaint.setTypeface(mTypeface);
        mPaint.setTextSize(app.mScreenSize.x/20);
        mPaint.setARGB(255, 255,255, 255);
    }


    public void update(int score) {
        mScore = score;
        mLives = app.mPlayer.lives;
    }

    public void draw(Canvas canvas) {
        drawScore(canvas);
        drawLives(canvas);
        drawPauseButton(canvas);
    }

    public void drawScore(Canvas canvas) {
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Score: " + Integer.toString(mScore), app.mScreenSize.y/12, app.mScreenSize.y/12, mPaint);
    }

    public void drawLives(Canvas canvas) {
        mPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Lives: " + Integer.toString(mLives), app.mScreenSize.x - app.mScreenSize.y/10 - app.mScreenSize.y/24, app.mScreenSize.y/12, mPaint);
    }

    public void drawPauseButton(Canvas canvas) {
        PointF size = new PointF(app.mScreenSize.y/10, app.mScreenSize.y/10);

        mRect.set(0, 0, size.x, size.y);
        mRect.offsetTo(app.mScreenSize.x - size.x,0);
        mBitmap = BitmapFactory.decodeResource(app.context.getResources(), PAUSE_BUTTON_SPRITE_ID);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) mRect.width(), (int) mRect.height(), true);
        canvas.drawBitmap(mBitmap, mRect.left, mRect.top, null);
    }

}
