package com.wethebest.spaceinvaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

//TODO create HitBoxBuilder
//TODO separate bitmap drawing into another class
//TODO make HitBoxList to replace Barrier and AlienArmy

//The HitBox class controls the position and movement of a rectangle
public class HitBox {
    private SpaceInvadersApp app;

    private RectF mRect = new RectF();
    private Bitmap mBitmap;

    public float velocity;
    public float speed;

    private HitBox(Builder builder) {
        this.app = builder.app;
        this.mRect = builder.mRect;
        this.mBitmap = builder.mBitmap;
        this.velocity = builder.velocity;
        this.speed = builder.speed;
    }

    public HitBox(SpaceInvadersApp app) { this.app = app; }

    public void setSize(PointF size) { mRect.set(0, 0, size.x, size.y); }
    public void setPosition(PointF position) { mRect.offsetTo(position.x, position.y); }
    public RectF getHitBox(){ return mRect; }

    //Drawing
    public void setBitmap(int spriteID) {
        mBitmap = BitmapFactory.decodeResource(app.context.getResources(), spriteID);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) mRect.width(), (int) mRect.height(), true);
    }
    public void display(Canvas canvas) {
        canvas.drawBitmap(mBitmap, mRect.left, mRect.top, null);
    }

    //Movement
    public void moveHorizontally(float offset) { mRect.offset(offset, 0); }
    public void moveVertically(float offset) { mRect.offset(0, offset); }
    public void moveDown() { mRect.offset(0, mRect.height()); }

    //Check bounds
    public boolean horizontalOutOfBounds() { return mRect.left <= 0 || mRect.right >= app.mScreenSize.x; }
    public boolean bottomOutOfBounds() { return mRect.bottom >= app.mScreenSize.y; }
    public boolean topOutOfBounds() { return mRect.top <= 0; }
    public void horizontalStayInBounds() {
        if (mRect.left <= 0) {
            mRect.offsetTo(0, mRect.top);
        } //reset to left edge
        else if (mRect.right >= app.mScreenSize.x) {
            mRect.offsetTo(app.mScreenSize.x - mRect.width(), mRect.top);
        } //reset pos to right edge
    }

    //Get center of hitbox
    public PointF centerBottom() { return new PointF(mRect.centerX(), mRect.bottom); }
    public PointF centerTop() { return new PointF(mRect.centerX(), mRect.top); }

    public static class Builder {
        private SpaceInvadersApp app;
        private RectF mRect;
        private Bitmap mBitmap;
        private float velocity;
        private float speed;


        public Builder(SpaceInvadersApp app, PointF size) {
            this.app = app;
            mRect = new RectF(0, 0, size.x, size.y);

        }

        public Builder withSprite(int spriteID) {
            mBitmap = BitmapFactory.decodeResource(app.context.getResources(), spriteID);
            mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) mRect.width(), (int) mRect.height(), true);
            return this;
        }

        public Builder withPosition(PointF position) {
            mRect.offsetTo(position.x, position.y);
            return this;
        }

        public Builder withVelocity(float velocity) {
            this.velocity = velocity;
            this.speed = Math.abs(this.velocity);
            return this;
        }

        public HitBox build() {
            return new HitBox(this);
        }
    }
}
