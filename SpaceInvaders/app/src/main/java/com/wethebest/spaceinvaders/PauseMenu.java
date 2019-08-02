package com.wethebest.spaceinvaders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class PauseMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_pause_menu);


        Button Resumebtn = findViewById(R.id.resume_btn);
        Button StartOverbtn = findViewById(R.id.StartOver_btn);
        Button EndGamebtn = findViewById(R.id.End_btn);

        Resumebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(PauseMenu.this, SpaceInvaders.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);

            }
        });

        StartOverbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(PauseMenu.this,SpaceInvaders.class);
                startActivityForResult(intent,0);
                overridePendingTransition( R.anim.trans_right_in, R.anim.trans_right_out );

            }
        });

        EndGamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(PauseMenu.this,Intro.class);
                startActivityForResult(intent,0);
                overridePendingTransition( R.anim.trans_left_in, R.anim.trans_left_out );

            }
        });





    }




}
