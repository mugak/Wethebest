package com.wethebest.spaceinvaders;

// starter code source: “Learning Java by Building Android Games - Second Edition.”

import java.util.ArrayList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.graphics.Paint;
import android.view.SurfaceView;

public class Renderer {
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;


    Renderer(SurfaceView sh, Point screenSize){
        mSurfaceHolder = sh.getHolder();
        mPaint = new Paint();

    }

    void draw(ArrayList<GameObject> objects,
               GameState gs,
               GameUI hud) {

        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255, 0, 0, 0));

            if(gs.getDrawing()) {

                for (GameObject object : objects) {
                    if (object.isActive()) {
                        object.display(mCanvas);
                    }
                }
            }

            hud.draw(mCanvas, gs);

            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }
}