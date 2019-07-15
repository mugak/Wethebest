package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

class Alien implements GameObject {
    private RectF mRect;
    private float mXVelocity;
    private float mAlienWidth;
    private float mAlienHeight;

    //Tells the game whether the object should still be in game
    private boolean isActive;

    private Point mScreenSize;

    private Paint mPaint;

    Alien(Point screenSize) {
        mScreenSize = screenSize;

        mAlienWidth = mScreenSize.x / 10;
        mAlienHeight = mScreenSize.y / 10;

        isActive = true;

        mRect = new RectF();
        mPaint = new Paint();
    }

    public RectF getHitBox() {
        return mRect;
    }

    public void reset(Point location) {
        mRect.left = location.x / 2;
        mRect.top = 0;
        mRect.right = location.x / 2 + mAlienWidth;
        mRect.bottom = mAlienHeight;
        mXVelocity = (location.y / 3);
    }

    public void update(long fps) {
        mRect.left = mRect.left + (mXVelocity / fps);
        mRect.top = mRect.top;

        mRect.right = mRect.left + mAlienWidth;
        mRect.bottom = mRect.top + mAlienHeight;

        checkBounds();
    }

    private void reverseXVelocity() {
        mXVelocity = -mXVelocity;
    }

    private void advance() {
        mRect.top = mRect.top + mAlienHeight;
        mRect.bottom = mRect.top + mAlienHeight; //moves alien down

        if (mRect.left < 0) {
            mRect.left = 0;
            mRect.right = 0 + mAlienWidth;
        } //reset to left edge

        if (mRect.right > mScreenSize.x) {
            mRect.right = mScreenSize.x;
            mRect.left = mScreenSize.x - mAlienWidth;
        } //reset pos to right edge
    }

    public void display(Canvas canvas) {
        mPaint.setColor(Color.argb(255, 255, 255, 255));

        canvas.drawRect(mRect, mPaint);
    }

    public void collide(GameObject gameObject) {
        //SpaceInvaders app already makes this check to make sure the gameObject is a projectile,
        // but this is a good check to make sure the alien class still works if the spaceInvadersApp
        // class changes
        //NOTE: SpaceInvadersApp.java checks for the collision so there is no need to in this class
        //Collide only describes what the class should do when it is collided with
        if (gameObject instanceof Projectile) {
            reset(mScreenSize);
            isActive = false;
        }
    }

    void checkBounds() {
        if (mRect.left < 0 || mRect.right > mScreenSize.x) {
            reverseXVelocity();
            advance();
        }
    }

    public boolean isActive() {
        return isActive;
    }
}