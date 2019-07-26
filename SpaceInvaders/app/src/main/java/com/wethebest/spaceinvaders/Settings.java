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

        



    }
}
