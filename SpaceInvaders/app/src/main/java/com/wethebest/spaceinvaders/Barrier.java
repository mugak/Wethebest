package com.wethebest.spaceinvaders;

import android.graphics.Point;
import android.graphics.PointF;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Barrier {
    //DEFAULTS
    private final Point DIMENSIONS = new Point(3, 3); //size is in units of barrier blocks
    private final PointF BLOCK_SIZE;

    private SpaceInvadersApp app;
    public List<BarrierBlock> barrierBlocks = new LinkedList<BarrierBlock>();
    private PointF position; //top left corner


    Barrier(SpaceInvadersApp app) {
        this.app = app;

        BLOCK_SIZE = new PointF(app.mScreenSize.x / 20, app.mScreenSize.y / 40); //TODO repeated in barrierblock, maybe get from gameconfig
    }

    public void createBarrierBlocks() {
        for (int i = 0; i < DIMENSIONS.x; i++) {
            for (int j = 0; j < DIMENSIONS.y; j++) {
                PointF blockPos = new PointF(position.x + i * BLOCK_SIZE.x,
                        position.y + j * BLOCK_SIZE.y);
                BarrierBlock mBarrierBlock = new BarrierBlock(app);
                mBarrierBlock.setPos(blockPos);

                barrierBlocks.add(mBarrierBlock);
            }
        }
    }

    public void setPosition(PointF centerPosition) {
        position = new PointF(centerPosition.x - (DIMENSIONS.x * BLOCK_SIZE.x / 2), centerPosition.y - (DIMENSIONS.y * BLOCK_SIZE.y / 2));
    }

}

