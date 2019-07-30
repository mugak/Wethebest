package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AlienArmy {
    //DEFAULTS
    private final int NUM_ALIENS = 12;

    //Fit NUM_ALIENS of aliens in 4 rows and 4 columns
    private final Point DIMENSIONS = new Point(NUM_ALIENS/4 + NUM_ALIENS % 4,
                                                NUM_ALIENS/4 );
    //SET BASED ON SCREEN SIZE
    private final float ROW_SPACING;
    private final float COL_SPACING;
    private final PointF STARTING_POSITION; //top left corner of first alien

    private SpaceInvadersApp app;
    public List<Alien> aliens = new LinkedList<Alien>();
    private boolean reverseNow = false;

    AlienArmy(SpaceInvadersApp app) {
        this.app = app;

        ROW_SPACING = 0;
        COL_SPACING = app.mScreenSize.y / 8;
        STARTING_POSITION = new PointF(app.mScreenSize.y / 10, app.mScreenSize.y / 10);

        createAliens();
    }

    //Instantiates and sets positions of every alien
    private void createAliens() {
        //First get the size of an alien
        GameObject tempAlien = GameObjectFactory.getGameObject("Alien");
        PointF size = new PointF(tempAlien.getHitBox().width(), tempAlien.getHitBox().height());
        int numAliensToAdd = NUM_ALIENS;

        for(int i = 0; i < DIMENSIONS.x; i++) {
            for(int j = 0; j < DIMENSIONS.y; j++) {
                if(numAliensToAdd <=0){break;}

                PointF position = new PointF(STARTING_POSITION.x + i * (size.x + COL_SPACING),
                        STARTING_POSITION.y + j * (size.y + ROW_SPACING));

                GameObject alien = GameObjectFactory.getGameObject("Alien");
                alien.setPosition(position);
                aliens.add((Alien) alien);
                numAliensToAdd--;
            }
        }
    }

    //Updates aliens, reverses direction and increases speed if needed
    public void update(long fps) {
        for(Alien alien : aliens) {
            alien.update(fps);
            alien.setShootInterval((float)aliens.size()/NUM_ALIENS);
            if(alien.outOfBounds()) {
                reverseNow = true;
            }
        }



        reverse();
        increaseSpeed();
    }

    //Reverses direction when an alien hits the side of screen
    private void reverse() {
        if(reverseNow) {
            for(Alien alien : aliens) {
                alien.reverseXVelocity();
            }
        }
        reverseNow = false;
    }

    //Calculates speed based on exponential growth: y(t) = a × e^(kt)
    //a = Alien BASE_SPEED
    //k = rate of growth
    //t = time (number of aliens killed)
    //y(t) = new speed at the given time
    private void increaseSpeed() {
        int aliensKilled = (DIMENSIONS.x * DIMENSIONS.y) - aliens.size(); //number of max aliens - number of current aliens
        float multiplier = exponentialGrowth(.09f, aliensKilled); //tweak rateOfGrowth based on game feel

        for(Alien alien : aliens) {
            alien.speedUp(multiplier); //sets SPEED aka y(t) by BASE_SPEED * multiplier in HitBoxs
        }
    }

    //Returns e^(kt)
    private float exponentialGrowth(float rateOfGrowth, int time) {
        return (float) Math.exp(rateOfGrowth * time);
    }

    //Returns list of alien projectiles
    public List getAlienProjs() {
        List<GameObject> alienProjs = new LinkedList<>();

        for(Alien alien: aliens) {
                if(alien.shootNow) {
                    alienProjs.add(alien.shoot());
                    alien.shootNow = false;
                }
            }
        return alienProjs;
    }

    public void draw(Canvas canvas) {
        for(Alien alien: aliens) {
            alien.display(canvas);
        }
    }

    //Removes killed aliens
    public void removeInactiveObjects() {
        Iterator<Alien> alienObjectIterator = aliens.iterator();

        while (alienObjectIterator.hasNext()) {
            Alien alienObject = alienObjectIterator.next();

            if (!alienObject.isActive()) {
                increaseScore();
                alienObjectIterator.remove();
            }
        }
    }

    //TODO put in spaceinvadersapp
    private void increaseScore() {
        app.score += 100;
    }


}