package com.juanmuscaria.nuke.logging;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class JavaLogger implements LoggerAdapter {
    private final Logger logger;

    public JavaLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(Level level, String message, Object... format) {
        if (logger.isLoggable(level)) {
            LogRecord lr = new LogRecord(level, message);
            if (format.length > 0 && format[format.length - 1] instanceof Throwable) {
                lr.setThrown((Throwable) format[format.length - 1]);
                format = Arrays.copyOf(format, format.length - 1);
            }
            lr.setParameters(format);
            lr.setLoggerName(logger.getName());

            logger.log(lr);
        }
    }
}
