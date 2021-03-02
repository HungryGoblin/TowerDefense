package com.mygdx.game;

import java.util.ArrayList;

public class GameLogic {

    private static final int VEHICLE_NUMBER = 100;

    private static Tank hero;

    private ArrayList<Vehicle> vehiclePool = new ArrayList<>();

    public static void setHero() {
        hero = (Tank) FacilityFactory.create(
                Tank.class,
                Tank.DEFAULT_IMAGE,
                200f,
                200f,
                0f);
    }

    public static Tank getHero() {
        return hero;
    }

    public static boolean isHero(Facility facility) {
        return (facility == hero);
    }

    public void initEntities() {
        for (int i = 0; i < VEHICLE_NUMBER; i++) {
            vehiclePool.add((Vehicle)
                    FacilityFactory.create(
                    Vehicle.class,
                    Vehicle.DEFAULT_IMAGE,
                    Vehicle.DEFAULT_X,
                    Vehicle.DEFAULT_Y,
                    Vehicle.DEFAULT_ANGLE));
        }
    }

    public void start() {
        for (Facility vehicle: vehiclePool){
            GameProcessor.setRandomPosition(vehicle, GameProcessor.getInnerScreen(), 0, 360f);
        }
    }

    public void checkGameOver() {

    }

    public static void onHit(Facility srcFacility, Facility trgFacility) {
        Logger.putLog("HIT", String.format("%s was hit by %s", trgFacility, srcFacility));
        trgFacility.reset();
    }

    public void stop () {
    }

    public void initialize() {
        setHero();
        initEntities();
    }

    public GameLogic () {
        initialize();
    }

}
