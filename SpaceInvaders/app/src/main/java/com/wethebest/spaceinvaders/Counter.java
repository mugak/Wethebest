package com.wethebest.spaceinvaders;

/*
Counter counts a specified number of seconds by decrementing frames
Instance variable in Alien and Cannon
Used for enemies shooting randomly, player ammo regeneration, firing rate, and invincibility.
 */

public class Counter {
    public boolean on = false;
    public boolean isCountingDown = false;
    private float frameCount = 0;
    private float seconds;

    Counter(float seconds) {
        setSeconds(seconds);
    }

    public void setFPS(long fps) {
        frameCount = fps * seconds;
        isCountingDown = true;
    }

    public boolean finished() {
        frameCount--;
        if(frameCount <= 0) {
            on = false;
            isCountingDown = false;
        }
        return !on;
    }

    public void reset() {
        frameCount = 0;
        on = false;
        isCountingDown = false;
    }

    public void setSeconds(float seconds) {
        this.seconds = seconds;
    }
}
