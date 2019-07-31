package com.wethebest.spaceinvaders;

import android.graphics.Color;

public class PauseState implements GameState {
    SpaceInvadersApp mApp;

    public PauseState(SpaceInvadersApp app) {
        mApp = app;
    }

    @Override
    public void changeState(SpaceInvadersApp app, State nextState) {
        switch(nextState) {
            case WAVE:
                app.setState(new WaveState(app));
        }
    }

    @Override
    public void run(SpaceInvadersApp app) {
        app.mGameUI.update(app.score);
        draw();
    }

    private void draw() {
        if (mApp.mOurHolder.getSurface().isValid()) {
            mApp.mCanvas = mApp.mOurHolder.lockCanvas();

            mApp.mGameObjectManager.displayGameObjects(mApp.mCanvas);
            mApp.mGameUI.draw(mApp.mCanvas);
            mApp.mCanvas.drawColor(Color.argb(150, 0, 0, 0));

            mApp.mOurHolder.unlockCanvasAndPost(mApp.mCanvas);
        }
    }
}
