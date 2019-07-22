package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

class SimpleCannon implements GameObject {
    private RectF mRect;
    private float mXVelocity;
    private float mCannonWidth;
    private float mCannonHeight;

    private Point mScreenSize;
    private Paint mPaint;

    final int STOPPED = 0;
    final int MOVINGLEFT = 1;
    final int MOVINGRIGHT = 2;

    private int cannonMovement = STOPPED;

    //Tells the game whether the object should still be in game
    private boolean isActive;

    SimpleCannon(Point screenSize) {
        mScreenSize = screenSize;
        mCannonWidth = mScreenSize.x / 10;
        mCannonHeight = mScreenSize.x / 10;
        mRect = new RectF();

        mPaint = new Paint();

        isActive = true;
    }

    public RectF getHitBox() {
        return mRect;
    }

    public void reset(Point location) {
        setPosition(location);
        mXVelocity = (location.y / 3);
    }

    public void setPosition(Point location) {
        mRect.left = location.x / 2;
        mRect.top = location.y - mCannonHeight;
        mRect.right = location.x / 2 + mCannonWidth;
        mRect.bottom = location.y;
    }

    public void update(long fps) {

        if (cannonMovement == MOVINGLEFT) {
            mRect.left = mRect.left - (mXVelocity / fps);
        }
        if (cannonMovement == MOVINGRIGHT) {
            mRect.left = mRect.left + (mXVelocity / fps);
        }

        checkBounds();
    }

    void setMovement(int state) {
        cannonMovement = state;
    }

    public PlayerProj shoot() {
        PlayerProj mProj = new PlayerProj(mScreenSize);
        mProj.setPos((mRect.right + mRect.left) / 2, mRect.top);
        return mProj;
    }

    public void display(Canvas canvas) {
        mPaint.setColor(Color.argb(255, 255, 255, 255));

        canvas.drawRect(mRect, mPaint);
    }

    //Check alien.java for an example on how this is implemented
    public void collide(GameObject gameObject) {

    }

    //Prevents cannon from moving out of bounds
    private void checkBounds() {
        if (mRect.left < 0) {
            mRect.left = 0;
        } // left out of bounds
        mRect.right = mRect.left + mCannonWidth;

        if (mRect.right > mScreenSize.x) {
            mRect.right = mScreenSize.x;
            mRect.left = mScreenSize.x - mCannonWidth;
        } // right out of bounds
    }

    public boolean isActive() {
        return isActive;
    }
}