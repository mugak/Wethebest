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
        draw();
    }

    private void draw() {
        if (mApp.mOurHolder.getSurface().isValid()) {
            mApp.mCanvas = mApp.mOurHolder.lockCanvas();

            mApp.mCanvas.drawColor(Color.argb(255, 0, 0, 0));

            mApp.mOurHolder.unlockCanvasAndPost(mApp.mCanvas);
        }
    }
}
