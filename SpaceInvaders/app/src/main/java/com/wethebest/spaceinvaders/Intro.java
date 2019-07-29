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
import android.view.View.OnKeyListener;
import android.view.KeyEvent;




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
        final ImageView Seagullpoop = findViewById(R.id.imageView4);
        final ImageView usermessage = findViewById(R.id.imageView6);


        Nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainImage.setVisibility(ImageView.INVISIBLE);
                title.setVisibility(ImageView.INVISIBLE);
                Nextbtn.setVisibility(ImageView.INVISIBLE);
                EnterName.setVisibility(ImageView.VISIBLE);
                PlayerName.setVisibility(ImageView.VISIBLE);
                Seagullpoop.setVisibility(ImageView.VISIBLE);
                usermessage.setVisibility(ImageView.VISIBLE);


            }
        });

        //goes to next activity once name is entered
        EnterName.setOnKeyListener(new OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            Intent intent=new Intent(Intro.this,SpaceInvaders.class);
                            startActivityForResult(intent,0);
                            overridePendingTransition( R.anim.trans_left_in, R.anim.trans_left_out );

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });




    }

    //main menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //back button forces to go to intro activity and stops app from crashing
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            startActivity(new Intent(Intro.this, Intro.class));

        }
        return super.onKeyDown(keyCode, event);
    }
}
