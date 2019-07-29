package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

class PlayerProj implements GameObject{
    SpaceInvadersApp app;

    public HitBox mHitBox;
    private Paint mPaint = new Paint();
    private boolean isActive = true;
    private float yVel;
    protected Point mScreenSize;

    private final PointF SIZE;
    private final int SPRITE_ID = R.drawable.projectile_a;

    PlayerProj(SpaceInvadersApp app) {
        this.app = app;
        mScreenSize = app.mScreenSize;
        SIZE = new PointF(mScreenSize.x / 80, mScreenSize.x / 40);
        yVel = -mScreenSize.y;

        mHitBox = new HitBox.Builder(this.app, SIZE).withSprite(SPRITE_ID).withVelocity(yVel).build();

    }

    @Override
    public void update(long fps){
        mHitBox.moveVertically(yVel/fps);
    }

    public void display(Canvas canvas){
        mHitBox.display(canvas);
    }

    public void playAudio(){
        //Creation and removal of projectile is handled in cannon and invader classes
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
