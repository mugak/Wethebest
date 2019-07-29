package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.Random;

class Alien implements GameObject {
    //Needed for Context and ScreenSize
    private SpaceInvadersApp app;

    public HitBox mHitBox;

    //All aliens have the same size and velocity
    public static PointF alienSize; //TODO set in AlienRow

    //Shoots projectiles randomly
    private AlienProj mProj;
    private static Random rand = new Random();
    private static Point shootInterval = new Point(5, 20); // shoots every 5-20 seconds
    private long framesUntilShoot;
    public boolean shootNow;
    private boolean waitingToShoot;

    private SoundEngine soundEngine;

    //Aliens have a constant movement speed
    private static final float BASE_SPEED = 200;
    private static float SPEED;

    //Current movement direction
    private boolean movingRight;

    Alien(SpaceInvadersApp app) {
        mHitBox = new HitBox(app);
        this.app = app;
        soundEngine = new SoundEngine(app.context);

        //mHitBox = new AlienHitBox(app);
        alienSize = new PointF(app.mScreenSize.x/10, app.mScreenSize.y/10);
        mHitBox.setSize(alienSize);
        mHitBox.setBitmap(R.drawable.invader_a01);

        mHitBox.velocity = SPEED = BASE_SPEED;

        movingRight = true;

        shootNow = false;
        waitingToShoot = false;
        framesUntilShoot = 0;

    }

    public void update(long fps) {
        //mHitBox.update(fps);
        if(movingRight) {
            mHitBox.velocity = SPEED;
        }
        else {
            mHitBox.velocity = -SPEED;
        }

        mHitBox.moveHorizontally(mHitBox.velocity / fps);

        timeToShoot(fps);
        checkAlienWin();

    }

    public void display(Canvas canvas){
        mHitBox.display(canvas);
    }



    public void playAudio() {
        if (shootNow) {
            soundEngine.alienShoot();
        }
    }


    public boolean outOfBounds() {
        //return mHitBox.horizontalOutOfBounds();
        return mHitBox.horizontalOutOfBounds();
    }

    public void reverseXVelocity() {
        //mHitBox.reverseXVelocity();
            movingRight = !movingRight;
            mHitBox.moveDown();
        mHitBox.horizontalStayInBounds();

    }

    public static void speedUp(float multiplier) {
        SPEED = BASE_SPEED * multiplier;
    }


    public void setPos(PointF position) {
        mHitBox.setPosition(position);
    }

    public void reset(Point location) {
    }



    public void collide(GameObject gameObject) {
        //mHitBox.collide(gameObject);
            //SpaceInvaders app already makes this check to make sure the gameObject is a projectile,
            // but this is a good check to make sure the Alien class still works if the spaceInvadersApp
            // class changes
            //NOTE: SpaceInvadersApp.java checks for the collision so there is no need to in this class
            //Collide only describes what the class should do when it is collided with
            if (gameObject instanceof PlayerProj) {
                //reset(mScreenSize);
                mHitBox.isActive = false;
            }
    }

    public static void setAlienSize(PointF size) {
        alienSize = size;
        //TODO change to setHitBoxSize and hitBoxSize?
    }


    public AlienProj shoot() {
            mProj = new AlienProj(app);
            //RectF tempRect = mHitBox.getmRect();
            RectF tempRect = mHitBox.getmRect();
            mProj.setPos((tempRect.right + tempRect.left) / 2, tempRect.bottom);
            soundEngine.alienShoot();
            return mProj;
    }

    //calculates when to shoot shooting by counting the number of frames
    private void timeToShoot(long fps) {
        if(!waitingToShoot) {
            int seconds = rand.nextInt(shootInterval.y - shootInterval.x) + shootInterval.x ; //random int in shooting interval
            framesUntilShoot = fps * seconds;
            waitingToShoot = true;
        }
        else if(waitingToShoot) {
            framesUntilShoot--;
            if (framesUntilShoot <= 0) {
                shootNow = true;
                waitingToShoot = false;
            }
        }
    }

    private void checkAlienWin() {
        if(/*mHitBox.*/mHitBox.bottomOutOfBounds()) {
            SimpleCannon.lives = 0; //game over when aliens reach bottom of screen
        }
    }

    public RectF getHitBox(){
        return mHitBox.getHitBox();
    }

    public boolean isActive(){
        return mHitBox.isActive();
    }

}