//starter code: http://gamecodeschool.com/android/coding-android-sprite-sheet-animations/?fbclid=IwAR058Eq8h-hJHmIh8lbXh9uwGl6B8tiEHsr0m92_Wrh5Ja3D3rHI5ueAKJ0

package com.wethebest.spaceinvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class Animation {
    Bitmap bitmapSheet;
    String bitmapName;
    private Rect sourceRect;
    private int frameCount;
    private int currentFrame;
    private long frameTicker;
    private int framePeriod;
    private int frameWidth;
    private int frameHeight;
    int pixelsPerMetre;

    Animation(Context context, String bitmapName, float frameHeight, float frameWidth,
              int animFps, int frameCount, int pixelsPerMetre){

        this.currentFrame = 0;
        this.frameCount = frameCount;

        this.frameWidth = (int)frameWidth * pixelsPerMetre;
        this.frameHeight = (int)frameHeight * pixelsPerMetre;
        sourceRect = new Rect(0, 0, this.frameWidth, this.frameHeight);
        framePeriod = 1000 / animFps;
        frameTicker = 0l;
        this.bitmapName = "" + bitmapName;
        this.pixelsPerMetre = pixelsPerMetre;
    }

    public Rect getCurrentFrame(long time, float xVelocity, boolean moves) {

        if (time > frameTicker + framePeriod) {
            frameTicker = time;
            currentFrame++;
            // loop around to first frame
            if (currentFrame >= frameCount) {
                currentFrame = 0;
            }
        }
        // update the left and right values of the source of
        // the next frame on the spritesheet
        this.sourceRect.left = currentFrame * frameWidth;
        this.sourceRect.right = this.sourceRect.left + frameWidth;

        return sourceRect;
    }

}
