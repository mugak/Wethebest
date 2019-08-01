# **Design Document**
* The game starts with Intro, displaying a main menu with buttons, one of which transitions to the SpaceInvaders activity.
* SpaceInvaders sets up the canvas, the display, and the accelerometer. It sets the view to SpaceInvadersApp.
* SpaceInvadersApp is a SurfaceView. It runs the game, handling game states such as WaveState. It instantiates GameObjectManager.
   * WaveState is where most of the game runs, using GameObjectManager methods.
   * GameObjectManager holds all of the GameObjects, updating, drawing, and removing them.  
* GameObject is an abstract class. Methods are used in GameObjectManager. It has a HitBox instance variable.
   * HitBox controls movement and drawing. It is instantiated using a builder. Parameters include size, sprite, position, and velocity.
* GameObjects are constructed with default values using the GameObjectFactory.
* GameObjectManager instantiates SimpleCannon, and GameObject containers AlienArmy, Barriers, and UFOSpawner.
   * Aliens are observers to AlienArmy.
   * Barriers construct Barrier objects, which set the BarrierBlocks.
   * AlienProj and PlayerProj are instantiated by their respective classes
---
# **Classes**  
#### Activities
* **Intro** - main activity, main menu
    * **Settings** 
    * **Story** - explains background story
* **SpaceInvaders** - creates game
    * **BackgroundSoundService** - plays music
    * **GameOver**

#### Views
* **SpaceInvadersApp** - game view, handles threading and states

#### States
* **State** - enum: WAVE, PAUSE, GAMEOVER
* **GameState** - interface
    * **WaveState** - spawns enemies
    * **PauseState**
    * **GameOverState**

#### Game Object
* **GameObject** - abstract class
    * **HitBox** - instance variable: controls movement and drawing
* **GameObjectFactory** - creates GameObjects with default values
* **GameObjectManager** - updates all GameObjects

##### *Game Objects*
* **SimpleCannon** - the player
* **Alien**
* **AlienProj** - alien projectile
* **PlayerProj** - player projectile
* **BarrierBlock** - single block in a Barrier
* **UFO**

##### *Game Object Containers*
* **AlienArmy** - controls Aliens
* **Barrier** - sets BarrierBlocks
* **Barriers** - sets multiple Barrier objects
* **UFOSpawner**

#### Misc
* **SoundEngine** - plays sound effects
* **GameUI** - draws score, lives, ammo
* **Counter** - counts seconds by decrementing frames
    * **AutomaticCounter** - counts without being triggered