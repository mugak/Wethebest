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
    private int maxNumAliens;

    private Point mScreenSize;
    private PointF rowPosition; //Position of top left corner of first alien in first row
    private float spaceBetweenRows;
    private float spaceBetweenAliens;


    private PointF alienPos;
    private int numAliensInRow;
    public List<Alien> allAliens = new LinkedList<Alien>(); //

    private SpaceInvadersApp app;
    public boolean changeDirection;

    AlienArmy(SpaceInvadersApp app) {

        this.app = app;
        Alien.setAlienSize(new PointF(app.mScreenSize.x/10, app.mScreenSize.y/10));
        mScreenSize = app.mScreenSize;
        numRows = 4; //TODO hardcoded
        numAliensInRow = 4;
        maxNumAliens = numRows * numAliensInRow;

        spaceBetweenRows = 0; //TODO set better spacing
        spaceBetweenAliens = Alien.alienSize.x/2; //TODO set better spacing
        setPos();
        setRows();

        changeDirection = false;
    }


    private void setPos() {
        rowPosition = new PointF(mScreenSize.x / 10, mScreenSize.y / 10); //TODO set better position
    }

    private void setRows() {
        for(int i = 0; i < numRows; i++) {
            alienPos = new PointF(rowPosition.x, rowPosition.y + i * (Alien.alienSize.y + spaceBetweenRows));

            for(int j = 0; j < numAliensInRow; j++) {
                Alien mAlien = new Alien(app);
                PointF position = new PointF(alienPos.x + j * (Alien.alienSize.x + spaceBetweenAliens), alienPos.y);
                mAlien.setPos(position);
                allAliens.add(mAlien);
            }
        }
    }

    public List getAliens() {
        return allAliens;
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
                app.score += 100;
                alienObjectIterator.remove();
            }
        }
    }
}