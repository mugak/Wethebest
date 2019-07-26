package com.wethebest.spaceinvaders;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import java.io.IOException;

public class Sound {

    SoundPool sp;
    int nowPlaying =-1;
    int idFX1 = -1;
    float volume = 1;// Volumes range from 0 through 1

    Context context;

    Sound(Context context){this.context = context;}

    public void setup(){
        // Instantiate a SoundPool dependent on Android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            // The new way
            // Build an AudioAttributes object
            AudioAttributes audioAttributes =
                    // First method call
                    new AudioAttributes.Builder()
                            .setUsage
                                    (AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                            .setContentType
                                    (AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build();

            // Initialize the SoundPool
            sp = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else {
            // The old way
            sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        try{

            // Create objects of the 2 required classes
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            // Load our fx in memory ready for use
            descriptor = assetManager.openFd("fx1.ogg");
            idFX1 = sp.load(descriptor, 0);
        }catch(IOException e){

            // Print an error message to the console
            Log.d("error", "failed to load sound files");
        }
    }
}
