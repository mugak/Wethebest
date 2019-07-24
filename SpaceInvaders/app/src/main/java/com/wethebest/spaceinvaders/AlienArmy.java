package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class AlienArmy {
    private int numRows;
    private Point mScreenSize;
    private PointF rowPosition; //Position of top left corner of first alien in first row
    private int spaceBetweenRows;
    private List <AlienRow> alienRows;

    public List<Alien> allAliens = new LinkedList<Alien>(); //

    private Context context;
    public boolean changeDirection;

    AlienArmy(Context context, Point screenSize) {
        this.context = context;
        mScreenSize = screenSize;
        numRows = 4; //TODO hardcoded
        spaceBetweenRows = mScreenSize.x / 6; //TODO set better spacing
        setPos();
        alienRows = new LinkedList<AlienRow>();
        Alien.setAlienSize(new PointF(mScreenSize.x/10, mScreenSize.y/10));
        setRows();

        changeDirection = false;
    }


    private void setPos() {
        rowPosition = new PointF(mScreenSize.x / 10, mScreenSize.y / 10); //TODO set better position
    }

    private void setRows() {
        for(int i = 0; i < numRows; i++) {
            AlienRow mAlienRow = new AlienRow(context, mScreenSize);
            mAlienRow.alienPos = new PointF(rowPosition.x, rowPosition.y + i * (Alien.alienSize.y + spaceBetweenRows));
            mAlienRow.setAliens();
            alienRows.add(mAlienRow);

        }
    }

    /*public List getAliens() {
        List<Alien> allAliens = new LinkedList<Alien>();
        for(AlienRow mAlienRow : alienRows) {
            allAliens.addAll(mAlienRow.aliens);
        }
        return allAliens;
    }*/

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
    }
}