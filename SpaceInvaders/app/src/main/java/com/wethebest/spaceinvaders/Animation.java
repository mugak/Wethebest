//source: http://gamecodeschool.com/android/coding-android-sprite-sheet-animations/?fbclid=IwAR058Eq8h-hJHmIh8lbXh9uwGl6B8tiEHsr0m92_Wrh5Ja3D3rHI5ueAKJ0

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
}
