package com.wethebest.spaceinvaders;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.Random;


public class AlienHitBox {

        private HitBox mHitBox;

        //Needed for Context and ScreenSize
        private SpaceInvadersApp app;

        //Used to draw on Canvas
        private RectF mRect;
        private Bitmap mBitmap;
        private Paint mPaint;

        //All aliens have the same size and velocity
        public static PointF alienSize;

        //Aliens have a constant movement speed
        private final float SPEED = 200;

        //Current movement direction
        private boolean movingRight;

        //Tells the game whether the object should still be in game
        private boolean isActive;

        AlienHitBox(SpaceInvadersApp app) {
            alienSize = new PointF(app.mScreenSize.x/8, app.mScreenSize.y/12);
            mHitBox = new HitBox(app);

            mHitBox.setSize(alienSize);
            mHitBox.setBitmap(R.drawable.invader_a01);



            this.app = app;

            //mBitmap = BitmapFactory.decodeResource(app.context.getResources(), R.drawable.invader_a01);
            //mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)alienSize.x, (int)alienSize.y, true );


            mRect = new RectF();
            mPaint = new Paint();

            isActive = true;
            movingRight = true;
        }

        public void update(long fps) {
            //Moves horizontally until it hits a screen edge

            if(movingRight) {
                mHitBox.velocity = SPEED;
            }
            else {
                mHitBox.velocity = -SPEED;
            }

            mHitBox.moveHorizontally(mHitBox.velocity / fps);

        }

        public RectF getHitBox() {
            return mHitBox.getHitBox();
        }

        public boolean outOfBounds() {
            return mHitBox.outOfBounds();
        }

        public void reverseXVelocity() {
            movingRight = !movingRight;
            advance();
            stayInBounds();
        }

        public Bitmap getBitmap(){ return mHitBox.mBitmap;}

        public void setPos(PointF position) {
            mHitBox.setPosition(position);
        }

        public void reset(PointF location) {
            mHitBox.resetPosition(location);
        }


        public void advance() {
           mHitBox.moveDown();
        }

        public void stayInBounds() {
            mHitBox.stayInBounds();
        }

        public void display(Canvas canvas) {
           mHitBox.display(canvas);
        }

        public void collide(GameObject gameObject) {
            //SpaceInvaders app already makes this check to make sure the gameObject is a projectile,
            // but this is a good check to make sure the Alien class still works if the spaceInvadersApp
            // class changes
            //NOTE: SpaceInvadersApp.java checks for the collision so there is no need to in this class
            //Collide only describes what the class should do when it is collided with
            if (gameObject instanceof PlayerProj) {
                //reset(mScreenSize);
                mHitBox.isActive = false;
            }
        }


        public boolean isActive() {
            return mHitBox.isActive();
        }
    }

