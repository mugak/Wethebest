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

import java.util.Iterator;
import java.util.LinkedList;

class SpaceInvadersApp extends SurfaceView implements Runnable {

    private SurfaceHolder mOurHolder;
    private Canvas mCanvas;
    public Point mScreenSize; //TODO: maybe this should be public since it's accessed by all GameObjects
    public Context context;

    private long mFPS;

    private Thread mGameThread = null;

    GameState mGameState;

    public int score;

    public boolean shootNow;

    public SpaceInvadersApp(Context context, int x, int y) {
        super(context);
        this.context = context;
        mOurHolder = getHolder();
        mScreenSize = new Point(x, y);

        GameObjectFactory.app = this;

        //start game
        mGameState = new WaveState();
    }

    public void setState(GameState newGameState) {
        if(newGameState != null) {
            mGameState = newGameState;
        }
    }

    private boolean isGameOver() {
        return mPlayer.lives == 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & motionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mPaused = false;
                if (motionEvent.getX() > mScreenSize.x / 2) {
                    mPlayer.setMovement(mPlayer.MOVINGRIGHT);
                } else {
                    mPlayer.setMovement(mPlayer.MOVINGLEFT);
                }

                shootNow = true;
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

            mCanvas.drawColor(Color.argb(255, 0, 0, 0));


            for (GameObject gameObject : gameObjects) {
                gameObject.display(mCanvas);
            }

            mAlienArmy.draw(mCanvas);

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

                for (GameObject alienObject : mAlienArmy.allAliens) {
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

        Iterator<Alien> alienObjectIterator = mAlienArmy.allAliens.iterator();

        while (alienObjectIterator.hasNext()) {
            Alien alienObject = alienObjectIterator.next();

            if (!alienObject.isActive()) {
                alienObjectIterator.remove();
            }
        }

    }
}
//    private void addAlienProjs() {
//        LinkedList<GameObject> alienProjs = new LinkedList<>(); // need temp list because can't modify Collections being iterated
//
//        for (GameObject gameObject : gameObjects) {
//            if (gameObject instanceof Alien) {
//                if (((Alien) gameObject).shootNow) {
//                    alienProjs.add(((Alien) gameObject).shoot());
//                    ((Alien) gameObject).shootNow = false;
//                }
//            }
//
//        }
//        gameObjects.addAll(alienProjs);
//    }
