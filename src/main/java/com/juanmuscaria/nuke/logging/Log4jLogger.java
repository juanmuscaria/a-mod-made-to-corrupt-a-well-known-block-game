package com.juanmuscaria.nuke.logging;

import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.logging.Level;

public class Log4jLogger implements LoggerDelegate {
    private final Logger logger;

    public Log4jLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(Level level, String message, Object... format) {
        org.apache.logging.log4j.Level log4jLevel = translate(level);
        if (logger.isEnabled(log4jLevel)) {

            Throwable throwable = null;
            if (format.length > 0 && format[format.length - 1] instanceof Throwable) {
                throwable = (Throwable) format[format.length - 1];
                format = Arrays.copyOf(format, format.length - 1);
            }
            logger.log(log4jLevel, javaLoggingFormat(message, format));
            if (throwable != null) {
                logger.log(log4jLevel, "Exception: ", throwable);
            }
        }
    }

    private org.apache.logging.log4j.Level translate(Level level) {
        if (level.intValue() >= Level.OFF.intValue()) {
            return org.apache.logging.log4j.Level.OFF;
        } else if (level.intValue() >= Level.SEVERE.intValue()) {
            return org.apache.logging.log4j.Level.ERROR;
        } else if (level.intValue() >= Level.WARNING.intValue()) {
            return org.apache.logging.log4j.Level.WARN;
        } else if (level.intValue() >= Level.INFO.intValue()) {
            return org.apache.logging.log4j.Level.INFO;
        } else if (level.intValue() >= Level.FINE.intValue()) {
            return org.apache.logging.log4j.Level.DEBUG;
        } else if (level.intValue() >= Level.FINEST.intValue()) {
            return org.apache.logging.log4j.Level.TRACE;
        } else if (level.intValue() >= Level.ALL.intValue()) {
            return org.apache.logging.log4j.Level.ALL;
        }
        return org.apache.logging.log4j.Level.INFO;
    }
}
