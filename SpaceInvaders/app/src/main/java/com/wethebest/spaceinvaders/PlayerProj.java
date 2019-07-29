package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

public class PlayerProj implements GameObject{
    private final int SPRITE_ID = R.drawable.projectile_a;
    private final PointF SIZE;
    private final float VELOCITY;

    private SpaceInvadersApp app;
    private HitBox mHitBox;
    private boolean isActive = true;

    public PlayerProj(SpaceInvadersApp app) {
        this.app = app;
        SIZE = new PointF(app.mScreenSize.x / 80, app.mScreenSize.x / 40);
        VELOCITY = -app.mScreenSize.y;

        mHitBox = new HitBox.Builder(this.app, SIZE).withSprite(SPRITE_ID).withVelocity(VELOCITY).build();
    }

    @Override
    public void update(long fps){
        mHitBox.moveVertically(VELOCITY/fps);
    }

    public void display(Canvas canvas){
        mHitBox.display(canvas);
    }

    public void playAudio(){
        //Creation and removal of projectile is handled in cannon and invader classes
    }

    public void setPosition(PointF position) {
        mHitBox.setPosition(position);
    }

    public void reset(){
        //Probably will override supermethod in GameObject class
    }

    public RectF getHitBox(){
        return mHitBox.getHitBox();
    }

    public boolean isActive() {
        return isActive;
    }

    public void collide(GameObject gameObject) {
        if((gameObject instanceof BarrierBlock || gameObject instanceof Alien)) {
            isActive = false;
        }
    }

    public void checkBounds() {
        if(mHitBox.topOutOfBounds()|| mHitBox.bottomOutOfBounds()) {
            isActive = false;
        }
    }
}
