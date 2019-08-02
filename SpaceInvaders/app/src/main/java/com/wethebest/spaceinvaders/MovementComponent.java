package com.wethebest.spaceinvaders;

// starter code source: “Learning Java by Building Android Games - Second Edition.”

interface MovementComponent {

    boolean move(long fps, Transform t);
}