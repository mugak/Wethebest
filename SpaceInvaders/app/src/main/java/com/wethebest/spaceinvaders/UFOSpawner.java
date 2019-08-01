package com.wethebest.spaceinvaders;


import android.graphics.PointF;

/*
UFOSpawner instantiates UFO randomly, adding it to GameObjects in GameObjectManager
Updated in GameObjectManager
 */

public class UFOSpawner {
    private final PointF SHOOT_INTERVAL = new PointF(20, 30); // every x-y seconds
    private Counter spawnUFO;

    private SpaceInvadersApp app;

    UFOSpawner(SpaceInvadersApp app) {
        this.app = app;
        spawnUFO = new AutomaticCounter(getRandomFloat(SHOOT_INTERVAL));
    }

    public void update(long fps) {
        if(spawnUFO.run(fps)) {
            createUFO();
            spawnUFO.setSeconds(getRandomFloat(SHOOT_INTERVAL));
        }
    }

    private void createUFO() {
        app.mGameObjectManager.add(GameObjectFactory.getGameObject("UFO"));
    }

    private float getRandomFloat(PointF interval) {
        return app.rand.nextFloat() * (interval.y - interval.x) + interval.x;

    }
}
