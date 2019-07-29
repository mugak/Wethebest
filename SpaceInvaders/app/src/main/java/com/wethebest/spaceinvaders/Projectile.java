package com.wethebest.spaceinvaders;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/*An abstract class is used for the ease of inheritance
*for the player and alien projectiles.
*The only difference between the two classes is the
*direction of the velocity, yVel.
*/
public abstract class Projectile implements GameObject {
    //Hitbox associated with rect

    public HitBox mHitBox;
    protected RectF mRect = new RectF();

    protected float xVel = 0;
    protected float yVel = 0;

    private float projWidth;
    private float projHeight;

    private Paint mPaint = new Paint();
    protected Bitmap mBitmap;

    protected boolean isActive = true;
    protected Point mScreenSize;

    protected SoundEngine soundEngine;

    Projectile(SpaceInvadersApp app){
        mScreenSize = app.mScreenSize;


        projWidth = mScreenSize.x/80;
        projHeight = mScreenSize.x/40;

        isActive = true;
        soundEngine = new SoundEngine(app.context);
    }

    //Updates the position of the projectile
    public void update(long fps){
        mRect.left = mRect.left + (xVel/fps);
        mRect.top = mRect.top + (yVel/fps);

        mRect.right = mRect.left + projWidth;
        mRect.bottom = mRect.top + projHeight;

        if(isActive) {
            checkBounds();
        }
    }

    public void display(Canvas canvas) {
        mPaint.setColor(Color.argb(255, 255, 255, 255));

        mBitmap = Bitmap.createScaledBitmap(mBitmap, (int)projWidth, (int)projHeight, true );

        canvas.drawBitmap(mBitmap, this.getHitBox().left, this.getHitBox().top, mPaint);
    }

    public void playAudio(){

    }


    void setPos(float x, float y){
        mRect.left = x + projWidth/2;
        mRect.right = mRect.left + projWidth;
        mRect.top = y + projHeight/2;
        mRect.bottom = mRect.top + projHeight;
    }

    //Wrapper for setPos so that Projectile implements GameObject
    public void reset(Point location) {
        isActive = false;
    }



//    public void collide(GameObject gameObject) {
//        isActive = false;
//    }

    //Returns rect of projectile to be drawn
    public RectF getHitBox() {
        return mRect;
    }

    public boolean isActive() {
        return isActive;
    }

    public void checkBounds() {
        if(mRect.centerX() > mScreenSize.x || mRect.centerX() < 0){
            isActive = false;
        }
        if(mRect.centerY() > mScreenSize.y || mRect.centerY() < 0 ){
            isActive = false;
        }
    }

}

