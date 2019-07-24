package com.wethebest.spaceinvaders;
/*all aliens reverse direction based on when the largest row hits the edge of screen
as alien count goes down, alien speed increases (exponentially)

AlienRow
checkBounds
size
numAliens*/

import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class AlienRow {
    private int numAliens;
    private Point mScreenSize;
    public PointF alienPos; //top left corner of first alien in the row
    private int spaceBetweenAliens;

    public List<Alien> aliens;

    AlienRow(Point screenSize) {
        aliens = new LinkedList<Alien>();
        mScreenSize = screenSize;
        numAliens = 4; //TODO hardcoded
        spaceBetweenAliens = mScreenSize.x/6; //TODO set better spacing
    }

    public void setAliens() {
        for(int i = 0; i < numAliens; i++) {
            Alien mAlien = new Alien(mScreenSize);
            mAlien.setPos(alienPos.x + i * (Alien.alienSize.x + spaceBetweenAliens), alienPos.y);
            aliens.add(mAlien);
        }
    }

    private void checkBounds() {

    }
}
