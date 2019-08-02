package com.wethebest.spaceinvaders;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.graphics.Point;
import android.view.Display;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;

/*
SpaceInvaders is the Activity for the actual game
It handles user events, the window manager, the accelerometer, and sets the view (SpaceInvadersApp)
Started in Intro
 */



public class SpaceInvaders extends Activity implements SensorEventListener { //AppCompatActivity implements SensorEventListener {

    private SpaceInvadersApp mSpaceInvadersApp;
    Bitmap myBlankBitmap;
    Canvas myCanvas;
    public float yAcceleration;
    private SensorManager sensorManager;
    private MediaPlayer mMediaPlayer;

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


        enableImmersiveMode();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealSize(size);
        }
        else {
            display.getSize(size);
        }

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSpaceInvadersApp = new SpaceInvadersApp(this, size.x, size.y);
        setContentView(mSpaceInvadersApp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_GAME);
        mSpaceInvadersApp.resume();

        super.onResume();
        mMediaPlayer = MediaPlayer.create(this,R.raw.example);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
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

        mMediaPlayer.stop(); mMediaPlayer.release();
        super.onPause();
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



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus) {
            enableImmersiveMode();
        }
    }

    protected void enableImmersiveMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }


    } //enables full screen - took from https://stackoverflow.com/questions/29186081/android-immersive-mode-reset-when-changing-activity





}
