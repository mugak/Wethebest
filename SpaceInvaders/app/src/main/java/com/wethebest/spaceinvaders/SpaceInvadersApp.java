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

import java.util.ArrayList;
import java.util.List;

class SpaceInvadersApp extends SurfaceView implements Runnable {

    private SurfaceHolder mOurHolder;
    private Canvas mCanvas;
    private Paint mPaint;

    private int mScreenX;
    private int mScreenY;

    private long mFPS;
    private final int MILLIS_IN_SECOND = 1000;

    //private Cannon mCannon;
    private SimpleCannon mCannon;
    private Alien mAlien;

    List<Projectile> mProjectiles = new ArrayList<Projectile>();

    List<GameObject> gameObjects = new ArrayList<>();

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
        mCannon = new SimpleCannon(mScreenX);


        startGame();
    }

    private void startGame() {
        mAlien.reset(mScreenX, mScreenY);
        mCannon.reset(mScreenX, mScreenY);


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
                mPaused = false;
                if(motionEvent.getX() > mScreenX / 2) {
                    mCannon.setMovement(mCannon.MOVINGRIGHT);
                }
                else {
                    mCannon.setMovement(mCannon.MOVINGLEFT);
                }

                mProjectiles.add(mCannon.shoot());
                break;

            case MotionEvent.ACTION_UP:
                mCannon.setMovement(mCannon.STOPPED);
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

            for(Projectile mProj : mProjectiles) {
                mCanvas.drawRect(mProj.getRect(), mPaint);
            }
            mCanvas.drawRect(mCannon.getRect(), mPaint);

            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
    }
    private void update() {
        mAlien.update(mFPS);

        for(Projectile mProj : mProjectiles) {
            mProj.update(mFPS);
        }

        mCannon.update(mFPS);
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

        for(Projectile mProj : mProjectiles) {
            if(mAlien.isHit(mProj.getRect())) {
                mAlien.reset(mScreenX, mScreenY); //currently just resets pos, but should remove alien object
            }

        }
    }

}