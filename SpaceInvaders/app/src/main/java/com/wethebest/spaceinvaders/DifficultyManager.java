package com.wethebest.spaceinvaders;

public class DifficultyManager {
    private GameObjectManager mGameObjectManager;

    private final float ALIENSPEEDMULTIPLIER = (float) 1.2;
    private final float ALIENFIRERATEINCREASE = (float) 2;
    private final int NUMALIENINCREASE = 2;

    public DifficultyManager(GameObjectManager gom) {
        mGameObjectManager = gom;
    }

    public void increaseWaveSpeed() {
        mGameObjectManager.mAlienArmy.increaseInitialSpeed(ALIENSPEEDMULTIPLIER);
    }

    public void increaseAlienFireRate() {
        mGameObjectManager.mAlienArmy.increaseAlienFireRate(ALIENFIRERATEINCREASE);
    }

    public void increaseAlienArmySize() {
        mGameObjectManager.mAlienArmy.increaseNumAliens(NUMALIENINCREASE);
    }

    public void increasePlayerFireRate() {

    }

    public void increasePlayerMaxAmmo() {
        mGameObjectManager.mPlayer.maxAmmo += 1;
    }

    public void increasePlayerAmmoRegenRate() {

    }
}
