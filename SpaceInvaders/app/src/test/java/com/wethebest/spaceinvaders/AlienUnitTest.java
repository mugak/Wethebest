package com.wethebest.spaceinvaders;

import android.graphics.Point;

import org.junit.Test;

import static org.junit.Assert.*;

public class AlienUnitTest {
    Alien alien = new Alien(new Point(100,100));

    @Test
    public void collision_with_player_missile_deactivates_alien() {
        PlayerProj missile = new PlayerProj(0);

        alien.collide(missile);

        assertFalse(alien.isActive());
    }

    @Test
    public void collision_with_alien_missile_doesnt_deactivate_alien() {
        AlienProj missile = new AlienProj(0);

        alien.collide(missile);

        assertTrue(alien.isActive());
    }
}
