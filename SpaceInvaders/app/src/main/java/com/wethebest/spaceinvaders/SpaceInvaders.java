package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Point;
import android.view.Display;

public class SpaceInvaders extends Activity {

    private SpaceInvadersApp mSpaceInvadersApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

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
