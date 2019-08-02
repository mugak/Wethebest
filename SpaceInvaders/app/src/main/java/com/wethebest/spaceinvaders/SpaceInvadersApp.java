package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/*
    SpaceInvadersApp is the view for the SpaceInvaders activity
    It starts the game, handles the threading, and controls the game states
    It also handles touch events
    Set in SpaceInvaders
 */
class SpaceInvadersApp extends SurfaceView implements Runnable {
    public final boolean debugging = true;
    public SurfaceHolder mOurHolder;
    public Canvas mCanvas;
    public Point mScreenSize;
    public Context context;
    public Paint mPaint;

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
    public DifficultyManager difficultyManager;

    public boolean isGameOver = false;
    public int level = 1;

    public SpaceInvadersApp(Context context, int x, int y) {
        super(context);
        this.context = context;
        mOurHolder = getHolder();
        mScreenSize = new Point(x, y);

        GameObjectFactory.app = this;

        soundEngine = new SoundEngine(context);

        rand = new Random();
        //mGameObjectManager relies on rand
        mGameObjectManager = new GameObjectManager(this);
        //mGameUI relies on mGameObjectManager
        mGameUI = new GameUI(this);

        difficultyManager = new DifficultyManager(mGameObjectManager);

        //This value gets updated very quickly, but starting with a zero value causes problems
        fps = 1;

        mGameState = new WaveState(this);

        mCanvas = new Canvas();
        mPaint = new Paint();//For printing debugging

    }

    //This class acts the context for the game states
    public void setState(GameState newGameState) {
        if(newGameState != null) {
            mGameState = newGameState;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & motionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                shootNow = mGameObjectManager.mPlayer.canShoot();
                break;

            case MotionEvent.ACTION_UP:

                mGameUI.pauseButton(new PointF(motionEvent.getX(), motionEvent.getY() ));
                break;
        }
        return true;
    }

    public void resume() {
        mPlaying = true;
        mGameThread = new Thread(this);
        mGameThread.start();
        mGameState.changeState(this, State.WAVE);
        soundEngine.resume();

    }

    public void pause() {
        mPlaying = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

        mGameState.changeState(this, State.PAUSE);
        soundEngine.pause();
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

    //Is called by wave state when the aliens win the game
    public void newGame() {
        score = 0;

        rand = new Random();

        //mGameObjectManager relies on rand
        mGameObjectManager = new GameObjectManager(this);
        //mGameUI relies on mGameObjectManager
        mGameUI = new GameUI(this);

        difficultyManager = new DifficultyManager(mGameObjectManager);

        fps = 1;

        mGameState = new WaveState(this);
    }

    public SimpleCannon getPlayer() {
        return mGameObjectManager.mPlayer;
    }

}

