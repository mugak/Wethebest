package com.wethebest.spaceinvaders;
import android.graphics.RectF;

/*An abstract class is used for the ease of inheritance
*for the player and alien projectiles.
*The only difference between the two classes is the
*direction of the velocity, yVel.
*/
public abstract class Projectile{
    //Projectile will be a rectangle as a placeholder
    private RectF rect;

    private float xVel;
    protected float yVel;

    private float projWidth;
    private float projHeight;

    Projectile(int screenX){
        projWidth = screenX/100;
        projHeight = screenX/100;
        xVel = 0;
        rect = new RectF();
    }


    //Returns rect of projectile to be drawn
    RectF getRect(){
        return rect;
    }


    //Updates the position of the projectile
    void update(long fps){
        rect.left = rect.left + (xVel/fps);
        rect.top = rect.top + (yVel/fps);

        rect.right = rect.left + projWidth;
        rect.bottom = rect.top + projHeight;
    }


    void setPos(int x, int y){
        rect.left = x + projWidth/2;
        rect.right = rect.left + projWidth;
        rect.top = y + projHeight/2;
        rect.bottom = rect.top + projHeight;
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