package com.wethebest.spaceinvaders;

import android.graphics.PointF;

import java.util.LinkedList;
import java.util.List;

public class Barriers {
    //DEFAULTS
    private final int NUM_BARRIERS = 3;

    private SpaceInvadersApp app;
    private List<Barrier> barriers = new LinkedList<Barrier>();

    Barriers(SpaceInvadersApp app) {
        this.app = app;
        createBarriers();
    }

    private void createBarriers() {
        for (int i = 1; i < NUM_BARRIERS + 1; i++) {
            Barrier barrier = new Barrier(app);
            PointF barrierCenterPosition = new PointF(app.mScreenSize.x * i / (NUM_BARRIERS + 1),app.mScreenSize.y * 3 / 4);
            barrier.setPosition(barrierCenterPosition);
            barrier.createBarrierBlocks();
            barriers.add(barrier);
        }
    }

    public List getBarrierBlocks() {
        List<BarrierBlock> barrierBlocks = new LinkedList<BarrierBlock>();

        for(Barrier barrier : barriers) {
            barrierBlocks.addAll(barrier.barrierBlocks);
        }

        return barrierBlocks;
    }
}

