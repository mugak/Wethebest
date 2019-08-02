package com.wethebest.spaceinvaders;


// starter code source: “Learning Java by Building Android Games - Second Edition.”


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Rect;

public class AnimatedGraphicsComponent implements GraphicsComponent {

    private String mBitmapName;
    private Animator mAnimator;
    private Rect mSectionToDraw;

    @Override
    public void initialize(Context context, GameObjectSpec spec, PointF size) {

        // Initialize the animation
        mAnimator = new Animator(size, spec.getNumFrames());

        // stretch the bitmap by the number of frames
        float totalWidth = size.x * spec.getNumFrames();

        mBitmapName = spec.getBitmapName();
        BitmapStore.addBitmap(context, mBitmapName,
                new PointF(totalWidth, size.y), true);

        // Get the first frame of animation
        mSectionToDraw = mAnimator.getCurrentFrame(System.currentTimeMillis());
    }

    @Override
    // Updated to take a reference to a Camera
    public void draw(Canvas canvas, Paint paint, Transform t) {

        // Get the section of bitmap to draw
        // when an object is in motion
        // OR if it is a object with
        // zero speed(like a fire tile)
        if (t.headingRight() ||
                t.headingLeft() ||
                t.getSpeed() == 0) {

            // Player is moving so animate/change the frame
            mSectionToDraw = mAnimator.getCurrentFrame(
                    System.currentTimeMillis());
        }

        // Where should the bitmap section be drawn?
        RectF screenCoordinates = (t.getLocation().x, t.getLocation().y, 
                t.getSize().x, t.getSize().y);

        if (t.getFacingRight()) {
            canvas.drawBitmap(
                    BitmapStore.getBitmap(mBitmapName),
                    mSectionToDraw,
                    screenCoordinates,
                    paint);
        } else
            canvas.drawBitmap(
                    BitmapStore.getBitmapReversed(mBitmapName),
                    mSectionToDraw,
                    screenCoordinates,
                    paint);
    }
}