package com.wethebest.spaceinvaders;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Iterator;
import java.util.LinkedList;

/*
GameObjectManager holds all of the GameObjects - the player, projectiles, AlienArmy, and Barriers
It updates, draws, removes, and detects collisions
Instantiated in SpaceInvadersApp
 */
public class GameObjectManager {
    private LinkedList<GameObject> gameObjects;
    public SimpleCannon mPlayer;
    public AlienArmy mAlienArmy;
    private Barriers mBarriers;
    private UFOSpawner mUFOSpawner;

    SpaceInvadersApp app;

    public GameObjectManager(SpaceInvadersApp app) {
        this.app = app;

        gameObjects = new LinkedList<>();

        mPlayer = (SimpleCannon) GameObjectFactory.getGameObject("Player");
        mBarriers = new Barriers(app);
        mAlienArmy = new AlienArmy(app);
        mUFOSpawner = new UFOSpawner(app);

        gameObjects.add(mPlayer);
        gameObjects.addAll(mAlienArmy.aliens);
        gameObjects.addAll(mBarriers.getBarrierBlocks());

        //Put all gameObjects in their original states
        for (GameObject gameObject : gameObjects) {
            gameObject.reset();
        }
    }

    /*
        Checks to see if the gameObject is inactive and if so removes it from the list of gameobject
        Also calls for the alienArmy to remove references to it's innactive aliens
     */
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

    //Allows a game object to be added to the gameobjects list
    public void add(GameObject gameObject) {
        if(gameObjects != null) {
            gameObjects.add(gameObject);
        }
    }

    // Handles what should happen in a change in state, like a collision for instance
    public void updateGameObjectStates(long fps) {
        updateGameObjects(fps);
        detectCollisions();
        playAudio();
        removeInactiveObjects();
    }

    // The function is called each frame and tells the gameobjects to update their state
    public void updateGameObjects(long fps) {
        for(GameObject gameObject : gameObjects) {
            gameObject.update(fps);
        }

        mUFOSpawner.update(fps);
        mAlienArmy.update(fps);
        gameObjects.addAll(mAlienArmy.getAlienProjs());
    }

    public void displayGameObjects(Canvas canvas) {
        for (GameObject gameObject : gameObjects) {
            gameObject.display(canvas);
        }

        mAlienArmy.draw(canvas);
    }

    //Plays all game object's audio
    public void playAudio() {
        for(GameObject gameObject : gameObjects) {
            gameObject.playAudio();
        }
    }

    /*
        Called each frame and checks to see if any of the projectiles have collided with any of the
        non-projectiles
     */
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

    //If there is a collision between the two game objects, call each object's collision behaviors
    private void collide(GameObject object1, GameObject object2) {
        if (RectF.intersects(object1.getHitBox(), object2.getHitBox())) {
            object1.collide(object2);
            object2.collide(object1);
        }
    }

    //Is used to determine if a wave should end
    public boolean allAliensDefeated() {
        return mAlienArmy.aliens.isEmpty();
    }


    public void newWave(){
        removeProjectiles();
        resetBarriers();
        mPlayer.resetAmmo();
        mAlienArmy.spawnNewWave();
    }
    private void removeBarriers(){
        for(GameObject gameObject : gameObjects){
            if(gameObject instanceof BarrierBlock){
                gameObject.isActive = false;
            }
        }
    }

    private void resetBarriers(){
        removeBarriers();
        mBarriers.createBarriers();
        gameObjects.addAll(mBarriers.getBarrierBlocks());
    }

    private void removeProjectiles(){
        for(GameObject gameObject : gameObjects){
            if(gameObject instanceof AlienProj || gameObject instanceof  PlayerProj){
                gameObject.isActive = false;
            }
        }
    }

}
