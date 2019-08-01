package com.wethebest.spaceinvaders;

public class DifficultyManager {
    private GameObjectManager mGameObjectManager;

    final float ALIENSPEEDMULTIPLIER = (float) 1.2;

    public DifficultyManager(GameObjectManager gom) {
        mGameObjectManager = gom;
    }

    public void increaseWaveSpeed() {
        mGameObjectManager.mAlienArmy.increaseInitialSpeed(ALIENSPEEDMULTIPLIER);
    }


}
