package com.wethebest.spaceinvaders;

public class Counter {
    public boolean on = false;
    public boolean isCountingDown = false;
    private float frameCount = 0;
    private float seconds;

    Counter(float seconds) {
        this.seconds = seconds;
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

}
