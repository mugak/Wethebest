package com.wethebest.spaceinvaders;

public interface GameObject {
    //Implements how the object changes state each frame
    void update();

    //Implements the way the object will be displayed to the screen
    void display();

    //Implements the behavior of how an object should change state when it is collided with
    void collide();
}
