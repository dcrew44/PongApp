package com.example.pongapp;


public class Ball {
    public int mX;
    public int mY;
    public int radius;
    public double xVelocity;
    public double yVelocity;
    public int leftBoundary, rightBoundary, topBoundary, bottomBoundary;

    public Ball(int mX, int mY, double xVelocity, double yVelocity) {
        this.mX = mX;
        this.mY = mY;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;

    }

    public void move() {
        mX += xVelocity;
        mY += yVelocity;
        if(mX < leftBoundary) {
            mX = leftBoundary;
            xVelocity = -xVelocity;
        }
        if(mX > rightBoundary) {
            mX = rightBoundary;
            xVelocity = -xVelocity;
        }
        if(mY < topBoundary) {
            mY = topBoundary;
            yVelocity = -yVelocity;
        }
        if(mY > bottomBoundary) {
            mY = bottomBoundary;
            yVelocity = -yVelocity;
        }

    }
    public void setCollisionBoundaries( int windowWidth, int windowHeight){
        leftBoundary = -windowWidth / 2 + radius;
        rightBoundary = windowWidth / 2 - radius;
        topBoundary = -windowHeight / 2 + radius;
        bottomBoundary = windowHeight / 2 - radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
