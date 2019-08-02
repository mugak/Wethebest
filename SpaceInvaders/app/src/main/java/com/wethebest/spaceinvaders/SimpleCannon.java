package com.wethebest.spaceinvaders;

import android.content.Intent;
import android.graphics.PointF;
import android.os.BaseBundle;
import android.os.Bundle;

/*
SimpleCannon represents the cannon controlled by the player
Movement is horizontal, determined by the device's accelerometer sensor.
The player shoots projectiles when the screen is touched
It has features such as lives, firing rate, invincibility
Instantiated in GameObjectManager
*/
public class SimpleCannon extends GameObject {


    //DEFAULTS
    private final int SPRITE_ID = R.drawable.biploar_red;
    private final int INVINCIBLE_SPRITE_ID = R.drawable.biploar_red_invincible;

    public static final int INVINCIBLE_SECONDS = 2; //how long cannon is invincible
    public static final int MAX_LIVES = 3;

    private final float HIGHEST_FIRING_RATE = .1f;
    private final float HIGHEST_REGEN_RATE = .5f;
    private float firingRate =  1.5f; //how frequently the player can shoot
    private float ammoRegenRate = 3f; //how frequently ammo regenerates

    public int maxAmmo = 2; //total projectiles the player can shoot

    public int lives;
    public int ammo;

    //Sound effects
    private boolean playShoot = false;
    private boolean playHit = false;

    public Counter waitToShoot;
    private Counter invincible;
    private Counter waitForAmmo;

    boolean testBool = false;

    SpaceInvadersApp mApp;

    SimpleCannon(SpaceInvadersApp app, PointF size, int spriteID, PointF position, float velocity) {
        super(app, size, spriteID, position, velocity);

        mApp = app;
        lives = MAX_LIVES;
        ammo = maxAmmo;

        waitToShoot = new Counter(firingRate);
        invincible = new Counter(INVINCIBLE_SECONDS);
        waitForAmmo = new AutomaticCounter(ammoRegenRate);
    }

    public void update(long fps) {
        if (((SpaceInvaders) app.context).yAcceleration >= .08f //tilt thresholds for cannon to stay still
                || ((SpaceInvaders) app.context).yAcceleration <= -.08f) {
            //change this multiplying constant to change movement speed
            mHitBox.moveHorizontally(((SpaceInvaders) app.context).yAcceleration * 10);
            mHitBox.horizontalStayInBounds();

        }
        handleCounters(fps);
    }

    public void playAudio() {
        if (playShoot) {
            app.soundEngine.playerShoot();
            playShoot = false;
        }
        if (playHit) {
            app.soundEngine.playerHit();
            playHit = false;
        }
        app.soundEngine.engineHum(Math.abs(((SpaceInvaders) app.context).yAcceleration) + 1); //modulate engine rate based on screen tilt

    }

    public void collide(GameObject gameObject) {
        if (gameObject instanceof AlienProj) {
            if (!invincible.on) {
                lives -= 1;

                if (lives == 0) {
                    app.isGameOver = true;
                }

                reset();
                invincible.on = true;
                mHitBox.setBitmap(INVINCIBLE_SPRITE_ID);
            }
        }
    }

    public void reset() {
        //waitToShoot.reset();
    }


    public GameObject shoot() {
        ammo--;
        GameObject mProj = GameObjectFactory.getGameObject("PlayerProj");
        mProj.setPosition(mHitBox.centerTop());
        playShoot = true;
        return mProj;
    }

    public boolean canShoot() {
        if (waitToShoot.on || ammo <= 0) {
            return false; //if player cant shoot yet, return false
        }

        waitToShoot.on = true; //player must wait after this shot
        return true; //shoot a projectile
    }

    private void handleCounters(long fps) {
        //Counter.run() returns true when done counting
        if (invincible.run(fps)) {
            mHitBox.setBitmap(SPRITE_ID);
        }

        if (waitToShoot.run(fps)) {
        }

        if (waitForAmmo.run(fps)) {
            ammo += 1;
            if (ammo >= maxAmmo) {
                ammo = maxAmmo;
            }
        }
    }

    public void increaseFireRate(float multiplier) {
        firingRate /= multiplier;
        if(firingRate < HIGHEST_FIRING_RATE) { firingRate = HIGHEST_FIRING_RATE; }
            waitToShoot.setSeconds(firingRate);
    }

    public void increaseAmmoRegenRate(float multiplier) {
        ammoRegenRate /= multiplier;
        if(ammoRegenRate < HIGHEST_REGEN_RATE) { ammoRegenRate = HIGHEST_REGEN_RATE; }
        waitForAmmo.setSeconds(ammoRegenRate);
    }

    public void resetAmmo(){
        ammo = maxAmmo;
    }
}