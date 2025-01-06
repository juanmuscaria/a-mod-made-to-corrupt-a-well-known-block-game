package com.juanmuscaria.nuke.launch;

import com.juanmuscaria.nuke.ChaosEngine;
import com.juanmuscaria.nuke.logging.LoggerAdapter;

import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;

public interface Platform {
    static Platform current() {
        Platform platform = REF.CURRENT.get();
        if (platform == null) {
            throw new IllegalStateException("Too early!");
        }
        return platform;
    }

    default boolean register() {
        return REF.CURRENT.compareAndSet(null, this);
    }

    LoggerAdapter logger();
    Path gameDir();
    ChaosEngine engine();
}

class REF {
    static AtomicReference<Platform> CURRENT = new AtomicReference<>();
}
