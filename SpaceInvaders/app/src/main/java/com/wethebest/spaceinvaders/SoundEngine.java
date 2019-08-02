package com.wethebest.spaceinvaders;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;


/*@SoundEngine
* Plays all sound effects in the game
* Loads the sound files into memory and assigns an ID
* Has methods for each sound effect, called by the respective GameObject
*/
public class SoundEngine {

    Context context;

    private SoundPool sp;

    private int playerShootID = -1;
    private int alienShootID = -1;
    private int barrierHitID = -1;
    private int alienHitID = -1;
    private int playerHitID = -1;
    private int engineHumID = -1;
    public boolean loaded = false;

    public float masterVolume = .5f; // Volumes range from 0 through 1
    int nowPlaying =-1;



    SoundEngine(Context context){
        this.context = context;

        // Instantiate a SoundPool dependent on Android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_GAME)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build();

            //Audioattributes for a stream
//            AudioAttributes streamAudio = new AudioAttributes.Builder()
//                    .setUsage(AudioAttributes.)
            // Initialize the SoundPool
            sp = new SoundPool.Builder()
                    .setMaxStreams(25)
                    .setAudioAttributes(audioAttributes)
                    .build();


//            sp = new SoundPool.Builder()
//                    .setMaxStreams(5)
//                    .setAudioAttributes()
        }
        else {
            // The old way
            sp = new SoundPool(25, AudioManager.STREAM_MUSIC, 0);
        }
        try{
            sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    loaded = true; //sets loaded to true when all sounds are loaded
                }
            });

            // Create objects of the 2 required classes
            AssetManager assetManager = context.getAssets();

            //Load all soundEngine files in memory
            playerShootID = sp.load(assetManager.openFd("playershoot.ogg"), 0);
            alienShootID = sp.load(assetManager.openFd("enemyshoot.ogg"), 0);
            barrierHitID = sp.load(assetManager.openFd("barrierhit.ogg"),0);
            alienHitID = sp.load(assetManager.openFd("enemyhit.ogg"), 0);
            playerHitID = sp.load(assetManager.openFd("playerhit.ogg"),0);
            engineHumID = sp.load(assetManager.openFd("enginehum.ogg"),0);


        }catch(IOException e){
            // Print an error message to the console
            Log.d("error", "failed to load soundEngine files");
        }
    }

    public void playerShoot(){
        sp.play(playerShootID, masterVolume, masterVolume, 1, 0, 1);
    }

    public void alienShoot(){
        sp.play(alienShootID , masterVolume, masterVolume , 1 , 0 , 1);
    }

    public void barrierHit(){
        sp.play(barrierHitID, masterVolume, masterVolume, 1, 0, 1);
    }

    public void alienHit(){
        sp.play(alienHitID, masterVolume, masterVolume, 1,0,1);
    }

    public void playerHit(){
        sp.play(playerHitID, masterVolume, masterVolume, 1,0,1);
    }

    public void engineHum(float factor){
        int rateBound = 5;
        if(factor >= rateBound) {
            factor = rateBound; //so the pitch doesnt go too high or too low
        }
        sp.play(engineHumID, masterVolume, masterVolume, 0, 0, factor);
    }

    public void resume() {
        sp.autoResume();
    }

    public void pause() {
        sp.autoPause();
    }


}
