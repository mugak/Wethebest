package com.wethebest.spaceinvaders;

/*
AutomaticCounter turns itself back on automatically
Instance variable in Cannon and Alien
Used in player ammo regeneration and shooting
 */
public class AutomaticCounter extends Counter {
    AutomaticCounter(float seconds) { super(seconds); }

    @Override
    public void reset() {
        super.reset();
        on = true;
    }
}