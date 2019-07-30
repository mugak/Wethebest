package com.wethebest.spaceinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.SurfaceHolder;

public class DisplayInformation {
    public SurfaceHolder mOurHolder;
    public Canvas mCanvas;
    public Point mScreenSize; //TODO: maybe this should be public since it's accessed by all GameObjects
    public Context context;
}
