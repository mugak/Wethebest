package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
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

    private Point mScreenSize;

    private long mFPS;
    private final int MILLIS_IN_SECOND = 1000;

    LinkedList<Projectile> mProjectiles = new LinkedList<Projectile>();

    LinkedList<GameObject> gameObjects = new LinkedList<>();
    SimpleCannon mPlayer;

    private Thread mGameThread = null;
    private volatile boolean mPlaying;
    private boolean mPaused = true;


    public SpaceInvadersApp(Context context, int x, int y) {
        super(context);
        mOurHolder = getHolder();

        mScreenSize = new Point(x, y);

        gameObjects.add(new Alien(mScreenSize));

        mPlayer = new SimpleCannon(mScreenSize);
        gameObjects.add(mPlayer);

        startGame();
    }

    private void startGame() {
        for(GameObject gameObject : gameObjects) {
            gameObject.reset(mScreenSize);
        }
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

            draw();

            removeInactiveObjects();

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
                if(motionEvent.getX() > mScreenSize.x / 2) {
                    mPlayer.setMovement(mPlayer.MOVINGRIGHT);
                }
                else {
                    mPlayer.setMovement(mPlayer.MOVINGLEFT);
                }

                gameObjects.add(mPlayer.shoot());
                break;

            case MotionEvent.ACTION_UP:
                mPlayer.setMovement(mPlayer.STOPPED);
                break;
        }
        return true;
    }

    private void draw() {
        if (mOurHolder.getSurface().isValid()) {
            mCanvas = mOurHolder.lockCanvas();

            mCanvas.drawColor(Color.argb(255, 26, 128, 182));

            /*mCanvas.drawRect(mAlien.getRect(), mPaint);

            for(Projectile mProj : mProjectiles) {
                mCanvas.drawRect(mProj.getRect(), mPaint);
            }
            mCanvas.drawRect(mCannon.getRect(), mPaint);*/

            for (GameObject gameObject : gameObjects) {
                gameObject.display(mCanvas);
            }

            mOurHolder.unlockCanvasAndPost(mCanvas);
        }
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

        //Checks to see if the first object is a projectile because in SpaceInvaders only
        // projectiles collide with non projectiles. There are no other types of collisions
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
        if(RectF.intersects(object1.getHitBox(), object2.getHitBox())) {
            object1.collide(object2);
            object2.collide(object1);
        }
    }

    private void removeInactiveObjects() {
        Iterator<GameObject> gameObjectIterator = gameObjects.iterator();

        while(gameObjectIterator.hasNext()) {
            GameObject gameObject = gameObjectIterator.next();

            if(!gameObject.isActive()) {
                gameObjectIterator.remove();
            }
        }
    }

}