# Acceptance Criteria

## As a player, I want to control a laser cannon
	AC1. Player input moves cannon left and right
	AC2. Cannon does not move off the screen
	AC3. Cannon shoots projectiles on player touch
	AC4. Cannon loses a life when hit by an alien projectile
	AC5. Cannon resets after it loses a life

## As a projectile, I want to collide with objects
	AC1. Projectile travels in a straight trajectory
	AC2. Projectile moves at a constant velocity
	AC3. Projectile is removed when off the screen
	AC4. Player projectile collides with alien and barrier
	AC5. Player projectile does not collide with the player
	AC6. Alien projectile collides with player and barrier
	AC7. Alien projectile does not collide with other aliens

## As an alien, I want to attack the player
	AC1. Alien moves left and right 
	AC2. Alien advances when it touches the edge of the screen
	AC3. Alien changes direction when it touches the edge of the screen
	AC4. Alien does not move off the screen
	AC5. Alien shoots projectiles at random intervals
	AC6. Alien dies when hit by a player projectile
	AC7. Aliens are placed as a grid
	AC8. Aliens move faster as aliens are killed
	AC9. Aliens respawn on a new level

## As a barrier, I want to stop projectiles
	AC1. Barrier loses durability when hit by a projectile
	AC2. Barrier sprite reflects loss in durability
	AC3. Barrier breaks when durability is 0
	AC4. Barrier positions are set according to screen size
	AC5. Barriers respawn on a new level

## As the game, I want to display my current state.
	AC1. Game draws sprites of aliens, barriers, cannon, projectiles
	AC2. The start button in the start menu runs the game 
	AC3. Game over screen is displayed when the player loses all lives
	AC4. Curent level updates when all aliens are dead
	AC5. Player score updates when the player kills an alien
	AC6. Sound effects play when projectiles shoot and collide