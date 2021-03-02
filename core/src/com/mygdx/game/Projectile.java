package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class Projectile extends Facility {

    private static final String DEFAULT_IMAGE = "projectile.png";
    private static final float DEFAULT_SPEED = 800f;

    private float speed = DEFAULT_SPEED;
    private float deltaX = 0;
    private float deltaY = 0;


    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void shoot(float x, float y, float angle) {
        setX(x);
        setY(y);
        setAngle(angle);
        deltaX = speed * MathUtils.cosDeg(angle);
        deltaY = speed * MathUtils.sinDeg(angle);
    }

    public void checkHitting() {
        Facility trgFacility;
        for (Facility facility : FacilityFactory.getFacilityPool()) {
            if (!facility.isVisible() ) continue;
            trgFacility = GameProcessor.getFacilityContainer(this);
            if ( trgFacility  == getParent() || trgFacility == getProtoParent()) continue;
            if (trgFacility != null) {
                GameLogic.onHit(this.getProtoParent(), trgFacility);
                this.reset();
            }

        }
    }

    @Override
    public void update() {
        if (isVisible()) {
            setX(getX() + deltaX * getDeltaTime());
            setY(getY() + deltaY * getDeltaTime());
            checkHitting();
        } else
            reset();
    }

    public Projectile(Facility parent) {
        super(DEFAULT_IMAGE, Facility.DEFAULT_X, Facility.DEFAULT_Y, 0);
        setParent(parent);
    }
}
