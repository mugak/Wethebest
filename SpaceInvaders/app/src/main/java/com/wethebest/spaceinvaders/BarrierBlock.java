package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

public class BarrierBlock implements GameObject {
    //DEFAULTS
    private final int SPRITE_ID = R.drawable.def_brick_01;
    private final int MAX_DURABILITY = 1;

    //SET BASED ON SCREEN SIZE
    private final PointF SIZE;

    private SpaceInvadersApp app;
    private HitBox mHitBox;
    private int durability = MAX_DURABILITY;

    //Movement
    private boolean isActive = true;

    //Audio
    private boolean playHit = false;

    BarrierBlock(SpaceInvadersApp app){
        this.app = app;

        //mHitBox = new HitBox(app);
        SIZE = new PointF(app.mScreenSize.x / 20, app.mScreenSize.y / 40); //TODO repeated in Barrier, maybe get from GameConfig
        mHitBox = new HitBox.Builder(this.app, SIZE).withSprite(SPRITE_ID).build();
        //mHitBox.setSize(SIZE);
        //mHitBox.setBitmap(SPRITE_ID);
        //mHitBox.velocity = speed;
    }

    public void update(long fps) { }

    public void display(Canvas canvas) {
        mHitBox.display(canvas);
    }

    public void playAudio() {
        if(playHit) {
            app.soundEngine.alienShoot(); //TODO get barrierHit() sound file
            playHit = false;
        }
    }

    public void setPos(PointF pos) {
        mHitBox.setPosition(pos);
    }

    public void reset() { }

    public RectF getHitBox() {
        return mHitBox.getHitBox();
    }

    public boolean isActive(){
        return isActive;
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