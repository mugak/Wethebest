package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;

import java.util.List;

public class AlienArmy {
    private int numRows;
    private Point mScreenSize;
    private Point rowPosition; //Position of top left corner of first alien in first row
    private int spaceBetweenRows;

    AlienArmy(Point screenSize) {
        mScreenSize = screenSize;
        numRows = 4; //TODO hardcoded
        spaceBetweenRows = mScreenSize.x / 10; //TODO set better spacing
        rowPosition = setPos();

    }


    private Point setPos() {
    }

    private void setRows() {

    }

    public List getRows() {

    }
}