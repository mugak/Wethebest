package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.RectF;

public interface GameObject {
    //Implements how the object changes state each frame
    void update(long fps);

    //Implements the way the object will be displayed to the screen
    void display(Canvas canvas);

    //Implements the behavior of how an object should change state when it is collided with
    //Implementations of GameObject should check to see if the passed gameObject parameter is
    //of the proper type to collide with
    void collide(GameObject gameobject);

    //Necessary for calculating collisions
    RectF getHitBox();

    //Checks to see if object should be in game
    boolean isActive();
}
