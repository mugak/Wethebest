package com.wethebest.spaceinvaders;

import android.graphics.RectF;

class Alien {
    private RectF mRect;
    private float mXVelocity;
    private float mAlienWidth;
    private float mAlienHeight;

    Alien(int screenX) {
        mAlienWidth = screenX / 10;
        mAlienHeight = screenX / 10;
        mRect = new RectF();
    }

    RectF getRect() {
        return mRect;
    }

    public void reset(int x, int y) {
        mRect.left = x / 2;
        mRect.top = 0;
        mRect.right = x / 2 + mAlienWidth;
        mRect.bottom = mAlienHeight;
        mXVelocity = (y / 3);
    }

    void update(long fps) {
        mRect.left = mRect.left + (mXVelocity / fps);
        mRect.top = mRect.top;

        mRect.right = mRect.left + mAlienWidth;
        mRect.bottom = mRect.top + mAlienHeight;
    }

    void reverseXVelocity() {
        mXVelocity = -mXVelocity;
    }

    void advance() {
        mRect.top = mRect.top + mAlienHeight;
        mRect.bottom = mRect.top + mAlienHeight;
    }
}