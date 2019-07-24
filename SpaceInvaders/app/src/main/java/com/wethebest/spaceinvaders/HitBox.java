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

//HitBox controls the hitboxes for each GameObject
//It handles movement, drawing, and collision
public class HitBox {

        //Needed for Context and ScreenSize
        private SpaceInvadersApp app;

        //Used to draw on Canvas
        private RectF mRect;
        private Bitmap mBitmap;
        private Paint mPaint;

        //All aliens have the same size and velocity
        private static float mXVelocity;
        public static PointF alienSize; //TODO set in AlienRow

        //Aliens have a constant movement speed
        private final float SPEED = 500;

        //Current movement direction
        private boolean movingRight;

        //Tells the game whether the object should still be in game
        private boolean isActive;

        //Shoots projectiles randomly
        private AlienProj mProj;
        private static Random rand = new Random();
        private static int shootInterval = 3;
        private long framesUntilShoot;
        public boolean shootNow;
        private boolean waitingToShoot;

        HitBox(SpaceInvadersApp app) {
            this.app = app;

            mBitmap = BitmapFactory.decodeResource(app.context.getResources(), R.drawable.invader_a01);
            mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)alienSize.x, (int)alienSize.y, true );

            mRect = new RectF();
            mPaint = new Paint();

            isActive = true;
            movingRight = true;
            mXVelocity = SPEED; //TODO hardcoded
        }

        public void update(long fps) {
            //Moves horizontally until it hits a screen edge

            if(movingRight) {
                mXVelocity = SPEED;
            }
            else {
                mXVelocity = -SPEED;
            }

            mRect.left = mRect.left + (mXVelocity / fps);

            mRect.right = mRect.left + alienSize.x;
            mRect.bottom = mRect.top + alienSize.y;

        }

        public RectF getHitBox() {
            return mRect;
        }

        public boolean outOfBounds() {
            return mRect.left < 0 || mRect.right > app.mScreenSize.x;
        }

        public void reverseXVelocity() {

            movingRight = !movingRight;
            advance();
            stayInBounds();
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


        public void advance() {
            mRect.top = mRect.top + alienSize.y;
            mRect.bottom = mRect.top + alienSize.y; //moves alien down
        }

        public void stayInBounds() {
            if (mRect.left < 0) {
                mRect.left = 0;
                mRect.right = 0 + alienSize.x;
            } //reset to left edge

            if (mRect.right > app.mScreenSize.x) {
                mRect.right = app.mScreenSize.x;
                mRect.left = app.mScreenSize.x - alienSize.x;
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

        public static void setAlienSize(PointF size) {
            alienSize = size;

        }
    }

