package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private GameLogic gameLogic;

    @Override
    public void create() {
        Logger.setLogLevel(3);
        batch = new SpriteBatch();
        GameProcessor.setBatch(batch);
        gameLogic = new GameLogic();
        gameLogic.start();
    }

    @Override
    public void render() {
        update();
        batch.begin();
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        GameProcessor.render();
        batch.end();
    }

    public void update() {
        GameProcessor.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        GameProcessor.dispose();
    }
}
