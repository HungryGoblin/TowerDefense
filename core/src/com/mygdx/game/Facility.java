package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;


abstract class Facility implements AutoCloseable, Pool.Poolable {

    public static final float DEFAULT_X = -500;
    public static final float DEFAULT_Y = -500;
    public static final float DEFAULT_ANGLE = 0;
    public static final float DEFAULT_HIT_BOX_RATIO = 0.4f;
    public static final float DEFAULT_SCALE_RATIO = 0.3f;
    public static final String DEFAULT_IMAGE = "_blank.png";

    private float x = DEFAULT_X;
    private float y = DEFAULT_Y;
    private float originX = 0;
    private float originY = 0;
    private float halfWidth = 0;
    private float halfHeight = 0;
    private float actualWidth = 0;
    private float actualHeight = 0;
    private float halfActualWidth = 0;
    private float halfActualHeight = 0;
    private float angle = 0;
    private float scale = DEFAULT_SCALE_RATIO;
    private float hitBoxRatio = DEFAULT_HIT_BOX_RATIO;
    private float hitBoxWidth = 0;
    private float halfHitBoxWidth = 0;
    private Texture texture;
    private Facility parent = null;

    public float getWidth() {
        return texture.getWidth();
    }

    public float getHeight() {
        return texture.getHeight();
    }

    public boolean isVisible() {
        return GameProcessor.isOnOuterScreen(this);
    }

    public void setParent(Facility parent) {
        this.parent = parent;
    }

    public static Facility getParent(Facility srcFacility) {
        return srcFacility.getParent();
    }

    public Facility getParent() {
        return parent;
    }

    public Facility getProtoParent() {
        Facility protoParent = getParent();
        while (true) {
            Facility tmpParent = getParent(protoParent);
            if (tmpParent == null) break;
            protoParent = tmpParent;
        }
        return protoParent;
    }

    public void setHitBoxRatio(float hitBoxRatio) {
        this.hitBoxRatio = hitBoxRatio;
        setDimensions();
    }

    private void setDimensions() {
        halfWidth = getWidth() / 2;
        halfHeight = getHeight() / 2;
        originX = getWidth() / 2;
        originY = getHeight() / 2;
        actualWidth = getWidth() * scale;
        actualHeight = getHeight() * scale;
        halfActualWidth = actualWidth / 2;
        halfActualHeight = actualHeight / 2;
        hitBoxWidth = actualWidth * hitBoxRatio;
        halfHitBoxWidth = hitBoxWidth / 2;
    }

    public float getActualWidth() {
        return actualWidth;
    }

    public float getActualHeight() {
        return actualHeight;
    }

    public float getHalfActualWidth() {
        return halfActualWidth;
    }

    public float getHalfActualHeight() {
        return halfActualHeight;
    }

    public float getCenterX() {
        return x + halfWidth;
    }

    public float getCenterX(float x) {
        return x + halfWidth;
    }

    public float getCenterY() {
        return y + halfHeight;
    }

    public float getCenterY(float y) {
        return y + halfHeight;
    }

    public float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setScale(float scale) {
        this.scale = scale;
        setDimensions();
    }

    abstract void update();

    public void render() {
        if (isVisible()) {
            GameProcessor.getBatch().draw(texture, x, y, originX, originY, getWidth(), getHeight(),
                    scale, scale, angle, 0, 0, (int) getWidth(), (int) getHeight(), false, false);
        }
    }

    public void setTexture(String fileName) {
        if (!Tools.fileExists(fileName)) {
            Logger.putError(String.format("File '%s' does not exist", fileName));
            fileName = DEFAULT_IMAGE;
        }
        texture = new Texture(fileName);
        setDimensions();
    }

    public Texture getTexture() {
        return texture;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean setPosition(float x, float y) {
        return setPosition(x, y, null);
    }

    public boolean setPosition(float x, float y, Rectangle region) {
        float centerX = getCenterX(x);
        float centerY = getCenterY(y);
        if (region == null ?
                GameProcessor.isOnInnerScreen(centerX, centerY) :
                region.contains(centerX, centerY))
        {
            this.x = x;
            this.y = y;
            return true;
        }
        return false;
    }

    public boolean setCenteredPosition(float x, float y, Rectangle region) {
        float actualX = x - halfWidth;
        float actualY = y - halfHeight;
        if (region == null ?
                GameProcessor.isOnInnerScreen(x, y) :
                region.contains(x, y))
        {
            this.x = actualX;
            this.y = actualY;
            return true;
        }
        return false;
    }

    public boolean setAsParentPosition() {
        if (parent != null) {
            return setCenteredPosition(parent.getCenterX(), parent.getCenterY(), null);
        } else
            return false;
    }

    public Rectangle getHitBox() {
        return new Rectangle(
                getCenterX() - halfHitBoxWidth,
                getCenterY() - halfHitBoxWidth,
                hitBoxWidth,
                hitBoxWidth);
    }

    public void dispose() {
        texture.dispose();
    }

    @Override
    public void reset() {
        setX(DEFAULT_X);
        setY(DEFAULT_Y);
    }

    @Override
    public void close() {
        dispose();
    }

    public String getTextureImage() {
        return ((FileTextureData) texture.getTextureData()).getFileHandle().path();
    }

    public Facility(String imagePath, float x, float y, float angle) {
        setTexture(imagePath);
        setCenteredPosition(x, y, null);
        this.angle = angle;
    }

}
