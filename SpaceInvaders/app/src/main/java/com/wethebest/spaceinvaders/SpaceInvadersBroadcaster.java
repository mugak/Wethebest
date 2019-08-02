package com.wethebest.spaceinvaders;

public interface SpaceInvadersBroadcaster {
    // This allows the Player and UI Controller components
    // to add themselves as listeners of the GameEngine class
    void addObserver(InputObserver o);
    
}
