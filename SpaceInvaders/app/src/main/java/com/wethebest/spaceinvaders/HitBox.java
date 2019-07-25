package com.wethebest.spaceinvaders;


import android.graphics.PointF;
import android.graphics.RectF;

//The HitBox class controls the position of the HitBox
public class HitBox {
    //Needed for Context and ScreenSize
    private SpaceInvadersApp app;

    //Used to draw on Canvas
    private RectF mRect;
    private Bitmap mBitmap;
    private Paint mPaint;
    private int spriteID;

    //
    public float velocity; //
    public PointF size; //

    //Aliens have a constant movement speed
    private final float SPEED = 500;

    //Current movement direction
    private boolean movingRight;

    //Tells the game whether the object should still be in game
    private boolean isActive;

    HitBox(SpaceInvadersApp app) {
        this.app = app;

        mBitmap = BitmapFactory.decodeResource(app.context.getResources(), spriteID);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)size.x, (int)size.y, true );

        mRect = new RectF();
        mPaint = new Paint();

        isActive = true;
        movingRight = true;
        velocity = SPEED; //TODO hardcoded
    }

    private void moveHorizontally(float offset) {
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

    private void moveDown() {
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

    void setSize(RectF rect) {
        size.x = rect.right - rect.left;
        size.y =  rect.top - rect.bottom;
    }


    //Returns true when the hitbox touches the edge of the screen
    boolean outOfBounds() {
        return mRect.left < 0 || mRect.right > app.mScreenSize.x || mRect.top < 0 || mRect.bottom > app.mScreenSize.y;
    }

    //Position of hitbox stays within screen boundaries
    void stayInBounds() {
        if (mRect.left < 0) {
            setPosition(new PointF(0, mRect.top));
        } //reset to left edge
        else if (mRect.right > app.mScreenSize.x) {
            setPosition(new PointF(app.mScreenSize.x - size.x, mRect.top));
        } //reset pos to right edge
        else if (mRect.top < 0) {
            setPosition(new PointF(mRect.left, 0));
        }
        else if (mRect.bottom > app.mScreenSize.y) {
            setPosition(new PointF(mRect.left, app.mScreenSize.y - size.y));
        }
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


}
