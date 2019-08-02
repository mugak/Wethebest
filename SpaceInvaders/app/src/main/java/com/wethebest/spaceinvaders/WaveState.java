package com.wethebest.spaceinvaders;

import android.graphics.Color;
import android.graphics.Paint;

/*
    WaveState represents the core game play where the player shoots down an advancing wave of
    aliens
    Default state in SpaceInvadersApp
    PauseState changes to WaveState when game is unpaused
 */
public class WaveState implements GameState {
    private SpaceInvadersApp mApp;


    public WaveState(SpaceInvadersApp app) {
        mApp = app;
    }

    @Override
    public void changeState(SpaceInvadersApp app, State nextState) {
        switch(nextState) {
            case WAVE:
                app.setState(new WaveState(app));
                break;
            case PAUSE:
                app.setState(new PauseState(app));
                break;
        }
    }

    /*
        This function is called by spaceinvaders which implements runnable
        Essentially this is the functionality that is called every frame of the game loop
        Creates player shots
        Updates all gameobject states
        Draws UI and screen
        And determines if the game has ended
     */
    @Override
    public void run(SpaceInvadersApp app) {
        if(app.shootNow) {
            //This seems needs to change
            //Maybe playercannon refactor to fix this
            app.mGameObjectManager.add(app.mGameObjectManager.mPlayer.shoot());
            app.shootNow = false;
        }

        app.mGameObjectManager.updateGameObjectStates(app.fps);
        app.mGameUI.update(app.score);

        draw();

        if (app.isGameOver) {
            app.level = 1;
            app.newGame();
        } else if (isWaveDefeated(app)) {
            app.level++;
            app.mGameObjectManager.mAlienArmy.spawnNewWave();
            app.difficultyManager.increaseGameDifficulty();
            changeState(app, State.WAVE);
        }
    }

    //Display the game
    private void draw() {
        if (mApp.mOurHolder.getSurface().isValid()) {
            mApp.mCanvas = mApp.mOurHolder.lockCanvas();

            mApp.mCanvas.drawColor(Color.argb(255, 135, 206, 250));

            mApp.mGameObjectManager.displayGameObjects(mApp.mCanvas);
            mApp.mGameUI.draw(mApp.mCanvas);

            mApp.mOurHolder.unlockCanvasAndPost(mApp.mCanvas);

//            mApp.mCanvas.drawText("FPS=" + mFPS, 10f, 150f, mPaint);

        }
    }

   //Checks to see if the current wave has been defeated
    private boolean isWaveDefeated(SpaceInvadersApp app) {
        return app.mGameObjectManager.allAliensDefeated();
    }
}
