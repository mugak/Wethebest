package com.wethebest.spaceinvaders;

// starter code source: “Learning Java by Building Android Games - Second Edition.”
import android.graphics.Rect;
import android.view.MotionEvent;
import java.util.ArrayList;

public interface InputObserver {

    // This allows InputObservers to be called by GameEngine
    //to handle input
    void handleInput(MotionEvent event,
                     GameState gs, ArrayList<Rect> buttons);
}