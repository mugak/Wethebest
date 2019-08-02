package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Paint;
import android.content.Context;

/*
GameObject is extended by SimpleCannon, Alien, BarrierBlock, AlienProj, PlayerProj, and UFO
Provides common variables such as sprite, size, and position
Uses HitBox for movement
Created by GameObjectFactory
 */


public abstract class GameObject {

    // part of Entity-Component pattern, holds data
    // & handles GameObject operations
    private Transform mTransform;
    private GraphicsComponent mGraphicsComponent;
    private UpdateComponent mUpdateComponent;

    private GameObjectType mType;

    private final int SPRITE_ID;
    private final PointF SIZE;
    private final PointF POSITION;
    protected final float VELOCITY;
    protected final float SPEED;

    protected SpaceInvadersApp app;
//    protected HitBox mHitBox;

    public boolean isActive = true;

    GameObject(SpaceInvadersApp app, PointF size, int spriteID, PointF position, float velocity) {
        this.app = app;
        this.SIZE = size;
        this.SPRITE_ID = spriteID;
        this.POSITION = position;
        this.VELOCITY = velocity;
        this.SPEED = Math.abs(velocity);
       // mHitBox = new HitBox.Builder(app, SIZE).withSprite(SPRITE_ID).withPosition(POSITION).withVelocity(VELOCITY).build();
    }

    //public abstract void update(long fps);

    public abstract void playAudio();

    public abstract void collide(GameObject gameobject);

    void setGraphics(GraphicsComponent g, Context c,
                     GameObjectSpec spec,
                     PointF size) {

        mGraphicsComponent = g;
        g.initialize(c, spec, size);
    }

    void setMovement(UpdateComponent m) {
        mUpdateComponent = m;
    }

    void setTransform(Transform t) {
        mTransform = t;
    }

    void display(Canvas canvas) {
        mGraphicsComponent.draw(canvas, mTransform);
    }

    void update(long fps) {
        mUpdateComponent.update(fps, mTransform);
    }

    String getType() {
        return mType.toString();
    }

    void setInactive() {
        isActive = false;
    }

    Transform getTransform() {
        return mTransform;
    }

    void setType(GameObjectType type) {
        mType = type;
    }


    //public void display(Canvas canvas) { mHitBox.display(canvas); };

    //public RectF getHitBox() { return mHitBox.getHitBox(); }

    public void reset() { }

   // public void setPosition(PointF position) { mHitBox.setPosition(position); }

  //  public void setVelocity(float velocity) { mHitBox.setVelocity(velocity); }

    public boolean isActive(){
        return isActive;
    }
}
