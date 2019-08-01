package com.wethebest.spaceinvaders;

import android.graphics.PointF;

/*
UFO moves across the top of the screen
Gives extra points if hit
Instantiated by UFOSpawner
 */

public class UFO extends GameObject {
    UFO(SpaceInvadersApp app, PointF size, int spriteID, PointF position, float velocity) {
        super(app, size, spriteID, position, velocity);
    }

    public void playAudio() { }

    public void update(long fps){
        mHitBox.moveHorizontally(mHitBox.velocity / fps);
        checkBounds();
    }

    public void collide(GameObject gameObject) {
        if((gameObject instanceof PlayerProj)) {
            isActive = false;
            app.score += 500;
        }
    }

    public void checkBounds() {
        if(mHitBox.horizontalOutOfBounds()) {
            isActive = false;
        }
    }
}
