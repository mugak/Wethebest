package com.wethebest.spaceinvaders;

public class DifficultyManager {
    private GameObjectManager mGameObjectManager;

    private final float ALIENSPEEDMULTIPLIER = (float) 1.2;
    private final float ALIENFIRERATEINCREASE = (float) 2;

    public DifficultyManager(GameObjectManager gom) {
        mGameObjectManager = gom;
    }

    public void increaseWaveSpeed() {
        mGameObjectManager.mAlienArmy.increaseInitialSpeed(ALIENSPEEDMULTIPLIER);
    }

    public void increaseAlienFireRate() {
        mGameObjectManager.mAlienArmy.increaseAlienFireRate(ALIENFIRERATEINCREASE);
    }
}
