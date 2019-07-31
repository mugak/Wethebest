package com.wethebest.spaceinvaders;

/*
AutomaticCounter turns itself back on automatically
Used in player ammo regeneration
 */
public class AutomaticCounter extends Counter {
    AutomaticCounter(float seconds) { super(seconds); }

    @Override
    public void reset() {
        super.reset();
        on = true;
    }
}