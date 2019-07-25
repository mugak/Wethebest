package com.wethebest.spaceinvaders;

public class GameObjectFactory {
    public static SpaceInvadersApp app;

    public static GameObject getGameObject(String objectName) {
        if(objectName.equalsIgnoreCase("alien")) {
            return new Alien(app);
        }
        else if(objectName.equalsIgnoreCase("cannon")) {
            //return new SimpleCannon(app);
        }
        else if(objectName.equalsIgnoreCase("projectile")) {
            //return new Projectile(app);
        }
        else if(objectName.equalsIgnoreCase("barrierblock")) {
           //return new BarrierBlock(app);
        }

        return null;


    }
}
