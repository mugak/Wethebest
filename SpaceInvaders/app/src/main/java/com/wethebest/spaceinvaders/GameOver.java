package com.wethebest.spaceinvaders;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*
GameOver draws the game over screen after player dies and lives = 0.
 */

public class GameOver extends Activity {// extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        enableImmersiveMode();

        TextView Score = findViewById(R.id.Score);
        Score.setText(String.valueOf(SpaceInvadersApp.score));

        Button StartOver = findViewById(R.id.StartOver_btn);

        //goes to intro class after button start over is clicked
        StartOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(GameOver.this,SpaceInvaders.class);
                startActivityForResult(intent,0);
                overridePendingTransition( R.anim.trans_left_in, R.anim.trans_left_out );

            }
        });

        Button IntroPage = findViewById(R.id.MainMenu_btn);

        StartOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(GameOver.this,Intro.class);
                startActivityForResult(intent,0);
                overridePendingTransition( R.anim.trans_left_in, R.anim.trans_left_out );

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus) {
            enableImmersiveMode();
        }
    }

    protected void enableImmersiveMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }


    } //enables full screen - took from https://stackoverflow.com/questions/29186081/android-immersive-mode-reset-when-changing-activity


//    //menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    //items in the menu
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//
//        if(item.getItemId() == R.id.action_startover) {
//            startActivity(new Intent(GameOver.this, SpaceInvaders.class));
//
//        }
//        if(item.getItemId() == R.id.action_newgame) {
//            startActivity(new Intent(GameOver.this, Intro.class));
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
