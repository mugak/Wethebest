package com.wethebest.spaceinvaders;

import android.graphics.PointF;
import android.graphics.RectF;

/*
Alien handles the enemies movement and game logic.
The enemy moves side to side, gradually moving towards the player.
It also shoots projectiles randomly.
Instantiated in AlienArmy
 */
public class Alien extends GameObject {

    public Alien() {super();}

    public class AlienUpdateComponent implements UpdateComponent {
        private PointF baseShootInterval = new PointF(3, 15); //shoots every x-y seconds

        //Shoot projectiles randomly
        private PointF shootInterval = baseShootInterval;
        public boolean shootNow = false;
        private Counter waitToShoot;

        //Sound effects
        private boolean playShoot = false;
        private boolean playHit = false;

        @Override
        public void update(long fps, Transform t) {
            PointF location = t.getLocation();
            float speed = t.getSpeed();

            if(t.headingRight()) {
                location.x += speed / fps;
            }
            else if (t.headingLeft()){
                location.x -= speed / fps;
            }

            if(fps != 0) {
                handleCounters(fps);
            }

            checkAlienWin();
        }

        public void playAudio() {
            if(playShoot) {
                app.soundEngine.alienShoot();
                playShoot = false;
            }
            if(playHit){
                app.soundEngine.alienHit();
                playHit = false;
            }
        }

        public void collide(GameObject gameObject) {
            if (gameObject instanceof PlayerProj) {
                playHit = true;
                isActive = false;
            }
        }

        public boolean outOfBounds() {
            return getTransform().mHitBox.bottom >= app.mScreenSize.y;
        }

        public void reverseXVelocity() {

            getTransform().headDown();
            if (getTransform().headingRight()) getTransform().headLeft();
            else getTransform().headRight();
        }

        public void speedUp(float multiplier) {
            getTransform().setSpeed(getTransform().getSpeed() * multiplier);
        }

        /*public GameObject shoot() {
            GameObject mProj = GameObjectFactory.getGameObject("AlienProj");
            mProj.setPosition(mHitBox.centerBottom());
            playShoot = true;
            return mProj;
        }*/

        private void handleCounters(long fps) {
            if(waitToShoot.run(fps)) {
                shootNow = true;
                waitToShoot.setSeconds(getRandomFloat(shootInterval));
            }
        }

        private float getRandomFloat(PointF interval) {
            return app.rand.nextFloat() * (interval.y - interval.x) + interval.x;

        }
        //Set the Shoot
        public void setShootInterval(float factor){
            float min = (baseShootInterval.x * factor);
            float max = (baseShootInterval.y * factor);
            shootInterval = new PointF(min, max);
        }

        private void checkAlienWin() {
            app.isGameOver = outOfBounds();
        }

        public void decreaseBaseShootInterval(float decreaseAmount) {
            if (baseShootInterval.x > decreaseAmount) {
                baseShootInterval.x -= decreaseAmount;
            } else {
                baseShootInterval.x = 1;
            }

            if (baseShootInterval.y > 1) {
                baseShootInterval.y -= decreaseAmount;
            } else {
                baseShootInterval.y = 1;
            }
        }
    }
}

public class AlienSpec extends GameObjectSpec {
    private static final GameObjectType mType = GameObjectType.ALIEN;
    private static final String mBitmapName = "seagull";
    private static final int mFramesOfAnimation = 2;
    private static final float mInitSpeed = app.mScreenSize.y / 4;
    private static final PointF mSize =
            new PointF(app.mScreenSize.x / 60, app.mScreenSize.x / 60);
    private static final String[] mComponents =
            new String [] {"AnimatedGraphicsComponent", "PlayerUpdateComponent"};

    public AlienSpec() {
        super(mType, mBitmapName, mInitSpeed,
                mSize, mComponents, mFramesOfAnimation);
    }
}
