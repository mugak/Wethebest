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

        increaseSpeed();
    }

    //calculates speed based on exponential growth: y(t) = a × e^(kt)
    //a = base_speed
    //k = rate of growth - tweak based on game feel
    //t = number of aliens killed (time)
    //y(t) = new speed at the number of aliens killed
    public void increaseSpeed() {
        int aliensKilled = maxNumAliens - allAliens.size();
        float multiplier = exponentialGrowth(.09f, aliensKilled);
        AlienHitBox.speedUp(multiplier); //sets SPEED aka y(t) in AlienHitBox
    }

    //returns e^(kt)
    public float exponentialGrowth(float rateOfGrowth, int time) {
        return (float) Math.exp(rateOfGrowth * time);
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
