package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.os.Bundle;

public class Cannon {

    // save starting point for each object in UI class or SpaceInvadersApplication class
    public final Point START = UI.cannonStart;
    // store location of cannon for movement
    public Point2D location;
    public int lives;

    // initialize Cannon with starting location stored in UI or SpaceInvadersApplication class
    public Cannon() {
        location = START;
        lives = 3;
    }

    // initialize Cannon with any point & number of lives
    public Cannon(Point2D loc, int lives) {
        location = loc;
        this.lives = lives;
    }

    /* For classes requiring speed: speed should be a multiplier of the default speed
       In SpaceInvadersApplication there should be a timer running throughout the game,
       where every "second" is counted by dividing the time passed by a constant (ex. if
       the time goes up by 60 every second, we divide by 60 to create a more reasonable
       second). If the speed entered is 2, then whatever is supposed to happen in a second
       will happen in .5 seconds.

       BUT HONESTLY MIGHT NOT BE NECESSARY JUST A CONCEPT TO THINK ABOUT
     */

    // shoot projectiles upwards
    public void shoot(float speed) {
        // initialize new projectile with starting location of cannon
        Projectile p = new Projectile(START);
        // call move in projectile with int UP, initialized in SpaceInvadersApplication?
        p.move(UP, speed); // in move, projectile stops when hits an obstacle
    }

    /* moves 1 space unit (depending on speed) to either direction, called in a loop
       in SpaceInvadersApplication as long as the user presses on the screen
     */

    // direction represented by ints LEFT and RIGHT
    public void move(int direction, float speed) {
        switch (direction) {

            // BASE_SPEED int in UI or SpaceInvadersApplication
            case LEFT: location.x -= (speed * BASE_SPEED);
            case RIGHT: location.y += (speed * BASE_SPEED);
        }
    }

    public void beenHit() {
        lives--;
        // holds the animation for the cannon to disintegrate
        UI.cannonDisintegrate();
        // stops the alien army for the amount of time necessary for the cannon to
        // disintegrate and restart
        AlienArmy.stop(AlienArmy.CANNON_TIME);

        // once cannon loses lives, set the gamestate to game over
        if (lives == 0) SpaceInvadersApp.setGameState(GAME_OVER);
        // restarts the cannon if the current game is still in play
        else UI.cannonReappear();
    }



}