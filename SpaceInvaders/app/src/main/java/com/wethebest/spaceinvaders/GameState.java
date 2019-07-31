package com.wethebest.spaceinvaders;

public interface GameState {
    void changeState(SpaceInvadersApp app, State nextState);
    void run(SpaceInvadersApp app);
}
