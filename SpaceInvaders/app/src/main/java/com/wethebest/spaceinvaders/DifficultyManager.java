package com.wethebest.spaceinvaders;

import java.util.Random;

public class DifficultyManager {
    private GameObjectManager mGameObjectManager;

    private final float ALIENSPEEDMULTIPLIER = (float) 1.2;
    private final float ALIENFIRERATEINCREASE = (float) 2;
    private final int NUMALIENINCREASE = 2;

    private final int NUMALIENBUFFS = 2;
    private final int NUMPLAYERBUFFS = 1;

    public DifficultyManager(GameObjectManager gom) {
        mGameObjectManager = gom;
    }

    public void increaseGameDifficulty() {
        Random alienRand = new Random();

        for(int i = 0; i < NUMALIENBUFFS; ++i) {
            increaseRandomAlienStat();
        }

        for(int i = 0; i < NUMPLAYERBUFFS; ++i) {
            increaseRandomPlayerBuff();
        }
    }

    public void increaseRandomAlienStat() {
        Random rand = new Random();

        int stat = rand.nextInt(4);

        switch(stat) {
            case 0:
                increaseWaveSpeed();
                break;
            case 1:
                increaseAlienFireRate();
                break;
            case 2:
                increaseAlienArmySize();
                break;
            case 3:
                //do nothing
                break;
        }
    }

    public void increaseRandomPlayerBuff() {
        Random rand = new Random();

        int stat = rand.nextInt(3);

        switch (stat) {
            case 0:
                increasePlayerFireRate();
            case 1:
                increasePlayerMaxAmmo();
            case 2:
                increasePlayerMaxAmmo();
        }
    }

    private void increaseWaveSpeed() {
        mGameObjectManager.mAlienArmy.increaseInitialSpeed(ALIENSPEEDMULTIPLIER);
    }

    private void increaseAlienFireRate() {
        mGameObjectManager.mAlienArmy.increaseAlienFireRate(ALIENFIRERATEINCREASE);
    }

    private void increaseAlienArmySize() {
        mGameObjectManager.mAlienArmy.increaseNumAliens(NUMALIENINCREASE);
    }

    private void increasePlayerFireRate() {

    }

    private void increasePlayerMaxAmmo() {
        mGameObjectManager.mPlayer.maxAmmo += 1;
    }

    private void increasePlayerAmmoRegenRate() {

    }
}
