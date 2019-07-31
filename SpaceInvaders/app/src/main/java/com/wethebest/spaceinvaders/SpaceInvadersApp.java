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

/*
    SpaceInvadersApp works as a manager of the different game states: wave and gameover
 */
class SpaceInvadersApp extends SurfaceView implements Runnable {

    public SurfaceHolder mOurHolder;
    public Canvas mCanvas;
    public Point mScreenSize; //TODO: maybe this should be public since it's accessed by all GameObjects
    public Context context;

    private Thread mGameThread = null;

    GameState mGameState;
    GameObjectManager mGameObjectManager;

    GameUI mGameUI;
    public int score;
    public long fps;

    private volatile boolean mPlaying;
    public boolean shootNow;
    public Random rand;
    public SoundEngine soundEngine;

    public SpaceInvadersApp(Context context, int x, int y) {
        super(context);
        this.context = context;
        mOurHolder = getHolder();
        mScreenSize = new Point(x, y);

        GameObjectFactory.app = this;

        soundEngine = new SoundEngine(context);
        mGameUI = new GameUI(this);
        fps = 1;

        rand = new Random();

        //start game
        mGameObjectManager = new GameObjectManager(this);
        mGameState = new WaveState(this);
        mGameState.changeState(this, State.WAVE);
    }

    public void setState(GameState newGameState) {
        if(newGameState != null) {
            mGameState = newGameState;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & motionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                     /*else if(mGameState instanceof WaveState) {
                    if (motionEvent.getX() > mScreenSize.x / 2) {
                        mGameObjectManager.mPlayer.setMovement(mGameObjectManager.mPlayer.MOVINGRIGHT);
                    } else {
                        mGameObjectManager.mPlayer.setMovement(mGameObjectManager.mPlayer.MOVINGLEFT);
                    }
                }*/
                shootNow = mGameObjectManager.mPlayer.canShoot();

                break;

            case MotionEvent.ACTION_UP:
                //mGameObjectManager.mPlayer.setMovement(mGameObjectManager.mPlayer.STOPPED);
                break;
        }
        return true;
    }

    public void resume() {
        mPlaying = true;
        mGameThread = new Thread(this);
        mGameThread.start();

        mGameState.changeState(this, State.WAVE);
    }

    public void pause() {
        mPlaying = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

        mGameState.changeState(this, State.PAUSE);
    }

    @Override
    public void run() {
        while(mPlaying) {
            long frameStartTime = System.currentTimeMillis();

            mGameState.run(this);

            long timeThisFrame = System.currentTimeMillis() - frameStartTime;

            //TODO: ensure that timeThisFrame isn't ridiculously high
            if (timeThisFrame > 0) {
                int MILLIS_IN_SECOND = 1000;
                fps = MILLIS_IN_SECOND / timeThisFrame;
                //Log.d("FRAMES", Long.toString(fps));
            }
        }
    }

    public SimpleCannon getPlayer() {
        return mGameObjectManager.mPlayer;
    }
}

