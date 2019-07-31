package com.wethebest.spaceinvaders;

/*
Counter counts a specified number of seconds by decrementing frames
Instance variable in Alien and Cannon
Used for player firing rate and invincibility.
 */

public class Counter {
    protected boolean on;
    protected boolean running;
    protected boolean done;
    protected float frameCount;
    protected float seconds;

    Counter(float seconds) {
        reset();
        setSeconds(seconds);
    }

    private void count() {
        frameCount--;
        if(frameCount <= 0) {
            done = true;
        }
    }

    public void reset() {
        frameCount = 0;
        on = false;
        done = false;
        running = false;
    }

    public void setSeconds(float seconds) {
        this.seconds = seconds;
    }

    public boolean run(long fps) {
        if(on && !running) {
            if(fps > 20 && fps < 100) { //keeps fps within reasonable range - sometimes fps is 1 or 1000
                frameCount = fps * seconds;
                running = true;
            }
        }
        else if(on && running) {
            count();
        }

        if(!done) {
            return false;
        } //return false when not done

        reset();
        return true; //reset the counter and return true when its done
    }
}