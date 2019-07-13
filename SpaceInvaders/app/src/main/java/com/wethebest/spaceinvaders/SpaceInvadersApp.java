package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class SpaceInvadersApp extends SurfaceView implements Runnable {

    private SurfaceHolder mOurHolder;
    private Canvas mCanvas;
    private Paint mPaint;

    private long mFPS;
    private final int MILLIS_IN_SECOND = 1000;
    
    private Cannon mCannon;
    private Alien mAlien;

    private Thread mGameThread = null;
    private volatile boolean mPlaying;
    private boolean mPaused = true;


    public SpaceInvadersApp(Context context, int x, int y) {
        super(context);
        mOurHolder = getHolder();
        mPaint = new Paint();

        startGame();
    }

    private void startGame() {

    }

    @Override
    public void run() {
        while (mPlaying) {
            long frameStartTime = System.currentTimeMillis();

            if(!mPaused) {
                update();
                detectCollisions();
            }

            draw();

            long timeThisFrame = System.currentTimeMillis() - frameStartTime;

            if(timeThisFrame > 0) {
                mFPS = MILLIS_IN_SECOND / timeThisFrame;
            }
        }

    }

    private void draw() {
        if (mOurHolder.getSurface().isValid()) {
            mCanvas = mOurHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255, 26, 128, 182));
            mPaint.setColor(Color.argb(255, 255, 255, 255));


            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
    }
    private void update() {

    }

    private void detectCollisions() {

    }

}