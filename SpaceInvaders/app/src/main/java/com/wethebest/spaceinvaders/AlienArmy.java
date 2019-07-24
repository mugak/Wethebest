package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class AlienArmy {
    private int numRows;
    private Point mScreenSize;
    private Point rowPosition; //Position of top left corner of first alien in first row
    private int spaceBetweenRows;
    private List <AlienRow> alienRows;

    AlienArmy(Point screenSize) {
        mScreenSize = screenSize;
        numRows = 4; //TODO hardcoded
        spaceBetweenRows = mScreenSize.x / 10; //TODO set better spacing
        rowPosition = setPos();
        alienRows = new LinkedList<AlienRow>();
    }


    private Point setPos() {
        rowPosition = new Point(mScreenSize.x / 10, mScreenSize.y / 10); //TODO set better position
    }

    private void setRows() {
        for(int i = 0; i < numRows; i++) {
            AlienRow mAlienRow = new AlienRow(mScreenSize);
            mAlienRow.alienPosition = new Point(rowPosition.x, rowPosition.y + i * (mAlienRow.mAlien.alienSize.height + spaceBetweenRows));
            alienRows.add(mAlienRow);
        }
    }

    public List getRows() {

    }
}