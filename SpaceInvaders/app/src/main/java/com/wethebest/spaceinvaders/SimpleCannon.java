package com.wethebest.spaceinvaders;

import android.graphics.RectF;

class SimpleCannon {
    private RectF mRect;
    private float mXVelocity;
    private float mCannonWidth;
    private float mCannonHeight;
    private int mScreenX;

    final int STOPPED = 0;
    final int MOVINGLEFT = 1;
    final int MOVINGRIGHT = 2;

    private int cannonMovement = STOPPED;

    SimpleCannon(int screenX) {
        mScreenX = screenX;
        mCannonWidth = screenX / 10;
        mCannonHeight = screenX / 10;
        mRect = new RectF();
    }

    RectF getRect() {
        return mRect;
    }

    public void reset(int x, int y) {
        mRect.left = x / 2;
        mRect.top = y - mCannonHeight;
        mRect.right = x / 2 + mCannonWidth;
        mRect.bottom = y;
        mXVelocity = (y / 3);
    }

    void update(long fps) {

        if(cannonMovement == MOVINGLEFT) {
            mRect.left = mRect.left - (mXVelocity / fps);
        }
        if(cannonMovement == MOVINGRIGHT) {
            mRect.left = mRect.left + (mXVelocity / fps);
        }
        if(mRect.left < 0) {
            mRect.left = 0;
        } // left out of bounds
        mRect.right = mRect.left + mCannonWidth;

        if(mRect.right > mScreenX) {
            mRect.right = mScreenX;
            mRect.left = mScreenX - mCannonWidth;
        } // right out of bounds
    }



    void setMovement(int state) {
        cannonMovement = state;
    }



}