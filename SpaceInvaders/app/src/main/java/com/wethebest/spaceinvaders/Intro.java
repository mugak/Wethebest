package com.wethebest.spaceinvaders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.util.Log;


public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        TextView PlayerName = findViewById(R.id.PlayerName);
        EditText EnterName = findViewById(R.id.EnterName);
        Button Enterbtn = findViewById(R.id.Enterbtn);

        Enterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intro.this, UI.class));
            }
        });

    }
}
