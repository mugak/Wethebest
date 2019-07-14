package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class SpaceInvadersApp extends SurfaceView implements Runnable {

    private SurfaceHolder mOurHolder;
    private Canvas mCanvas;
    private Paint mPaint;

    private int mScreenX;
    private int mScreenY;

    private long mFPS;
    private final int MILLIS_IN_SECOND = 1000;

    //private Spaceship mSpaceship;
    private Alien mAlien;


    private Projectile mProjectile;

    private Thread mGameThread = null;
    private volatile boolean mPlaying;
    private boolean mPaused = true;


    public SpaceInvadersApp(Context context, int x, int y) {
        super(context);
        mOurHolder = getHolder();
        mPaint = new Paint();

        mScreenX = x;
        mScreenY = y;

        mAlien = new Alien(mScreenX);
        mProjectile = new Projectile(mScreenX);


        startGame();
    }

    private void startGame() {
        mAlien.reset(mScreenX, mScreenY);
        mProjectile.setPos(mScreenX / 2, mScreenY / 2);


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

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & motionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mPaused = !mPaused;
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void draw() {
        if (mOurHolder.getSurface().isValid()) {
            mCanvas = mOurHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255, 26, 128, 182));
            mPaint.setColor(Color.argb(255, 255, 255, 255));

            mCanvas.drawRect(mAlien.getRect(), mPaint);
            mCanvas.drawRect(mProjectile.getRect(), mPaint);

            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
    }
    private void update() {
        mAlien.update(mFPS);
        mProjectile.update(mFPS);
    }

    public void resume() {
        mPlaying = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    public void pause() {
        mPlaying = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");

        }

    }


    private void detectCollisions() {
        if(mAlien.getRect().left < 0 || mAlien.getRect().right > mScreenX) {
            mAlien.reverseXVelocity();
            mAlien.advance();
        }

    }

}