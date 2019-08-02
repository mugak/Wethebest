package com.wethebest.spaceinvaders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*
GameOver draws the game over screen after player dies and lives = 0.
 */

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        TextView Score = findViewById(R.id.Score);

        Button StartOver = findViewById(R.id.StartOver_btn);

        //goes to intro class after button start over is clicked
        StartOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(GameOver.this,Intro.class);
                startActivityForResult(intent,0);
                overridePendingTransition( R.anim.trans_left_in, R.anim.trans_left_out );

            }
        });
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //items in the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId() == R.id.action_startover) {
            startActivity(new Intent(GameOver.this, SpaceInvaders.class));

        }
        if(item.getItemId() == R.id.action_newgame) {
            startActivity(new Intent(GameOver.this, Intro.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
