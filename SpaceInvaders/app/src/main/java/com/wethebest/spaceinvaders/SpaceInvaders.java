package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.graphics.Point;
import android.view.Display;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.Menu;

public class SpaceInvaders extends Activity implements SensorEventListener {

    private SpaceInvadersApp mSpaceInvadersApp;
    Bitmap myBlankBitmap;
    Canvas myCanvas;

    public float yAcceleration;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);





            mSpaceInvadersApp = new SpaceInvadersApp(this, size.x, size.y);
        setContentView(mSpaceInvadersApp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_GAME);
        mSpaceInvadersApp.resume();
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSpaceInvadersApp.pause();
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_GRAVITY) {
            yAcceleration = sensorEvent.values[1];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int x) {
    }

    //back button forces to go to intro activity and stops app from crashing
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            Intent intent=new Intent(SpaceInvaders.this,Intro.class);
            startActivityForResult(intent,0);
            overridePendingTransition( R.anim.trans_right_in, R.anim.trans_right_out );

        }
        return super.onKeyDown(keyCode, event);
    }
}
