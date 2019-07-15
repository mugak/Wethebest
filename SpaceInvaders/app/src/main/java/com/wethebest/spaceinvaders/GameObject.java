package com.wethebest.spaceinvaders;

public interface GameObject {
    //Implements how the object changes state each frame
    void update(long fps);

    //Implements the way the object will be displayed to the screen
    void display();

    //Implements the behavior of how an object should change state when it is collided with
    void collide(GameObject gameobject);

    //Checks to see if object is within screen
    void checkBounds(int screenX, int screenY);

    //Checks to see if object should be in game
    boolean isActive();
}
