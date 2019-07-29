package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

public class BarrierBlock extends GameObject {
    private final int MAX_DURABILITY = 1;
    private int durability = MAX_DURABILITY;
    private boolean playHit = false; //Sound effect

    BarrierBlock(SpaceInvadersApp app){
        super(app, new PointF(app.mScreenSize.x / 20, app.mScreenSize.y / 40), R.drawable.def_brick_01);
        //super(app, size, sprite)
    }

    public void update(long fps) { }

    public void playAudio() {
        if(playHit) {
            app.soundEngine.alienShoot(); //TODO get barrierHit() sound file
            playHit = false;
        }
    }

    public void collide(GameObject gameObject) {
        if (gameObject instanceof AlienProj || gameObject instanceof PlayerProj) {
            playHit = true;
            removeDurability(1); //TODO could implement projectile.damage in the future, for now just 1
            if(durability <= 0) {
                isActive = false;
            }
        }
    }

    private void removeDurability(int x){
        durability -= x;
    }
}