package com.wethebest.spaceinvaders;


public class GameObjectFactory {
    public static SpaceInvadersApp app;

    GameObjectFactory(){}

    public static GameObject getGameObject(String objectName) {
        if(objectName.equalsIgnoreCase("alien")) {
            return new Alien(app);
        }
        else if(objectName.equalsIgnoreCase("cannon")) {
            return new SimpleCannon(app);
        }
        else if(objectName.equalsIgnoreCase("playerprojectile")) {
            return new PlayerProj(app);
        }
        else if(objectName.equalsIgnoreCase("barrierblock")) {
           //return new BarrierBlock(app);
        }

        return null;


    }
}
