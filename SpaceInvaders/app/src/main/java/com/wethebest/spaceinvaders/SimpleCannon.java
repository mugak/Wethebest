package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

class SimpleCannon implements GameObject {
    //DEFAULTS
    private final int SPRITE_ID = R.drawable.player;
    private final int INVINCIBLE_SPRITE_ID = R.drawable.player_invincible;
    private final PointF DEFAULT_POSITION;

    private final int INVICIBLE_SECONDS = 2; //how long cannon is invincible
    public final int MAX_LIVES = 3;
    public int lives = MAX_LIVES;

    //SET BASED ON SCREEN SIZE
    private final PointF SIZE;

    private SpaceInvadersApp app;
    private HitBox mHitBox;
    private boolean isActive = true;

    private boolean playShoot = false;

    private boolean invincible = false;
    private long frameCount = 0;

    SimpleCannon(SpaceInvadersApp app) {
        this.app = app;

        mHitBox = new HitBox(app);
        SIZE = new PointF(app.mScreenSize.x / 10, app.mScreenSize.x / 10);
        DEFAULT_POSITION = new PointF(app.mScreenSize.x / 2, app.mScreenSize.y - SIZE.y);
        mHitBox.setSize(SIZE);
        mHitBox.setBitmap(SPRITE_ID);
    }

    public void update(long fps) {
        if(((SpaceInvaders)app.context).yAcceleration >= .08f || ((SpaceInvaders)app.context).yAcceleration <= -.08f) { //tilt thresholds for cannon to stay still
            mHitBox.moveHorizontally(((SpaceInvaders)app.context).yAcceleration * 10); //change this multiplying constant to change movement speed
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

    public void display(Canvas canvas) {
        mHitBox.display(canvas);
    }

    public void playAudio(){
        if(playShoot) {
            app.soundEngine.playerShoot();
            playShoot = false;
        }
    }

    public RectF getHitBox() {
        return mHitBox.getHitBox();
    }

    public void setPosition(PointF position) {
        mHitBox.setPosition(position);
    }

    public void reset() {
        setPosition(DEFAULT_POSITION);
    }

    public boolean isActive() {
        return isActive;
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
        mProj.mHitBox.setPosition(mHitBox.centerTop());
        playShoot = true;
        return mProj;
    }
}