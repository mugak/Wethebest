package com.wethebest.spaceinvaders;

import android.graphics.Point;
import android.graphics.PointF;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilUnitTest {
    @Test
    public void computeBarrierPosition_isCorrect() {
        Point screenSize = new Point(10, 10);
        PointF pos = Util.computeBarrierPosition(1, 1, screenSize);
        assertEquals(0, pos.x, 0);
    }
}
