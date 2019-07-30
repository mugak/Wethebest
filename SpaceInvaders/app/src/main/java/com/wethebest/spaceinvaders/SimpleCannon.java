package com.wethebest.spaceinvaders;

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

    private final int INVICIBLE_SECONDS = 2; //how long cannon is invincible
    public final int MAX_LIVES = 3;
    public int lives = MAX_LIVES;

    private boolean playShoot = false;

    private boolean invincible = false;
    private long frameCount = 0;

    SimpleCannon(SpaceInvadersApp app) {
        super(app,
                new PointF(app.mScreenSize.x / 10, app.mScreenSize.x / 10),
                R.drawable.player,
                new PointF(app.mScreenSize.x / 2, app.mScreenSize.y - app.mScreenSize.x/10));
        //super(app, size, sprite, position)
    }

    public void update(long fps) {
        if(((SpaceInvaders)app.context).yAcceleration >= .08f //tilt thresholds for cannon to stay still
                || ((SpaceInvaders)app.context).yAcceleration <= -.08f)
        {
            //change this multiplying constant to change movement speed
            mHitBox.moveHorizontally(((SpaceInvaders)app.context).yAcceleration * 10);
            mHitBox.horizontalStayInBounds();
        }

        if(invincible && frameCount <= 0) {
            frameCount = fps * INVICIBLE_SECONDS;
        }
        else {
            frameCount--;
            if(frameCount <= 0) {
                invincible = false;
                mHitBox.setBitmap(SPRITE_ID);
            }
        }
    }

    public void playAudio(){
        if(playShoot) {
            app.soundEngine.playerShoot();
            playShoot = false;
        }
    }

    public void collide(GameObject gameObject) {
        if(gameObject instanceof AlienProj) {
            if(!invincible) {
                lives -= 1;
                reset();
                invincible = true;
                mHitBox.setBitmap(INVINCIBLE_SPRITE_ID);
            }
        }
    }

    public PlayerProj shoot() {
        PlayerProj mProj = new PlayerProj(app);
        mProj.setPosition(mHitBox.centerTop());
        playShoot = true;
        return mProj;
    }
}