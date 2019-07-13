package com.wethebest.spaceinvaders;
import android.graphics.RectF;

public class Projectile{
    //Projectile will be a rectangle
    private RectF rect;

    private float xVel;
    private float yVel;

    private float projWidth;
    private float projHeight;

    Projectile(int screenX, float speed){
        projWidth = screenX/100;
        projHeight = screenX/100;

        xVel = 0;
        yVel = -speed/100;
        rect = new RectF();
    }


    //Returns rect of projectile to be drawn
    RectF getRect(){
        return rect;
    }


    //Updates the position of the projectile
    void update(long fps){
        rect.left = rect.left + (xVel/fps);
        rect.right = rect.right + (yVel/fps);

        rect.right = rect.left + projWidth;
        rect.bottom = rect.top + projHeight;
    }


    void setPos(int x, int y){
        rect.left = x/2;
        rect.right = rect.left + projWidth;
        rect.top = y/2;
        rect.bottom = rect.top + projHeight;
    }
}

public class AlienProj extends Projectile{

    Projectile(int screenX, float speed){
        projWidth = screenX/100;
        projHeight = screenX/100;
        xVel = 0;
        yVel = speed/100;
        rect = new RectF();
    }
}