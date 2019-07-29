package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.PointF;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AlienArmy {
    //DEFAULTS
    private final int NUM_ROWS = 4;
    private final int NUM_COLS = 4;

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
        COL_SPACING = app.mScreenSize.y / 10;
        STARTING_POSITION = new PointF(app.mScreenSize.y / 10, app.mScreenSize.y / 10);

        setAliens();
    }

    //Instantiates and sets positions of every alien
    private void setAliens() {
        PointF size = new PointF(app.mScreenSize.x/10, app.mScreenSize.y/10);//TODO Size shared with Alien. maybe get from GameConfig
        for(int numRow = 0; numRow < NUM_ROWS; numRow++) {
            float rowPosition = getNewPosition(STARTING_POSITION.y, numRow, size.y + ROW_SPACING);

            for(int numCol = 0; numCol < NUM_COLS; numCol++) {
                float colPosition = getNewPosition(STARTING_POSITION.x, numCol, size.x + COL_SPACING);

                Alien alien = new Alien(app);
                alien.setPos(new PointF(colPosition, rowPosition));
                aliens.add(alien);
            }
        }
    }

    //Calculates new position using an index
    private float getNewPosition(float startingPosition, int index, float spacing) {
        return startingPosition + (index * spacing);
    }

    //Updates aliens, reverses direction and increases speed if needed
    public void update(long fps) {
        for(Alien alien : aliens) {
            alien.update(fps);

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
        int aliensKilled = (NUM_ROWS * NUM_COLS) - aliens.size(); //number of max aliens - number of current aliens
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