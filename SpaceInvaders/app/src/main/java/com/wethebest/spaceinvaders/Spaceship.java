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

    private final float WIDTH_DIVIDER = 8f;
    private final float HEIGHT_DIVIDER = 10f;
    private final float X_DIVIDER = 2f;
    private final float Y_DIVIDER = 1.2f;
    private final float INITIAL_VELOCITY_DIVIDER = 5f;
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
        // I still don't think we should separate projectile into 2 classes
        // but go off I guess
        Projectile p = new PlayerProj(screenX);
        p.setPos((int)mRect.centerX(), (int)mRect.top);

    }

    public void setShootVelocity(float shootVelocity) {
        mShootVelocity = shootVelocity;
    }

    // Updates position of spaceship when player moves it
    // Might need to represent direction some other way
    public void update(long fps, int direction) {

        switch (direction) {

            case LEFT: mRect.set(mRect.left - (mXVelocity/fps),
                                 mRect.top, mRect.right - (mXVelocity/fps),
                                 mRect.bottom);
            case RIGHT: mRect.set(mRect.left + (mXVelocity/fps),
                    mRect.top, mRect.right + (mXVelocity/fps),
                    mRect.bottom);
            //default: error
        }
    }


    // checks whether it's been hit by a projectile
    public boolean collisionDetection(Projectile p) {

        return false;
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