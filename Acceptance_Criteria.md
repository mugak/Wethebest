# Acceptance Criteria

## GUI

### As a GUI, I want to display the visual features of the game in the playing state.
  
	AC1. At the top of screen, there is a score board displaying the amount of points.
	AC2. The middle of screen contains a total of 5 rows of 3 different types of aliens. The first type is in the first 		 row. Rows 2 & 3 have a different alien species and the next 2 rows (4 & 5) are different aliens.
	AC3. There are 4 green barriers toward the bottom of the screen, but above the cannon. These barriers protect the 	       cannon.
	AC4. Below the barriers, a cannon is able to move left and right and fire shots straight towards the moving aliens.
	AC5. Below the cannon, towards the bottom of the screen the number of lives left are shown by a picture of 3 space 		ships.

## PlayerCannon

### As a player, I want to control a laser cannon and shoot projectiles at aliens.
	
	AC1. Player can move laser cannon to the right or left (within the boundaries of the screen) by touching the screen 		 (either by touching and holding to the left or right of the cannon or by dragging the cannon).
	AC2. Projectiles shoot upwards when player touches the screen.
	AC3. Projectiles move fast enough to be easy to track with eyes and position accurately but not too sluggish to feel 		  unresponsive.	
	AC4. Cannon loses a life when hit.
	AC5. Cannon can be upgraded to be faster or have faster fire rate.
	
## Alien

### As an alien, I want to attack the player.
	
	AC1. Screen features several rows of aliens. 
	AC2. Starting with the bottom row, every row moves in sync a small amount to one side following the row below it.
	AC3. When a row reaches the end of the screen, repeat the process in the other direction.
	AC4. Move downwards towards the cannon at a set interval.
	AC5. Move increasingly faster as more aliens are killed.
	AC6. Aliens shoot projectiles downwards toward cannon.

## GameState

### As a GameState, I want to represent a state of gameplay.

	AC1. GameState is an interface to represent a state of the game.
	AC2. Home, gameplay, game over, and help, all implement GameState. 
	
## Projectile

### As a projectile, I want to move in a straight line until I hit something.

	AC1. Projectile travels in a straight line at a constant velocity
	AC2. Upon contact with an object, projectile is consumed
	AC3. A projectile shot from an alien cannot come in contact with another alien. 
	
### As a projectile, I want to cause damage to whatever I hit.

	AC1. If a projectile hits an alien, the alien dies.
	AC2. If a projectile hits a barrier, the barrier deteriorates by a set amount.
	AC3. If a projectile hits the cannon, the cannon loses a life.

 ## Barrier

 ### As a barrier, I want to block projectiles until I break.

	AC1. Barrier loses durability with each projectile consumed
	AC2. Barrier breaks when durability reaches 0.	

