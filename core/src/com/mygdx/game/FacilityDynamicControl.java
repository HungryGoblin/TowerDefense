package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class FacilityDynamicControl extends FacilityDynamic {

    @Override
    public void update() {

        if (Gdx.input.isKeyPressed(ActionKey.MOVE_FORWARD.getKeyCode())) {
            moveForward();
        }
        if (Gdx.input.isKeyPressed(ActionKey.MOVE_BACKWARD.getKeyCode())) {
            moveBackward();
        }

        if (Gdx.input.isKeyPressed(ActionKey.TURN_LEFT.getKeyCode())) {
            turnLeft();
        }

        if (Gdx.input.isKeyPressed(ActionKey.TURN_RIGHT.getKeyCode())) {
            turnRight();
        }

        if (Gdx.input.isKeyPressed(ActionKey.WEAPON_TURN_LEFT.getKeyCode())) {
            weaponTurnLeft();
        }

        if (Gdx.input.isKeyPressed(ActionKey.WEAPON_TURN_RIGHT.getKeyCode())) {
            weaponTurnRight();
        }
    }

    @Override
    public void moveForward() {
        setCenteredPosition(
                getCenterX() + getSpeed() * MathUtils.cosDeg(getAngle()) * getDeltaTime(),
                getCenterY() + getSpeed() * MathUtils.sinDeg(getAngle()) * getDeltaTime(),
                GameProcessor.getInnerScreen());
    }

    @Override
    public void moveBackward() {
        setCenteredPosition(
                getCenterX() - getReverseSpeed() * MathUtils.cosDeg(getAngle()) * getDeltaTime(),
                getCenterY() - getReverseSpeed() * MathUtils.sinDeg(getAngle()) * getDeltaTime(),
                GameProcessor.getInnerScreen());
    }

    @Override
    public void turnLeft() {
        setAngle(getAngle() + 90.0f * getDeltaTime());
    }

    @Override
    public void turnRight() {
        setAngle(getAngle() - 90.0f * getDeltaTime());
    }

    public void weaponTurnRight() {
        setAngle(getAngle() - 90.0f * getDeltaTime());
    }

    public void weaponTurnLeft() {
        setAngle(getAngle() + 90.0f * getDeltaTime());
    }

    public FacilityDynamicControl(String imagePath, float x, float y, float angle) {
        super(imagePath, x, y, angle);
    }

}
