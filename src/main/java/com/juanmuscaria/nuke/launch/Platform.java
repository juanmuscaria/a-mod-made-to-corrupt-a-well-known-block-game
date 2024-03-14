package com.juanmuscaria.nuke.launch;

import com.juanmuscaria.nuke.ChaosEngine;
import com.juanmuscaria.nuke.logging.LoggerDelegate;

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

    default void register() {
        // TODO: so there may be a slim chance more than one entrypoint is used, should add a check for that
        REF.CURRENT.set(this);
    }

    LoggerDelegate logger();
    Path mcHome();
    ChaosEngine engine();
}

// Lol
class REF {
    static AtomicReference<Platform> CURRENT = new AtomicReference<>();
}
