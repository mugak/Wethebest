package com.wethebest.spaceinvaders;

import android.graphics.PointF;
import android.graphics.RectF;

import java.util.Iterator;
import java.util.LinkedList;

public class GameObjectManager {
    LinkedList<GameObject> gameObjects;
    SimpleCannon mPlayer;
    AlienArmy mAlienArmy;
    LinkedList<Barrier> mBarriers;

    SpaceInvadersApp app;

    public GameObjectManager(SpaceInvadersApp app) {
        this.app = app;

        gameObjects = new LinkedList<>();

        mPlayer = new SimpleCannon(app.context, app.mScreenSize);
        mAlienArmy = new AlienArmy(app);
        mAlienArmy.setAliens();

        gameObjects.addAll(mAlienArmy.getAliens());
        gameObjects.add(mPlayer);

        for (GameObject gameObject : gameObjects) {
            gameObject.reset(app.mScreenSize);
        }

        createBarriers(3);
    }

    private void createBarriers(int numBarriers) {
        for (int i = 1; i < numBarriers + 1; i++) {
            PointF barrierCenterPosition = Util.computeBarrierPosition(i, numBarriers, app.mScreenSize);

            addBarrierToGameObjects(new Barrier(app.mScreenSize, barrierCenterPosition));
        }
    }

    private void addBarrierToGameObjects(Barrier barrier) {
        mBarriers.add(barrier);
        gameObjects.addAll(barrier.getBarrierBlocks());
    }

    private void detectCollisions() {
        //Checks to see if the first object is a projectile because in SpaceInvaders only
        // projectiles collide with non projectiles. There are no other types of collisions
        Iterator<GameObject> firstObjectItr = gameObjects.iterator();
        while (firstObjectItr.hasNext()) {
            GameObject object1 = firstObjectItr.next();
            if (object1 instanceof Projectile) {

                for (GameObject object2 : gameObjects) {
                    if (!(object2 instanceof Projectile)) {
                        collide(object1, object2);
                    }
                }

                for (GameObject alienObject : mAlienArmy.allAliens) {
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

    private void removeInactiveObjects() {
        Iterator<GameObject> gameObjectIterator = gameObjects.iterator();

        while (gameObjectIterator.hasNext()) {
            GameObject gameObject = gameObjectIterator.next();

            if (!gameObject.isActive()) {
                gameObjectIterator.remove();
            }
        }

        Iterator<Alien> alienObjectIterator = mAlienArmy.allAliens.iterator();

        while (alienObjectIterator.hasNext()) {
            Alien alienObject = alienObjectIterator.next();

            if (!alienObject.isActive()) {
                alienObjectIterator.remove();
            }
        }

    }

    public void add(GameObject gameObject) {
        if(gameObjects != null) {
            gameObjects.add(gameObject);
        }
    }

    //    private void addAlienProjs() {
//        LinkedList<GameObject> alienProjs = new LinkedList<>(); // need temp list because can't modify Collections being iterated
//
//        for (GameObject gameObject : gameObjects) {
//            if (gameObject instanceof Alien) {
//                if (((Alien) gameObject).shootNow) {
//                    alienProjs.add(((Alien) gameObject).shoot());
//                    ((Alien) gameObject).shootNow = false;
//                }
//            }
//
//        }
//        gameObjects.addAll(alienProjs);
//    }
}
