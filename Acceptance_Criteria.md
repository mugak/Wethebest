# Acceptance Criteria

## GUI

### GUI should have 
  
   -At the top of screen, a score board calculating the amount of points
   
   -In the middle of screen should contain total 5 rows of 3 different aliens. The first type is in the first row. 
    The row 2 & 3 have a different alien species and the next 2 rows (4 & 5) are different aliens. The aliens are able to 
    move left, right and down.
    
   -Have 4 green shields toward the bottom of the screen, but not all the way to the bottom. These shields protect the spaceship.
   
   -Have a space ship below the shields that is able to move left and right and able to fire shots straight towards the moving aliens.
   
   -Below the shapship, towards the bottom of the screen shows the number of lives (total 3) left and resembled by a picture of 3 
    space ships.

## PlayerCannon

### As a player, I want to control a laser cannon and shoot projectiles at aliens.
	
	AC1. Player can move laser cannon to the right or left (within the boundaries of the screen) by touching the screen 		 (either by touching and holding to the left or right of the cannon or by dragging the cannon).
	AC2. Projectiles shoot upwards when player touches the screen.
	AC3. Projectiles move fast enough to be easy to track with eyes and position accurately but not too sluggish to feel 		  unresponsive.	
	AC4. Cannon loses a life when hit.
	AC5. Cannon can be upgraded to be faster or have faster fire rate.
	
## Alien

### As an alien, I want to move with my fellow aliens in a synchronized manner.
	
	AC1. Screen features several rows of aliens. 
	AC2. Starting with the bottom row, every row moves in sync a small amount to one side following the row below it.
	AC3. When a row reaches the end of the screen, repeat the process in the other direction.


## Gamestate

	-Points should be accumulated with enemy deaths
	-When points reach a certain threshold playerCannon receives upgrade

