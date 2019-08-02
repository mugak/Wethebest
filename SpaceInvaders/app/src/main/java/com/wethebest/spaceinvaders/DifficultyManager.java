package com.wethebest.spaceinvaders;

import java.util.Random;

/*gives player and aliens stat increases after each wave
* currently not random
*/

public class DifficultyManager {
    private GameObjectManager mGameObjectManager;

    private final float ALIENSPEEDMULTIPLIER = 1.2f;
    private final float ALIENFIRERATEINCREASE = 2f;
    private final int NUMALIENINCREASE = 1;

    private final float PLAYERFIRERATEINCREASE = 1.3f;
    private final float PLAYERAMMOREGENRATEINCREASE = 1.2f;

    private final int NUMALIENBUFFS = 1;
    private final int NUMPLAYERBUFFS = 1;

    public DifficultyManager(GameObjectManager gom) {
        mGameObjectManager = gom;
    }

    //Endows the aliens and players with random stat upgrades
    public void increaseGameDifficulty() {
        for(int i = 0; i < NUMALIENBUFFS; ++i) {
            increaseRandomAlienStat();
        }

        for(int i = 0; i < NUMPLAYERBUFFS; ++i) {
            increaseRandomPlayerBuff();
        }
    }

    //Gives the alien a random stat upgrade
    public void increaseRandomAlienStat() {
        Random rand = new Random();

        int stat = rand.nextInt(3);
        increaseWaveSpeed();
        increaseAlienFireRate();
        increaseAlienArmySize();

//        switch(stat) {
//            case 0:
//                increaseWaveSpeed();
//                break;
//            case 1:
//                increaseAlienFireRate();
//                break;
//            case 2:
//                increaseAlienArmySize();
//                break;
//        }
    }

    //Gives the player a random stat upgrade
    public void increaseRandomPlayerBuff() {
        Random rand = new Random();
        int stat = rand.nextInt(3);
        increasePlayerFireRate();
        increasePlayerMaxAmmo();
        increasePlayerAmmoRegenRate();
//        switch (stat) {
//            case 0:
//                increasePlayerFireRate();
//                break;
//            case 1:
//                increasePlayerMaxAmmo();
//                break;
//            case 2:
//                increasePlayerAmmoRegenRate();
//                break;
//        }
    }

    //Increases the initial wave speed of an alien in subsequent waves
    private void increaseWaveSpeed() {
        mGameObjectManager.mAlienArmy.increaseInitialSpeed(ALIENSPEEDMULTIPLIER);
    }

    //Causes the aliens to shoot more frequently in subsequent waves
    private void increaseAlienFireRate() {
        mGameObjectManager.mAlienArmy.increaseAlienFireRate(ALIENFIRERATEINCREASE);
    }

    //Increases the number of aliens in subsequent waves
    private void increaseAlienArmySize() {
        mGameObjectManager.mAlienArmy.increaseNumAliens(NUMALIENINCREASE);
    }

    //Increases the number of shots a player fires per second
    private void increasePlayerFireRate() {
        mGameObjectManager.mPlayer.increaseFireRate(PLAYERFIRERATEINCREASE);
    }

    //Increases the maximum ammount of ammo that a player can have at one time
    private void increasePlayerMaxAmmo() {
        mGameObjectManager.mPlayer.maxAmmo += 1;
    }

    //Increases the rate that the player regenerates used ammo
    private void increasePlayerAmmoRegenRate() {
        mGameObjectManager.mPlayer.increaseAmmoRegenRate(PLAYERAMMOREGENRATEINCREASE);
    }
}
