package com.wethebest.spaceinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.RectF;

//All GameObjects should be displayable and collidable objects
public interface GameObject {
    //Implements how the object changes state each frame
    //Could be used to update position of GameObjects each frame
    void update(long fps);

    //Implements the way the object will be displayed to the screen
    //Requires the class to have it's own Paint object to draw itself to the canvas
    void display(Canvas canvas);

    //Object plays the appropriate sound at the current frame, e.g. during collision or
    //shooting a projectile
    void playAudio();

    //Resets state of object to beginning of GameState
    //Examples would include moving GameObjects to their starting positions in the game or
    // deactivating an object that shouldn't be instantiated yet
    void reset(Point location);

    //Implements the behavior of how an object should change state when it is collided with
    //Implementations of GameObject should check to see if the passed gameObject parameter is
    //of the proper type to collide with
    void collide(GameObject gameobject);

    //Necessary for the SpaceInvadersApp class to calculate if a collision has occured between two
    // objects
    RectF getHitBox();

    //Checks to see if object should be in game
    //If false then the SpaceInvadersApp will remove it's reference to the object and it will dissapear
    boolean isActive();
}
