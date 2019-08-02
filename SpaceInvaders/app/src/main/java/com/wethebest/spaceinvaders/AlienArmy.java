package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
AlienArmy places the enemies, updates the enemies, and handles their logic as a group
When one enemy touches the screen, the whole army reverses direction and moves forward.
As the player kills off enemies, the enemies speed up and shoot projectiles more frequently.
Instantiated in GameObjectManager
 */

public class AlienArmy {
    //DEFAULTS
    public int numAliens = 12;

    //Fit NUM_ALIENS of aliens in 4 rows and 4 columns
    private Point DIMENSIONS = new Point(numAliens/3 + numAliens % 3, 3 );
    //SET BASED ON SCREEN SIZE
    private final float ROW_SPACING;
    private final float COL_SPACING;
    private final PointF STARTING_POSITION; //top left corner of first alien

    private SpaceInvadersApp app;
    public List<Alien> aliens = new LinkedList<Alien>();
    private boolean reverseNow = false;

    // Initial speed of the wave
    private float waveSpeed;

    AlienArmy(SpaceInvadersApp app) {
        this.app = app;

        waveSpeed = GameObjectFactory.getGameObject("alien").SPEED;

        ROW_SPACING = 0;
        COL_SPACING = app.mScreenSize.y / 8;
        STARTING_POSITION = new PointF(app.mScreenSize.y / 10, app.mScreenSize.y / 10);

        createAliens();
    }

    //Instantiates and sets positions and velocites of every alien
    private void createAliens() {
        //First get the size of an alien
        GameObject tempAlien = GameObjectFactory.getGameObject("Alien");
        PointF size = new PointF(tempAlien.getHitBox().width(), tempAlien.getHitBox().height());
        int numAliensToAdd = numAliens;

        for(int i = 0; i < DIMENSIONS.x; i++) {
            for(int j = 0; j < DIMENSIONS.y; j++) {
                if(numAliensToAdd <=0){break;}

                PointF position = new PointF(STARTING_POSITION.x + i * (size.x + COL_SPACING),
                        STARTING_POSITION.y + j * (size.y + ROW_SPACING));

                GameObject alien = GameObjectFactory.getGameObject("Alien");
                alien.setPosition(position);
                alien.setVelocity(waveSpeed);
                aliens.add((Alien) alien);
                numAliensToAdd--;
            }
        }
    }

    //Updates aliens, reverses direction and increases speed if needed
    public void update(long fps) {
        for(Alien alien : aliens) {
            alien.update(fps);
            alien.setShootInterval((float)aliens.size()/numAliens);
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

    // This sets the initial speed of a wave to be faster if the difficulty manager changes it
    public void increaseInitialSpeed(float multiplier) {
        waveSpeed *= multiplier;

        for(Alien alien : aliens) {
            alien.setVelocity(waveSpeed);
        }
    }

    //Calculates speed based on exponential growth: y(t) = a × e^(kt)
    //a = Alien BASE_SPEED
    //k = rate of growth
    //t = time (number of aliens killed)
    //y(t) = new speed at the given time
    private void increaseSpeed() {
        int aliensKilled =  numAliens - aliens.size(); //number of max aliens - number of current aliens
        float multiplier = exponentialGrowth(.15f, aliensKilled); //tweak rateOfGrowth based on game feel

        for(Alien alien : aliens) {
            alien.speedUp(multiplier); //sets SPEED aka y(t) by BASE_SPEED * multiplier in HitBoxes
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

    //displays all aliens
    public void draw(Canvas canvas) {
        for(Alien alien: aliens) {
            alien.display(canvas);
        }
    }

    //Removes killed aliens and alerts app if all aliens defeated
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

    /*
        Increase alien fire rate by decreasing the possible amounts of time that aliens have to
        wait to shoot
     */
    public void increaseAlienFireRate(float decreaseAmount) {
        for (Alien alien : aliens) {
            alien.decreaseBaseShootInterval(decreaseAmount);
        }
    }

    /*
        Saves the new number of aliens and recreates the wave with more aliens
     */
    public void increaseNumAliens(int numNewAliens) {
        numAliens += numNewAliens;
        aliens.clear();
        resetDimensions();
        createAliens();
    }

    public void spawnNewWave() {
        createAliens();
    }

    /*
        Resets the dimensions to fit the new alien size
     */
    public void resetDimensions() {
        DIMENSIONS = new Point(numAliens/3 + numAliens % 3, 3 );
    }
}