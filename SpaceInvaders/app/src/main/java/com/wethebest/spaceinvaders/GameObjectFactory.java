package com.wethebest.spaceinvaders;

import android.content.Context;

public class GameObjectFactory {
    public static SpaceInvadersApp app;
    private static Context context;

    GameObjectFactory(Context context){
        this.context = context;
    }

    public static GameObject getGameObject(String objectName) {
        if(objectName.equalsIgnoreCase("alien")) {
            return new Alien(context, app);
        }
        else if(objectName.equalsIgnoreCase("cannon")) {
            //return new SimpleCannon(app);
        }
        else if(objectName.equalsIgnoreCase("playerprojectile")) {
            //return new PlayerProj(app);
        }
        else if(objectName.equalsIgnoreCase("barrierblock")) {
           //return new BarrierBlock(app);
        }

        return null;


    }
}
