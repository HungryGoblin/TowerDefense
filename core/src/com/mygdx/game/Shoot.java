package com.mygdx.game;

public interface Shoot {

    boolean shoot();

    void reload();

    boolean canShoot();

    void setReloadTime(float time);

    float getReloadTime();

    void setPowerShoot(float power);

    float getPowerShoot();

}
