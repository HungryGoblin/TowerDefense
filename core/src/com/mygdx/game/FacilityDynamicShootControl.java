package com.mygdx.game;

import com.badlogic.gdx.Gdx;

abstract class FacilityDynamicShootControl extends FacilityDynamicControl implements Dynamic, Shoot {


    protected Projectile projectile;

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    @Override
    public void update() {
        super.update();
        if (Gdx.input.isKeyJustPressed(ActionKey.SHOOT.getKeyCode())) {
            shoot();
        }
    }

    @Override
    public void reload() {
    }

    @Override
    public boolean canShoot() {
        return !projectile.isVisible();
    }

    @Override
    public void setReloadTime(float time) {
    }

    @Override
    public float getReloadTime() {
        return 0;
    }

    @Override
    public void setPowerShoot(float power) {
    }

    @Override
    public float getPowerShoot() {
        return 0;
    }

    @Override
    public void render() {
        super.render();
        projectile.render();
    }

    public FacilityDynamicShootControl(String imagePath, float x, float y, float angle) {
        super(imagePath, x, y, angle);
        projectile = new Projectile(this);
    }

}
