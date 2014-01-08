package com.flipper83.protohipster.globalutils.module;

import com.flipper83.protohipster.globalutils.interfaces.Logger;

import javax.inject.Inject;


public class LoggerProvider {

    @Inject
    static Logger logger;

    public LoggerProvider() {

    }

    public static Logger getLogger() {
        return logger;
    }
}
