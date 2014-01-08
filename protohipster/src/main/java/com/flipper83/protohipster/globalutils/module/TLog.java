package com.flipper83.protohipster.globalutils.module;

import java.util.Arrays;

import javax.inject.Inject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.flipper83.protohipster.globalutils.interfaces.Logger;


/**
 * Real Logger 
 **/
 class TLog implements Logger {

    public static final Boolean TLOG_ENABLED = true;


    private static class Singleton {
        static final TLog INSTANCE = new TLog();
    }

    public static Logger getLogger() {
        return Singleton.INSTANCE;
    }

    // API Level: supported values
    private static final String LEVEL_INFO = "0";
    private static final String LEVEL_ERROR = "1";
    private static final String LEVEL_DEBUG = "2";
    private static final String LEVEL_WARNING = "3";

    protected Config logConfig;

    public TLog(Config logConfig) {
        this.logConfig = logConfig;
    }

    @Inject
    public TLog() {
        this(new Config());
    }

    // Verbose
    public void v(String tag, String msg) {
        if (isVerboseEnabled()) {
            Log.v(tag + getThreadId(), msg);
        }

    }

    public void v(String tag, String msg, Throwable tr) {
        if (isVerboseEnabled()) {
            Log.v(tag + getThreadId(), msg + '\n' + Log.getStackTraceString(tr));
        }
    }

    // Debug
    /**
     * Adds a <b>debug</b> log.<br>
     * This level of logging should be used to further note what is happening on
     * the device that could be relevant to investigate and debug unexpected
     * behaviors.
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     */
    public void d(String tag, String msg) {
        d(tag, msg, false);
    }

    /**
     * Adds a <b>debug</b> log. As optional, it can send a report. <br>
     * This level of logging should be used to further note what is happening on
     * the device that could be relevant to investigate and debug unexpected
     * behaviors.
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     * @param report
     *            - true, if a report has to be sent; false, otherwise.
     */
    public void d(String tag, String msg, boolean report) {
        if (isDebugEnabled()) {
            Log.d(tag + getThreadId(), msg);
        }
        if (report) {
            sendReport(tag, msg, LEVEL_DEBUG, new Exception("Crash Report: " + msg));
        }

    }

    /**
     * Adds a <b>debug</b> log. <br>
     * This level of logging should be used to further note what is happening on
     * the device that could be relevant to investigate and debug unexpected
     * behaviors.
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     * @param tr
     *            - exception
     */
    public void d(String tag, String msg, Throwable tr) {
        d(tag, msg, tr, false);
    }

    /**
     * Adds a <b>debug</b> log. As optional, it can send a report.<br>
     * This level of logging should be used to further note what is happening on
     * the device that could be relevant to investigate and debug unexpected
     * behaviors.
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     * @param tr
     *            - exception
     * @param report
     *            - true, if a report has to be sent; false, otherwise.
     */
    public void d(String tag, String msg, Throwable tr, boolean report) {
        if (isDebugEnabled()) {
            Log.d(tag + getThreadId(), msg + '\n' + Log.getStackTraceString(tr));
        }
        if (report) {
            sendReport(tag, msg, LEVEL_DEBUG, tr);
        }
    }

    // Info
    /**
     * Adds an <b>informative</b> log. <br>
     * This level of logging should used be to note that something interesting
     * to most people happened
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     */
    public void i(String tag, String msg) {
        i(tag, msg, false);
    }

    /**
     * Adds an <b>informative</b> log. As optional, it can send a report.<br>
     * This level of logging should used be to note that something interesting
     * to most people happened
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     * @param report
     *            - true, if a report has to be sent; false, otherwise.
     */
    public void i(String tag, String msg, boolean report) {
        if (isInfoEnabled()) {
            Log.i(tag, msg);
        }
        if (report) {
            sendReport(tag, msg, LEVEL_INFO, new Exception("Crash Report: " + msg));
        }
    }

    /**
     * Adds an <b>informative</b> log.<br>
     * This level of logging should used be to note that something interesting
     * to most people happened
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     * @param tr
     *            - exception
     */
    public void i(String tag, String msg, Throwable tr) {
        i(tag, msg, tr, false);
    }

    /**
     * Adds an <b>informative</b> log. As optional, it can send a report.<br>
     * This level of logging should used be to note that something interesting
     * to most people happened
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     * @param tr
     *            - exception
     * @param report
     *            - true, if a report has to be sent; false, otherwise.
     */
    public void i(String tag, String msg, Throwable tr, boolean report) {
        if (isInfoEnabled()) {
            Log.i(tag, msg + '\n' + Log.getStackTraceString(tr));
        }
        if (report) {
            sendReport(tag, msg, LEVEL_INFO, tr);
        }
    }

    // error
    /**
     * Adds an <b>error</b> log. This method will send a report to the server.<br>
     * This level of logging should be used when something fatal has happened.
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     */
    public void e(String tag, String msg) {
        e(tag, msg, false);
    }

    /**
     * Adds an <b>error</b> log. As optional, it can send a report.<br>
     * This level of logging should be used when something fatal has happened.
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     * @param report
     *            - true, if a report has to be sent; false, otherwise.
     */
    public void e(String tag, String msg, boolean report) {
        if (isErrorEnabled()) {
            Log.e(tag, msg);
        }
        if (report) {
            sendReport(tag, msg, LEVEL_ERROR, new Exception("Crash Report: " + msg));
        }
    }

    /**
     * Adds an <b>error</b> log. This method will send a report to the server.<br>
     * This level of logging should be used when something fatal has happened.
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     * @param tr
     *            - exception
     */
    public void e(String tag, String msg, Throwable tr) {
        e(tag, msg, tr, true);
    }

    /**
     * Adds an <b>error</b> log. As optional, it can send a report.<br>
     * This level of logging should be used when something fatal has happened.
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     * @param tr
     *            - exception
     * @param report
     *            - true, if a report has to be sent; false, otherwise.
     */
    public void e(String tag, String msg, Throwable tr, boolean report) {
        if (isErrorEnabled()) {
            Log.e(tag, msg + '\n' + Log.getStackTraceString(tr));
        }
        if (report) {
            sendReport(tag, msg, LEVEL_ERROR, tr);
        }
    }

    // warning
    /**
     * Adds a <b>warning</b> log. <br>
     * This level of logging should used when something serious and unexpected
     * happened
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     */
    public void w(String tag, String msg) {
        w(tag, msg, false);
    }

    /**
     * Adds a <b>warning</b> log. As optional, it can send a report.<br>
     * This level of logging should used when something serious and unexpected
     * happened
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     * @param report
     *            - true, if a report has to be sent; false, otherwise.
     */
    public void w(String tag, String msg, boolean report) {
        if (isInfoEnabled()) {
            Log.w(tag, msg);
        }
        if (report) {
            sendReport(tag, msg, LEVEL_WARNING, new Exception("Crash Report: " + msg));
        }

    }

    // Force report
    /**
     * This level of logging should be used when a weird behavior happens and we
     * want the user to send us a comment to help us to figure out why that
     * happened.
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     */
    public void r(final String tag, final String msg) {
        r(tag, msg, new Exception("Crash Report: " + msg));

    }

    /**
     * This level of logging should be used when a weird behavior happens and we
     * want the user to send us a comment to help us to figure out why that
     * happened.
     *
     * @param tag
     *            - LOG tag
     * @param msg
     *            - message
     * @param tr
     *            - exception
     */
    public void r(final String tag, final String msg, final Throwable tr) {
        Log.e(tag, msg + '\n' + Log.getStackTraceString(tr));

    }

    /**
     * Sends a crash report.
     *
     * @param tag
     * @param msg
     *            - message
     * @param level
     *            - type of information being stored. Values supported are: <li>
     *            0 = info <li>1 = error <li>2 = debug <li>3 = warning
     * @param tr
     */
    private void sendReport(String tag, String msg, String level, Throwable tr) {

    }

    // Performance recording
    public void p(String tag, long time) {
        // Performance recording
        if (isDebugEnabled())
            Log.i(tag + getThreadId(), "Performance - Total time: " + time + "ms");
    }

    public void p(String tag, String msg, long time) {
        // Performance recording
        if (isDebugEnabled())
            Log.i(tag + getThreadId(), "Performance - " + msg + " - Total time: " + time + "ms");
    }

    public void pt(String tag, String msg, long time) {
        // Performance recording
        if (isDebugEnabled())
            Log.i(tag + getThreadId(), "Performance - " + msg + " - Total time: " + (System.currentTimeMillis() - time)
                    + "ms");
    }

    public void t(Context context, String tag, String msg) {
        if (isVerboseEnabled())
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public void t(Context context, String msg) {
        if (isVerboseEnabled())
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public void tr(String tag, String msg) {
        if (isDebugEnabled())
            Log.i(tag, "Trace: " + msg + " : " + getCalledFrom());
    }

    public String getCalledFrom() {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
        return caller.getClassName() + "." + caller.getMethodName() + ":" + caller.getLineNumber();
    }

    public boolean isDebugEnabled() {
        return logConfig.minimumLogLevel <= Log.DEBUG;
    }

    public boolean isVerboseEnabled() {
        return logConfig.minimumLogLevel <= Log.VERBOSE;
    }

    public boolean isInfoEnabled() {
        return logConfig.minimumLogLevel <= Log.INFO;
    }

    public boolean isErrorEnabled() {
        return logConfig.minimumLogLevel <= Log.ERROR;
    }

    public Config getConfig() {
        return logConfig;
    }

    public String getCallStack() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        return Arrays.toString(stackTraceElements);
    }

    // Config class
    public static class Config {
        protected int minimumLogLevel = TLOG_ENABLED ? Log.VERBOSE : Log.ERROR;

        protected Config() {
        }

        public int getLoggingLevel() {
            return minimumLogLevel;
        }

        public void setLoggingLevel(int level) {
            minimumLogLevel = level;
        }
    }

    private String getThreadId() {
        return " - " + String.valueOf(Thread.currentThread().getId());
    }
}
