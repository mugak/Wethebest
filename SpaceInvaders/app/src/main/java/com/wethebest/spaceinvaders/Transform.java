package com.wethebest.spaceinvaders;

import android.graphics.RectF;
import android.graphics.PointF;

// starter code source: “Learning Java by Building Android Games - Second Edition.”
public class Transform {
    RectF mHitBox;
    private PointF mLocation;
    private float mSpeed;
    private float mObjectHeight;
    private float mObjectWidth;
    private PointF mStartingPosition;
    private boolean mHeadingUp = false;
    private boolean mHeadingDown = false;

    private boolean mFacingRight = true;
    private boolean mHeadingLeft = false;
    private boolean mHeadingRight = false;

    Transform(float speed, float objectWidth,
              float objectHeight,
              PointF startingLocation) {

        RectF mHitBox = new RectF();
        mSpeed = speed;
        mObjectHeight = objectHeight;
        mObjectWidth = objectWidth;
        mLocation = startingLocation;

        // This tells movable blocks their starting position
        mStartingPosition = new PointF(
                mLocation.x, mLocation.y);

        mHitBox = new RectF(startingLocation.x - (objectWidth / 2),
                startingLocation.y - (objectHeight / 2),
                startingLocation.x + (objectWidth / 2),
                startingLocation.y + (objectHeight / 2));
    }

    public void updateHitBox() {
        mHitBox.top = mLocation.y - (mObjectHeight / 2);
        mHitBox.left = mLocation.x - (mObjectWidth / 2);
        mHitBox.bottom =
                (mHitBox.top + mObjectHeight);

        mHitBox.right =
                (mHitBox.left + mObjectWidth);
    }


    public RectF getHitBox() {
        return mHitBox;
    }

    void headUp() {
        mHeadingUp = true;
        mHeadingDown = false;
    }

    void headDown() {
        mHeadingDown = true;
        mHeadingUp = false;
    }

    boolean headingUp() {
        return mHeadingUp;
    }

    boolean headingDown() {
        return mHeadingDown;
    }

    float getSpeed() {
        return mSpeed;
    }

    void setSpeed(float speed) {mSpeed = speed;}

    PointF getLocation() {
        return mLocation;
    }

    PointF getSize() {
        return new PointF(
                (int) mObjectWidth,
                (int) mObjectHeight);
    }

    void headRight() {
        mHeadingRight = true;
        mHeadingLeft = false;
        mFacingRight = true;

    }

    void headLeft() {
        mHeadingLeft = true;
        mHeadingRight = false;
        mFacingRight = false;
    }

    boolean headingRight() {
        return mHeadingRight;
    }

    boolean headingLeft() {
        return mHeadingLeft;
    }

    void stopHorizontal() {
        mHeadingLeft = false;
        mHeadingRight = false;
    }

    void stopMovingLeft() {
        mHeadingLeft = false;
    }

    void stopMovingRight() {
        mHeadingRight = false;
    }

    boolean getFacingRight() {
        return mFacingRight;
    }

    PointF getStartingPosition() {
        return mStartingPosition;
    }
}