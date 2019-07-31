package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.PointF;

/*
SimpleCannon represents the cannon controlled by the player
Movement is horizontal, determined by the device's accelerometer sensor.
The player shoots projectiles when the screen is touched
It has features such as lives, firing rate, invincibility
Instantiated in GameObjectManager
*/
public class SimpleCannon extends GameObject {
    //DEFAULTS
    private final int SPRITE_ID = R.drawable.player;
    private final int INVINCIBLE_SPRITE_ID = R.drawable.player_invincible;

    public static final int INVINCIBLE_SECONDS = 2; //how long cannon is invincible
    public static final float FIRING_RATE = .1f; //how frequently the player can shoot
    public static final float AMMO_REGEN_RATE = 1f; //how frequently ammo regenerates
    public static final int MAX_AMMO = 5; //total projectiles the player can shoot
    public static final int MAX_LIVES = 3;

    public int lives;
    public int ammo;

    private boolean playShoot = false;
    private boolean playHit = false; //Sound effect

    public Counter waitToShoot;
    private Counter invincible;
    private Counter waitForAmmo;

    SimpleCannon(SpaceInvadersApp app, PointF size, int spriteID, PointF position, float velocity) {
        super(app, size, spriteID, position, velocity);

        lives = MAX_LIVES;
        ammo = MAX_AMMO;

        waitToShoot = new Counter(FIRING_RATE);
        invincible = new Counter(INVINCIBLE_SECONDS);
        waitForAmmo = new Counter(AMMO_REGEN_RATE);
    }

    public void update(long fps) {
        if(((SpaceInvaders)app.context).yAcceleration >= .08f //tilt thresholds for cannon to stay still
                || ((SpaceInvaders)app.context).yAcceleration <= -.08f)
        {
            //change this multiplying constant to change movement speed
            mHitBox.moveHorizontally(((SpaceInvaders)app.context).yAcceleration * 10);
            mHitBox.horizontalStayInBounds();

        }

        if(invincible.on && !invincible.isCountingDown) {
            mHitBox.setBitmap(INVINCIBLE_SPRITE_ID);
            invincible.setFPS(fps);
        }
        else if(invincible.on && invincible.isCountingDown) {
            if(invincible.finished()) {
                mHitBox.setBitmap(SPRITE_ID);
            }
        }

        if(waitToShoot.on && !waitToShoot.isCountingDown) {
            waitToShoot.setFPS(fps);
        }
        else if(waitToShoot.on && waitToShoot.isCountingDown) {
            waitToShoot.finished();
        }

        waitForAmmo.on = true;
        if(!waitForAmmo.isCountingDown) {
            waitForAmmo.setFPS(fps);
        }
        else if(waitForAmmo.isCountingDown) {
            if(waitForAmmo.finished()) {
                ammo += 1;
                if(ammo >= MAX_AMMO) {
                    ammo = MAX_AMMO;
                }
            }
        }

    }

    public void playAudio(){
        if(playShoot) {
            app.soundEngine.playerShoot();
            playShoot = false;
        }
        if(playHit){
            app.soundEngine.playerHit();
            playHit = false;
        }
        app.soundEngine.setEngineHumPitch(abs(((SpaceInvaders)app.context).yAcceleration )/9.81f);
    }

    public void collide(GameObject gameObject) {
        if(gameObject instanceof AlienProj) {
            if(!invincible.on) {
                lives -= 1;
                reset();
                invincible.on = true;
            }
        }
    }

    public void reset() {
        waitToShoot.reset();
    }


    public GameObject shoot() {
        ammo--;
        GameObject mProj = GameObjectFactory.getGameObject("PlayerProj");
        mProj.setPosition(mHitBox.centerTop());
        playShoot = true;
        return mProj;
    }

    public boolean canShoot() {
        if(waitToShoot.on || ammo <= 0) {
            return false; //if player cant shoot yet, return false
        }

        waitToShoot.on = true; //player must wait after this shot
        return true; //shoot a projectile
    }

    private void regenerateAmmo() {

    }
}