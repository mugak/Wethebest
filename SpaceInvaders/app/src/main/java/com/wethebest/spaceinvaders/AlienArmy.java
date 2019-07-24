package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class AlienArmy {
    private int numRows;
    private Point mScreenSize;
    private PointF rowPosition; //Position of top left corner of first alien in first row
    private int spaceBetweenRows;
    private List <AlienRow> alienRows;

    AlienArmy(Point screenSize) {
        mScreenSize = screenSize;
        numRows = 4; //TODO hardcoded
        spaceBetweenRows = mScreenSize.x / 2; //TODO set better spacing
        setPos();
        alienRows = new LinkedList<AlienRow>();
        Alien.setAlienSize(new PointF(mScreenSize.x/10, mScreenSize.y/10));
        setRows();
    }


    private void setPos() {
        rowPosition = new PointF(mScreenSize.x / 10, mScreenSize.y / 10); //TODO set better position
    }

    private void setRows() {
        for(int i = 0; i < numRows; i++) {
            AlienRow mAlienRow = new AlienRow(mScreenSize);
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
}