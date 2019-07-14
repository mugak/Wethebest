//package com.wethebest.spaceinvaders;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.ImageView;
//
//import android.view.View;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//
//public class UI extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // define variables in the game
//
//        final ImageView Spaceship = findViewById(R.id.Spaceship);
//
//        final ImageView Brick1 = findViewById(R.id.Brick1);
//        final ImageView Brick2 = findViewById(R.id.Brick2);
//        final ImageView Brick3 = findViewById(R.id.Brick3);
//        ImageView Brick4 = findViewById(R.id.Brick4);
//
//        ImageView Laserbeam = findViewById(R.id.laserbeam);
//        ImageView InvaderLaserBeam = findViewById(R.id.Invader_laser_beam);
//
//        ImageView Invader1 = findViewById(R.id.Invader1);
//        ImageView Invader1a = findViewById(R.id.Invader1a);
//        ImageView Invader2 = findViewById(R.id.Invader2);
//        ImageView Invader2a = findViewById(R.id.Invader2a);
//        ImageView Invader3 = findViewById(R.id.Invader3);
//        ImageView Invader3a = findViewById(R.id.Invader3a);
//
//        ImageView Scoreboard = findViewById(R.id.Scoreboard);
//        ImageView Gameover = findViewById(R.id.gameover);
//        final ImageView Start = findViewById(R.id.start);
//
//        Start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Start.setVisibility(ImageView.INVISIBLE);
//                Spaceship.setVisibility(ImageView.VISIBLE);
//                Brick1.setVisibility(ImageView.VISIBLE);
//                Brick2.setVisibility(ImageView.VISIBLE);
//                Brick3.setVisibility(ImageView.VISIBLE);
//
//
//
//            }
//        });
//
//
//
//
//    }
//
//}