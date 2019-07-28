package com.wethebest.spaceinvaders;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.widget.Space;

class PlayerProj extends  Projectile{
    PlayerProj(SpaceInvadersApp app){
        super(app);
        this.mBitmap = BitmapFactory.decodeResource(app.context.getResources(), R.drawable.powerup_blue01);
        yVel = -mScreenSize.x/3; //Projectile shoots up
        //soundEngine.playerShoot();
    }

    @Override
    public void collide (GameObject gameObject) {
        if(!(gameObject instanceof SimpleCannon)) {
            isActive = false; //PlayerProj can't shoot the player
        }
    }
    //TODO: add checkbounds(), possibly add to GameObject interface


}
