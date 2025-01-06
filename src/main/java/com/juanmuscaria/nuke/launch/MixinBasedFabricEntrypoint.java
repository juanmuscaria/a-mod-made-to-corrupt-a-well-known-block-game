package com.juanmuscaria.nuke.launch;


import com.juanmuscaria.nuke.ChaosEngine;
import com.juanmuscaria.nuke.logging.Log4jLogger;
import com.juanmuscaria.nuke.logging.LoggerAdapter;
import com.juanmuscaria.nuke.logging.OutStreamLogger;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

// TODO: Figure out how to get access to all minecraft classes
// Perhaps we could hack into mixin and steal class nodes after they are mixed
// Then just make a small check like launch wrapper to see if they belong to minecraft
// It's not like we have to care about being compatible with other mods here anyways
public class MixinBasedFabricEntrypoint implements IMixinConfigPlugin, Platform {
    private final LoggerAdapter logger = makeTheLogger();
    private final Path mcHome = FabricLoader.getInstance().getGameDir();
    private ChaosEngine engine;

    @Override
    public void onLoad(String s) {
        this.engine = new ChaosEngine(this);
        logger.error("Fabric is not supported, at least not yet. If you are a nerd reach out to me on discord to figure out ways to make it work discord.juanmuscaria.com");
        ChaosEngine.$$__PANIC__$$();
    }

    @Override
    public String getRefMapperConfig() {
        return "";
    }

    @Override
    public boolean shouldApplyMixin(String s, String s1) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> set, Set<String> set1) {

    }

    @Override
    public List<String> getMixins() {
        //TODO: how am I supposed to create a mixin for every single minecraft class???
        return Collections.emptyList();
    }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {
        engine.corruptClass(classNode);
    }

    @Override
    public LoggerAdapter logger() {
        return logger;
    }

    @Override
    public Path gameDir() {
        return mcHome;
    }

    @Override
    public ChaosEngine engine() {
        return engine;
    }

    private static LoggerAdapter makeTheLogger() {
        try {
            return new Log4jLogger(LogManager.getLogger("ChaosEngine"));
        } catch (Throwable ignored) {
            // Either a really old minecraft version or log4j is plain missing?
            return new OutStreamLogger(System.out, "ChaosEngine", Level.INFO);
        }
    }
}
