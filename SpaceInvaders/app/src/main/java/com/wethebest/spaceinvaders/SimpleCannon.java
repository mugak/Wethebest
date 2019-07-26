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

    public int lives;

    final int STOPPED = 0;
    final int MOVINGLEFT = 1;
    final int MOVINGRIGHT = 2;

    private int cannonMovement = STOPPED;

    private Context context;

    //Tells the game whether the object should still be in game
    private boolean isActive;

    SimpleCannon(Context context, Point screenSize) {
        this.context = context;

        lives = 3;

        mScreenSize = screenSize;
        mSize = new PointF(mScreenSize.x / 10, mScreenSize.x / 10);
        mRect = new RectF();
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) mSize.x, (int) mSize.y, true);

        mPaint = new Paint();

        isActive = true;
    }

    public RectF getHitBox() {
        return mRect;
    }

    public Bitmap getBitmap(){
        return mBitmap;
    }
    public void reset(Point location) {
        setPosition(location);
        mXVelocity = (location.y / 3);
    }

    public void setPosition(Point location) {
        mRect.left = location.x / 2;
        mRect.top = location.y - mSize.y;
        mRect.right = location.x / 2 + mSize.x;
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
        PlayerProj mProj = new PlayerProj(context, mScreenSize);
        mProj.setPos((mRect.right + mRect.left) / 2, mRect.top);
        return mProj;
    }

    public void display(Canvas canvas) {
        mPaint.setColor(Color.argb(255, 255, 255, 255));

        //canvas.drawRect(mRect, mPaint);
        canvas.drawBitmap(this.getBitmap(), this.getHitBox().left, this.getHitBox().top, mPaint);
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