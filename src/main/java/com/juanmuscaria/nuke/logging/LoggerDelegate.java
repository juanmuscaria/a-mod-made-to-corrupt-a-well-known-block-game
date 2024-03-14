package com.juanmuscaria.nuke.logging;

import java.text.MessageFormat;
import java.util.logging.Level;

public interface LoggerDelegate {
    void log(Level level, String message, Object... format);

    default void error(String message, Object... format) {
        log(Level.SEVERE, message, format);
    }

    default void warn(String message, Object... format) {
        log(Level.WARNING, message, format);
    }

    default void info(String message, Object... format) {
        log(Level.INFO, message, format);
    }

    default void debug(String message, Object... format) {
        log(Level.FINE, message, format);
    }

    default String javaLoggingFormat(String message, Object... format) {
        if (format == null || format.length == 0) {
            return message;
        }
        try {
            return MessageFormat.format(message, format);
        } catch (Exception ex) {
            return message;
        }
    }
}
