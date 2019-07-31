package com.wethebest.spaceinvaders;

import android.util.Log;

public class UFOSpawner {
    private final float UFO_SPAWN_INTERVAL = 5f; //TODO make this random
    private Counter spawnUFO;

    private SpaceInvadersApp app;

    UFOSpawner(SpaceInvadersApp app) {
        this.app = app;
        spawnUFO = new AutomaticCounter(UFO_SPAWN_INTERVAL);
    }

    public void update(long fps) {
        if(spawnUFO.run(fps)) {
            createUFO();
        }
    }

    private void createUFO() {
        app.mGameObjectManager.add(GameObjectFactory.getGameObject("UFO"));
    }

}
