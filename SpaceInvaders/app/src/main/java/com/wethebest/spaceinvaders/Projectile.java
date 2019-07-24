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
    //Projectile will be a rectangle as a placeholder
    protected RectF mRect;

    private float xVel;
    protected float yVel;

    private float projWidth;
    private float projHeight;

    private Paint mPaint;

    private Bitmap mBitmap;

    protected boolean isActive;
    protected Point mScreenSize;

    Projectile(Point screenSize){
        mScreenSize = screenSize;
        projWidth = mScreenSize.x/100;
        projHeight = mScreenSize.x/100;
        xVel = 0;
        mRect = new RectF();
        mPaint = new Paint();
        isActive = true;
        //mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_laser);
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

        if(isActive) {
            checkBounds();
        }
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

    public void checkBounds() {
    }

}

class PlayerProj extends  Projectile{
    PlayerProj(Point screenSize){
        super(screenSize);
        yVel = -mScreenSize.x/3; //Projectile shoots up
    }

    @Override
    public void collide (GameObject gameObject) {
        if(!(gameObject instanceof SimpleCannon)) {
            isActive = false; //PlayerProj can't shoot the player
        }
    }
    //TODO: add checkbounds(), possibly add to GameObject interface


}
class AlienProj extends Projectile{

    AlienProj( Point screenSize){
        super(screenSize);
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