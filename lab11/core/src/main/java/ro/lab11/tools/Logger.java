package ro.lab11.tools;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.lab11.tools.OS;
import ro.lab11.tools.Meta;

@Component
public class Logger {
    public final org.slf4j.Logger logger;

//    public Logger(org.slf4j.Logger logger) {
//        this.logger = logger;
//    }

    public Logger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public void info(String message) {
        logger.info(formatString(message));
    }

    public void debug(String message) {
        logger.debug(formatString(message));
    }

    public void warn(String message) {
        logger.warn(formatString(message));
    }

    public void trace(String message) {
        logger.trace(formatString(message));
    }

    public static String formatString(String message) {
        return "[%s][%s] %s".formatted(OS.getDateTime(), getCallerName(), message);
    }

    public static String getCallerName(){
        return Meta.getMethodName(4);
    }
}
