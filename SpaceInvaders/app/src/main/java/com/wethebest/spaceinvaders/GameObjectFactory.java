package com.wethebest.spaceinvaders;


import android.graphics.PointF;

/*
GameObjectFactory handles the constructors for each GameObject
Holds default values for each GameObject
 */

public class GameObjectFactory {
    public static SpaceInvadersApp app; //set in SpaceInvadersApp
    private static PointF size;
    private static int spriteID;
    private static PointF position;
    private static float velocity;


    GameObjectFactory(){}

    public static GameObject getGameObject(String objectName) {
        if(objectName.equalsIgnoreCase("ALIEN")) {
            size = new PointF(app.mScreenSize.x / 15, app.mScreenSize.y / 10);
            spriteID = R.drawable.seagull;
            position = new PointF(0, 0);
            velocity = 50f;
            return new Alien(app, size, spriteID, position, velocity);
        }
        else if(objectName.equalsIgnoreCase("PLAYER")) {
            size = new PointF(app.mScreenSize.x / 7, app.mScreenSize.x / 10);
            spriteID = R.drawable.biploar_red;
            position = new PointF(app.mScreenSize.x / 2, app.mScreenSize.y - size.y*3/4);
            velocity = 0f;
            return new SimpleCannon(app, size, spriteID, position, velocity);
        }
        else if(objectName.equalsIgnoreCase("BARRIERBLOCK")) {
            size = new PointF(app.mScreenSize.x / 30, app.mScreenSize.x / 60);
            spriteID = R.drawable.pink_cloud_face;
            position = new PointF(0, 0);
            velocity = 0;
            return new BarrierBlock(app, size, spriteID, position, velocity);
        }
        else if(objectName.equalsIgnoreCase("PLAYERPROJ")) {
            size = new PointF(app.mScreenSize.x / 80, app.mScreenSize.x / 40);
            spriteID = R.drawable.missile;
            position = new PointF(0, 0);
            velocity = -app.mScreenSize.y;
            return new PlayerProj(app, size, spriteID, position, velocity);
        }
        else if(objectName.equalsIgnoreCase("ALIENPROJ")) {
            size = new PointF(app.mScreenSize.x / 60, app.mScreenSize.x / 60);
            spriteID = R.drawable.smiley_poop;
            position = new PointF(0, 0);
            velocity = app.mScreenSize.y / 4;
            return new AlienProj(app, size, spriteID, position, velocity);
        }
        else if(objectName.equalsIgnoreCase("UFO")) {
            size = new PointF(app.mScreenSize.x / 15, app.mScreenSize.y / 10);
            spriteID = R.drawable.invader_ufo_flying01;
            position = new PointF(50, 50);
            velocity = 200f;
            return new UFO(app, size, spriteID, position, velocity);
        }
        return null;


    }
}
