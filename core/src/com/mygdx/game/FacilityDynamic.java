package com.mygdx.game;

abstract class FacilityDynamic extends Facility implements Dynamic {

    private static final float DEFAULT_SPEED = 100f;
    private static final float DEFAULT_REVERSE_SPEED = DEFAULT_SPEED * 0.5f;

    private float speed = DEFAULT_SPEED;
    private float reverseSpeed = DEFAULT_REVERSE_SPEED;

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public float getReverseSpeed() {
        return reverseSpeed;
    }

    @Override
    public void setReverseSpeed(float speed) {
        this.reverseSpeed = speed;
    }

    @Override
    public void moveForward() {
    }

    @Override
    public void moveBackward() {
    }

    @Override
    public void turnLeft() {
    }

    @Override
    public void turnRight() {
    }

    public FacilityDynamic(String imagePath, float x, float y, float angle) {
        super(imagePath, x, y, angle);
    }

}
