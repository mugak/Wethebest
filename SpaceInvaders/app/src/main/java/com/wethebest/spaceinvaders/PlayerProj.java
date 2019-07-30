package com.wethebest.spaceinvaders;

import android.graphics.PointF;

public class PlayerProj extends GameObject {
    public PlayerProj(SpaceInvadersApp app, PointF size, int spriteID, PointF position, float velocity) {
        super(app, size, spriteID, position, velocity);
    }
    public void playAudio() { }

    public void update(long fps){
        mHitBox.moveVertically(mHitBox.velocity/fps);
    }

    public void collide(GameObject gameObject) {
        if((gameObject instanceof BarrierBlock || gameObject instanceof Alien)) {
            isActive = false;
        }
    }

    public void checkBounds() {
        if(mHitBox.topOutOfBounds()|| mHitBox.bottomOutOfBounds()) {
            isActive = false;
        }
    }
}
