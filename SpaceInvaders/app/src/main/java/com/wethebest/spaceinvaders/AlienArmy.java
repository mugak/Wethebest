package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AlienArmy {
    private int numRows;
    private int aliensPerRow;
    private int maxNumAliens;

    private Point mScreenSize;
    private PointF rowPosition; //Position of top left corner of first alien in first row
    private int spaceBetweenRows;
    private List <AlienRow> alienRows;

    public List<Alien> allAliens = new LinkedList<Alien>(); //

    //These lists contain the corresponding speed multiplier to the number of aliens left
    //When there is a certain amount of aliens, the speed multiplier at the same index is applied
    //Values set in the constructor
    public List<Float> speedMultipliers = new ArrayList<Float>();
    public List<Integer> numAliensWhenSpeedIncreases = new ArrayList<Integer>();


    private SpaceInvadersApp app;
    public boolean changeDirection;

    AlienArmy(SpaceInvadersApp app) {
        this.app = app;
        mScreenSize = app.mScreenSize;
        numRows = 4; //TODO hardcoded
        aliensPerRow = AlienRow.numAliens;
        maxNumAliens = numRows * aliensPerRow;

        spaceBetweenRows = mScreenSize.x / 15; //TODO set better spacing
        setPos();
        alienRows = new LinkedList<AlienRow>();
        setRows();

        changeDirection = false;

        speedMultipliers.add(1.5f); //first speed multiplier
        speedMultipliers.add(2f);
        speedMultipliers.add(4f);
        numAliensWhenSpeedIncreases.add((int) (maxNumAliens / 2)); //number of aliens when first speed multiplier is applied
        numAliensWhenSpeedIncreases.add((int) (maxNumAliens / 4));
        numAliensWhenSpeedIncreases.add((int) (maxNumAliens / 8));
        //numAliensWhenSpeedIncreases.add((int) (maxNumAliens / 1.5));


    }


    private void setPos() {
        rowPosition = new PointF(mScreenSize.x / 10, mScreenSize.y / 10); //TODO set better position
    }

    private void setRows() {
        for(int i = 0; i < numRows; i++) {
            AlienRow mAlienRow = new AlienRow(app);
            Alien.setAlienSize(new PointF(app.mScreenSize.x/10, app.mScreenSize.y/10));
            mAlienRow.alienPos = new PointF(rowPosition.x, rowPosition.y + i * (Alien.alienSize.y + spaceBetweenRows));
            mAlienRow.setAliens();
            alienRows.add(mAlienRow);

        }
    }

    public List getAliens() {
        List<Alien> allAliens = new LinkedList<Alien>();
        for(AlienRow mAlienRow : alienRows) {
            allAliens.addAll(mAlienRow.aliens);
        }
        return allAliens;
    }

    public void setAliens() {
        for(AlienRow mAlienRow : alienRows) {
            allAliens.addAll(mAlienRow.aliens);
        }
    }

    public void changeDirection() {
        for (Alien a : allAliens) {
            a.reverseXVelocity();
        }
    }

    public void update(long fps) {
        for(Alien mAlien : allAliens) {
            mAlien.update(fps);
            if(mAlien.outOfBounds()) {
                changeDirection = true;
            }
        }
        if(changeDirection == true) {
            changeDirection();
            changeDirection = false;
        }

        checkIfIncreaseSpeed();
    }

    public void checkIfIncreaseSpeed() {
        if(!numAliensWhenSpeedIncreases.isEmpty()) { //if there are more speed multipliers
            if (allAliens.size() <= numAliensWhenSpeedIncreases.get(0)) { //when the number of aliens is right, apply speed multiplier
                increaseSpeed(speedMultipliers.get(0));
            }
        }
    }

    public void increaseSpeed(float multiplier) {
        AlienHitBox.speedUp(multiplier);
        numAliensWhenSpeedIncreases.remove(0);
        speedMultipliers.remove(0);
    }



    public void draw(Canvas canvas) {
        for (Alien a : allAliens) {
            a.display(canvas);
        }
    }


    public List getAlienProjs() {
        List<GameObject> alienProjs = new LinkedList<>();

        for (Alien a : allAliens) {
                if (a.shootNow) {
                    alienProjs.add(a.shoot());
                    a.shootNow = false;
                }
            }
        return alienProjs;
    }

    public void removeInactiveObjects() {
        Iterator<Alien> alienObjectIterator = allAliens.iterator();

        while (alienObjectIterator.hasNext()) {
            Alien alienObject = alienObjectIterator.next();

            if (!alienObject.isActive()) {
                alienObjectIterator.remove();
            }
        }
    }
}
