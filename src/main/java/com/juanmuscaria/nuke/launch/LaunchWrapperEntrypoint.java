package com.juanmuscaria.nuke.launch;

import com.juanmuscaria.nuke.ChaosEngine;
import com.juanmuscaria.nuke.logging.Log4jLogger;
import com.juanmuscaria.nuke.logging.LoggerDelegate;
import com.juanmuscaria.nuke.logging.OutStreamLogger;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;

public class LaunchWrapperEntrypoint implements ITweaker, Platform {
    private final LoggerDelegate logger = makeTheLogger();
    private Path mcHome;
    private ChaosEngine engine;

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        register();
        if (gameDir == null) {
            gameDir = new File(".");
        }
        mcHome = gameDir.toPath().normalize();
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        engine = new ChaosEngine(this);
        engine.primeTheExplosives();
        classLoader.registerTransformer("com.juanmuscaria.nuke.launch.LaunchWrapperTransformer");
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return new String[0];
    }

    @Override
    public LoggerDelegate logger() {
        return logger;
    }

    @Override
    public Path mcHome() {
        return mcHome;
    }

    @Override
    public ChaosEngine engine() {
        return engine;
    }

    private static LoggerDelegate makeTheLogger() {
        try {
            return new Log4jLogger(LogManager.getLogger("ChaosEngine"));
        } catch (Throwable ignored) {
            // Either a really old minecraft version or log4j is plain missing?
            return new OutStreamLogger(System.out, "ChaosEngine", Level.INFO);
        }
    }
}
