package com.mygdx.game;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public final class FacilityFactory {

    private static final Class[] parameterType = {String.class, Float.class, Float.class, Float.class};
    private static ArrayList<Facility> facilityPool = new ArrayList<>();

    public static ArrayList<Facility> getFacilityPool() {
        return facilityPool;
    }

    public static Facility create(
            Class<? extends Facility> cls,
            String imagePath,
            Float x,
            Float y,
            Float angle) {
        try {
            Facility facility;
            Constructor<?> consArray[] = cls.getConstructors();
            Constructor<?> cons = cls.getConstructor(parameterType);
            facility = (Facility) cons.newInstance(imagePath, x, y, angle);
            facilityPool.add(facility);
            return facility;
        } catch (Exception e) {
            Logger.putError(String.format("Can't create object of class '%s'", cls.getName()), e);
        }
        return null;
    }
}
