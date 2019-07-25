package com.wethebest.spaceinvaders;

import android.graphics.Point;

import java.util.List;
import java.util.ArrayList;

public class Barrier {

    private Point mScreenSize;
    private Point mBarrierPosition; //top left corner of the barrier
    private final Point BARRIER_SIZE = new Point(3, 3); //size is in units of barrier blocks
    private final Point BARRIER_DIMENSIONS =
            new Point(BARRIER_SIZE.x * mScreenSize.x / 20, BARRIER_SIZE.y * mScreenSize.y / 40);
    private BarrierBlock[][] barrierBlocks;

    Barrier(Point ScreenSize, Point barrierPosition) {
        mScreenSize = ScreenSize;
        barrierBlocks = new BarrierBlock[BARRIER_SIZE.x][BARRIER_SIZE.y];
        mBarrierPosition = barrierPosition;
        setBarrierBlocks();
    }

    //TODO: dynamically set the position on the screen, possibly depending on number of barriers
    private void setBarrierPosition(Point barrierPosition) {
        //mBarrierPosition = new Point(mScreenSize.x / 2 - 100, mScreenSize.y / 2);
        mBarrierPosition = new Point(barrierPosition.x - );
    }


    private void setBarrierBlocks() {
        for(int i = 0; i < BARRIER_SIZE.x; i++) {
            for(int j = 0; j < BARRIER_SIZE.y; j++) {
                BarrierBlock mBarrierBlock = new BarrierBlock(mScreenSize);
                mBarrierBlock.setPos(mBarrierPosition.x + (i * mBarrierBlock.barrierBlockSize.width),
                        mBarrierPosition.y + j * mBarrierBlock.barrierBlockSize.height);
                barrierBlocks[i][j] = mBarrierBlock;
            }
        }
    }


    //gameObjects in SpaceInvadersApp.java only takes Collections in its addAll method
    //barrierBlocks is a primitive 2D array
    //Creating this ArrayList is a quick fix
    //TODO: refactor barrierBlocks to be a Collections object
    public List getBarrierBlocks() {
        List<BarrierBlock> barrierBlockList = new ArrayList<BarrierBlock>();
        for(BarrierBlock[] arr : barrierBlocks) {
            for(BarrierBlock barrierBlock : arr) {
                barrierBlockList.add(barrierBlock);
            }
        }

       return barrierBlockList;
    }
}

