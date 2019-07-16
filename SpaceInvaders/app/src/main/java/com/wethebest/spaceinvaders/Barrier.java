package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

public class Barrier implements GameObject {
    private RectF mRect;
    private float mBarrierWidth;
    private float mBarrierHeight;

    //Tells the game whether the object should still be in game
    private boolean isActive;

    private Point mScreenSize;
    private Paint mPaint;

    private int durability;

    Barrier(Point screenSize){
        mScreenSize = screenSize;

        mBarrierWidth = mScreenSize.x / 5;
        mBarrierHeight = mScreenSize.y / 10;

        isActive = true;

        mRect = new RectF();
        mPaint = new Paint();

        durability = 3;
    }

    public RectF getHitBox() {
        return mRect;
    }

    public void update(long fps) {

    }

    //Set position of barrier, centered on x,y
    public void reset(Point location) {
        mRect.left = location.x / 2 + mBarrierWidth / 2;
        mRect.top = location.y / 2 + mBarrierHeight / 2;
        mRect.right = mRect.left + mBarrierWidth;
        mRect.bottom = mRect.top + mBarrierHeight;
    }


    public void display(Canvas canvas) {
        mPaint.setColor(Color.argb(255, 255, 255, 255));

        canvas.drawRect(mRect, mPaint);
    }

    public void collide(GameObject gameObject) {
        //SpaceInvaders app already makes this check to make sure the gameObject is a projectile,
        // but this is a good check to make sure the Barrier class still works if the spaceInvadersApp
        // class changes
        //NOTE: SpaceInvadersApp.java checks for the collision so there is no need to in this class
        //Collide only describes what the class should do when it is collided with
        if (gameObject instanceof Projectile) {
            removeDurability(1);
            checkDurability();
        }
    }

    public boolean isActive() {
        return isActive;
    }

    //Updates the durability of barrier if hit
    private void removeDurability(int x){
        durability -= x;
    }

    private void checkDurability() {
        if(durability <= 0) {
            isActive = false;
        }
    }
}