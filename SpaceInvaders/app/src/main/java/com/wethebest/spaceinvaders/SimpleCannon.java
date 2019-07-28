package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

class SimpleCannon implements GameObject {
    private Bitmap mBitmap;

    private RectF mRect;
    private float mXVelocity;
    private PointF mSize;

    private Point mScreenSize;
    private Paint mPaint;

    public static final int MAX_LIVES = 3;
    public static int lives;

    final int STOPPED = 0;
    final int MOVINGLEFT = 1;
    final int MOVINGRIGHT = 2;

    private int cannonMovement = STOPPED;

    private Context context;
    private SoundEngine soundEngine;

    //Tells the game whether the object should still be in game
    private boolean isActive;

    private boolean shootNow;
    SimpleCannon(Context context, Point screenSize) {
        this.context = context;

        lives = MAX_LIVES;

        mScreenSize = screenSize;
        mSize = new PointF(mScreenSize.x / 10, mScreenSize.x / 10);
        mRect = new RectF();
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) mSize.x, (int) mSize.y, true);

        mPaint = new Paint();

        isActive = true;
        shootNow = false;

        soundEngine = new SoundEngine(context);
    }


    public Bitmap getBitmap(){
        return mBitmap;
    }

    public void display(Canvas canvas) {
        mPaint.setColor(Color.argb(255, 255, 255, 255));

        //canvas.drawRect(mRect, mPaint);
        canvas.drawBitmap(this.getBitmap(), this.getHitBox().left, this.getHitBox().top, mPaint);
    }

    public void update(long fps) {
        if(((SpaceInvaders)context).yAcceleration >= .08f || ((SpaceInvaders)context).yAcceleration<= -.08f) {
            mRect.left += ((SpaceInvaders)context).yAcceleration * 10;
            mRect.right = mRect.left + mSize.x;
        }


//        if (cannonMovement == MOVINGLEFT) {
//            mRect.left = mRect.left - (mXVelocity / fps);
//        }
//        if (cannonMovement == MOVINGRIGHT) {
//            mRect.left = mRect.left + (mXVelocity / fps);
//        }

        checkBounds();
    }

    public void playAudio(){
        if(shootNow) {
            soundEngine.playerShoot();
            shootNow = false;
        }
    }

    public RectF getHitBox() {
        return mRect;
    }

    public void setPosition(Point location) {
        mRect.left = location.x / 2;
        mRect.top = location.y - mSize.y;
        mRect.right = location.x / 2 + mSize.x;
        mRect.bottom = location.y;
    }

    public void reset(Point location) {
        setPosition(location);
        mXVelocity = (location.y / 3);
    }





    void setMovement(int state) {
        cannonMovement = state;
    }

//    void setMovement(int state) {
//        cannonMovement = state;
//    }

    public PlayerProj shoot() {
        PlayerProj mProj = new PlayerProj(context, mScreenSize);
        mProj.setPos((mRect.right + mRect.left) / 2, mRect.top);
        shootNow = true;
        return mProj;
    }



    //Check alien.java for an example on how this is implemented
    public void collide(GameObject gameObject) {
        /*if(gameObject instanceof AlienProj) {
            lives -= 1;
        }

        reset(mScreenSize);*/
    }

    //Prevents cannon from moving out of bounds
    private void checkBounds() {
        if (mRect.left < 0) {
            mRect.left = 0;
        } // left out of bounds
        mRect.right = mRect.left + mSize.x;

        if (mRect.right > mScreenSize.x) {
            mRect.right = mScreenSize.x;
            mRect.left = mScreenSize.x - mSize.x;
        } // right out of bounds
    }

    public boolean isActive() {
        return isActive;
    }
}