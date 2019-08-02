package com.wethebest.spaceinvaders;

import android.graphics.PointF;

import java.util.LinkedList;
import java.util.List;
/*
Barriers handles the placement of each Barrier, set dynamically based on the number of barriers desired
Instantiated in GameObjectManager
 */

public class Barriers {
    //DEFAULTS
    private final int NUM_BARRIERS = 3;

    private SpaceInvadersApp app;
    private List<Barrier> barriers = new LinkedList<Barrier>();

    Barriers(SpaceInvadersApp app) {
        this.app = app;
        createBarriers();
    }

    //Creates NUM_BARRIERS ammount of barriers center within the game screen
    public void createBarriers() {
        for (int i = 1; i < NUM_BARRIERS + 1; i++) {
            Barrier barrier = new Barrier(app);
            PointF barrierCenterPosition = new PointF(app.mScreenSize.x/4 * i ,app.mScreenSize.y * 4 / 5);
            barrier.setPosition(barrierCenterPosition);
            barrier.createBarrierBlocks();
            barriers.add(barrier);
        }
    }

    //Returns all the barrier blocks in all of the barriers
    public List getBarrierBlocks() {
        List<GameObject> barrierBlocks = new LinkedList<GameObject>();

        for(Barrier barrier : barriers) {
            barrierBlocks.addAll(barrier.barrierBlocks);
        }

        return barrierBlocks;
    }
}

