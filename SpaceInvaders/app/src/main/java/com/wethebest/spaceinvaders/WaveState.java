package com.wethebest.spaceinvaders;

/*
    This state represents the core game play where the player shoots down an advancing wave of
    aliens
 */
public class WaveState implements GameState {
    @Override
    public void changeState(SpaceInvadersApp app, State nextState) {
        switch(nextState) {
            case PAUSE:
                app.setState(new PauseState());
            case GAMEOVER:
                app.setState(new GameOverState());
        }
    }

    @Override
    public void run() {

    }

    @Override
    public void draw() {

    }
}
