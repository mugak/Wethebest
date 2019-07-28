package com.wethebest.spaceinvaders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;


public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        final TextView PlayerName = findViewById(R.id.PlayerName);
        final EditText EnterName = findViewById(R.id.EnterName);
        final Button Enterbtn = findViewById(R.id.Enterbtn);
        final Button Nextbtn = findViewById(R.id.Next_btn);
        final TextView title = findViewById(R.id.textView2);
        final ImageView MainImage = findViewById(R.id.imageView3);

        Enterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intro.this, SpaceInvaders.class));
            }
        });

        Nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainImage.setVisibility(ImageView.INVISIBLE);
                title.setVisibility(ImageView.INVISIBLE);
                Nextbtn.setVisibility(ImageView.INVISIBLE);
                Enterbtn.setVisibility(ImageView.VISIBLE);
                EnterName.setVisibility(ImageView.VISIBLE);
                PlayerName.setVisibility(ImageView.VISIBLE);




            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }



}
