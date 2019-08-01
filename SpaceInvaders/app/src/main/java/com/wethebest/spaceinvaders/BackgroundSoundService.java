package com.wethebest.spaceinvaders;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;

/*
BackgroundSoundService plays the music using MediaPlayer
 */

public class BackgroundSoundService extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMediaPlayer = MediaPlayer.create(this,R.raw.example);  //add music file .m4a or mp3
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }

    @Override
    protected void onPause() {
        mMediaPlayer.stop(); mMediaPlayer.release();
        super.onPause();
    }

}