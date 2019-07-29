package com.wethebest.spaceinvaders;

import android.graphics.PointF;

public class PlayerProj extends GameObject {
    public PlayerProj(SpaceInvadersApp app) {
        super(app, new PointF(app.mScreenSize.x / 80, app.mScreenSize.x / 40), R.drawable.projectile_a, -app.mScreenSize.y);
        //super(app, size, sprite, velocity)
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
