package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Logger {

    private static final String ERROR_TEG = "ERROR";
    private static final String MESSAGE_TEG = "MESSAGE";
    private static final String DEBUG_TEG = "DEBUG";

    public static void setLogLevel (int logLevel) {
        Gdx.app.setLogLevel(logLevel);
    }

    public static int getLogLevel () {
        return Gdx.app.getLogLevel();
    }

    public static void putLog(String teg, String message) {
        Gdx.app.log(teg, message);
    }

    public static void putMessage(String message) {
        Gdx.app.log(MESSAGE_TEG, message);
    }

    public static void putError (String message) {
        Gdx.app.error(ERROR_TEG, message);
    }

    public static void putError(String message, Exception e) {
        Gdx.app.error(ERROR_TEG, message, e);
    }

    public static void putDebug(String message) {
        Gdx.app.debug(DEBUG_TEG, message);
    }

    public static void putDebug(String teg, String message) {
        Gdx.app.debug(teg, message);
    }





}
