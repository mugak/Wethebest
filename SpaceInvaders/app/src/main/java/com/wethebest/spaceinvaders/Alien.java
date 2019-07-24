package com.wethebest.spaceinvaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.content.Context;

import java.util.Random;

class Alien implements GameObject {
    private RectF mRect;
    private Bitmap mBitmap;

    //Velocity is static for all the aliens in the row
    private static float mXVelocity;
    private final float SPEED = 1000;
    public static PointF alienSize;

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

    Alien(Context context, Point screenSize) {
        mScreenSize = screenSize;
        isActive = true;

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.invader_a01);
        mRect = new RectF();
        mPaint = new Paint();
        framesUntilShoot = 0;
        shootNow = false;
        waitingToShoot = false;
        mXVelocity = SPEED; //TODO hardcoded
    }

    public void update(long fps) {
        mRect.left = mRect.left + (mXVelocity / fps);
        mRect.top = mRect.top;

        mRect.right = mRect.left + alienSize.x;
        mRect.bottom = mRect.top + alienSize.y;

        //If alien out of bounds change it's direction and lower it on screen
        //NOTE: alien groups move as a unit not as individuals. This code will change if we
        // introduce waves of aliens
        if (mRect.left < 0){

            //Move right
            mXVelocity = SPEED;
            advance();
        }

        else if (mRect.right > mScreenSize.x){

            //Move left
            mXVelocity = - SPEED;
        }

        timeToShoot(fps);

    }

    public RectF getHitBox() {
        return mRect;
    }

    public Bitmap getBitmap(){ return mBitmap;}

    public void setPos(float x, float y) {
        mRect.left = x;
        mRect.top = y;
        mRect.right = x + alienSize.x;
        mRect.bottom = alienSize.y;
    }

    public void reset(Point location) {
    }


    private void advance() {
        mRect.top = mRect.top + alienSize.x;
        mRect.bottom = mRect.top + alienSize.y; //moves alien down

        if (mRect.left < 0) {
            mRect.left = 0;
            mRect.right = 0 + alienSize.x;
        } //reset to left edge

        if (mRect.right > mScreenSize.x) {
            mRect.right = mScreenSize.x;
            mRect.left = mScreenSize.x - alienSize.x;
        } //reset pos to right edge
    }

    public void display(Canvas canvas) {
        mPaint.setColor(Color.argb(255, 255, 255, 255));

        canvas.drawBitmap(this.getBitmap(), this.getHitBox().left, this.getHitBox().top, mPaint);
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

    public static void setAlienSize(PointF size) {
        alienSize = size;

    }
}