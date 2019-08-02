package com.wethebest.spaceinvaders;

import android.graphics.Point;
import android.graphics.PointF;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
/*
Barrier handles the placement of BarrierBlocks so that they look like one single barrier
Instantiated in Barriers
 */

public class Barrier {
    //DEFAULTS
    private final Point DIMENSIONS = new Point(4, 4); //size is in units of barrier blocks


    private final PointF BLOCK_SIZE;

    private SpaceInvadersApp app;
    public List<GameObject> barrierBlocks = new LinkedList<GameObject>();
    private PointF position; //top left corner


    Barrier(SpaceInvadersApp app) {
        this.app = app;
        GameObject tempBarrierBlock = GameObjectFactory.getGameObject(("BarrierBlock"));
        BLOCK_SIZE = new PointF(tempBarrierBlock.getHitBox().width(), tempBarrierBlock.getHitBox().height());
    }

    public void createBarrierBlocks() {
        for (int i = 0; i < DIMENSIONS.x; i++) {
            for (int j = 0; j < DIMENSIONS.y; j++) {
                PointF blockPos = new PointF(position.x + i * BLOCK_SIZE.x,
                        position.y + j * BLOCK_SIZE.y);
                GameObject mBarrierBlock = GameObjectFactory.getGameObject("BarrierBlock");
                mBarrierBlock.setPosition(blockPos);

                barrierBlocks.add(mBarrierBlock);
            }
        }
    }

    public void setPosition(PointF centerPosition) {
        position = new PointF(centerPosition.x - (DIMENSIONS.x * BLOCK_SIZE.x ), centerPosition.y - (DIMENSIONS.y * BLOCK_SIZE.y ));
    }

}

