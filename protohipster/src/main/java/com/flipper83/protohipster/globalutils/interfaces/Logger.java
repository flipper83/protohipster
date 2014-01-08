package com.flipper83.protohipster.globalutils.interfaces;

public interface Logger {

    void v(String tag, String msg);

    void v(String tag, String msg, Throwable tr);

    void d(String tag, String msg);

    void d(String tag, String msg, boolean report);

    void d(String tag, String msg, Throwable tr);

    void d(String tag, String msg, Throwable tr, boolean report);

    void i(String tag, String msg);

    void i(String tag, String msg, boolean report);

    void i(String tag, String msg, Throwable tr);

    void i(String tag, String msg, Throwable tr, boolean report);

    void e(String tag, String msg);

    void e(String tag, String msg, boolean report);

    void e(String tag, String msg, Throwable tr);

    void e(String tag, String msg, Throwable tr, boolean report);

    void w(String tag, String msg);

    void w(String tag, String msg, boolean report);

    void r(String tag, String msg);

    void r(String tag, String msg, Throwable tr);

    // Performance recording
    void p(String tag, long time);

    void p(String tag, String msg, long time);

    void pt(String tag, String msg, long time);

    void tr(String tag, String msg);

    String getCalledFrom();

    boolean isDebugEnabled();

    boolean isVerboseEnabled();

    boolean isInfoEnabled();

    boolean isErrorEnabled();

    String getCallStack();
}

