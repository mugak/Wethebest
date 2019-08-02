package com.wethebest.spaceinvaders;

import android.graphics.PointF;
import android.graphics.RectF;

// starter code source: “Learning Java by Building Android Games - Second Edition.”

public class Animator {

    private RectF mRect;
    private PointF size;
    private int mFrameCount;
    private int mCurrentFrame;
    private long mFrameTicker;
    private int mFramePeriod;

    Animator(PointF size, int frameCount, int fps) {

        this.mCurrentFrame = 0;
        this.mFrameCount = frameCount;
        this.size = size;

        mRect = new RectF(0, 0, size.x, size.y);

        // length of frame in milliseconds
        mFramePeriod = 1000 / fps;
        mFrameTicker = 0L;
    }

    RectF getCurrentFrame(long time) {
        if (time > mFrameTicker + mFramePeriod) {
            mFrameTicker = time;
            mCurrentFrame++;
            if (mCurrentFrame >= mFrameCount) {
                mCurrentFrame = 0;
            }
        }

        // Update the left and right values of the source of
        // the next frame on the spritesheet
        this.mRect.left = mCurrentFrame * size.x;
        this.mRect.right = this.mRect.left
                + size.x;

        return mRect;
    }

}