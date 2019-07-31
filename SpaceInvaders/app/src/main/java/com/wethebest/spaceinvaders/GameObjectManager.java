package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.Iterator;
import java.util.LinkedList;

/*
    Potentially collisions should be handled by another class
 */
public class GameObjectManager {
    private LinkedList<GameObject> gameObjects;
    public SimpleCannon mPlayer;
    private AlienArmy mAlienArmy;
    private Barriers mBarriers;

    SpaceInvadersApp app;

    public GameObjectManager(SpaceInvadersApp app) {
        this.app = app;

        gameObjects = new LinkedList<>();

        mPlayer = (SimpleCannon) GameObjectFactory.getGameObject("Player");
        mBarriers = new Barriers(app);
        mAlienArmy = new AlienArmy(app);

        gameObjects.add(mPlayer);
        gameObjects.addAll(mAlienArmy.aliens);
        gameObjects.addAll(mBarriers.getBarrierBlocks());

        for (GameObject gameObject : gameObjects) {
            gameObject.reset();
        }
    }

    private void removeInactiveObjects() {
        Iterator<GameObject> gameObjectIterator = gameObjects.iterator();

        while (gameObjectIterator.hasNext()) {
            GameObject gameObject = gameObjectIterator.next();

            if (!gameObject.isActive()) {
                gameObjectIterator.remove();
            }
        }

        mAlienArmy.removeInactiveObjects();
    }

    public void add(GameObject gameObject) {
        if(gameObjects != null) {
            gameObjects.add(gameObject);
        }
    }

    // This function updates all gameobjects each frame
    public void updateGameObjectStates(long fps) {
        updateGameObjects(fps);
        detectCollisions();
        removeInactiveObjects();
    }

    public void updateGameObjects(long fps) {
        for(GameObject gameObject : gameObjects) {
            gameObject.update(fps);
        }

        mAlienArmy.update(fps);
        gameObjects.addAll(mAlienArmy.getAlienProjs());
    }

    public void displayGameObjects(Canvas canvas) {
        for (GameObject gameObject : gameObjects) {
            gameObject.display(canvas);
            gameObject.playAudio();
        }

        mAlienArmy.draw(canvas);
    }

    private void detectCollisions() {
        //Checks to see if the first object is a projectile because in SpaceInvaders only
        // projectiles collide with non projectiles. There are no other types of collisions
        for (GameObject object1 : gameObjects) {
            if (object1 instanceof AlienProj || object1 instanceof PlayerProj) {

                for (GameObject object2 : gameObjects) {
                    if (!(object2 instanceof AlienProj || object2 instanceof PlayerProj)) {
                        collide(object1, object2);
                    }
                }

                for (GameObject alienObject : mAlienArmy.aliens) {
                    collide(object1, alienObject);
                }
            }
        }
    }

    private void collide(GameObject object1, GameObject object2) {
        if (RectF.intersects(object1.getHitBox(), object2.getHitBox())) {
            object1.collide(object2);
            object2.collide(object1);
        }
    }

}
