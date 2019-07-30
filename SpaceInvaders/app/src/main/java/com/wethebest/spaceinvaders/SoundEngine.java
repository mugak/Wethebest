package com.wethebest.spaceinvaders;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import java.io.IOException;


/*@SoundEngine
* Sets up all of the sounds in the game to be played in each game object.
* Loads the sound files into memory and assigns them with an ID.
* Sounds are played through the member methods.
*/
public class SoundEngine {

    Context context;

    private SoundPool sp;
    private int playerShootID = -1;
    private int alienShootID = -1;

    int nowPlaying =-1;
    float volume = 1;// Volumes range from 0 through 1



    SoundEngine(Context context){
        this.context = context;

        // Instantiate a SoundPool dependent on Android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
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

            //Load all soundEngine files in memory
            playerShootID = sp.load(assetManager.openFd("proj_shoot.ogg"), 0);
            alienShootID = sp.load(assetManager.openFd("alien_shoot.ogg"), 0);


        }catch(IOException e){
            // Print an error message to the console
            Log.d("error", "failed to load soundEngine files");
        }

    }

    public void playerShoot(){

        sp.play(playerShootID, .5f, .5f, 0, 0, 1);
        //sp.stop(nowplaying);
    }

    public void alienShoot(){

        sp.play(alienShootID , .5f, .5f , 0 , 0 , 1);
    }
}
