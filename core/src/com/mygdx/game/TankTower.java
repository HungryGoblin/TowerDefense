package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class TankTower extends FacilityDynamicShootControl {

    public static final String DEFAULT_IMAGE = "acs_tower.png";

    @Override
    public boolean shoot() {
        if (canShoot()) {
            projectile.shoot(
                    getCenterX() + MathUtils.cosDeg(getAngle()) * getHalfActualWidth(),
                    getCenterY() + MathUtils.sinDeg(getAngle()) * getHalfActualHeight(),
                    getAngle());
            return true;
        } else
            return false;
    }

    @Override
    public void update() {
        super.update();
        setAsParentPosition();
        projectile.update();
    }

    @Override
    public void render() {
        super.render();
        projectile.render();
    }

    @Override
    public void moveForward() {
    }

    @Override
    public void moveBackward() {
    }

    public TankTower(String imagePath, Facility parent) {
        super(imagePath, DEFAULT_X, DEFAULT_Y, parent.getAngle());
        setParent(parent);
        setAsParentPosition();
    }

    public TankTower(String imagePath, Float x, Float y, Float angle) {
        super(imagePath, x, y, angle);
    }

}
