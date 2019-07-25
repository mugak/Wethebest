package com.wethebest.spaceinvaders;

import android.graphics.Point;
import android.graphics.PointF;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Barrier {

    private Point mScreenSize;
    private PointF mBarrierPosition; //top left corner of the barrier
    private final Point BARRIER_SIZE = new Point(3, 3); //size is in units of barrier blocks
    private final PointF BLOCK_SIZE;
    private BarrierBlock[][] barrierBlocks;

    Barrier(Point screenSize, PointF centerPosition) {
        mScreenSize = screenSize;
        barrierBlocks = new BarrierBlock[BARRIER_SIZE.x][BARRIER_SIZE.y];
        BLOCK_SIZE = new PointF(mScreenSize.x / (float) 20, mScreenSize.y / (float) 40);

        PointF dimensions = getBarrierDimensions();
        mBarrierPosition = Util.getTopLeftPosFromCenterPos(centerPosition, dimensions);

        setBarrierBlocks();
    }

    private PointF getBarrierDimensions() {
        return new PointF(BARRIER_SIZE.x * BLOCK_SIZE.x, BARRIER_SIZE.y * BLOCK_SIZE.y);
    }

    private void setBarrierBlocks() {
        for(int i = 0; i < BARRIER_SIZE.x; i++) {
            for(int j = 0; j < BARRIER_SIZE.y; j++) {
                PointF blockPos = new PointF(mBarrierPosition.x + i * BLOCK_SIZE.x,
                        mBarrierPosition.y + j * BLOCK_SIZE.y);
                BarrierBlock mBarrierBlock = new BarrierBlock(BLOCK_SIZE, blockPos);

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
            barrierBlockList.addAll(Arrays.asList(arr));
        }

       return barrierBlockList;
    }
}

