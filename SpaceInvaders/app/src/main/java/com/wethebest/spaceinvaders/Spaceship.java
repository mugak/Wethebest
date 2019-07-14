package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.RectF;


public class Spaceship {

    private RectF mRect;
    private float mXVelocity;
    private float mSpaceshipWidth;
    private float mSpaceshipHeight;
    private float mShootVelocity;
    private int mMovementState;

    private final float WIDTH_DIVIDER = 8.0;
    private final float HEIGHT_DIVIDER = 10.0;
    private final float X_DIVIDER = 2.0;
    private final float Y_DIVIDER = 1.2;
    private final float INITIAL_VELOCITY_DIVIDER = 5.0;
    private final int LEFT = 0, RIGHT = 1;

    // screenX is the width of the screen
    public Spaceship(int screenX) {
        mSpaceshipWidth = screenX / WIDTH_DIVIDER;
        mSpaceshipHeight = screenX / HEIGHT_DIVIDER;
        mRect = new RectF();
    }

    // initializes both size & other member variables
    public Spaceship(int screenX, int screenY) {
        mSpaceshipWidth = screenX / WIDTH_DIVIDER;
        mSpaceshipHeight = screenX / HEIGHT_DIVIDER;
        mRect = new RectF();

        this.reset(screenX, screenY);
    }

    public RectF getRect() {
        return mRect;
    }

    // Initialize the 4 points of the rectangle which defines the spaceship
    // screenX and screenY are the width and height of the screen
    public void reset(int screenX, int screenY) {
        mRect.left = screenX / X_DIVIDER;
        mRect.top = screenY / Y_DIVIDER;
        mRect.right = mRect.left + mSpaceshipWidth;
        mRect.bottom = mRect.top + mSpaceshipHeight;
        mXVelocity = (screenX / INITIAL_VELOCITY_DIVIDER);
    }

    // shoot projectiles upwards, hy                                                                                                        y67
    public void shoot(int screenX) {
        // initialize new projectile with width of screen
        Projectile p = new Projectile(screenX);
        p.setPos(mRect.centerX(), mRect.top);
        while()
    }

    public void setShootVelocity(float shootVelocity) {
        mShootVelocity = shootVelocity;
    }

    // Updates position of spaceship when player moves it
    // Might need to represent direction some other way
    public void update(long fps, int direction) {

        switch (direction) {

            // BASE_SPEED int in UI or SpaceInvadersApplication
            case LEFT: mRect.set(mRect.left - (mXVelocity/fps),
                                 mRect.top, mRect.right - (mXVelocity/fps),
                                 mRect.bottom);
            case RIGHT: mRect.set(mRect.left + (mXVelocity/fps),
                    mRect.top, mRect.right + (mXVelocity/fps),
                    mRect.bottom);
        }
    }


    // checks whether it's been hit by a projectile
    public boolean beenHit(Projectile p) {


    }

    // upgrade is a multiplier of the current velocity
    public void shootUpgrade(float upgrade) {
        mShootVelocity *= upgrade;
    }

    // upgrade is a multiplier of the current velocity
    public void moveUpgrade(float upgrade) {
        mXVelocity *= upgrade;
    }

    public void setmMovementState(int movementState) {
        mMovementState = movementState;
        // error if not LEFT or RIGHT
    }

}