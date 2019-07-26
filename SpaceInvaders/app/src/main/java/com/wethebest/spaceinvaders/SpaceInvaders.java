package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.graphics.Point;
import android.view.Display;

import android.graphics.Canvas;
import android.graphics.Bitmap;

public class SpaceInvaders extends Activity {

    private SpaceInvadersApp mSpaceInvadersApp;
    Bitmap myBlankBitmap;
    Canvas myCanvas;

    Sound sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int widthInPixels = 800;
        int heightInPixels = 600;

        myBlankBitmap = Bitmap.createBitmap(widthInPixels,
                heightInPixels,
                Bitmap.Config.ARGB_8888);
        // Initialize the Canvas and associate it
        // with the Bitmap to draw on
        myCanvas = new Canvas(myBlankBitmap);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        sound = new Sound(this);
        sound.setup();



            mSpaceInvadersApp = new SpaceInvadersApp(this, size.x, size.y);
        setContentView(mSpaceInvadersApp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSpaceInvadersApp.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSpaceInvadersApp.pause();
    }
}
