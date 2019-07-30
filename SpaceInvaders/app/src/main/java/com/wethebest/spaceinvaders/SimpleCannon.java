package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.PointF;

/*@SimpleCannon
* This game object shoots projectiles and is controlled by the player.
* Movement is horizontally left or right and is determined by the device's accelerometer sensor.
* When the player shoots, a PlayerProj object at the position of the cannon.
* The cannon starts with MAX_LIVES number of lives and has a short interval of invincibility when hit.
*/
public class SimpleCannon extends GameObject {
    //DEFAULTS
    private final int SPRITE_ID = R.drawable.player;
    private final int INVINCIBLE_SPRITE_ID = R.drawable.player_invincible;

    private PlayerConfig pc;

    public int lives;
    public int ammo;

    private boolean playShoot = false;
    private boolean playHit = true; //Sound effect

    public Counter waitToShoot;
    private Counter invincible;
    private Counter waitForAmmo;

    SimpleCannon(SpaceInvadersApp app, PointF size, int spriteID, PointF position, float velocity, PlayerConfig pc) {
        super(app, size, spriteID, position, velocity);
        this.pc = pc;

        lives = pc.MAX_LIVES;
        ammo = pc.MAX_AMMO;

        waitToShoot = new Counter(pc.FIRING_RATE);
        invincible = new Counter(pc.INVINCIBLE_SECONDS);
        waitForAmmo = new Counter(pc.AMMO_REGEN_RATE);
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
                if(ammo >= pc.MAX_AMMO) {
                    ammo = pc.MAX_AMMO;
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