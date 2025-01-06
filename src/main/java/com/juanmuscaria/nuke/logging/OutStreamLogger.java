package com.juanmuscaria.nuke.logging;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.logging.Level;

public class OutStreamLogger implements LoggerAdapter {
    private final PrintStream logger;
    private final String loggerName;
    private final Level defaultLevel;

    public OutStreamLogger(PrintStream logger, String loggerName, Level defaultLevel) {
        this.logger = logger;
        this.loggerName = loggerName;
        this.defaultLevel = defaultLevel;
    }

    @Override
    public void log(Level level, String message, Object... format) {
        if (level.intValue() >= defaultLevel.intValue() && defaultLevel.intValue() != Level.OFF.intValue()) {
            Throwable t = null;
            if (format.length > 0 && format[format.length - 1] instanceof Throwable) {
                t = (Throwable) format[format.length - 1];
                format = Arrays.copyOf(format, format.length - 1);
            }
            logger.println("[" + loggerName + "] " + javaLoggingFormat(message, format));
            if (t != null) {
                t.printStackTrace(logger);
            }
        }
    }
}

