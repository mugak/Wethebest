package com.wethebest.spaceinvaders;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.Random;


public class AlienHitBox extends HitBox {
        //All aliens have the same size and velocity
        public static PointF alienSize;

        //Aliens have a constant movement speed
        private static final float BASE_SPEED = 200;
        private static float SPEED;

        //Current movement direction
        private boolean movingRight;

        AlienHitBox(SpaceInvadersApp app) {
            super(app);
            alienSize = new PointF(app.mScreenSize.x/10, app.mScreenSize.y/10);
            setSize(alienSize);
            setBitmap(R.drawable.invader_a01);

            SPEED = BASE_SPEED;
            isActive = true;
            movingRight = true;
        }

        public void update(long fps) {
            //Moves horizontally until it hits a screen edge

            if(movingRight) {
                velocity = SPEED;
            }
            else {
                velocity = -SPEED;
            }

            moveHorizontally(velocity / fps);

        }

        public void reverseXVelocity() {
            movingRight = !movingRight;
            moveDown();
            horizontalStayInBounds();
        }

        public void collide(GameObject gameObject) {
            //SpaceInvaders app already makes this check to make sure the gameObject is a projectile,
            // but this is a good check to make sure the Alien class still works if the spaceInvadersApp
            // class changes
            //NOTE: SpaceInvadersApp.java checks for the collision so there is no need to in this class
            //Collide only describes what the class should do when it is collided with
            if (gameObject instanceof PlayerProj) {
                //reset(mScreenSize);
                isActive = false;
            }
        }

        public static void speedUp(float multiplier) {
            SPEED = BASE_SPEED * multiplier;
        }
    }

