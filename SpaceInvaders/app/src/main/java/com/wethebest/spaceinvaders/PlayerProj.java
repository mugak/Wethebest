package com.wethebest.spaceinvaders;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.widget.Space;

class PlayerProj implements GameObject{
    SpaceInvadersApp app;

    public HitBox mHitBox;
    private Paint mPaint = new Paint();
    private boolean isActive = true;
    protected Point mScreenSize;

    PlayerProj(SpaceInvadersApp app){
        this.app = app;
        mScreenSize = app.mScreenSize;
        mHitBox = new HitBox(app);
        mHitBox.setSize(new PointF(mScreenSize.x/80, mScreenSize.x/40));
        mHitBox.setBitmap(R.drawable.projectile_a);

    }

    @Override
    public void update(long fps){
        mHitBox.moveUp();
    }

    public void display(Canvas canvas){
        mHitBox.display(canvas);
    }

    public void playAudio(){
        //Creation and removal of projectile is handled in cannon and invader classes
    }

    public void reset(Point location){
        //Probably will override supermethod in GameObject class
    }

    public RectF getHitBox(){
        return mHitBox.getHitBox();
    }

    public boolean isActive() {
        return isActive;
    }

    public void collide (GameObject gameObject) {
        if(!(gameObject instanceof SimpleCannon)) {
            isActive = false; //PlayerProj can't shoot the player
        }
    }
}
