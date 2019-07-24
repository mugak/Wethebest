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
    private final float SPEED = 500;
    public static PointF alienSize;

    //Tells the game whether the object should still be in game
    private boolean isActive;

    private static Random rand = new Random();
    private AlienProj mProj;
    private static int shootInterval = 3;
    private long framesUntilShoot;
    public boolean shootNow;
    private boolean waitingToShoot;
    private boolean movingRight = true;
    private boolean advanced = false;
    final int MOVINGLEFT = 1;
    final int MOVINGRIGHT = 2;
    private int direction = MOVINGRIGHT;

    private Point mScreenSize;

    private Paint mPaint;

    Alien(Context context, Point screenSize) {
        mScreenSize = screenSize;
        isActive = true;

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.invader_a01);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)alienSize.x, (int)alienSize.y, true );

        mRect = new RectF();
        mPaint = new Paint();
        framesUntilShoot = 0;
        shootNow = false;
        waitingToShoot = false;
        mXVelocity = SPEED; //TODO hardcoded
    }

    public void update(long fps) {

        //If alien out of bounds change it's direction and lower it on screen
        //NOTE: alien groups move as a unit not as individuals. This code will change if we
        // introduce waves of aliens

        //Reverse direction if reaching the side of screen
//        if (direction == MOVINGLEFT){
//
//            //Move right
//            mXVelocity = -SPEED;
//        }
//
//        else if (direction == MOVINGRIGHT){
//
//            //Move left
//            mXVelocity = SPEED;
//        }

        if(movingRight) {
            mXVelocity = SPEED;
        }
        else {
            mXVelocity = -SPEED;
        }

        mRect.left = mRect.left + (mXVelocity / fps);

        mRect.right = mRect.left + alienSize.x;
        mRect.bottom = mRect.top + alienSize.y;

        timeToShoot(fps);

    }

    public RectF getHitBox() {
        return mRect;
    }

    public boolean outOfBounds() {
        if (!movingRight) {
            return mRect.left < 0;
        }

        else {
            return mRect.right > mScreenSize.x; //moving right
        }
    }

    public void reverseXVelocity() {
//        if(direction == MOVINGLEFT) {
//            direction = MOVINGRIGHT;
//        }
//        else if(direction == MOVINGRIGHT) {
//            direction = MOVINGLEFT;
//        }
        //mXVelocity = -mXVelocity;

        movingRight = !movingRight;
        advance();
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
        mRect.top = mRect.top + alienSize.y;
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