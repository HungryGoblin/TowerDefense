package com.mygdx.game;

interface Dynamic {

    float getSpeed();

    void setSpeed(float speed);

    float getReverseSpeed();

    void setReverseSpeed(float speed);

    void moveForward();

    void moveBackward();

    void turnLeft();

    void turnRight();

}
