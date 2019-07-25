package com.wethebest.spaceinvaders;

import android.graphics.Point;
import android.graphics.PointF;

public class Util {
    public static PointF computeBarrierPosition(int i, int numBarriers, Point screenSize) {
        return new PointF((float) screenSize.x *  i / (numBarriers + 1), (float) screenSize.y * 3 / 4);
    }

    public static PointF getTopLeftPosFromCenterPos(PointF centerPosition, PointF dimensions) {
        return new PointF(centerPosition.x - dimensions.x / 2, centerPosition.y - dimensions.y / 2);
    }
}
