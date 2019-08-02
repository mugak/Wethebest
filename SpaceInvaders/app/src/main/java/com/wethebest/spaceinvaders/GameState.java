package com.wethebest.spaceinvaders;
/*
Implemented by WaveState, PauseState
 */
public interface GameState {
    void changeState(SpaceInvadersApp app, State nextState);
    void run(SpaceInvadersApp app);
}
