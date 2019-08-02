package com.wethebest.spaceinvaders;

import android.graphics.RectF;
import android.graphics.PointF;

// starter code source: “Learning Java by Building Android Games - Second Edition.”
public class Transform {
    RectF mCollider;
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

        mCollider = new RectF();
        mSpeed = speed;
        mObjectHeight = objectHeight;
        mObjectWidth = objectWidth;
        mLocation = startingLocation;

        // This tells movable blocks their starting position
        mStartingPosition = new PointF(
                mLocation.x, mLocation.y);
    }
}