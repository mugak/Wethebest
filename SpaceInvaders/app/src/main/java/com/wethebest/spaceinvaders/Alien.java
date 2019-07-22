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

    /*TODO: shoot()- alien owns an alien projectile object
   if it exists or is active, then the alien cant shoot (add checkbounds in projectile class)
   if not, then the alien shoots at random intervals - have a booolean turn on and off in update()


    how to add alienproj to gameobject list in spaceinvadersapp.java???
    have shoot return the alienproj
    then in spaceinvadersapp...we need to poll for alien shoot()
    how? either tag alien gameobjects, or add to detectcollisions(), when gameobjects are already being looped
    */

    AlienProj mProj =
    private int shootInterval; //TODO: make this random
    private boolean isShooting = true;

    private Point mScreenSize;

    private Paint mPaint;

    Alien(Point screenSize) {
        mScreenSize = screenSize;

        mAlienWidth = mScreenSize.x / 10;
        mAlienHeight = mScreenSize.y / 20;

        isActive = true;

        mRect = new RectF();
        mPaint = new Paint();

        shootInterval = 1; //TODO
    }

    public void update(long fps) {
        mRect.left = mRect.left + (mXVelocity / fps);
        mRect.top = mRect.top;

        mRect.right = mRect.left + mAlienWidth;
        mRect.bottom = mRect.top + mAlienHeight;

        //If alien out of bounds change it's direction and lower it on screen
        //NOTE: alien groups move as a unit not as individuals. This code will change if we
        // introduce waves of aliens
        if (mRect.left < 0 || mRect.right > mScreenSize.x) {
            reverseXVelocity();
            advance();
        }

        if(true) {
            shoot();
        }
    }

    public RectF getHitBox() {
        return mRect;
    }

    public void setPosition(Point location) {
        mRect.left = location.x / 2;
        mRect.top = 0;
        mRect.right = location.x / 2 + mAlienWidth;
        mRect.bottom = mAlienHeight;
    }

    public void reset(Point location) {
        setPosition(location);
        //Why is velocity dependent on location?
        mXVelocity = (location.y / 3);
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

    private AlienProj shoot() {
        if(!isShooting) {
            AlienProj mProj = new AlienProj(mScreenSize.x);
            return mProj;
        }
    }
}