package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

class GameProcessor {

    private static final float INNER_SCREEN_RATIO = 0.95f;
    private static final float OUTER_SCREEN_RATIO = 1.1f;
    private static final boolean DEBUG_STATE = true;
    private static Batch batch = null;

    private static final ShapeRenderer shapeRenderer;
    private static Rectangle outerScreen = new Rectangle();
    private static Rectangle innerScreen = new Rectangle();
    public static BitmapFont font = new BitmapFont();
    private static boolean debugState = DEBUG_STATE;

    public static void setBatch(Batch batch) {
        GameProcessor.batch = batch;
    }

    public static Batch getBatch() {
        return batch;
    }

    public static Rectangle getOuterScreen() {
        return outerScreen;
    }

    public static Rectangle getInnerScreen() {
        return innerScreen;
    }

    private static void setScreen(Rectangle screen, float ratio) {
        screen.setSize(Gdx.graphics.getWidth() * ratio, Gdx.graphics.getHeight() * ratio);
        screen.setX((Gdx.graphics.getWidth() - screen.getWidth()) / 2);
        screen.setY((Gdx.graphics.getHeight() - screen.getHeight()) / 2);
    }

    public static void setOuterScreen(float ratio) {
        setScreen(outerScreen, ratio);
    }

    public static void setInnerScreen(float ratio) {
        setScreen(innerScreen, ratio);
    }

    public static void setDebugState(boolean debugState) {
        GameProcessor.debugState = debugState;
    }

    public static boolean isOnOuterScreen(Facility facility) {
        return isOnOuterScreen(facility.getCenterX(), facility.getCenterY());
    }

    public static boolean isOnOuterScreen(float x, float y) {
        return outerScreen.contains(x, y);
    }

    public static boolean isOnInnerScreen(Facility facility) {
        return isOnInnerScreen(facility.getCenterX(), facility.getCenterY());
    }

    public static boolean isOnInnerScreen(float x, float y) {
        return innerScreen.contains(x, y);
    }

    public static float getOuterScreenDiagonal() {
        return Tools.getDiagonal(outerScreen.width, outerScreen.height);
    }

    public static void setRandomPosition(Facility facility, Rectangle region, float minAngle, float maxAngle) {
        facility.setAngle((minAngle != 0 || maxAngle != 0) ? Tools.randomValue(minAngle, maxAngle) : 0);
        while (true) {
            float x = Tools.randomValue(region.x, region.x + region.width);
            float y = Tools.randomValue(region.y, region.y + region.height);
            if (facility.setCenteredPosition(x, y, region))
                break;
        }
    }

    public static Facility getFacilityContainer(Facility srcFacility) {
        Facility facilityContainer = null;
        for (Facility facility : FacilityFactory.getFacilityPool()) {
            if (!facility.isVisible() || facility == srcFacility) continue;
            if (facility.getHitBox().contains(srcFacility.getCenterX(), srcFacility.getCenterY())) {
                facilityContainer = facility;
                break;
            }
        }
        return facilityContainer;
    }

    public static void render() {

        for (Facility facility : FacilityFactory.getFacilityPool()) {
            facility.render();
            if (Logger.getLogLevel() >= 3) {
                font.setColor(1, 1, 1, 1);
                font.draw(batch, facility.toString(), facility.getX(), facility.getCenterY());
            }
        }

        if (Logger.getLogLevel() >= 3) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(1, 0, 0, 1);
            shapeRenderer.rect(innerScreen.getX(), innerScreen.getY(), innerScreen.getWidth(), innerScreen.getHeight());

            shapeRenderer.setColor(0, 0, 1, 1);
            for (Facility facility : FacilityFactory.getFacilityPool()) {
                Rectangle hitBox = facility.getHitBox();
                shapeRenderer.rect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
//                shapeRenderer.rect(
//                        facility.getX(),
//                        facility.getY(),
//                        facility.getTexture().getWidth(),
//                        facility.getTexture().getWidth());
            }

//                Polygon heroView = Tools.getPolygon3(GameLogic.getHero().getCenterX(),
//                        GameLogic.getHero().getCenterY(),
//                        Tools.getDiagonal(outerScreen.getWidth(),outerScreen.getHeight()),
//                        outerScreen.getHeight());
//                shapeRenderer.polygon(heroView.getVertices());
        }

        Facility hero = GameLogic.getHero();
        if (hero != null) {
            shapeRenderer.setColor(1, 0, 0, 1);
            shapeRenderer.rect(
                    hero.getHitBox().x,
                    hero.getHitBox().y,
                    hero.getHitBox().getWidth(),
                    hero.getHitBox().getHeight());
            shapeRenderer.rect(
                    hero.getX(),
                    hero.getY(),
                    hero.getWidth(),
                    hero.getHeight());
            shapeRenderer.end();
        }
    }

    public static void update() {
        for (Facility facility : FacilityFactory.getFacilityPool()) {
            facility.update();
        }
    }

    public static void dispose() {
        for (Facility facility : FacilityFactory.getFacilityPool()) {
            facility.dispose();
        }
    }

    static {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        setOuterScreen(OUTER_SCREEN_RATIO);
        setInnerScreen(INNER_SCREEN_RATIO);
        setDebugState(DEBUG_STATE);
    }

}
