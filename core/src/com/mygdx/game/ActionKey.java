package com.mygdx.game;

import com.badlogic.gdx.Input;

public enum ActionKey {

    TURN_LEFT(Input.Keys.LEFT),
    TURN_RIGHT(Input.Keys.RIGHT),
    MOVE_FORWARD(Input.Keys.UP),
    MOVE_BACKWARD(Input.Keys.DOWN),
    SHOOT(Input.Keys.SPACE),
    WEAPON_TURN_LEFT(Input.Keys.Z),
    WEAPON_TURN_RIGHT(Input.Keys.X);

    private int keyCode;

    public int getKeyCode() {
        return keyCode;
    }

    ActionKey(int keyCode) {
        this.keyCode = keyCode;
    }

}
