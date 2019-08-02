package com.wethebest.spaceinvaders;

// starter code source: “Learning Java by Building Android Games - Second Edition.”

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public interface GraphicsComponent {

    void initialize(Context c, GameObjectSpec spec,
                    PointF size);

    // Updated to take a reference to a Camera
    void draw(Canvas canvas, Transform t);
}
