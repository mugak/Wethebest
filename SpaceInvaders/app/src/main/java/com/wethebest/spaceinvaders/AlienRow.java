package com.wethebest.spaceinvaders;
/*all aliens reverse direction based on when the largest row hits the edge of screen
as alien count goes down, alien speed increases (exponentially)

AlienRow
checkBounds
size
numAliens*/

import android.graphics.Point;
import android.graphics.PointF;
import android.content.Context;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class AlienRow {
    private int numAliens;
    private Point mScreenSize;
    public PointF alienPos; //top left corner of first alien in the row
    private float spaceBetweenAliens;

    private SpaceInvadersApp app;

    public List<Alien> aliens;

    AlienRow(SpaceInvadersApp app) {
        this.app = app;
        aliens = new LinkedList<Alien>();
        mScreenSize = app.mScreenSize;
        numAliens = 4; //TODO hardcoded
        spaceBetweenAliens = Alien.alienSize.x/2; //TODO set better spacing
    }

    public void setAliens() {
        for(int i = 0; i < numAliens; i++) {
            //Alien mAlien = new Alien(app);

            GameObject mAlien = GameObjectFactory.getGameObject("alien");
            ((Alien) mAlien).setPos(alienPos.x + i * (Alien.alienSize.x + spaceBetweenAliens), alienPos.y);
            aliens.add((Alien) mAlien); //TODO need to refactor GameObject so we don't have these ugly casts
        }
    }

    private void checkBounds() {

    }
}
