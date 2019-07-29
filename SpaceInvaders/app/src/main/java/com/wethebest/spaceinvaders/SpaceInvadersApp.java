package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

class SpaceInvadersApp extends SurfaceView implements Runnable {

    private SurfaceHolder mOurHolder;
    private Canvas mCanvas;
    public Point mScreenSize; //TODO: maybe this should be public since it's accessed by all GameObjects
    public Context context;

    private long mFPS;

    LinkedList<GameObject> gameObjects;
    SimpleCannon mPlayer;
    AlienArmy mAlienArmy;
    LinkedList<Barrier> mBarriers = new LinkedList<Barrier>();

    private Thread mGameThread = null;
    private volatile boolean mPlaying;
    private boolean mPaused = true;

    GameUI mGameUI;
    public int score;

    private boolean shootNow;
    public Random rand;
    public SoundEngine soundEngine;




    public SpaceInvadersApp(Context context, int x, int y) {
        super(context);
        this.context = context;
        mOurHolder = getHolder();
        mScreenSize = new Point(x, y);

        GameObjectFactory.app = this;

        //SoundEngine
        soundEngine = new SoundEngine(context);

        startGame();
    }

    private void createBarriers(int numBarriers) {
        for (int i = 1; i < numBarriers + 1; i++) {
            PointF barrierCenterPosition = Util.computeBarrierPosition(i, numBarriers, mScreenSize);

            addBarrierToGameObjects(new Barrier(mScreenSize, barrierCenterPosition));
        }
    }

    private void addBarrierToGameObjects(Barrier barrier) {
        mBarriers.add(barrier);
        gameObjects.addAll(barrier.getBarrierBlocks());
    }

    private void startGame() {
        rand = new Random();
        gameObjects = new LinkedList<>();

        mPlayer = new SimpleCannon(this);
        mAlienArmy = new AlienArmy(this);

        gameObjects.addAll(mAlienArmy.aliens);
        gameObjects.add(mPlayer);

        score = 0;
        mGameUI = new GameUI(this);

        for (GameObject gameObject : gameObjects) {
            gameObject.reset(mScreenSize);
        }

        createBarriers(3);
    }

    private boolean isGameOver() {
            return mPlayer.lives == 0;
    }

    @Override
    public void run() {
        while (mPlaying) {
            long frameStartTime = System.currentTimeMillis();

            if (!mPaused) {

                if(shootNow) {
                    gameObjects.add(mPlayer.shoot());
                    shootNow = false;
                }

                for (GameObject object : gameObjects) {
                    object.update(mFPS);
                }

                mAlienArmy.update(mFPS);
                gameObjects.addAll(mAlienArmy.getAlienProjs());
                detectCollisions();
            }

            removeInactiveObjects();
            mAlienArmy.removeInactiveObjects();

            mGameUI.update(score);
            draw();

            long timeThisFrame = System.currentTimeMillis() - frameStartTime;

            if (timeThisFrame > 0) {
                final int MILLIS_IN_SECOND = 1000;
                mFPS = MILLIS_IN_SECOND / timeThisFrame;
            }

            if (isGameOver()) {
                startGame();
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & motionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mPaused = false;
//                if (motionEvent.getX() > mScreenSize.x / 2) {
//                    mPlayer.setMovement(mPlayer.MOVINGRIGHT);
//                } else {
//                    mPlayer.setMovement(mPlayer.MOVINGLEFT);
//                }
                shootNow = true;

                break;

            case MotionEvent.ACTION_UP:
                //mPlayer.setMovement(mPlayer.STOPPED);
                break;
        }
        return true;
    }

    private void draw() {
        if (mOurHolder.getSurface().isValid()) {
            mCanvas = mOurHolder.lockCanvas();

            mCanvas.drawColor(Color.argb(255, 0, 0, 0));


            for (GameObject gameObject : gameObjects) {
                gameObject.display(mCanvas);
                gameObject.playAudio();
            }

            mAlienArmy.draw(mCanvas);
            mGameUI.draw(mCanvas);
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
        //Checks to see if the first object is a projectile because in SpaceInvaders only
        // projectiles collide with non projectiles. There are no other types of collisions
        Iterator<GameObject> firstObjectItr = gameObjects.iterator();
        while (firstObjectItr.hasNext()) {
            GameObject object1 = firstObjectItr.next();
            if (object1 instanceof Projectile) {

                for (GameObject object2 : gameObjects) {
                    if (!(object2 instanceof Projectile)) {
                        collide(object1, object2);
                    }
                }

                for (GameObject alienObject : mAlienArmy.aliens) {
                    collide(object1, alienObject);
                }
            }
        }
    }

    private void collide(GameObject object1, GameObject object2) {
        if (RectF.intersects(object1.getHitBox(), object2.getHitBox())) {
            object1.collide(object2);
            object2.collide(object1);
        }
    }

    private void removeInactiveObjects() {
        Iterator<GameObject> gameObjectIterator = gameObjects.iterator();

        while (gameObjectIterator.hasNext()) {
            GameObject gameObject = gameObjectIterator.next();

            if (!gameObject.isActive()) {
                gameObjectIterator.remove();
            }
        }
    }
}

