package com.wethebest.spaceinvaders;

import android.graphics.Color;

/*
    This state represents the core game play where the player shoots down an advancing wave of
    aliens
 */
public class WaveState implements GameState {
    private long mFPS;
    private SpaceInvadersApp mApp;

    public WaveState(SpaceInvadersApp app) {
        mApp = app;
        mApp.score = 0;
        app.mGameObjectManager = new GameObjectManager(app);
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
            case GAMEOVER:
                app.setState(new GameOverState());
                break;
        }
    }

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

        if (isGameOver(app)) {
            changeState(app, State.WAVE);
        }
    }

    private void draw() {
        if (mApp.mOurHolder.getSurface().isValid()) {
            mApp.mCanvas = mApp.mOurHolder.lockCanvas();

            mApp.mCanvas.drawColor(Color.argb(255, 0, 0, 0));

            mApp.mGameObjectManager.displayGameObjects(mApp.mCanvas);
            mApp.mGameUI.draw(mApp.mCanvas);

            mApp.mOurHolder.unlockCanvasAndPost(mApp.mCanvas);
        }
    }

    private boolean isGameOver(SpaceInvadersApp app) {
        return app.mGameObjectManager.mPlayer.lives == 0;
    }
}
