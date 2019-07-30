package com.wethebest.spaceinvaders;

import android.graphics.Point;
import android.graphics.PointF;

public class Alien extends GameObject {
    private final Point SHOOT_INTERVAL = new Point(5, 20); //shoots every 5-20 seconds

    //Shoot projectiles randomly
    private GameObject mProj;
    private Point shootInterval = SHOOT_INTERVAL;
    private long frameCount = 0;
    public boolean shootNow = false;

    private boolean movingRight = true; //Current movement direction
    private boolean playShoot = false; //Sound effect

    Alien(SpaceInvadersApp app, PointF size, int spriteID, PointF position, float velocity) {
        super(app, size, spriteID, position, velocity);
    }

    public void update(long fps) {
        if(movingRight) {
            mHitBox.velocity = mHitBox.speed;
        }
        else {
            mHitBox.velocity = -mHitBox.speed;
        }

        mHitBox.moveHorizontally(mHitBox.velocity / fps);

        timeToShoot(fps);
        checkAlienWin();
    }

    public void playAudio() {
        if(playShoot) {
            app.soundEngine.alienShoot();
            playShoot = false;
        }
    }

    public void collide(GameObject gameObject) {
        if (gameObject instanceof PlayerProj) {
            isActive = false;
        }
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
        mHitBox.speed = SPEED * multiplier;
    }

    public GameObject shoot() {
            mProj = GameObjectFactory.getGameObject("AlienProj");
            mProj.setPosition(mHitBox.centerBottom());
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