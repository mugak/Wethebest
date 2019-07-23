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

    List<Alien> aliens;

    AlienRow(Point screenSize) {
        aliens = new LinkedList<Alien>();
        mScreenSize = screenSize;
    }

    private void checkBounds() {

    }
}
