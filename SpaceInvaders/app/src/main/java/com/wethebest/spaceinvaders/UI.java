package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
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
import android.widget.TextView;
import android.widget.Toast;
import android.view.animation.Animation;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;

import androidx.appcompat.app.AppCompatActivity;

import static android.view.MotionEvent.*;


public class UI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define variables in the game

        final ImageView Spaceship = findViewById(R.id.Spaceship);

        final ImageView Brick1 = findViewById(R.id.Brick1);
        final ImageView Brick2 = findViewById(R.id.Brick2);
        final ImageView Brick3 = findViewById(R.id.Brick3);
        ImageView Brick4 = findViewById(R.id.Brick4);

        ImageView Laserbeam = findViewById(R.id.laserbeam);
        ImageView InvaderLaserBeam = findViewById(R.id.Invader_laser_beam);

        final ImageView Invader1 = findViewById(R.id.Invader1);
        final ImageView Invader1a = findViewById(R.id.Invader1a);
        final ImageView Invader1b = findViewById(R.id.Invader1b);
        final ImageView Invader2 = findViewById(R.id.Invader2);
        final ImageView Invader2a = findViewById(R.id.Invader2a);
        final ImageView Invader2b = findViewById(R.id.Invader2b);
        final ImageView Invader3 = findViewById(R.id.Invader3);
        final ImageView Invader3a = findViewById(R.id.Invader3a);
        final ImageView Invader3b = findViewById(R.id.Invader3b);


        final ImageView Scoreboard = findViewById(R.id.Scoreboard);
        final TextView Score = findViewById(R.id.Score);
        ImageView Gameover = findViewById(R.id.gameover);
        final ImageView Start = findViewById(R.id.start);



        int xDelta;
        int yDelta;

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Start.setVisibility(ImageView.INVISIBLE);
                Spaceship.setVisibility(ImageView.VISIBLE);
                Brick1.setVisibility(ImageView.VISIBLE);
                Brick2.setVisibility(ImageView.VISIBLE);
                Brick3.setVisibility(ImageView.VISIBLE);
                Invader1.setVisibility(ImageView.VISIBLE);
                Invader1a.setVisibility(ImageView.VISIBLE);
                Invader1b.setVisibility(ImageView.VISIBLE);
                Invader2.setVisibility(ImageView.VISIBLE);
                Invader2a.setVisibility(ImageView.VISIBLE);
                Invader2b.setVisibility(ImageView.VISIBLE);
                Invader3.setVisibility(ImageView.VISIBLE);
                Invader3a.setVisibility(ImageView.VISIBLE);
                Invader3b.setVisibility(ImageView.VISIBLE);
                Score.setVisibility(ImageView.VISIBLE);
                Scoreboard.setVisibility(ImageView.VISIBLE);



            }
        });

        Spaceship.setOnTouchListener(new View.OnTouchListener() {
            @Override
           public boolean onTouch(View view, MotionEvent motionEvent) {
                TranslateAnimation animation = new TranslateAnimation(-900.0f, 900.0f,
                        0.0f, 0.0f);//  new TranslateAnimation(xFrom,xTo, yFrom,yTo)

                animation.setDuration(5000);  // animation duration
               animation.setRepeatCount(1);  // animation repeat count
                animation.setRepeatMode(0);   // repeat animation (left to right, right to left )
               animation.setFillAfter(true);

                Spaceship.startAnimation(animation);  // start animation


              return false;

            }
        });








    }

}