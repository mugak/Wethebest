package com.wethebest.spaceinvaders;

import android.graphics.PointF;

/*@AlienProj
* Represents the projectiles shot from each alien.
* Moves vertically down in a straight line with constant velocity.
* Collides only with the player cannon and barrier blocks.
 */
public class AlienProj extends GameObject{
    AlienProj(SpaceInvadersApp app) {
        super(app, new PointF(app.mScreenSize.x / 160, app.mScreenSize.x / 40), R.drawable.alien_laser, app.mScreenSize.y / 3);
        //super(app, size, sprite, velocity)
    }

    public void playAudio() { }

    public void update(long fps){ mHitBox.moveVertically(mHitBox.velocity / fps); }

    public void collide(GameObject gameObject) {
        if((gameObject instanceof SimpleCannon || gameObject instanceof BarrierBlock)) {
            isActive = false;
        }
    }

    public void checkBounds() {
        if(mHitBox.topOutOfBounds()|| mHitBox.bottomOutOfBounds()) {
            isActive = false;
        }
    }
}