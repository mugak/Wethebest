package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import java.util.Random;

class Alien implements GameObject {
    private RectF mRect;
    private float mXVelocity;
    public static Size alienSize;

    //Tells the game whether the object should still be in game
    private boolean isActive;

    private static Random rand = new Random();
    private AlienProj mProj;
    private static int shootInterval = 3;
    private long framesUntilShoot;
    public boolean shootNow;
    private boolean waitingToShoot;

    private Point mScreenSize;

    private Paint mPaint;

    Alien(Point screenSize) {
        mScreenSize = screenSize;
        alienSize = new Size(mScreenSize.x / 10, mScreenSize.y / 10);

        isActive = true;

        mRect = new RectF();
        mPaint = new Paint();
        framesUntilShoot = 0;
        shootNow = false;
        waitingToShoot = false;
        mXVelocity = 1000; //TODO hardcoded
    }

    public void update(long fps) {
        mRect.left = mRect.left + (mXVelocity / fps);
        mRect.top = mRect.top;

        mRect.right = mRect.left + alienSize.width;
        mRect.bottom = mRect.top + alienSize.height;

        //If alien out of bounds change it's direction and lower it on screen
        //NOTE: alien groups move as a unit not as individuals. This code will change if we
        // introduce waves of aliens
        if (mRect.left < 0 || mRect.right > mScreenSize.x) {
            reverseXVelocity();
            advance();
        }

        timeToShoot(fps);

    }

    public RectF getHitBox() {
        return mRect;
    }

    public void setPos(float x, float y) {
        mRect.left = x / 2;
        mRect.top = 0;
        mRect.right = x / 2 + alienSize.width;
        mRect.bottom = alienSize.height;
    }

    public void reset(Point location) {
    }

    private void reverseXVelocity() {
        mXVelocity = -mXVelocity;
    }

    private void advance() {
        mRect.top = mRect.top + alienSize.height;
        mRect.bottom = mRect.top + alienSize.height; //moves alien down

        if (mRect.left < 0) {
            mRect.left = 0;
            mRect.right = 0 + alienSize.width;
        } //reset to left edge

        if (mRect.right > mScreenSize.x) {
            mRect.right = mScreenSize.x;
            mRect.left = mScreenSize.x - alienSize.width;
        } //reset pos to right edge
    }

    public void display(Canvas canvas) {
        mPaint.setColor(Color.argb(255, 255, 255, 255));

        canvas.drawRect(mRect, mPaint);
    }

    public void collide(GameObject gameObject) {
        //SpaceInvaders app already makes this check to make sure the gameObject is a projectile,
        // but this is a good check to make sure the Alien class still works if the spaceInvadersApp
        // class changes
        //NOTE: SpaceInvadersApp.java checks for the collision so there is no need to in this class
        //Collide only describes what the class should do when it is collided with
        if (gameObject instanceof PlayerProj) {
            //reset(mScreenSize);
            isActive = false;
        }
    }


    public boolean isActive() {
        return isActive;
    }

    private boolean noProjectile() {
        return mProj == null || !mProj.isActive();
    }

    public AlienProj shoot() {
            mProj = new AlienProj(mScreenSize);
            mProj.setPos((mRect.right + mRect.left) / 2, mRect.bottom);
            return mProj;
    }

    private void timeToShoot(long fps) {
        if(!waitingToShoot) {
            int seconds = rand.nextInt(shootInterval) + 3; // 3-5 seconds
            framesUntilShoot = fps * seconds;
            waitingToShoot = true;
        }
        else if(waitingToShoot) {
            framesUntilShoot--;
            if (framesUntilShoot <= 0) {
                shootNow = true;
                waitingToShoot = false;
            }
        }

    }
}