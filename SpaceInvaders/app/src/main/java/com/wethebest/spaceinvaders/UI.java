package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import android.view.View;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;



public class UI extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define variables in the game

        final ImageView Spaceship = (ImageView)findViewById(R.id.Spaceship);

        ImageView Brick1 = (ImageView)findViewById(R.id.Brick1);
        ImageView Brick2 = (ImageView)findViewById(R.id.Brick2);
        ImageView Brick3 = (ImageView)findViewById(R.id.Brick3);
        ImageView Brick4 = (ImageView)findViewById(R.id.Brick4);

        ImageView Laserbeam = (ImageView)findViewById(R.id.laserbeam);
        ImageView InvaderLaserBeam = (ImageView)findViewById(R.id.Invader_laser_beam);

        ImageView Invader1 = (ImageView)findViewById(R.id.Invader1);
        ImageView Invader1a = (ImageView)findViewById(R.id.Invader1a);
        ImageView Invader2 = (ImageView)findViewById(R.id.Invader2);
        ImageView Invader2a = (ImageView)findViewById(R.id.Invader2a);
        ImageView Invader3 = (ImageView)findViewById(R.id.Invader3);
        ImageView Invader3a = (ImageView)findViewById(R.id.Invader3a);

        ImageView Scoreboard = (ImageView)findViewById(R.id.Scoreboard);
        ImageView Gameover = (ImageView)findViewById(R.id.gameover);
        ImageView Start = (ImageView)findViewById(R.id.start);

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Spaceship.setVisibility(ImageView.VISIBLE);
                Spaceship.setY(30);
                Spaceship.setX(40);


            }
        });




    }

}