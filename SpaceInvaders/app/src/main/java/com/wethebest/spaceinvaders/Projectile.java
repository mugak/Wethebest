package com.wethebest.spaceinvaders;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

/*An abstract class is used for the ease of inheritance
*for the player and alien projectiles.
*The only difference between the two classes is the
*direction of the velocity, yVel.
*/
public abstract class Projectile implements GameObject {
    //Projectile will be a rectangle as a placeholder
    private RectF mRect;

    private float xVel;
    protected float yVel;

    private float projWidth;
    private float projHeight;

    private Paint mPaint;

    private boolean isActive;

    Projectile(int screenX){
        projWidth = screenX/100;
        projHeight = screenX/100;
        xVel = 0;
        mRect = new RectF();
        mPaint = new Paint();
        isActive = true;
    }


    //Returns rect of projectile to be drawn
    RectF getRect(){
        return mRect;
    }


    //Updates the position of the projectile
    public void update(long fps){
        mRect.left = mRect.left + (xVel/fps);
        mRect.top = mRect.top + (yVel/fps);

        mRect.right = mRect.left + projWidth;
        mRect.bottom = mRect.top + projHeight;
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

    public void display(Canvas canvas) {
        mPaint.setColor(Color.argb(255, 255, 255, 255));

        canvas.drawRect(mRect, mPaint);
    }

    public void collide(GameObject gameObject) {
        isActive = false;
    }

    public RectF getHitBox() {
        return mRect;
    }

    public boolean isActive() {
        return isActive;
    }
}
class PlayerProj extends  Projectile{
    PlayerProj(int screenX){
        super(screenX);
        //Vertical velocity is negative for
        //the proj to travel down the screen
        yVel = -screenX/3;
    }
}
class AlienProj extends Projectile{

    AlienProj(int screenX){
        super(screenX);
        //Vertical velocity is positive for
        //the proj to travel down the screen
        yVel = screenX / 3;
    }
}