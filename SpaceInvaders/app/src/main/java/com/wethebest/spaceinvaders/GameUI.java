package com.wethebest.spaceinvaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;

/*
GameUI draws the UI while the game is playing
It updates the score, player lives, and ammo, and the red square that lets the player know they can't shoot
Instantiated in SpaceInvadersApp
 */

public class GameUI {
    private final int PAUSE_BUTTON_SPRITE_ID = R.drawable.pause_button;
    public int mScore;
    public int mLives;
    public int mAmmo;
    private Paint mPaint;
    private Bitmap mBitmap;
    private RectF mRect;
    private SpaceInvadersApp app;
    private long fps = 0;

    GameUI(SpaceInvadersApp app) {
        this.app = app;

        mScore = 0;
        mLives = app.getPlayer().MAX_LIVES;
        mAmmo = app.getPlayer().maxAmmo;
        mPaint = new Paint();
        mRect = new RectF();
        Typeface mTypeface = Typeface.createFromAsset(this.app.context.getAssets(), "fonts/Bangers-Regular.ttf");
        mPaint.setTypeface(mTypeface);
        mPaint.setTextSize(app.mScreenSize.y / 12);
    }


    public void update(int score) {
        mScore = score;
        mLives = app.getPlayer().lives;
        mAmmo = app.getPlayer().ammo;

    }

    public void draw(Canvas canvas) {
        drawAmmo(canvas);
        drawLives(canvas);
        drawScore(canvas);
        drawPauseButton(canvas);
        if (app.getPlayer().waitToShoot.on) {
            drawWaitToShoot(canvas);
        }

        printDebugText();
    }

    public void drawAmmo(Canvas canvas) {
        mPaint.setARGB(255, 255, 255, 255);
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Ammo: " + Integer.toString(mAmmo), app.mScreenSize.y / 12, app.mScreenSize.y / 12, mPaint);
    }

    public void drawLives(Canvas canvas) {
        mPaint.setARGB(255, 255, 255, 255);
        mPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Lives: " + Integer.toString(mLives), app.mScreenSize.x - app.mScreenSize.y / 10 - app.mScreenSize.y / 24, app.mScreenSize.y / 12, mPaint);
    }

    public void drawScore(Canvas canvas) {
        mPaint.setARGB(255, 255, 255, 255);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("Score: " + Integer.toString(mScore), app.mScreenSize.x / 2, app.mScreenSize.y / 12, mPaint);
    }

    public void drawPauseButton(Canvas canvas) {
        PointF size = new PointF(app.mScreenSize.y / 10, app.mScreenSize.y / 10);

        mRect.set(0, 0, size.x, size.y);
        mRect.offsetTo(app.mScreenSize.x - size.x, 0);
        mBitmap = BitmapFactory.decodeResource(app.context.getResources(), PAUSE_BUTTON_SPRITE_ID);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) mRect.width(), (int) mRect.height(), true);
        canvas.drawBitmap(mBitmap, mRect.left, mRect.top, null);
//        MotionEvent motionEvent = MotionEvent.;
//        if(app.onTouchEvent(MotionEvent.ACTION_UP){
//
//        }
    }

    public void drawWaitToShoot(Canvas canvas) {
        mPaint.setARGB(255, 255, 0, 0);
        canvas.drawRect(0, app.mScreenSize.y / 12 - app.mScreenSize.x / 20, app.mScreenSize.y / 13, app.mScreenSize.y / 12, mPaint);
    }


    public void printDebugText() {
        int textSize = app.mScreenSize.y / 15;
        mPaint.setTextSize(textSize);
        mPaint.setARGB(255, 0, 0, 255);


        //long timeThisFrame = System.currentTimeMillis() - frameStartTime;
        //Print fps every second
//
//        if (System.currentTimeMillis() % 1000 == 1000) {
//            fps = fps +1;
//        }

    int textX = app.mScreenSize.x*3/4;
    int textY = app.mScreenSize.y/4;

        app.mCanvas.drawText("FPS=" + app.fps, textX, textY, mPaint);
        textY += textSize;
        app.mCanvas.drawText("debugging=" + app.debugging, textX, textY, mPaint);
        textY += textSize;
        app.mCanvas.drawText("shootNow=" + app.shootNow, textX, textY, mPaint);
        textY += textSize;
        app.mCanvas.drawText("mScreenSize=" + app.mScreenSize.x + " x " + app.mScreenSize.y, textX, textY, mPaint);
        textY += textSize;
        app.mCanvas.drawText("mPlayer x pos=" + (short)(app.mGameObjectManager.mPlayer.mHitBox.centerTop().x), textX, textY, mPaint);

    }
}
