package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

/*
GameObject is extended by SimpleCannon, Alien, BarrierBlock, AlienProj, PlayerProj, and UFO
Provides common variables such as sprite, size, and position
Uses HitBox for movement
Created by GameObjectFactory
 */


public abstract class GameObject {
    private final int SPRITE_ID;
    private final PointF SIZE;
    private final PointF POSITION;
    protected final float VELOCITY;
    protected final float SPEED;

    protected SpaceInvadersApp app;
    protected HitBox mHitBox;

    public boolean isActive = true;

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

    public void setVelocity(float velocity) { mHitBox.setVelocity(velocity); }

    public boolean isActive(){
        return isActive;
    }
}

/*
“class GameObject {

    private Transform mTransform;


    private boolean mActive = true;
    private String mTag;

    private GraphicsComponent mGraphicsComponent;
    private UpdateComponent mUpdateComponent;


    void setGraphics(GraphicsComponent g,
                          Context c,
                          GameObjectSpec spec,
                          PointF objectSize,
                          int pixelsPerMetre) {

       mGraphicsComponent = g;
       g.initialize(c, spec, objectSize, pixelsPerMetre);
    }

    void setMovement(UpdateComponent m) {
       mUpdateComponent = m;
    }


    void setPlayerInputTransform(PlayerInputComponent s) {
       s.setTransform(mTransform);
    }


    void setTransform(Transform t) {
        mTransform = t;
    }

    void draw(Canvas canvas, Paint paint, Camera cam) {
   mGraphicsComponent.draw(canvas,
               paint,
               mTransform, cam);
    }

    void update(long fps, Transform playerTransform) {
       mUpdateComponent.update(fps,
                   mTransform,
                   playerTransform);
    }

    boolean checkActive() {
       return mActive;
    }

    String getTag() {
       return mTag;
    }

    void setInactive() {
       mActive = false;
    }

    Transform getTransform() {
       return mTransform;
    }

    void setTag(String tag) {
       mTag = tag;
    }
 }



    */