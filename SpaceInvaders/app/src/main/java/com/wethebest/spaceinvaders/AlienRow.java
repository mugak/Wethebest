package com.wethebest.spaceinvaders;
/*all aliens reverse direction based on when the largest row hits the edge of screen
as alien count goes down, alien speed increases (exponentially)

AlienRow
checkBounds
size
numAliens*/

import android.graphics.Point;

import java.util.LinkedList;
import java.util.List;

public class AlienRow {
    private int numAliens;
    private Point mScreenSize;
    private Point alienPosition; //top left corner of first alien in row
    private int spaceBetweenAliens;

    List<Alien> aliens;

    AlienRow(Point screenSize) {
        aliens = new LinkedList<Alien>();
        mScreenSize = screenSize;
        numAliens = 4; //TODO hardcoded
        spaceBetweenAliens = mScreenSize.x/10; //TODO set better spacing
        setAlienPosition();
        setAliens();
    }

    private void setAlienPosition() {
        alienPosition = new Point(mScreenSize.x / 10, mScreenSize.y / 10); //TODO set better position
    }

    private void setAliens() {
        for(int i = 0; i < numAliens; i++) {
            Alien mAlien = new Alien(mScreenSize);
            mAlien.setPos( alienPosition.x + mAlien.alienSize.width + spaceBetweenAliens, alienPosition.y);
            aliens.add(mAlien);
        }
    }
    private void checkBounds() {

    }
}
