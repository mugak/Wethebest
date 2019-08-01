package com.wethebest.spaceinvaders;

import android.graphics.PointF;

/*
Alien handles the enemies movement and game logic.
The enemy moves side to side, gradually moving towards the player.
It also shoots projectiles randomly.
Instantiated in AlienArmy
 */
public class Alien extends GameObject {
    private PointF baseShootInterval = new PointF(10, 25); //shoots every x-y seconds

    //Shoot projectiles randomly
    private PointF shootInterval = baseShootInterval;
    public boolean shootNow = false;
    private Counter waitToShoot;

    //Sound effects
    private boolean playShoot = false;
    private boolean playHit = false;

    private boolean movingRight = true; //Current movement direction

    Alien(SpaceInvadersApp app, PointF size, int spriteID, PointF position, float velocity) {
        super(app, size, spriteID, position, velocity);
        waitToShoot = new AutomaticCounter(getRandomFloat(shootInterval));
    }

    public void update(long fps) {
        if(movingRight) {
            mHitBox.velocity = mHitBox.speed;
        }
        else {
            mHitBox.velocity = -mHitBox.speed;
        }

        if(fps != 0) {
            mHitBox.moveHorizontally(mHitBox.velocity / fps);
            handleCounters(fps);
        }

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
            GameObject mProj = GameObjectFactory.getGameObject("AlienProj");
            mProj.setPosition(mHitBox.centerBottom());
            playShoot = true;
            return mProj;
    }

    private void handleCounters(long fps) {
        if(waitToShoot.run(fps)) {
            shootNow = true;
            waitToShoot.setSeconds(getRandomFloat(shootInterval));
        }
    }

    private float getRandomFloat(PointF interval) {
        return app.rand.nextFloat() * (interval.y - interval.x) + interval.x;

    }
    //Set the Shoot
    public void setShootInterval(float factor){
        float min = (baseShootInterval.x * factor);
        float max = (baseShootInterval.y * factor);
        shootInterval = new PointF(min, max);
    }

    private void checkAlienWin() {
        if(mHitBox.bottomOutOfBounds()) {
            app.getPlayer().lives = 0; //game over when aliens reach bottom of screen
        }
    }

    public void decreaseBaseShootInterval() {
        if(baseShootInterval.x > 1) {
            baseShootInterval.x -= 2;
        }

        if(baseShootInterval.y > 1) {
            baseShootInterval.y -= 2;
        }
    }
}