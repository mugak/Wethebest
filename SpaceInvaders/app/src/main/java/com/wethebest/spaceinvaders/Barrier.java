package com.wethebest.spaceinvaders;

import android.graphics.Point;

import java.util.List;
import java.util.ArrayList;

public class Barrier {

    private Point mScreenSize;
    private Point barrierPosition; //top left corner of the barrier
    private final Point BARRIER_SIZE = new Point(5, 5); //size is in units of barrier blocks
    private BarrierBlock[][] barrierBlocks;

    Barrier(Point ScreenSize) {
        mScreenSize = ScreenSize;
        barrierBlocks = new BarrierBlock[BARRIER_SIZE.x][BARRIER_SIZE.y];
        setBarrierPosition();
        setBarrierBlocks();
    }

    //TODO: dynamically set the position on the screen, possibly depending on number of barriers
    private void setBarrierPosition() {
        barrierPosition = new Point(mScreenSize.x / 2 - 100, mScreenSize.y / 2);
    }


    private void setBarrierBlocks() {
        for(int i = 0; i < BARRIER_SIZE.x; i++) {
            for(int j = 0; j < BARRIER_SIZE.y; j++) {
                BarrierBlock mBarrierBlock = new BarrierBlock(mScreenSize);
                mBarrierBlock.setPos(barrierPosition.x + (i * mBarrierBlock.barrierBlockSize.width),
                        barrierPosition.y + j * mBarrierBlock.barrierBlockSize.height);
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

