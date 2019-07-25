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
    //Needed for Context and ScreenSize
    private SpaceInvadersApp app;

    private AlienHitBox mHitBox;

    //All aliens have the same size and velocity
    public static PointF alienSize; //TODO set in AlienRow

    //Shoots projectiles randomly
//    private AlienProj mProj;
//    private static Random rand = new Random();
//    private static int shootInterval = 3;
//    private long framesUntilShoot;
//    public boolean shootNow;
//    private boolean waitingToShoot;

    Alien(SpaceInvadersApp app) {
        mHitBox = new AlienHitBox(app);
//        shootNow = false;
//        waitingToShoot = false;
//        framesUntilShoot = 0;

    }

    public void update(long fps) {
        mHitBox.update(fps);
        //timeToShoot(fps);

    }

    public RectF getHitBox() {
        return mHitBox.getHitBox();
    }

    public boolean outOfBounds() {
        return mHitBox.outOfBounds();
    }

    public void reverseXVelocity() {
        mHitBox.reverseXVelocity();
    }

    public Bitmap getBitmap(){ return mHitBox.getBitmap();}

    public void setPos(float x, float y) {
        mHitBox.setPos(x, y);
    }

    public void reset(Point location) {
    }


    private void advance() {
        mHitBox.advance();
    }

    private void stayInBounds() {
        mHitBox.stayInBounds();
    }

    public void display(Canvas canvas) {
        mHitBox.display(canvas);
    }

    public void collide(GameObject gameObject) {
        mHitBox.collide(gameObject);
    }

    public static void setAlienSize(PointF size) {
        alienSize = size;
        AlienHitBox.alienSize = size;
        //TODO change to setHitBoxSize and hitBoxSize?
    }

    public boolean isActive() {
        return mHitBox.isActive();
    }

//    public AlienProj shoot() {
//            mProj = new AlienProj(context, mScreenSize);
//            RectF tempRect = mHitBox.getHitBox();
//            mProj.setPos((tempRect.right + tempRect.left) / 2, tempRect.bottom);
//            return mProj;
//    }
//
//    private void timeToShoot(long fps) {
//        if(!waitingToShoot) {
//            int seconds = rand.nextInt(shootInterval) + 3; // 3-5 seconds
//            framesUntilShoot = fps * seconds;
//            waitingToShoot = true;
//        }
//        else if(waitingToShoot) {
//            framesUntilShoot--;
//            if (framesUntilShoot <= 0) {
//                shootNow = true;
//                waitingToShoot = false;
//            }
//        }
//
//    }


}