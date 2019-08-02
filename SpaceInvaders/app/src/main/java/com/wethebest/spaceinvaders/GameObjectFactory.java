package com.wethebest.spaceinvaders;


import android.graphics.PointF;
import android.content.Context;
import android.widget.Space;

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

    private Context mContext;
    private PointF mScreenSize;


    GameObjectFactory(){}

    GameObjectFactory(Context c, PointF screenSize, SpaceInvadersApp app) {
        this.mContext = c;
        this.mScreenSize = screenSize;
        this.app = app;
    }

    GameObject getGameObject(GameObjectSpec spec) {
        GameObject object = new GameObject();

        int numComponents = spec.getComponents().length;

        final float HIDDEN = -2000f;

        object.setType(spec.getType());

        // Configure the speed relative to the screen size
        float speed = mScreenSize.x / spec.getSpeed();

        // Configure the object size relative to screen size
        size = spec.getSize();

        // Set the location to somewhere off-screen
        PointF location = new PointF(HIDDEN, HIDDEN);

        object.setTransform(new Transform(velocity, size.x,
                size.y, location));

        return object;
    }

    /*
    public static GameObject getGameObject(GameObjectType objectName) {
        switch(objectName) {
            case ALIEN:
                size = new PointF(app.mScreenSize.x / 15, app.mScreenSize.y / 10);
                spriteID = R.drawable.seagull;
                position = new PointF(0, 0);
                velocity = 50f;
                return new Alien(app, size, spriteID, position, velocity);
            case PLAYER:
                size = new PointF(app.mScreenSize.x / 7, app.mScreenSize.x / 10);
                spriteID = R.drawable.biploar_red;
                position = new PointF(app.mScreenSize.x / 2, app.mScreenSize.y - size.y*3/4);
                velocity = 0f;
                return new SimpleCannon(app, size, spriteID, position, velocity);
            case BARRIER_BLOCK:
                size = new PointF(app.mScreenSize.x / 30, app.mScreenSize.x / 60);
                spriteID = R.drawable.cloud;
                position = new PointF(0, 0);
                velocity = 0;
                return new BarrierBlock(app, size, spriteID, position, velocity);
            case PLAYER_PROJ:
                size = new PointF(app.mScreenSize.x / 80, app.mScreenSize.x / 40);
                spriteID = R.drawable.missile;
                position = new PointF(0, 0);
                velocity = -app.mScreenSize.y;
                return new PlayerProj(app, size, spriteID, position, velocity);
            case ALIEN_PROJ:
                size = new PointF(app.mScreenSize.x / 60, app.mScreenSize.x / 60);
                spriteID = R.drawable.smiley_poop;
                position = new PointF(0, 0);
                velocity = app.mScreenSize.y / 4;
                return new AlienProj(app, size, spriteID, position, velocity);
            case UFO:
                size = new PointF(app.mScreenSize.x / 15, app.mScreenSize.y / 10);
                spriteID = R.drawable.spaceinvader;
                position = new PointF(50, 50);
                velocity = 200f;
                return new UFO(app, size, spriteID, position, velocity);
            default:
                return null;
        }
    }*/
}
