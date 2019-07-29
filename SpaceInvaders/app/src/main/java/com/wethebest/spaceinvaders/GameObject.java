package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

public abstract class GameObject {
    private final int SPRITE_ID;
    private final PointF SIZE;
    private final PointF POSITION;
    protected final float VELOCITY;
    protected final float SPEED;

    protected SpaceInvadersApp app;
    protected HitBox mHitBox;

    public boolean isActive = true;

    //TODO create GameObjectBuilder, use GameObjectFactory
    GameObject(SpaceInvadersApp app, PointF size, int spriteID) {
        this(app, size, spriteID, new PointF(0, 0), 0);
    }

    GameObject(SpaceInvadersApp app, PointF size, int spriteID, PointF position) {
        this(app, size, spriteID, position, 0);
    }

    GameObject(SpaceInvadersApp app, PointF size, int spriteID, float velocity) {
        this(app, size, spriteID, new PointF(0, 0), velocity);
    }

    GameObject(SpaceInvadersApp app, PointF size, int spriteID, PointF position, float velocity) {
        this.app = app;
        this.SIZE = size;
        this.SPRITE_ID = spriteID;
        this.POSITION = position;
        this.VELOCITY = velocity;
        this.SPEED = Math.abs(velocity);
        mHitBox = new HitBox.Builder(app, SIZE).withSprite(SPRITE_ID).withPosition(POSITION).withVelocity(VELOCITY).build();
    }

    public abstract void update(long fps);

    public abstract void playAudio();

    public abstract void collide(GameObject gameobject);


    public void display(Canvas canvas) { mHitBox.display(canvas); };

    public RectF getHitBox() { return mHitBox.getHitBox(); }

    public void reset() { }

    public void setPosition(PointF position) { mHitBox.setPosition(position); }

    public boolean isActive(){
        return isActive;
    }
}
