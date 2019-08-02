package com.wethebest.spaceinvaders;

import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Rect;


// starter code source: “Learning Java by Building Android Games - Second Edition.”

public class Animator {

    private Rect mRect;
    private PointF size;
    private int mFrameCount;
    private int mCurrentFrame;
    private long mFrameTicker;
    private int mFramePeriod;

    Animator(PointF size, int frameCount) {

        this.mCurrentFrame = 0;
        this.mFrameCount = frameCount;
        this.size = size;

        mRect = new Rect(0, 0, (int)size.x, (int)size.y);

        // length of frame in milliseconds
        mFramePeriod = 1000 / fps;
        mFrameTicker = 0L;
    }

    Rect getCurrentFrame(long time) {
        if (time > mFrameTicker + mFramePeriod) {
            mFrameTicker = time;
            mCurrentFrame++;
            if (mCurrentFrame >= mFrameCount) {
                mCurrentFrame = 0;
            }
        }

        // Update the left and right values of the source of
        // the next frame on the spritesheet
        this.mRect.left = mCurrentFrame * (int)size.x;
        this.mRect.right = this.mRect.left
                + (int)size.x;

        return mRect;
    }

}