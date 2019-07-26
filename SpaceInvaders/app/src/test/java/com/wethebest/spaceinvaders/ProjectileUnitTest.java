package com.wethebest.spaceinvaders;

import android.content.Context;
import android.graphics.Point;

import org.junit.Test;
import static org.junit.Assert.*;


public class ProjectileUnitTest {
    private Context context;

    private int testX;
    private int testY;
    private  PlayerProj testPlayerProj;

    public void setUp(){
        //invalid positions
        testX = -1;
        testY = -1;
        PlayerProj testPlayerProj = new PlayerProj(context, new Point(0,0));
    }
    public void act(){
        testPlayerProj.setPos(testX, testY);
    }

    @Test
    public void verify(){
        assertEquals (checkOutOfScreen(testPlayerProj), true);
    }

    public boolean checkOutOfScreen(Projectile proj){
        float  centerX = proj.getHitBox().centerX();
        float  centerY = proj.getHitBox().centerY();
        return ( centerX < 0 || centerX > 600)
                || (centerY < 0 || centerY > 800); //Need to get screen sizes
    }


}
