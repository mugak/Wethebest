package com.wethebest.spaceinvaders;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Point;

class AlienProj extends Projectile{

    AlienProj(SpaceInvadersApp app) {
        super(app);
        this.mBitmap = BitmapFactory.decodeResource(app.context.getResources(), R.drawable.alien_laser);
        yVel = mScreenSize.x/3; //Projectile shoots down
    }

    @Override
    public void collide (GameObject gameObject) {
        if(!(gameObject instanceof Alien)) {
            isActive = false; //AlienProj can't shoot other Aliens
        }
    }

    public void checkBounds() {
        if(mRect.top >= mScreenSize.y) {
            isActive = false;
        }
    }
}
