package com.mygdx.game;

public class Vehicle extends FacilityDynamic {

    public static final String DEFAULT_IMAGE = "humvee2.png";

    @Override
    public void update() {
    }

    public Vehicle(String imagePath, Float x, Float y, Float angle) {
        super(imagePath, x, y, angle);
    }

}
