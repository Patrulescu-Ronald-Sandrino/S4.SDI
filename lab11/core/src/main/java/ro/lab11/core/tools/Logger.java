package ro.lab11.core.tools;

import org.slf4j.LoggerFactory;

public class Logger { // TODO: idea: create a private void trace(String message, int level) which is called by every other trace
    public final org.slf4j.Logger logger;

    public static final String START = "START";
    public static final String END = "END";

    public Logger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public void info(String message) {
        logger.info(prependWithCallInfo(message));
    }

    public void debug(String message) {
        logger.debug(prependWithCallInfo(message));
    }

    public void warn(String message) {
        logger.warn(prependWithCallInfo(message));
    }

    public void trace(String message) {
//        logger.trace(prependWithCallInfo(message));
        trace(prependWithCallInfo(message), 7);
    }

    public void traceStartArgs(Object... objects) {
        logger.trace(prependWithCallInfo("START args: %s".formatted(Collections.join(", ", objects))));
//        trace("START args: %s".formatted(Collections.join(", ", objects)), 8);
    }

    public void traceEndResult(String message) {
        logger.trace(prependWithCallInfo("END result: %s".formatted(message)));
    }

    // LEVEL 2

    private void trace(String message, int level) {
        logger.trace(prependWithCallInfo(message, level));
    }

    // LEVEL 3

    /** must be called from the 1st level **/
    public static String prependWithCallInfo(String message) {
        return prependWithCallInfo(message, 6);
    }

    // LEVEL 4

    public static String prependWithCallInfo(String message, int level) {
        return "[%s][%s()] %s ".formatted(OS.getDateTime(), getCallInfo(level), message);
    }

    // LEVEL 5

    public static String getCallInfo(int level) {
        return "[%s][%s()]".formatted(OS.getDateTime(), Meta.getMethodName(level));
    }
}
