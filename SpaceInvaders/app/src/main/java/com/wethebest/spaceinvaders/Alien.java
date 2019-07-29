package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.Random;

public class Alien implements GameObject {
    //DEFAULTS
    private final int SPRITE_ID = R.drawable.invader_a01;
    private final float BASE_SPEED = 200;
    private final Point SHOOT_INTERVAL = new Point(5, 20); // shoots every 5-20 seconds

    //SET BASED ON SCREEN SIZE
    private final PointF SIZE;

    private SpaceInvadersApp app;
    private HitBox mHitBox;

    //Shoot projectiles randomly
    private AlienProj mProj;
    private Point shootInterval = SHOOT_INTERVAL;
    private long frameCount = 0;
    public boolean shootNow = false;

    //Movement
    private boolean isActive = true;
    private float speed = BASE_SPEED;
    private boolean movingRight = true; //Current movement direction

    //Audio
    private boolean playShoot = false;

    Alien(SpaceInvadersApp app) {
        this.app = app;

        //mHitBox = new HitBox(app);
        SIZE = new PointF(app.mScreenSize.x / 10, app.mScreenSize.y / 10);//TODO repeated in AlienArmy, maybe get from GameConfig
        mHitBox = new HitBox.Builder(this.app, SIZE).withSprite(SPRITE_ID).withVelocity(speed).build();
        //mHitBox.setSize(SIZE);
        //mHitBox.setBitmap(SPRITE_ID);
        //mHitBox.velocity = speed;
    }

    public void update(long fps) {
        if(movingRight) {
            mHitBox.velocity = speed;
        }
        else {
            mHitBox.velocity = -speed;
        }

        mHitBox.moveHorizontally(mHitBox.velocity / fps);

        timeToShoot(fps);
        checkAlienWin();
    }

    public void display(Canvas canvas){
        mHitBox.display(canvas);
    }

    public void playAudio() {
        if(playShoot) {
            app.soundEngine.alienShoot();
            playShoot = false;
        }
    }

    public void setPos(PointF position) {
        mHitBox.setPosition(position);
    }

    public void reset() {
    }

    public void collide(GameObject gameObject) {
        if (gameObject instanceof PlayerProj) {
            //reset(mScreenSize);
            isActive = false;
        }
    }

    public RectF getHitBox(){
        return mHitBox.getHitBox();
    }

    public boolean isActive(){
        return isActive;
    }

    public boolean outOfBounds() {
        return mHitBox.horizontalOutOfBounds();
    }





    public void reverseXVelocity() {
        movingRight = !movingRight;
        mHitBox.moveDown();
        mHitBox.horizontalStayInBounds();
    }

    public void speedUp(float multiplier) {
        speed = BASE_SPEED * multiplier;
    }

    public AlienProj shoot() {
            mProj = new AlienProj(app);
            mProj.mHitBox.setPosition(mHitBox.centerBottom());
            playShoot = true;
            return mProj;
    }

    //calculates when to shoot shooting by counting the number of frames
    private void timeToShoot(long fps) {
        if(frameCount <= 0) {
            int seconds = getRandomInt(shootInterval);
            frameCount = fps * seconds;
        } //if alien is not waiting to shoot, assign frameCount
        else {
            frameCount--;
            if (frameCount <= 0) {
                shootNow = true;
            }
        } //alien is waiting to shoot
    }

    private int getRandomInt(Point interval) {
        return app.rand.nextInt(interval.y - interval.x) + interval.x;
    }

    private void checkAlienWin() {
        if(mHitBox.bottomOutOfBounds()) {
            app.mPlayer.lives = 0; //game over when aliens reach bottom of screen
        }
    }
}