package com.wethebest.spaceinvaders;

import android.graphics.Point;
import android.graphics.PointF;

public class Alien extends GameObject {
    private final PointF SHOOT_INTERVAL = new PointF(5, 25); //shoots every x-y seconds

    //Shoot projectiles randomly
    private GameObject mProj;
    private PointF shootInterval = SHOOT_INTERVAL;
    private float frameCount = 0;
    public boolean shootNow = false;
    private boolean playHit = false;

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
        if(playHit){
            app.soundEngine.alienHit();
            playHit = false;
        }
    }

    public void collide(GameObject gameObject) {
        if (gameObject instanceof PlayerProj) {
            playHit = true;
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
            float seconds = getRandomFloat(shootInterval);
            frameCount = fps * seconds;
        } //if alien is not waiting to shoot, assign frameCount
        else {
            frameCount--;
            if (frameCount <= 0) {
                shootNow = true;
            }
        } //alien is waiting to shoot
    }

    private float getRandomFloat(PointF interval) {
        return app.rand.nextFloat() * (interval.y - interval.x) + interval.x;

    }
    //Set the Shoot
    public void setShootInterval(float factor){
        float min = (SHOOT_INTERVAL.x * factor);
        float max = (SHOOT_INTERVAL.y * factor);
        shootInterval = new PointF(min, max);
    }

    private void checkAlienWin() {
        if(mHitBox.bottomOutOfBounds()) {
            app.getPlayer().lives = 0; //game over when aliens reach bottom of screen
        }
    }
}