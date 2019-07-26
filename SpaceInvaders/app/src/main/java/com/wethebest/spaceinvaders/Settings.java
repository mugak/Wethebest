package com.wethebest.spaceinvaders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Button lower_volume = findViewById(R.id.Lower_Volume);
        Button Higher_Volume = findViewById(R.id.higher_volume);

        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        //To increase volume
        Higher_Volume.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
            }
        });

        


    }
}
