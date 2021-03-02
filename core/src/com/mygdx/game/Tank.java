package com.mygdx.game;

public class Tank extends FacilityDynamicShootControl {

    public static final String DEFAULT_IMAGE = "acs_base.png";
    public static final float DEFAULT_SPEED = 120f;

    private TankTower tankTower;

    @Override
    public void setSpeed(float speed) {
        super.setSpeed(speed);
        tankTower.setSpeed(speed);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        tankTower.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        tankTower.setY(y);
    }

    @Override
    public void update() {
        super.update();
        tankTower.update();
    }

    @Override
    public void render() {
        super.render();
        tankTower.render();
    }

    @Override
    public void weaponTurnRight() {
        tankTower.weaponTurnRight();
    }

    @Override
    public void weaponTurnLeft() {
        tankTower.weaponTurnLeft();
    }

    @Override
    public boolean shoot() {
        return tankTower.shoot();
    }

    public Tank(String imagePath, Float x, Float y, Float angle) {
        super(imagePath, x, y, angle);
        tankTower = new TankTower(TankTower.DEFAULT_IMAGE, this);
        setProjectile(tankTower.getProjectile());
        setSpeed(DEFAULT_SPEED);
        setReverseSpeed(DEFAULT_SPEED * 0.5f);
    }

}
