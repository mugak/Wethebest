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

import java.util.Iterator;
import java.util.LinkedList;

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

    LinkedList<Projectile> mProjectiles = new LinkedList<Projectile>();

    LinkedList<GameObject> gameObjects = new LinkedList<>();

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
                for (GameObject object : gameObjects) {
                    object.update(mFPS);
                }

                detectCollisions();
            }

            for (GameObject object : gameObjects) {
                object.display();
            }

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

    /*private void draw() {
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
    }*/

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
        /*if(mAlien.getRect().left < 0 || mAlien.getRect().right > mScreenX) {
            mAlien.reverseXVelocity();
            mAlien.advance();
        }


        Iterator<Projectile> i = mProjectiles.iterator(); //needs iterator so projectiles can be removed in a loop
        while (i.hasNext()) {
            Projectile mProj = i.next();

            if(mProj.getRect().top <= 0) {
                i.remove();
            }

            else if(mAlien.isHit(mProj.getRect())) {
                mAlien.reset(mScreenX, mScreenY); //currently just resets pos
                i.remove();
            }

        }*/

        Iterator<GameObject> firstObjectItr = gameObjects.iterator();
        while(firstObjectItr.hasNext()) {
            GameObject object1 = firstObjectItr.next();

            if(object1 instanceof Projectile) {
                Iterator<GameObject> secondObjectItr = gameObjects.iterator();

                while(secondObjectItr.hasNext()) {
                    GameObject object2 = secondObjectItr.next();

                    if(!(object2 instanceof  Projectile)) {
                        collide(object1, object2);
                    }
                }
            }
        }
    }

    private void collide(GameObject object1, GameObject object2) {
        object1.collide(object2);
        object2.collide(object1);
    }

}