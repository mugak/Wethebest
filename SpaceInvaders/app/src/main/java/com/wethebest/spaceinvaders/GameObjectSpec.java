package com.wethebest.spaceinvaders;

// starter code source: “Learning Java by Building Android Games - Second Edition.”
import android.graphics.PointF;

public abstract class GameObjectSpec {
    private GameObjectType mType;
    private String mBitmapName;
    private float mSpeed;
    private PointF mSize;
    private String[] mComponents;
    private int mFramesAnimation;

    GameObjectSpec(GameObjectType type,
                   String bitmapName,
                   float speed,
                   PointF size,
                   String[] components,
                   int framesAnimation ){

        mType = type;
        mBitmapName = bitmapName;
        mSpeed = speed;
        mSize = size;
        mComponents = components;
        mFramesAnimation = framesAnimation;
    }

    public int getNumFrames(){
        return mFramesAnimation;
    }

    public String getType(){
        return mType.toString();
    }

    public String getBitmapName(){
        return mBitmapName;
    }

    public float getSpeed(){
        return mSpeed;
    }

    public PointF getSize(){
        return mSize;
    }

    public String[] getComponents(){
        return mComponents;
    }
}
