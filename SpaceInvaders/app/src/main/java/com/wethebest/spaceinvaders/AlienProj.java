package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

public class AlienProj implements GameObject{
    //DEFAULTS
    private final int SPRITE_ID = R.drawable.alien_laser;
    private final PointF SIZE;
    private final float VELOCITY;

    private SpaceInvadersApp app;
    private HitBox mHitBox;
    private boolean isActive = true;

    AlienProj(SpaceInvadersApp app) {
        this.app = app;
        SIZE = new PointF(app.mScreenSize.x / 160, app.mScreenSize.x / 40);
        VELOCITY = app.mScreenSize.y / 3;

        mHitBox = new HitBox.Builder(this.app, SIZE).withSprite(SPRITE_ID).withVelocity(VELOCITY).build();
    }

    public void update(long fps){ mHitBox.moveVertically(VELOCITY / fps); }

    public void display(Canvas canvas){ mHitBox.display(canvas); }

    public void playAudio(){
        //Creation and removal of projectile is handled in cannon and invader classes
    }

    public void reset(){
        //Probably will override supermethod in GameObject class
    }

    public void setPosition(PointF position) {
        mHitBox.setPosition(position);
    }
    public RectF getHitBox(){
        return mHitBox.getHitBox();
    }

    public boolean isActive() {
        return isActive;
    }

    public void collide(GameObject gameObject) {
        if((gameObject instanceof SimpleCannon || gameObject instanceof BarrierBlock)) {
            isActive = false;
        }
    }

    public void checkBounds() {
        if(mHitBox.topOutOfBounds()|| mHitBox.bottomOutOfBounds()) {
            isActive = false;
        }
    }
}