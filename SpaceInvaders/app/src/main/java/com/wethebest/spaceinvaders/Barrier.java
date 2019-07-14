package com.wethebest.spaceinvaders;

import android.app.Activity;
import android.graphics.RectF;
import android.os.Bundle;

public class Barrier{
    private RectF rect;

    private float barrierWidth;
    private float barrierHeight;

    private int durability;

    Barrier(int screenX){
        barrierWidth = screenX/5;
        barrierHeight = barrierWidth/2;

        durability = 1;
    }

    RectF getRect(){
        return rect;
    }

    //Set position of barrier, centered on x,y
    void setPos(int x, int y){
        rect.left = x + barrierWidth/2;
        rect.right = rect.left + barrierWidth;
        rect.top = y + barrierHeight/2;
        rect.bottom = rect.top + barrierHeight;
    }

    int getBarDurability(){return durability;}

    //Updates the durability of barrier if hit
    void addBarDurability(int x){
        durability += x;
    }
}