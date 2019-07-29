package com.wethebest.spaceinvaders;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.widget.Space;

class PlayerProj {
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


    public void update(long fps){
        mHitBox.moveUp();
    }

    @Override
    public void collide (GameObject gameObject) {
        if(!(gameObject instanceof SimpleCannon)) {
            isActive = false; //PlayerProj can't shoot the player
        }
    }
}
