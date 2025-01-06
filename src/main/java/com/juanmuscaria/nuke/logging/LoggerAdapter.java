package com.juanmuscaria.nuke.logging;

import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface LoggerAdapter {
    AtomicReference<LoggerProvider> PROVIDER = new AtomicReference<>(new LoggerProvider() {
        @Override
        public LoggerAdapter getLogger(String name) {
            return new JavaLogger(Logger.getLogger(name));
        }
    });

    static LoggerAdapter getLogger(String name) {
        return PROVIDER.get().getLogger(name);
    }

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

    interface LoggerProvider {
        LoggerAdapter getLogger(String name);
    }
}
