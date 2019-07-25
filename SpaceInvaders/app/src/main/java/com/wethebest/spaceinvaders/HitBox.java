package com.wethebest.spaceinvaders;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;


//TODO create horizontal/vertical out of bounds methods
//TODO create constants for HitBox
//TODO create HitBoxBuilder
//TODO separate bitmap drawing into another class
//TODO create GameObjectHitBox classes, integrate HitBox into all of them
//TODO make HitBoxList to replace Barrier and AlienArmy

//The HitBox class controls the position and movement a GameObject's HitBox.
public class HitBox {
    //Needed for Context and ScreenSize
    private SpaceInvadersApp app;

    //Used to draw on Canvas
    private RectF mRect;
    public Bitmap mBitmap;
    private Paint mPaint;

    //
    public float velocity; //
    public static PointF size; //

    //Aliens have a constant movement speed
    private final float SPEED = 500;


    //Tells the game whether the object should still be in game
    public boolean isActive;

    HitBox(SpaceInvadersApp app) {
        this.app = app;

        mRect = new RectF();
        mPaint = new Paint();

        isActive = true;
        velocity = SPEED; //TODO hardcoded
    }

    public void moveHorizontally(float offset) {
        mRect.left = mRect.left + offset;
        updateRightSide();
    }

    private void moveLeft() {
        mRect.left = mRect.left - size.x;
        updateRightSide();
    }

    private void moveRight() {
        mRect.left = mRect.left + size.x;
        updateRightSide();
    }

    private void updateRightSide() {
        mRect.right = mRect.left + size.x;
    }

    private void moveVertically(float offset) {
        mRect.top = mRect.top + offset;
        updateBottomSide();
    }
    private void moveUp() {
        mRect.top = mRect.top - size.y;
        updateBottomSide();
    }

    public void moveDown() {
        mRect.top = mRect.top + size.y;
        updateBottomSide();
    }

    private void updateBottomSide() {
        mRect.bottom = mRect.top + size.y;
    }
    //Manually sets position of hitbox
    void setPosition(PointF position) {
        mRect.left = position.x;
        mRect.top = position.y;

        updateRightSide();
        updateBottomSide();
    }

    public void setSize(RectF rect) {
        this.size = new PointF();
        size.x = rect.right - rect.left;
        size.y =  rect.top - rect.bottom;
    }

    public void setSize(PointF size) {
        this.size = new PointF(size.x, size.y);

    }


    //Returns true when the hitbox touches the edge of the screen
    boolean outOfBounds() {
        return mRect.left < 0 || mRect.right > app.mScreenSize.x;// || mRect.top < 0 || mRect.bottom > app.mScreenSize.y;
    }

    //Position of hitbox stays within screen boundaries
    void stayInBounds() {
        if (mRect.left < 0) {
            setPosition(new PointF(0, mRect.top));
        } //reset to left edge
        else if (mRect.right > app.mScreenSize.x) {
            setPosition(new PointF(app.mScreenSize.x - size.x, mRect.top));
        } //reset pos to right edge
//        else if (mRect.top < 0) {
//            setPosition(new PointF(mRect.left, 0));
//        }
//        else if (mRect.bottom > app.mScreenSize.y) {
//            setPosition(new PointF(mRect.left, app.mScreenSize.y - size.y));
//        }
    }

    //Resets position of hitbox to default
    void resetPosition(PointF position) {

    }

    //Returns true when hitbox is still active
    public boolean isActive() {
        return isActive;
    }

    //Returns the hitbox
    RectF getHitBox() {
        return mRect;
    }


    public void setBitmap(int spriteID) {
        mBitmap = BitmapFactory.decodeResource(app.context.getResources(), spriteID);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)size.x, (int)size.y, true );
    }

    public void display(Canvas canvas) {
        mPaint.setColor(Color.argb(255, 255, 255, 255));

        canvas.drawBitmap(mBitmap, this.getHitBox().left, this.getHitBox().top, mPaint);
    }
}
