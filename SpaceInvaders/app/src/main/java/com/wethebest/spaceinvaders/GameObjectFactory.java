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
            spriteID = R.drawable.invader_a01;
            position = new PointF(0, 0);
            velocity = 50f;
            return new Alien(app, size, spriteID, position, velocity);
        }
        else if(objectName.equalsIgnoreCase("PLAYER")) {
            size = new PointF(app.mScreenSize.x / 10, app.mScreenSize.x / 10);
            spriteID = R.drawable.player;
            position = new PointF(app.mScreenSize.x / 2, app.mScreenSize.y - app.mScreenSize.x/10);
            velocity = 0f;
            return new SimpleCannon(app, size, spriteID, position, velocity);
        }
        else if(objectName.equalsIgnoreCase("BARRIERBLOCK")) {
            size = new PointF(app.mScreenSize.x / 20, app.mScreenSize.y / 40);
            spriteID = R.drawable.def_brick_01;
            position = new PointF(0, 0);
            velocity = 0;
            return new BarrierBlock(app, size, spriteID, position, velocity);
        }
        else if(objectName.equalsIgnoreCase("PLAYERPROJ")) {
            size = new PointF(app.mScreenSize.x / 80, app.mScreenSize.x / 40);
            spriteID = R.drawable.projectile_a;
            position = new PointF(0, 0);
            velocity = -app.mScreenSize.y;
            return new PlayerProj(app, size, spriteID, position, velocity);
        }
        else if(objectName.equalsIgnoreCase("ALIENPROJ")) {
            size = new PointF(app.mScreenSize.x / 240, app.mScreenSize.x / 40);
            spriteID = R.drawable.alien_laser;
            position = new PointF(0, 0);
            velocity = app.mScreenSize.y / 4;
            return new AlienProj(app, size, spriteID, position, velocity);
        }
        else if(objectName.equalsIgnoreCase("UFO")) {
            size = new PointF(app.mScreenSize.x / 15, app.mScreenSize.y / 10);
            spriteID = R.drawable.invader_ufo_flying01;
            position = new PointF(50, 50);
            velocity = 50f;
            return new UFO(app, size, spriteID, position, velocity);
        }

        return null;


    }
}
