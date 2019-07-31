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
    private SoundPool spStream;
    private int playerShootID = -1;
    private int alienShootID = -1;
    private int barrierHitID = -1;
    private int alienHitID = -1;
    private int playerHitID = -1;
    private int engineHumID = -1;

    public float masterVolume = .5f; // Volumes range from 0 through 1
    int nowPlaying =-1;



    SoundEngine(Context context){
        this.context = context;

        // Instantiate a SoundPool dependent on Android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build();

            //Audioattributes for a stream
//            AudioAttributes streamAudio = new AudioAttributes.Builder()
//                    .setUsage(AudioAttributes.)
            // Initialize the SoundPool
            sp = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();

//            sp = new SoundPool.Builder()
//                    .setMaxStreams(5)
//                    .setAudioAttributes()
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
            barrierHitID = sp.load(assetManager.openFd("Collision8-Bit.ogg"),0);
            alienHitID = sp.load(assetManager.openFd("SmallExplosion8-Bit.ogg"), 0);
            playerHitID = sp.load(assetManager.openFd("Metal_Hit.ogg"),0);
            engineHumID = sp.load(assetManager.openFd("Helicopter, chopper idling.wav"),0);


        }catch(IOException e){
            // Print an error message to the console
            Log.d("error", "failed to load soundEngine files");
        }

    }

    public void playerShoot(){

        sp.play(playerShootID, masterVolume, masterVolume, 0, 0, 1);
        //sp.stop(nowplaying);
    }

    public void alienShoot(){

        sp.play(alienShootID , masterVolume, masterVolume , 0 , 0 , 1);
    }

    public void barrierHit(){
        sp.play(barrierHitID, masterVolume, masterVolume, 0, 0, 1);
    }

    public void alienHit(){
        sp.play(alienHitID, masterVolume, masterVolume, 0,0,1);
    }

    public void playerHit(){
        sp.play(playerHitID, masterVolume, masterVolume, 0,0,1);
    }

    public void startEngineHum(){ sp.play(engineHumID, masterVolume, masterVolume, 0, -1, 1);}

    public void stopEngineHum(){sp.stop(engineHumID);}

    public void setEngineHumPitch(float factor){//factor ranges from 0 to 1
        sp.setVolume(engineHumID, factor, factor);
        sp.setRate(engineHumID, factor*10);
    }


}
